package at.htl.vehicle.control;

import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class InitBean {

    private static final Logger LOG = Logger.getLogger(InitBean.class);

    void startup(@Observes StartupEvent event) {
        LOG.info("It works!");
    }

}
