package at.htl.vehicle.boundary;

//import at.htl.vehicle.control.VehicleRepository;

import at.htl.vehicle.control.VehicleRepository;
import at.htl.vehicle.entity.Vehicle;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/api-json")
public class VehicleResourceJson {

    private static final Logger LOG = Logger.getLogger(VehicleResourceJson.class.getSimpleName());

    @Inject
    VehicleRepository vehicleRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(JsonObject vehicleJson, @Context UriInfo uriInfo) {

//        if (vehicle.getLicensePlateNo().isBlank()) {
//            throw new WebApplicationException("LicensePlate not valid");
//        }

        Vehicle vehicle = new Vehicle(
                vehicleJson.getString("brand"),
                vehicleJson.getString("model"),
                vehicleJson.getString("license-plate-no"));

        Vehicle vehicle2 = JsonbBuilder
                .create()
                .fromJson(
                        vehicleJson.toString(), 
                        Vehicle.class
                );
        
        LOG.info("vehicle 2 -> " + vehicle2);

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
    @Path("html/{id}")
    @Produces(MediaType.APPLICATION_XHTML_XML)
    public Response exceptionDemo(@PathParam("id") String id) {
        // if (id == 1) {
        Response.ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
        builder.type("application/xhtml+xml")
                .header("x-my-message", "very bad request")
                .entity("<html><body><h1>BAD REQUEST</h1></body></html>");
        //throw new WebApplicationException(builder.build());
        return Response.ok(builder.build()).build();
//        } else if (id == 3) {
//            throw new WebApplicationException(Response.Status.BAD_REQUEST);
//        }
//        return "ok";
    }
}