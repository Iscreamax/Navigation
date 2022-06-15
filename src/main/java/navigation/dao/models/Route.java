package navigation.dao.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlRootElement(name = "route")
@XmlType(propOrder = {"id", "busId", "busStopId"})
public class Route {
    private int id;
    private int busId;
    private int busStopId;

    public Route() {
    }
    public Route(int id, int busId, int busStopId) {
        this.id = id;
        this.busId = busId;
        this.busStopId = busStopId;
    }

    public int getId() {
        return id;
    }
    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    public int getBusId() {
        return busId;
    }
    @XmlElement
    public void setBusId(int busId) {
        this.busId = busId;
    }

    public int getBusStopId() {
        return busStopId;
    }
    @XmlElement
    public void setBusStopId(int busStopId) {
        this.busStopId = busStopId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        Route route = (Route) o;
        return id == route.id && busId == route.busId && busStopId == route.busStopId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, busId, busStopId);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", busId=" + busId +
                ", busStopId=" + busStopId +
                '}';
    }
}
