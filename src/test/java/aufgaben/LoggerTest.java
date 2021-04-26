package aufgaben;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class LoggerTest {
    private static final Logger LOGGER = LogManager.getLogger(LoggerTest.class);

    @Test
    void differentLogLevels() {
        LOGGER.trace("trace"); // invisible Log Level debug in log4j2.xml
        LOGGER.debug("debug");
        LOGGER.info("info");
        LOGGER.warn("warn");
        LOGGER.error("error");
    }

    @Test
    void logWithParams() {
        LOGGER.debug("debug {}, {}", "param A", "param B");
    }

    @Test
    void logException() {
        try {
            throw new IllegalArgumentException("Test exception");
        } catch (IllegalArgumentException e) {
            LOGGER.error("exception", e);
        }
    }
}
