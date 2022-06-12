package navigation.dao.models;

import java.util.List;

public class BusStop {
    private int id;
    private String name;
    private float latitude;
    private float longitude;
    private String city;
    private List<Route> routes;

    public BusStop() {
    }

    public BusStop(int id) {
        this.id = id;
    }

    public BusStop(int id, String name, float latitude, float longitude, String city, List<Route> routes) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.routes = routes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusStop)) return false;

        BusStop busStop = (BusStop) o;

        if (id != busStop.id) return false;
        if (Float.compare(busStop.latitude, latitude) != 0) return false;
        if (Float.compare(busStop.longitude, longitude) != 0) return false;
        if (name != null ? !name.equals(busStop.name) : busStop.name != null) return false;
        if (city != null ? !city.equals(busStop.city) : busStop.city != null) return false;
        return routes != null ? routes.equals(busStop.routes) : busStop.routes == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (latitude != +0.0f ? Float.floatToIntBits(latitude) : 0);
        result = 31 * result + (longitude != +0.0f ? Float.floatToIntBits(longitude) : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (routes != null ? routes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BusStop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", city='" + city + '\'' +
                ", routes=" + routes +
                '}';
    }
}
