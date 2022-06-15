package navigation.jaxb;

import navigation.dao.models.Bus;
import navigation.dao.models.BusStop;
import navigation.dao.models.City;
import navigation.dao.models.Route;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax. xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax. xml.bind.Marshaller;

public class JaxbRunner {

    public static void main(String[] args) throws JAXBException, IOException {
        marshal();
    }

    public static void marshal() throws JAXBException, IOException {
//        Route route = new Route();
//        Bus bus = new Bus();
//        BusStop busStop = new BusStop();
//        City city = new City();
        ForJaxb forJaxb = new ForJaxb();

        JAXBContext context = JAXBContext.newInstance(ForJaxb.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(forJaxb, new File(System.getProperty("user.dir") + "/src/main/resources/route.xml"));

    }
}
