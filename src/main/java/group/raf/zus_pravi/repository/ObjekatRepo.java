package group.raf.zus_pravi.repository;

import group.raf.zus_pravi.model.base.Objekat;
import group.raf.zus_pravi.model.utility.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ObjekatRepo {

    public static ObjekatRepo instance = null;

    public static ObjekatRepo getInstance() {
        if(instance == null) {
            instance = new ObjekatRepo();
        }
        return instance;
    }

    public List<Objekat> listAllObjekti() {
        List<Objekat> objektiList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtils.connect();
            String sql = "SELECT * FROM Objekti";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Objekat objekat = new Objekat(
                        resultSet.getInt("id"),
                        resultSet.getInt("kvadratura"),
                        resultSet.getInt("cena")
                );
                objektiList.add(objekat);
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

        return objektiList;
    }

    public Objekat addObjekat(Objekat objekat) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;

        try {
            connection = JDBCUtils.connect();
            String sql = "INSERT INTO Objekti (id, kvadratura, cena) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, objekat.getIdObjekta());
            preparedStatement.setInt(2, objekat.getKvadratura());
            preparedStatement.setInt(3, objekat.getCena());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                objekat.setIdObjekta(resultSet.getInt(1));
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

        return objekat;
    }

    public Objekat getObjekatById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Objekat objekat = null;

        try {
            connection = JDBCUtils.connect();
            String sql = "SELECT * FROM Objekti WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                objekat = new Objekat(
                        resultSet.getInt("id"),
                        resultSet.getInt("kvadratura"),
                        resultSet.getInt("cena")
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

        return objekat;
    }

    public Objekat updateObjekat(Objekat objekat) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.connect();
            String sql = "UPDATE Objekti SET kvadratura = ?, cena = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, objekat.getKvadratura());
            preparedStatement.setInt(2, objekat.getCena());
            preparedStatement.setInt(3, objekat.getIdObjekta());
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

        return objekat;
    }

    public boolean deleteObjekat(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean deleted = false;

        try {
            connection = JDBCUtils.connect();
            String sql = "DELETE FROM Objekti WHERE id = ?";
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