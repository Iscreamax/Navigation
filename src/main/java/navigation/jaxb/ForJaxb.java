package navigation.jaxb;

import navigation.dao.models.Bus;
import navigation.dao.models.BusStop;
import navigation.dao.models.City;
import navigation.dao.models.Route;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Collection;

@XmlRootElement(name = "route")
@XmlType(propOrder = {"bus", "busStop", "city", "route"})
public class ForJaxb {
    Bus bus = new Bus();
    BusStop busStop = new BusStop();
    City city = new City();
    Route route = new Route();

    public Bus getBus() {
        return bus;
    }
    @XmlElement
    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public BusStop getBusStop() {
        return busStop;
    }
    @XmlElement
    public void setBusStop(BusStop busStop) {
        this.busStop = busStop;
    }

    public City getCity() {
        return city;
    }
    @XmlElement
    public void setCity(City city) {
        this.city = city;
    }

    public Route getRoute() {
        return route;
    }
    @XmlElement
    public void setRoute(Route route) {
        this.route = route;
    }
}
