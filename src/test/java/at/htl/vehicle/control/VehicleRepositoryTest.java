package at.htl.vehicle.control;

import at.htl.vehicle.entity.Vehicle;
import io.quarkus.test.junit.QuarkusTest;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VehicleRepositoryTest {

    private static final Logger LOG = Logger.getLogger(VehicleRepositoryTest.class.getSimpleName());

    @Inject
    VehicleRepository repo;

    @Test
    @Order(100)
    void repoExists() {
        assertThat(repo).isNotNull();
    }

    /**
     * https://dzone.com/articles/assertj-and-collections-introduction
     * https://gist.github.com/mhaligowski/a902ed35910b223633c0f187a0cd0947
     */
    @Test
    @Order(110)
    void initRepo() {
        // arrange
        Vehicle blitz = new Vehicle("Opel", "Blitz", "UU-12345A");
        Vehicle kadett = new Vehicle("Opel", "Kadett", "LL-12345B");
        Vehicle commodore = new Vehicle("Opel", "Commodore", "W-12345C");
        Vehicle beetle = new Vehicle("VW", "Käfer", "L-12345D");

        // act
        // already done by the injection

        // assert
        assertThat(repo.findAll().size()).isEqualTo(4);

        //assertThat(repo.findAll()).containsExactly(blitz, kadett, commodore, beetle);

        assertThat(repo.findAll())
                .extracting(Vehicle::getBrand)
                .contains("Opel", "Opel", "Opel", "VW");

        assertThat(repo.findAll())
                .extracting(Vehicle::getModel)
                .containsExactly(blitz.getModel(), commodore.getModel(), kadett.getModel(), beetle.getModel());
    }

    @Test
    @Order(120)
    void saveVehicleOk() {
        // arrange
        Vehicle tornado = new Vehicle("Tucker", "Tornado", "LL-HTL1");
        LOG.info(repo.findAll());

        // act
        repo.save(tornado);

        // assert
        assertThat(repo.findAll()).contains(tornado);
    }

    @Test
    @Order(130)
    void saveVehicleDoubleAttempt() {
        // arrange
        repo.init();
        Vehicle tornado = new Vehicle("Tucker", "Tornado", "LL-HTL1");
        LOG.info(repo.findAll());

        // act
        repo.save(tornado);
        repo.save(tornado);
        repo.save(tornado);
        LOG.info(repo.findAll());

        // assert
        assertThat(repo.findAll().size()).isEqualTo(5);
        assertThat(repo.findAll()).contains(tornado);
    }

    @Test
    @Order(140)
    void deleteVehicle() {
        // arrange
        repo.init();
        LOG.info(repo.findAll());
        String licensePlate = "W-12345C";

        // act
        // it is no good practice to use a untested funtion from the repository -> findById(...)
        Vehicle commodore = repo.findById(licensePlate);
        repo.delete(licensePlate);
        LOG.info(repo.findAll());

        // assert
        assertThat(repo.findAll()).doesNotContain(commodore);
        assertThat(repo.findAll().size()).isEqualTo(3);
    }

    @Test
    @Order(150)
    void deleteVehicledoubleAttempt() {
        // arrange
        repo.init();
        LOG.info(repo.findAll());
        String licensePlate = "W-12345C";

        // act
        // it is no good practice to use a untested funtion from the repository -> findById(...)
        Vehicle commodore = repo.findById(licensePlate);
        repo.delete(licensePlate);
        repo.delete(licensePlate);
        LOG.info(repo.findAll());

        // assert
        assertThat(repo.findAll()).doesNotContain(commodore);
        assertThat(repo.findAll().size()).isEqualTo(3);
    }


    @Test
    @Order(160)
    void deleteWithEmptyParam() {
        //fail("not yet implemented");
        // arrange
        repo.init();

        // act
        List<Vehicle> actualResult = repo.findByBrand("");
        LOG.info(actualResult);

        // assert
        assertThat(actualResult).isEmpty();
    }

    @Test
    @Order(170)
    void findByBrandOpel() {
        // arrange
        repo.init();

        // act
        List<Vehicle> actualResult = repo.findByBrand("Opel");
        LOG.info(actualResult);

        // assert
        assertThat(actualResult)
                .extracting(Vehicle::toString)
                .containsExactly(
                        "UU-12345A: Opel Blitz",
                        "W-12345C: Opel Commodore",
                        "LL-12345B: Opel Kadett"
                );
        // not necessary anymore
        assertThat(actualResult.size())
                .isEqualTo(3);
    }


    @Test
    @Order(180)
    void findByBrandVw() {
        // arrange
        repo.init();

        // act
        List<Vehicle> actualResult = repo.findByBrand("VW");
        LOG.info(actualResult);

        // assert
        assertThat(actualResult)
                .extracting(Vehicle::toString)
                .containsExactly(
                        "L-12345D: VW Käfer"
                );
    }

    @Test
    @Order(190)
    void findByBrandOpelUppercase() {
        // arrange
        repo.init();

        // act
        List<Vehicle> actualResult = repo.findByBrand("OPEL");
        LOG.info(actualResult);

        // assert
        assertThat(actualResult)
                .extracting(Vehicle::toString)
                .containsExactly(
                        "UU-12345A: Opel Blitz",
                        "W-12345C: Opel Commodore",
                        "LL-12345B: Opel Kadett"
                );
        // not necessary anymore
        assertThat(actualResult.size())
                .isEqualTo(3);
    }

    @Test
    @Order(200)
    void findByBrandBmwNotExisting() {
        // arrange
        repo.init();

        // act
        List<Vehicle> actualResult = repo.findByBrand("BMW");
        LOG.info(actualResult);

        // assert
        assertThat(actualResult).isEmpty();
    }

    @Test
    void generateFakeCars_noTest() {

        LOG.info(repo.generateFakeCars(100));

    }
}