package navigation.dao.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(propOrder = {"id", "number", "maxCountOfPassengers", "startTime", "endTime", "routes"})
public class Bus {
    private int id;
    private String number;
    private int maxCountOfPassengers;
    private String startTime;
    private String endTime;
    private List<Route> routes;

    public Bus() {
    }

    public Bus(int id, String number, int maxCountOfPassengers, String startTime, String endTime) {
        this.id = id;
        this.number = number;
        this.maxCountOfPassengers = maxCountOfPassengers;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    @XmlElement
    public void setNumber(String number) {
        this.number = number;
    }

    public int getMaxCountOfPassengers() {
        return maxCountOfPassengers;
    }

    @XmlElement
    public void setMaxCountOfPassengers(int maxCountOfPassengers) {
        this.maxCountOfPassengers = maxCountOfPassengers;
    }

    public String getStartTime() {
        return startTime;
    }

    @XmlElement
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @XmlElement
    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
        if (!(o instanceof Bus)) return false;

        Bus bus = (Bus) o;

        if (id != bus.id) return false;
        if (maxCountOfPassengers != bus.maxCountOfPassengers) return false;
        if (number != null ? !number.equals(bus.number) : bus.number != null) return false;
        if (startTime != null ? !startTime.equals(bus.startTime) : bus.startTime != null) return false;
        return endTime != null ? endTime.equals(bus.endTime) : bus.endTime == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + maxCountOfPassengers;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        return result;
    }


}
