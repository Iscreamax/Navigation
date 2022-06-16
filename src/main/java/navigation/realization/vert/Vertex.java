package navigation.realization.vert;

import navigation.dao.impl.BusStopDAO;
import navigation.dao.interfaces.IBusStopDAO;
import navigation.dao.models.BusStop;
import navigation.dao.models.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Vertex implements Comparable {
    private static final Logger LOGGER = LogManager.getLogger(Vertex.class);
    private boolean visited;
    private String name;
    private List<Edge> List;
    private double dist = Double.MAX_VALUE;
    private Vertex pr;
    private String busNumber;
    private String city;
    private PathFinder pathFinder;

    public Vertex() {
    }

    public Vertex(String name, String city) {
        this.name = name;
        this.List = new ArrayList();
        this.city = city;

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isVisited() {
        return visited;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public List<Edge> getList() {
        return List;
    }

    public void setList(List<Edge> list) {
        List = list;
    }

    public void addNeighbour(Edge edge) {
        this.List.add(edge);
    }

    public boolean Visited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Vertex getPr() {
        return pr;
    }

    public void setPr(Vertex pr) {
        this.pr = pr;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }


    public static List<BusStop> getBusStops(List<Vertex> vertexes) {
        IBusStopDAO busStopDAO = new BusStopDAO();
        List<BusStop> result = new ArrayList<>();
        for (Vertex v : vertexes) {
            result.add(busStopDAO.showAll().stream().filter(f -> f.getName().equals(v.getName()))
                    .collect(Collectors.toList()).get(0));
        }
        return result;
    }

    public static City getStartCity(List<Vertex> vertexes) {
        IBusStopDAO busStopDAO = new BusStopDAO();
        List<BusStop> busStops = new ArrayList<>();
        for (Vertex v : vertexes) {
            busStops.add(busStopDAO.showAll().stream().filter(f -> f.getName().equals(v.getName()))
                    .collect(Collectors.toList()).get(0));
        }
        return busStops.get(0).getCity();
    }

    public static City getFinalCity(List<Vertex> vertexes) {
        IBusStopDAO busStopDAO = new BusStopDAO();
        List<BusStop> busStops = new ArrayList<>();
        for (Vertex v : vertexes) {
            busStops.add(busStopDAO.showAll().stream().filter(f -> f.getName().equals(v.getName()))
                    .collect(Collectors.toList()).get(0));
        }
        return busStops.get(busStops.size() - 1).getCity();
    }

    @Override
    public String toString() {
        return this.name;
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return visited == vertex.visited && Double.compare(vertex.dist, dist) == 0 && Objects.equals(name, vertex.name) && Objects.equals(List, vertex.List) && Objects.equals(pr, vertex.pr) && Objects.equals(busNumber, vertex.busNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visited, name, List, dist, pr, busNumber);
    }
}
