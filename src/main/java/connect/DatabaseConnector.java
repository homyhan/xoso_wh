package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    public static Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/wh";
        String user = "root";
        String password = "123456";
        return DriverManager.getConnection(url, user, password);
    }
}
