package navigation.parsers.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import navigation.parsers.models.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class JaxbRunner {
    private static final Logger LOGGER = LogManager.getLogger(JaxbRunner.class);

    public static void marshal(Route route) {
        try {
            JAXBContext context = JAXBContext.newInstance(Route.class);
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(route, new File(System.getProperty("user.dir") + "/src/main/resources/route.xml"));
        } catch (JAXBException e) {
            LOGGER.info(e);
        }
        if (Files.exists(Path.of(System.getProperty("user.dir") + "/src/main/resources/route.xml"))) {
            LOGGER.info("Successfully serialized to: "
                    + System.getProperty("user.dir") + "/src/main/resources/route.xml");
        }
    }

    public static Route unmarhall() {
        Route result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Route.class);
            result = (Route) context.createUnmarshaller()
                    .unmarshal(new FileReader(System.getProperty("user.dir") + "/src/main/resources/route.xml"));
            if (result != null) {
                if (Files.exists(Path.of(System.getProperty("user.dir") + "/src/main/resources/route.xml"))) {
                    LOGGER.info("Successfully deserialized from: "
                            + System.getProperty("user.dir") + "/src/main/resources/route.xml");
                }
            }
        } catch (JAXBException e) {
            LOGGER.info(e);
        } catch (FileNotFoundException e) {
            LOGGER.info(e);
        }
        return result;
    }

    public static void main(String[] args) {
        Route route = JaxbRunner.unmarhall();
        LOGGER.info(route);

    }
}
