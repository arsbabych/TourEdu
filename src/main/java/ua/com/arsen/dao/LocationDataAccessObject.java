package ua.com.arsen.dao;

import org.springframework.stereotype.Component;
import ua.com.arsen.entities.Location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Thor on 12.07.2015.
 */
@Component
public class LocationDataAccessObject extends DataAccessObject {

    public List<Location> getLocations() throws Exception {
        List<Location> locations = new LinkedList<Location>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM location");

            while (resultSet.next()) {
                Location location = new Location();

                location.setId(resultSet.getInt("id"));
                location.setCountry(resultSet.getString("country"));
                location.setCity(resultSet.getString("city"));
                location.setAddress(resultSet.getString("address"));
                location.setHotelName(resultSet.getString("hotel_name"));
                location.setHotelStatus(resultSet.getInt("hotel_status"));

                locations.add(location);
            }

        } finally {
            connection.close();
        }

        return locations;
    }

    public void insertLocation(Location location) throws Exception {

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO location (country, city, address, hotel_name, hotel_status) " +
                            "VALUES('%s', '%s', '%s', '%s', '%s')",
                    location.getCountry(), location.getCity(), location.getAddress(), location.getHotelName(),
                    location.getHotelStatus()));

        } finally {
            connection.close();
        }
    }

    public void updateLocation(Location location, String id) throws SQLException {

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("UPDATE location SET country='%s' AND city='%s' " +
                            "AND address='%s' AND hotel_name='%s' AND hotel_status=%s WHERE id=%s",
                    location.getCountry(), location.getCity(), location.getAddress(), location.getHotelName(),
                    location.getHotelStatus(), id));

        } finally {
            connection.close();
        }
    }

    public void deleteLocationByPath(String country, String city, String address, String hotelName, String hotelStatus)
            throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.execute(String.format("DELETE FROM location WHERE country='%s' AND city='%s' AND " +
                    "address='%s' AND hotel_name='%s' AND hotel_status='%s'", country, city, address, hotelName, hotelStatus));

        } finally {
            connection.close();
        }
    }

    public void deleteLocationById(String id) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.execute(String.format("DELETE FROM location WHERE id=%s", id));

        } finally {
            connection.close();
        }
    }

}
