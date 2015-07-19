package ua.com.arsen.rest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.com.arsen.dao.DataAccessObject;
import ua.com.arsen.dao.OrderDataAccessObject;
import ua.com.arsen.entities.Order;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;


/**
 * Created by Thor on 12.07.2015.
 */
@Path("/order")
public class OrderWebService extends WebService {

    public OrderWebService() {
        initDao();
    }

    @Override
    public void initDao() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        OrderWebService.dao = context.getBean("orderDaoObject", OrderDataAccessObject.class);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("get")
    public Response getOrder() {
        try {
            return Response.ok().entity(((OrderDataAccessObject)dao).getOrders()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("create")
    public Response createOrder(Order order) {
        try {
            ((OrderDataAccessObject)dao).insertOrder(order);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }

        return Response.status(200).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("update/{id}")
    public Response updateOrder(Order order, @PathParam("id") String id) {
        try {
            ((OrderDataAccessObject)dao).updateOrder(order, id);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }

        return Response.status(200).build();
    }

    @DELETE
    @Path("delete")
    public Response deleteOrder(@QueryParam("tour_id") int tour_id,
                                @QueryParam("customer_id") int customer_id) {
        try {
            ((OrderDataAccessObject)dao).deleteOrderByPath(tour_id, customer_id);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }

        return Response.status(200).build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteLocation(@PathParam("id") int id) {
        try {
            ((OrderDataAccessObject)dao).deleteOrderById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }

        return Response.ok().build();
    }

}
