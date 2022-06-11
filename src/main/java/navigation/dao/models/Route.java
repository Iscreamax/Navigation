package navigation.dao.models;

public class Route {
    private int id;
    private Bus bus;
    private BusStop busStop;

    public Route() {
    }

    public Route(int id, Bus bus, BusStop busStop) {
        this.id = id;
        this.bus = bus;
        this.busStop = busStop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public BusStop getBusStop() {
        return busStop;
    }

    public void setBusStop(BusStop busStop) {
        this.busStop = busStop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;

        Route route = (Route) o;

        if (id != route.id) return false;
        if (bus != null ? !bus.equals(route.bus) : route.bus != null) return false;
        return busStop != null ? busStop.equals(route.busStop) : route.busStop == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (bus != null ? bus.hashCode() : 0);
        result = 31 * result + (busStop != null ? busStop.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", bus=" + bus +
                ", busStop=" + busStop +
                '}';
    }
}
