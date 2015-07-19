package ua.com.arsen.rest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.com.arsen.dao.TourDataAccessObject;
import ua.com.arsen.entities.Tour;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * Created by Thor on 12.07.2015.
 */
@Path("/tour")
public class TourWebService extends WebService {

     public TourWebService() {
        initDao();
    }

    @Override
    public void initDao() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        TourWebService.dao = context.getBean("tourDaoObject", TourDataAccessObject.class);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("get")
    public Response getTours() {
        try {
            return Response.ok().entity(((TourDataAccessObject)dao).getTours()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("create")
    public Response createTours(Tour tour) {
        try {
            ((TourDataAccessObject)dao).insertTour(tour);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }

        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("update/{id}")
    public Response updateTour(Tour tour, @PathParam("id") String id) {
        try {
            ((TourDataAccessObject)dao).updateTour(tour, id);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }

        return Response.ok().build();
    }

    @DELETE
    @Path("delete")
    public Response deleteTour(@QueryParam("location_id") String location_id,
                               @QueryParam("start_date") String start_date,
                               @QueryParam("count_days") String count_days,
                               @QueryParam("price") String price) {
        try {
            ((TourDataAccessObject)dao).deleteTourByPath(location_id, start_date, count_days, price);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }

        return Response.ok().build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteTour(@PathParam("id") String id) {
        try {
            ((TourDataAccessObject)dao).deleteTourById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }

        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("filter")
    public Response getTourTillDate(@QueryParam("date") String date,
                                    @QueryParam("price") int price,
                                    @QueryParam("country") String country) {
        try {
            return Response.ok().entity(((TourDataAccessObject)dao).filterTours(date, price, country)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }
    }

}
