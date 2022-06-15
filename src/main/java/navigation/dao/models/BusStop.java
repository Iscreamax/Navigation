package navigation.dao.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Objects;

@XmlType(propOrder = {"id", "name", "latitude", "longitude", "city", "routes"})
public class BusStop implements Comparable {
    private int id;
    private String name;
    private float latitude;
    private float longitude;
    private City city;
    private List<Route> routes;

    public BusStop() {
    }

    public BusStop(int id, String name, float latitude, float longitude, City city) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
    }

    public int getId() {
        return id;
    }
    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public float getLatitude() {
        return latitude;
    }
    @XmlElement
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }
    @XmlElement
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public City getCity() {
        return city;
    }
    @XmlElement
    public void setCity(City city) {
        this.city = city;
    }

    public List<Route> getRoutes() {
        return routes;
    }
    @XmlElement
    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusStop)) return false;
        BusStop busStop = (BusStop) o;
        return id == busStop.id && Float.compare(busStop.latitude, latitude) == 0 && Float.compare(busStop.longitude, longitude) == 0 && Objects.equals(name, busStop.name) && Objects.equals(city, busStop.city) && Objects.equals(routes, busStop.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, latitude, longitude, city, routes);
    }

    @Override
    public String toString() {
        return "BusStop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", city=" + city +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
