package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/pp1134";
    private static final String user = "root";
    private static final String pass = "Bay7013782!";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, user, pass);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return connection;
    }
}