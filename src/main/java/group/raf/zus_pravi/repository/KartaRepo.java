package group.raf.zus_pravi.repository;

import group.raf.zus_pravi.model.base.Karta;
import group.raf.zus_pravi.model.utility.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KartaRepo {

    public static KartaRepo instance = null;

    public static KartaRepo getInstance() {
        if(instance == null) {
            instance = new KartaRepo();
        }
        return instance;
    }

    public List<Karta> listAllKarte() {
        List<Karta> karte = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;

        try {
            connection = JDBCUtils.connect();
            String sql = "SELECT * FROM Karte";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Karta karta = new Karta(
                        resultSet.getInt("id"),
                        resultSet.getInt("cena"),
                        resultSet.getString("odrediste")
                );
                karte.add(karta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Optional.ofNullable(preparedStatement).ifPresent(p -> {
                try {
                    p.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            Optional.ofNullable(connection).ifPresent(c -> {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }

        return karte;
    }

    public Karta addKarta(Karta karta) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;

        try {
            connection = JDBCUtils.connect();
            String sql = "INSERT INTO Karte (id, cena, odrediste) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, karta.getId());
            preparedStatement.setInt(2, karta.getCena());
            preparedStatement.setString(3, karta.getOdrediste());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                karta.setId(resultSet.getInt(1));
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

        return karta;
    }

    public Karta getKartaById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Karta karta = null;

        try {
            connection = JDBCUtils.connect();
            String sql = "SELECT * FROM Karte WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                karta = new Karta(
                        resultSet.getInt("id"),
                        resultSet.getInt("cena"),
                        resultSet.getString("odrediste")
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

        return karta;
    }

    public Karta updateKarta(Karta karta) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.connect();
            String sql = "UPDATE Karte SET cena = ?, odrediste = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, karta.getCena());
            preparedStatement.setString(2, karta.getOdrediste());
            preparedStatement.setInt(3, karta.getId());
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

        return karta;
    }

    public boolean deleteKarta(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean deleted = false;

        try {
            connection = JDBCUtils.connect();
            String sql = "DELETE FROM Karte WHERE id = ?";
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
