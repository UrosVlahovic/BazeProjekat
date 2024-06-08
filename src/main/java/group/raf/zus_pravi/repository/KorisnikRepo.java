package group.raf.zus_pravi.repository;

import group.raf.zus_pravi.model.base.Korisnik;
import group.raf.zus_pravi.model.utility.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KorisnikRepo {

    public static KorisnikRepo instance = null;

    public static KorisnikRepo getInstance() {
        if(instance == null) {
            instance = new KorisnikRepo();
        }
        return instance;
    }

    public List<Korisnik> listAllKorisnici() {
        List<Korisnik> korisnici = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtils.connect();
            System.out.println(connection.getMetaData().getURL());
            String sql = "SELECT * FROM Korisnici";
            preparedStatement = connection.prepareStatement(sql);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Korisnik korisnik = new Korisnik(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("ime"),
                        resultSet.getString("prezime"),
                        resultSet.getInt("godine")
                );
                korisnici.add(korisnik);
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

        return korisnici;
    }

    public Korisnik addKorisnik(Korisnik korisnik) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;

        try {
            connection = JDBCUtils.connect();
            String sql = "INSERT INTO Korisnici (id, ime, prezime, godine, username, password) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, korisnik.getId());
            preparedStatement.setString(2, korisnik.getIme());
            preparedStatement.setString(3, korisnik.getPrezime());
            preparedStatement.setInt(4, korisnik.getGodine());
            preparedStatement.setString(5, korisnik.getUsername());
            preparedStatement.setString(6, korisnik.getPassword());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                korisnik.setId(resultSet.getInt(1));
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

        return korisnik;
    }

    public Korisnik getKorisnikById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Korisnik korisnik = null;

        try {
            connection = JDBCUtils.connect();
            String sql = "SELECT * FROM Korisnici WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                korisnik = new Korisnik(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("ime"),
                        resultSet.getString("prezime"),
                        resultSet.getInt("godine")
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

        return korisnik;
    }

    public Korisnik getKorisnikByNameAndPass(String name, String pass) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Korisnik korisnik = null;

        try {
            connection = JDBCUtils.connect();
            String sql = "SELECT * FROM Korisnici WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                korisnik = new Korisnik(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("ime"),
                        resultSet.getString("prezime"),
                        resultSet.getInt("godine")
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

        return korisnik;
    }

    public Korisnik updateKorisnik(Korisnik korisnik) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.connect();
            String sql = "UPDATE Korisnici SET ime = ?, prezime = ?, godine = ?, username = ?, password = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, korisnik.getIme());
            preparedStatement.setString(2, korisnik.getPrezime());
            preparedStatement.setInt(3, korisnik.getGodine());
            preparedStatement.setString(4, korisnik.getUsername());
            preparedStatement.setString(5, korisnik.getPassword());
            preparedStatement.setInt(6, korisnik.getId());
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

        return korisnik;
    }

    public boolean deleteKorisnik(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean deleted = false;

        try {
            connection = JDBCUtils.connect();
            String sql = "DELETE FROM Korisnici WHERE id = ?";
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
