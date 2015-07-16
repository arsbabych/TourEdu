package ua.com.arsen.rest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.com.arsen.dao.CustomerDataAccessObject;
import ua.com.arsen.entities.Customer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * Created by Thor on 12.07.2015.
 */
@Path("/customer")
public class CustomerWebService extends WebService {
    @Override
    public void initDao() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        this.dao = context.getBean("customerDaoObject", CustomerDataAccessObject.class);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("get")
    public Response getCustomers() {
        CustomerDataAccessObject dao = new CustomerDataAccessObject();
        try {
            return Response.ok().entity(dao.getCustomers()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("create")
    public Response createCustomer(Customer customer) {
        CustomerDataAccessObject dao = new CustomerDataAccessObject();
        try {
            dao.insertCustomer(customer);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }

        return Response.status(200).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("update/{id}")
    public Response updateCustomer(Customer customer, @PathParam("id") String id) {
        CustomerDataAccessObject dao = new CustomerDataAccessObject();
        try {
            dao.updateCustomer(customer, id);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }

        return Response.status(200).build();
    }

    @DELETE
    @Path("delete")
    public Response deleteCustomer(@QueryParam("name") String name) {
        CustomerDataAccessObject dao = new CustomerDataAccessObject();
        try {
            dao.deleteCustomerByPath(name);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }

        return Response.status(200).build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        CustomerDataAccessObject dao = new CustomerDataAccessObject();
        try {
            dao.deleteCustomerById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }

        return Response.ok().build();
    }

}
