package ua.com.arsen.rest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.com.arsen.dao.LocationDataAccessObject;
import ua.com.arsen.entities.Location;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * Created by Thor on 12.07.2015.
 */
@Path("/location")
public class LocationWebService extends WebService {
    @Override
    public void initDao() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        this.dao = context.getBean("locationDaoObject", LocationDataAccessObject.class);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("get")
    public Response getLocations() {
        LocationDataAccessObject dao = new LocationDataAccessObject();
        try {
            return Response.ok().entity(dao.getLocations()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("create")
    public Response createLocation(Location location) {
        LocationDataAccessObject dao = new LocationDataAccessObject();
        try {
            dao.insertLocation(location);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }

        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("update/{id}")
    public Response updateLocation(Location location, @PathParam("id") String id) {
        LocationDataAccessObject dao = new LocationDataAccessObject();
        try {
            dao.updateLocation(location, id);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }

        return Response.ok().build();
    }

    @DELETE
    @Path("delete")
    public Response deleteLocation(@QueryParam("country") String country, @QueryParam("city") String city,
                                   @QueryParam("address") String address, @QueryParam("hotel_name") String hotelName,
                                   @QueryParam("hotel_status") String hotelStatus) {
        LocationDataAccessObject dao = new LocationDataAccessObject();
        try {
            dao.deleteLocationByPath(country, city, address, hotelName, hotelStatus);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }

        return Response.ok().build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteLocation(@PathParam("id") String id) {
        LocationDataAccessObject dao = new LocationDataAccessObject();
        try {
            dao.deleteLocationById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(400).entity(e).build();
        }

        return Response.ok().build();
    }

}
