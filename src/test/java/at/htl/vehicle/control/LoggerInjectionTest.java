package at.htl.vehicle.control;

import io.quarkus.test.junit.QuarkusTest;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class LoggerInjectionTest {

    //private static final Logger LOG = Logger.getLogger(LoggerInjectionTest.class.getSimpleName());

    @Inject
    Logger log;

    @Test
    void testInjectedLogger() {
        log.info("log-message");
        assertThat(log).isNotNull();
    }
}