package ua.com.arsen.dao;

import ua.com.arsen.entities.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Thor on 12.07.2015.
 */
public class CustomerDataAccessObject extends DataAccessObject {

    public List<Customer> getCustomers() throws SQLException {
        List<Customer> customers = new LinkedList<Customer>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");

            while (resultSet.next()) {
                Customer customer = new Customer();

                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));

                customers.add(customer);
            }

        } finally {
            connection.close();
        }

        return customers;
    }

    public void insertCustomer(Customer customer) throws SQLException {

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO customer (name) " +
                            "VALUES('%s')",
                    customer.getName()));

        } finally {
            connection.close();
        }
    }

    public void updateCustomer(Customer customer, String id) throws SQLException {

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("UPDATE customer SET name='%s' WHERE id=%s",
                    customer.getName(), id));

        } finally {
            connection.close();
        }
    }

    public void deleteCustomerByPath(String name) throws SQLException {

        try {
            Statement statement = connection.createStatement();
            statement.execute(String.format("DELETE FROM customer WHERE name='%s'", name));

        } finally {
            connection.close();
        }
    }

    public void deleteCustomerById(int id) throws SQLException {

        try {
            Statement statement = connection.createStatement();
            statement.execute(String.format("DELETE FROM customer WHERE id=%s", id));

        } finally {
            connection.close();
        }
    }

}
