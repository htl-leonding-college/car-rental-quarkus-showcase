package at.htl.vehicle.boundary;

//import at.htl.vehicle.control.VehicleRepository;

import at.htl.vehicle.control.VehicleRepository;
import at.htl.vehicle.entity.Vehicle;
import org.jboss.logging.Logger;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/api-object")
public class VehicleResourceObject {

    private static final Logger LOG = Logger.getLogger(VehicleResourceObject.class.getSimpleName());

    @Inject
    VehicleRepository vehicleRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Vehicle vehicle, @Context UriInfo uriInfo) {
        vehicleRepository.save(vehicle);

        // https://stackoverflow.com/a/26094619
        UriBuilder uriBuilder = uriInfo
                .getAbsolutePathBuilder()
                .path(vehicle.getLicensePlateNo());

        LOG.info(uriInfo
                .getAbsolutePathBuilder()
                .path(vehicle.getLicensePlateNo()).build());

        return Response.created(uriBuilder.build()).build();
    }

    @GET
    @Produces({
            MediaType.APPLICATION_JSON, 
            MediaType.APPLICATION_XML
    })
    @Path("{id}")
    public Vehicle find(@PathParam("id") String id) {
        return vehicleRepository.findById(id);
    }

    @GET
    @Produces({
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML
    })
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String exceptionDemo(@PathParam("id") int id) {
        if (id == 1) {
            Response.ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
            builder.type("text/html")
                    .header("x-my-message", "very bad request")
                    .entity("<h1>BAD REQUEST</h1>");
            throw new WebApplicationException(builder.build());
        } else if (id == 3) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return "ok";
    }
}