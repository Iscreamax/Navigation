package navigation.parsers.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import navigation.dao.models.BusStop;
import navigation.dao.models.City;
import navigation.parsers.jaxb.TimeConverter;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@XmlType(namespace = "navigation.parsers.models.Route",
        propOrder = {"startPoint", "endPoint", "busStops", "time"})
@XmlRootElement
@JsonPropertyOrder({"startPoint", "endPoint", "busStops", "time"})
public class Route {
    private City startPoint;
    private City endPoint;
    private List<BusStop> busStops;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH.mm", timezone = "Europe/Moscow")
    private Calendar time;

    public Route() {
    }

    public Route(City startPoint, City endPoint, List<BusStop> route, Calendar time) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.busStops = route;
        this.time = time;
    }

    public City getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(City startPoint) {
        this.startPoint = startPoint;
    }

    public City getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(City endPoint) {
        this.endPoint = endPoint;
    }

    @XmlElementWrapper(name = "busStops")
    @XmlElement(name = "stop")
    public List<BusStop> getBusStops() {
        return this.busStops;
    }

    public void setBusStops(List<BusStop> busStops) {
        this.busStops = busStops;
    }

    @XmlJavaTypeAdapter(TimeConverter.class)
    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        Route route1 = (Route) o;
        return Objects.equals(startPoint, route1.startPoint) && Objects.equals(endPoint, route1.endPoint) && Objects.equals(busStops, route1.busStops) && Objects.equals(time, route1.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPoint, endPoint, busStops, time);
    }

    @Override
    public String toString() {
        return "Route{" +
                "startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                ", route=" + busStops +
                ", time=" + time.get(Calendar.HOUR_OF_DAY) + "." + time.get(Calendar.MINUTE) +
                '}';
    }
}
