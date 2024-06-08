package group.raf.zus_pravi.repository;

import group.raf.zus_pravi.model.base.Kupovina;
import group.raf.zus_pravi.model.utility.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KupovinaRepo {

    public static KupovinaRepo instance = null;

    public static KupovinaRepo getInstance() {
        if(instance == null) {
            instance = new KupovinaRepo();
        }
        return instance;
    }

    public List<Kupovina> listAllKupovina() {
        List<Kupovina> kupovinaList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtils.connect();
            String sql = "SELECT * FROM Kupovina";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Kupovina kupovina = new Kupovina(
                        resultSet.getInt("id"),
                        resultSet.getDate("datum"),
                        resultSet.getInt("id_korisnika"),
                        resultSet.getInt("id_objekta")
                );
                kupovinaList.add(kupovina);
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

        return kupovinaList;
    }

    public Kupovina addKupovina(Kupovina kupovina) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.connect();
            String sql = "INSERT INTO Kupovina (id, datum, id_korisnika, id_objekta) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, kupovina.getId());
            preparedStatement.setDate(2, kupovina.getDatum());
            preparedStatement.setInt(3, kupovina.getIdKorisnika());
            preparedStatement.setInt(4, kupovina.getIdObjekta());
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

        return kupovina;
    }

    public Kupovina getKupovinaById(int idKorisnika, int idObjekta) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Kupovina kupovina = null;

        try {
            connection = JDBCUtils.connect();;
            String sql = "SELECT * FROM Kupovina WHERE id_korisnika = ? AND id_objekta = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idKorisnika);
            preparedStatement.setInt(2, idObjekta);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                kupovina = new Kupovina(
                        resultSet.getInt("id"),
                        resultSet.getDate("datum"),
                        resultSet.getInt("id_korisnika"),
                        resultSet.getInt("id_objekta")
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

        return kupovina;
    }

    public Kupovina updateKupovina(Kupovina kupovina) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.connect();;
            String sql = "UPDATE Kupovina SET datum = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(2, kupovina.getId());
            preparedStatement.setDate(1, kupovina.getDatum());
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

        return kupovina;
    }

    public boolean deleteKupovina(int idKorisnika, int idObjekta) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean deleted = false;

        try {
            connection = JDBCUtils.connect();;
            String sql = "DELETE FROM Kupovina WHERE id_korisnika = ? AND id_objekta = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idKorisnika);
            preparedStatement.setInt(2, idObjekta);
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
