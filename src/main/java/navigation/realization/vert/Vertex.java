package navigation.realization.vert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertex implements Comparable {
    private static final Logger LOGGER = LogManager.getLogger(Vertex.class);

    private boolean visited;
    private String name;
    private List<Edge> List;
    private double dist = Double.MAX_VALUE;
    private Vertex pr;
    private String busNumber;

    public Vertex() {
    }

    public Vertex(String name) {
        this.name = name;
        this.List = new ArrayList();

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
