package at.htl.vehicle.boundary;

import at.htl.vehicle.entity.Vehicle;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.mapper.ObjectMapperType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Entity;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import static org.assertj.core.api.Assertions.*;

@QuarkusTest
public class VehicleResourceTest {

    @Test
    public void testVehicleEndpoint() {
        //RestAssuredConfig config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.JSONB));
        final String route = "api-object";
        List<Vehicle> vehicles = new ArrayList<>();

        vehicles = given()
                .when()
                //.log().body() // to log the request body (here it is empty)
                .get("/" + route)
                .then()
                .log().body()   // to log the response body
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList(".", Vehicle.class)
        ;
        // https://stackoverflow.com/a/39588918

        System.out.println(vehicles);

        assertThat(vehicles)
                .isNotEmpty()
                .hasSize(4)
                .extracting(Vehicle::getBrand)
                .containsOnly("VW", "Opel");
    }

    @Disabled
    @Test
    public void testVehicleEndpointWithId() {
        given()
                .pathParam("id", "2")
                .when()
                //.log().body() // to log the request body (here is empty)
                .get("/vehicle/{id}")
                .then()
                .log().body()   // to log the response body
                .statusCode(200)
                .body("brand", is("Opel"),
                        "model", is("Blitz"));
    }
}

