package navigation.jaxb;

import navigation.dao.models.Route;

import java.io.File;
import java.io.IOException;
import javax. xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax. xml.bind.Marshaller;

public class JaxbRunner {
    public static void main(String[] args) throws JAXBException, IOException {
        marshal();
    }

    public static void marshal() throws JAXBException, IOException {
        Route route = new Route();
        JAXBContext context = JAXBContext.newInstance(Route.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(route, new File(System.getProperty("user.dir") + "/src/main/resources/route.xml"));

    }
}
