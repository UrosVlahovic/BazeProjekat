package group.raf.zus_pravi.model.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {

    public static Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/my_database";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }
}
