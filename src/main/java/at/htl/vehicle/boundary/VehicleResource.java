package at.htl.vehicle.boundary;

import at.htl.vehicle.control.VehicleRepository;
import at.htl.vehicle.entity.Vehicle;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("vehicle")
public class VehicleResource {

    @Inject
    VehicleRepository vehicleRepository;

    @GET
    @Path("{brand}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllBrands(@PathParam("brand") String brand) {
        List<Vehicle> vehicles = vehicleRepository.findByBrand(brand);
        return Response
                .ok(vehicles)
                .header("Reason","alles ok")
                .build();
    }
}
