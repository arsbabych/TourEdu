package ua.com.arsen.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Thor on 12.07.2015.
 */
public abstract class DataAccessObject {
    protected static Connection connection = getConnection();

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/tour?user=root&password=123456789");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
