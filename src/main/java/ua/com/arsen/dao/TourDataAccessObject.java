package ua.com.arsen.dao;

import org.springframework.stereotype.Component;
import ua.com.arsen.entities.Tour;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Thor on 12.07.2015.
 */
@Component
public class TourDataAccessObject extends DataAccessObject {

    public List<Tour> getTours() throws Exception {
        List<Tour> tours = new LinkedList<Tour>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tour");

            while (resultSet.next()) {
                Tour tour = new Tour();

                tour.setId(resultSet.getInt("id"));
                tour.setLocationId(resultSet.getInt("location_id"));
                tour.setStartDate(resultSet.getDate("start_date"));
                tour.setCountDays(resultSet.getInt("count_days"));
                tour.setPrice(resultSet.getInt("price"));

                tours.add(tour);
            }
        } finally {
            closeConnention();
        }
        return tours;
    }

    public void insertTour(Tour tour) throws Exception {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO tour (location_id, start_date, count_days, price) " +
                            "VALUES(%s, '%s', %s, %s)",
                    tour.getLocationId(), tour.getStartDate().toString(), tour.getCountDays(), tour.getPrice()));

        } finally {
            closeConnention();
        }
    }

    public void updateTour(Tour tour, String id) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("UPDATE tour SET location_id=%s AND start_date='%s' " +
                            "AND count_days=%s AND price=%s WHERE id=%s",
                    tour.getLocationId(), tour.getStartDate().toString(), tour.getCountDays(), tour.getPrice(), id));

        } finally {
            closeConnention();
        }
    }

    public void deleteTourByPath(String location_id, String start_date, String count_days, String price)
            throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.execute(String.format("DELETE FROM tour WHERE location_id=%s AND start_date='%s' AND " +
                    "count_days=%s AND price=%s", location_id, start_date, count_days, price));

        } finally {
            closeConnention();
        }
    }

    public void deleteTourById(String id) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.execute(String.format("DELETE FROM tour WHERE id=%s", id));

        } finally {
            closeConnention();
        }
    }

    public List<Tour> filterTours(String date, int price, String country) throws SQLException {
        List<Tour> tours = new LinkedList<Tour>();
        String whereCondition = getFilterWhereCondition(date, price, country);

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT tour.id, tour.start_date, tour.count_days, tour.price, location.country FROM tour JOIN location ON tour.location_id = location.id WHERE start_date<'2015-06-01' AND price<1000 \n" +
                    "AND country='Turkey'", whereCondition));

            while (resultSet.next()) {
                Tour tour = new Tour();

                tour.setId(resultSet.getInt("id"));
                tour.setLocationId(resultSet.getInt("location_id"));
                tour.setStartDate(resultSet.getDate("start_date"));
                tour.setCountDays(resultSet.getInt("count_days"));
                tour.setPrice(resultSet.getInt("price"));

                tours.add(tour);
            }
        } finally {
            closeConnention();
        }

        return tours;
    }

    private String getFilterWhereCondition(String date, int price, String country) {
        boolean and = false;
        StringBuffer result = new StringBuffer();
        result.append("WHERE ");

        if (date != null) {
            result.append("start_date < ");
            result.append(String.format("'%s'", date));
            and = true;
        }

        if (and) {
            result.append(" AND ");
            and = false;
        }

        if (price != 0) {
            result.append(" price < ");
            result.append(price);
            and = true;
        }

        if (and) {
            result.append(" AND ");
        }

        if (country != null) {
            result.append(" country = ");
            result.append(country);
        }

        return result.toString();
    }

}
