package at.htl.vehicle.control;

import at.htl.vehicle.entity.Vehicle;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class InitBean {

    @Inject
    VehicleRepository vehicleRepository;

    private static final Logger LOG = Logger.getLogger(InitBean.class);

    @Transactional
    void startup(@Observes StartupEvent event) {
        Vehicle mustang = new Vehicle("Ford", "Mustang");
        Vehicle ram = new Vehicle("Ford", "Ram");
        vehicleRepository.persist(mustang);
        vehicleRepository.persist(ram);
        LOG.info("It works!");
    }

}
