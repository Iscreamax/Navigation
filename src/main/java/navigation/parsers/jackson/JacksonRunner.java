package navigation.parsers.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import navigation.parsers.models.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JacksonRunner {
    private static final Logger LOGGER = LogManager.getLogger(JacksonRunner.class);
    private static final File FILE = new File(System.getProperty("user.dir") + "/src/main/resources/route.json");

    public static void serialize(Route route) {
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(FILE, route);
            LOGGER.info("Serialization succesfull to: " + FILE.getPath());
        } catch (JsonProcessingException e) {
            LOGGER.info("JsonProcessingException", e);
        } catch (IOException e) {
            LOGGER.info(e);
        }
    }

    public static void deserialize() {
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        JavaType type = objectMapper.getTypeFactory().constructType(Route.class);
        try {
            Route route = objectMapper.readValue(FILE, type);
            LOGGER.info("Successfully deserialized from: " + FILE.getPath());
            LOGGER.info(route);
        } catch (IOException e) {
            LOGGER.info("Error", e);
        }
    }

    public static void main(String[] args) {
        deserialize();
    }
}
