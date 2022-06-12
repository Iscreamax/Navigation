package navigation.dao.models;

public class Route {
    private int id;
    private int busId;
    private int busStop;

    public Route() {
    }

    public Route(int id, int busId, int busStop) {
        this.id = id;
        this.busId = busId;
        this.busStop = busStop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public int getBusStop() {
        return busStop;
    }

    public void setBusStop(int busStop) {
        this.busStop = busStop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;

        Route route = (Route) o;

        if (id != route.id) return false;
        if (busId != route.busId) return false;
        return busStop == route.busStop;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + busId;
        result = 31 * result + busStop;
        return result;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", busId=" + busId +
                ", busStop=" + busStop +
                '}';
    }
}
