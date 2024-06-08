package group.raf.zus_pravi.repository;

import group.raf.zus_pravi.model.base.Planeta;
import group.raf.zus_pravi.model.utility.JDBCUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.util.Optional;

public class PlanetaRepo {

    public static PlanetaRepo instance = null;

    public static PlanetaRepo getInstance() {
        if(instance == null) {
            instance = new PlanetaRepo();
        }
        return instance;
    }

    public List<Planeta> listAllPlanete() {
        List<Planeta> planete = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtils.connect();
            String sql = "SELECT * FROM Planete";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Planeta planeta = new Planeta(
                        resultSet.getInt("id"),
                        resultSet.getString("naziv"),
                        resultSet.getInt("sredanja_udaljenost"),
                        resultSet.getInt("najniza_temperatura"),
                        resultSet.getInt("najvisa_temperatura"),
                        resultSet.getInt("razlika_temperatura"),
                        resultSet.getInt("procenat_kiseonika"),
                        resultSet.getInt("procenat_drugog_gasa"),
                        resultSet.getString("naziv_drugog_gasa"),
                        resultSet.getInt("zbir_gasova"),
                        resultSet.getInt("max_visina_gravitacije"),
                        resultSet.getInt("brzina_orbitiranja"),
                        resultSet.getInt("broj_umrlih")
                );
                planete.add(planeta);
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

        return planete;
    }

    public Planeta addPlaneta(Planeta planeta) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;

        try {
            connection = JDBCUtils.connect();
            String sql = "INSERT INTO Planete (id, naziv, sredanja_udaljenost, najniza_temperatura, najvisa_temperatura, razlika_temperatura, procenat_kiseonika, procenat_drugog_gasa, naziv_drugog_gasa, zbir_gasova, max_visina_gravitacije, brzina_orbitiranja, broj_umrlih) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, planeta.getId());
            preparedStatement.setString(2, planeta.getNaziv());
            preparedStatement.setInt(3, planeta.getSredanja_udaljenost());
            preparedStatement.setInt(4, planeta.getNajniza_temperatura());
            preparedStatement.setInt(5, planeta.getNajvisa_temperatura());
            preparedStatement.setInt(6, planeta.getRazlika_temperatura());
            preparedStatement.setInt(7, planeta.getProcenat_kiseonika());
            preparedStatement.setInt(8, planeta.getProcenat_drugog_gasa());
            preparedStatement.setString(9, planeta.getNaziv_drugog_gasa());
            preparedStatement.setInt(10, planeta.getZbir_gasova());
            preparedStatement.setInt(11, planeta.getMax_visina_gravitacije());
            preparedStatement.setInt(12, planeta.getBrzina_orbitiranja());
            preparedStatement.setInt(13, planeta.getBroj_umrlih());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                planeta.setId(resultSet.getInt(1));
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

        return planeta;
    }

    public Planeta getPlanetaById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Planeta planeta = null;

        try {
            connection = JDBCUtils.connect();
            String sql = "SELECT * FROM Planete WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                planeta = new Planeta(
                        resultSet.getInt("id"),
                        resultSet.getString("naziv"),
                        resultSet.getInt("sredanja_udaljenost"),
                        resultSet.getInt("najniza_temperatura"),
                        resultSet.getInt("najvisa_temperatura"),
                        resultSet.getInt("razlika_temperatura"),
                        resultSet.getInt("procenat_kiseonika"),
                        resultSet.getInt("procenat_drugog_gasa"),
                        resultSet.getString("naziv_drugog_gasa"),
                        resultSet.getInt("zbir_gasova"),
                        resultSet.getInt("max_visina_gravitacije"),
                        resultSet.getInt("brzina_orbitiranja"),
                        resultSet.getInt("broj_umrlih")
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

        return planeta;
    }

    public Planeta updatePlaneta(Planeta planeta) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.connect();
            String sql = "UPDATE Planete SET naziv = ?, sredanja_udaljenost = ?, najniza_temperatura = ?, najvisa_temperatura = ?, razlika_temperatura = ?, procenat_kiseonika = ?, procenat_drugog_gasa = ?, naziv_drugog_gasa = ?, zbir_gasova = ?, max_visina_gravitacije = ?, brzina_orbitiranja = ?, broj_umrlih = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, planeta.getNaziv());
            preparedStatement.setInt(2, planeta.getSredanja_udaljenost());
            preparedStatement.setInt(3, planeta.getNajniza_temperatura());
            preparedStatement.setInt(4, planeta.getNajvisa_temperatura());
            preparedStatement.setInt(5, planeta.getRazlika_temperatura());
            preparedStatement.setInt(6, planeta.getProcenat_kiseonika());
            preparedStatement.setInt(7, planeta.getProcenat_drugog_gasa());
            preparedStatement.setString(8, planeta.getNaziv_drugog_gasa());
            preparedStatement.setInt(9, planeta.getZbir_gasova());
            preparedStatement.setInt(10, planeta.getMax_visina_gravitacije());
            preparedStatement.setInt(11, planeta.getBrzina_orbitiranja());
            preparedStatement.setInt(12, planeta.getBroj_umrlih());
            preparedStatement.setInt(13, planeta.getId());
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

        return planeta;
    }

    public boolean deletePlaneta(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean deleted = false;

        try {
            connection = JDBCUtils.connect();
            String sql = "DELETE FROM Planete WHERE id = ?";
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
