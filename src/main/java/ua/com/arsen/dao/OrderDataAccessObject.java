package ua.com.arsen.dao;

import org.springframework.stereotype.Component;
import ua.com.arsen.entities.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Thor on 12.07.2015.
 */
@Component
public class OrderDataAccessObject extends DataAccessObject {

    public List<Order> getOrders() throws SQLException {
        List<Order> orders = new LinkedList<Order>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM order");

            while (resultSet.next()) {
                Order order = new Order();

                order.setId(resultSet.getInt("id"));
                order.setCustomerId(resultSet.getInt("customer_id"));
                order.setTourId(resultSet.getInt("tour_id"));

                orders.add(order);
            }

        } finally {
            connection.close();
        }

        return orders;
    }

    public void insertOrder(Order order) throws SQLException {

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO order (customer_id, tour_id) " +
                            "VALUES(%s, %s)",
                    order.getCustomerId(), order.getTourId()));

        } finally {
            connection.close();
        }
    }

    public void updateOrder(Order order, String id) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("UPDATE order SET customer_id=%s AND tour_id=%s WHERE id=%s",
                    order.getCustomerId(), order.getTourId(), id));

        } finally {
            connection.close();
        }
    }

    public void deleteOrderByPath(int customerId, int tourId) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.execute(String.format("DELETE FROM order WHERE customer_id=%s AND tour_id=%s", customerId, tourId));

        } finally {
            connection.close();
        }
    }

    public void deleteOrderById(int id) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.execute(String.format("DELETE FROM order WHERE id=%s", id));

        } finally {
            connection.close();
        }
    }

}