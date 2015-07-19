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

    public CustomerWebService() {
        initDao();
    }

    @Override
    public void initDao() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        CustomerWebService.dao = context.getBean("customerDaoObject", CustomerDataAccessObject.class);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("get")
    public Response getCustomers() {
        try {
            return Response.ok().entity(((CustomerDataAccessObject)dao).getCustomers()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("create")
    public Response createCustomer(Customer customer) {
        try {
            ((CustomerDataAccessObject)dao).insertCustomer(customer);
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
        try {
            ((CustomerDataAccessObject)dao).updateCustomer(customer, id);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }

        return Response.status(200).build();
    }

    @DELETE
    @Path("delete")
    public Response deleteCustomer(@QueryParam("name") String name) {
        try {
            ((CustomerDataAccessObject)dao).deleteCustomerByPath(name);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }

        return Response.status(200).build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        try {
            ((CustomerDataAccessObject)dao).deleteCustomerById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }

        return Response.ok().build();
    }

}
