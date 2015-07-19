package ua.com.arsen.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * Created by Thor on 12.07.2015.
 */
@Service
public abstract class DataAccessObject {

    @Autowired
    protected static DataSource dataSource;

    protected static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public static void setDataSource(DataSource dataSource) {
        DataAccessObject.dataSource = dataSource;
        DataAccessObject.connection = getConnection();
    }

    protected void closeConnention() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
