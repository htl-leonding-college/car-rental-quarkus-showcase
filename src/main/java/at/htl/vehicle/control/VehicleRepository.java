package at.htl.vehicle.control;

import at.htl.vehicle.entity.Vehicle;
import com.github.javafaker.Faker;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ApplicationScoped
public class VehicleRepository {

    private Map<String, Vehicle> vehicles;

    public VehicleRepository() {
        vehicles = new HashMap<>();
        init();
    }

    void init() {
        vehicles.clear();
        save(new Vehicle("Opel", "Blitz", "UU-12345A"));
        save(new Vehicle("Opel", "Kadett", "LL-12345B"));
        save(new Vehicle("Opel", "Commodore", "W-12345C"));
        save(new Vehicle("VW", "Käfer", "L-12345D"));
    }

    public void save(Vehicle vehicle) {
        vehicles.put(vehicle.getLicensePlateNo(), vehicle);
    }

    public void save(String brand, String model, String licensePlateNo) {
        save(new Vehicle(brand, model, licensePlateNo));
    }

    public void delete(String licensePlate) {
        vehicles.remove(licensePlate);
    }

    public Vehicle findById(String licensePlateNo) {
        return vehicles.get(licensePlateNo);
    }

    public List<Vehicle> findByBrand(String brand) {
        return vehicles
                .values()
                .stream()
                .filter(x -> x.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    public List<Vehicle> findAll() {
        return Collections.unmodifiableList(
                new LinkedList<>(vehicles.values())
        );
    }

    public List<Vehicle> generateFakeCars(int noOfCars) {


        return IntStream.rangeClosed(1, noOfCars)
                .boxed()
                .map(i -> createFakeCar())
                .collect(Collectors.toList());
    }

    private Vehicle createFakeCar() {
        Faker faker = new Faker();
        Random rnd = new Random();
        int min = 10000;
        int max = 99999;

        String brand = faker.dune().planet().toString();
        String model = faker.ancient().titan().toString();
        String licensePlate = faker.address().countryCode().toString() + "-" + (rnd.nextInt(max - min) + min);
        return new Vehicle(brand, model, licensePlate);
    }
}
