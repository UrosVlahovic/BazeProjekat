package group.raf.zus_pravi.repository;

import group.raf.zus_pravi.model.base.Polazak;
import group.raf.zus_pravi.model.utility.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PolazakRepo {

    public static PolazakRepo instance = null;

    public static PolazakRepo getInstance() {
        if(instance == null) {
            instance = new PolazakRepo();
        }
        return instance;
    }

    public List<Polazak> listAllPolazak() {
        List<Polazak> polazakList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtils.connect();
            String sql = "SELECT * FROM Polazak";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Polazak polazak = new Polazak(
                        resultSet.getInt("id"),
                        resultSet.getString("vozilo"),
                        resultSet.getDate("datum"),
                        resultSet.getString("sediste"),
                        resultSet.getInt("id_korisnika"),
                        resultSet.getInt("id_karte")
                );
                polazakList.add(polazak);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Optional.ofNullable(resultSet).ifPresent(r -> {
                    try { r.close(); } catch (SQLException e) { e.printStackTrace(); }
                });
                Optional.ofNullable(preparedStatement).ifPresent(p -> {
                    try { p.close(); } catch (SQLException e) { e.printStackTrace(); }
                });
                Optional.ofNullable(connection).ifPresent(c -> {
                    try { c.close(); } catch (SQLException e) { e.printStackTrace(); }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return polazakList;
    }

    public Polazak addPolazak(Polazak polazak) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;

        try {
            connection = JDBCUtils.connect();;
            String sql = "INSERT INTO Polazak (id, vozilo, datum, sediste, id_korisnika, id_karte) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, polazak.getId());
            preparedStatement.setString(2, polazak.getVozilo());
            preparedStatement.setDate(3, polazak.getDatum());
            preparedStatement.setString(4, polazak.getSediste());
            preparedStatement.setInt(5, polazak.getIdKorisnika());
            preparedStatement.setInt(6, polazak.getIdKarte());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                polazak.setId(resultSet.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Optional.ofNullable(preparedStatement).ifPresent(p -> {
                    try { p.close(); } catch (SQLException e) { e.printStackTrace(); }
                });
                Optional.ofNullable(connection).ifPresent(c -> {
                    try { c.close(); } catch (SQLException e) { e.printStackTrace(); }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return polazak;
    }

    public Polazak getPolazakById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Polazak polazak = null;

        try {
            connection = JDBCUtils.connect();;
            String sql = "SELECT * FROM Polazak WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                polazak = new Polazak(
                        resultSet.getInt("id"),
                        resultSet.getString("vozilo"),
                        resultSet.getDate("datum"),
                        resultSet.getString("sediste"),
                        resultSet.getInt("id_korisnika"),
                        resultSet.getInt("id_karte")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Optional.ofNullable(resultSet).ifPresent(r -> {
                    try { r.close(); } catch (SQLException e) { e.printStackTrace(); }
                });
                Optional.ofNullable(preparedStatement).ifPresent(p -> {
                    try { p.close(); } catch (SQLException e) { e.printStackTrace(); }
                });
                Optional.ofNullable(connection).ifPresent(c -> {
                    try { c.close(); } catch (SQLException e) { e.printStackTrace(); }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return polazak;
    }

    public Polazak updatePolazak(Polazak polazak) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.connect();;
            String sql = "UPDATE Polazak SET vozilo = ?, datum = ?, sediste = ?, id_korisnika = ?, id_karte = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, polazak.getVozilo());
            preparedStatement.setDate(2, polazak.getDatum());
            preparedStatement.setString(3, polazak.getSediste());
            preparedStatement.setInt(4, polazak.getIdKorisnika());
            preparedStatement.setInt(5, polazak.getIdKarte());
            preparedStatement.setInt(6, polazak.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Optional.ofNullable(preparedStatement).ifPresent(p -> {
                    try { p.close(); } catch (SQLException e) { e.printStackTrace(); }
                });
                Optional.ofNullable(connection).ifPresent(c -> {
                    try { c.close(); } catch (SQLException e) { e.printStackTrace(); }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return polazak;
    }

    public boolean deletePolazak(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean deleted = false;

        try {
            connection = JDBCUtils.connect();;
            String sql = "DELETE FROM Polazak WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            deleted = rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Optional.ofNullable(preparedStatement).ifPresent(p -> {
                    try { p.close(); } catch (SQLException e) { e.printStackTrace(); }
                });
                Optional.ofNullable(connection).ifPresent(c -> {
                    try { c.close(); } catch (SQLException e) { e.printStackTrace(); }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return deleted;
    }
}
