package navigation.realization.vert;

public class Edge {
    private double weight;
    private Vertex startVert;
    private Vertex targetVert;
    private String busNumber;

    public Edge() {

    }

    public Edge(double weight, Vertex startVert, Vertex targetVert, String busNumber) {
        this.weight = weight;
        this.startVert = startVert;
        this.targetVert = targetVert;
        this.busNumber = busNumber;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Vertex getStartVert() {
        return startVert;
    }

    public void setStartVert(Vertex startVert) {
        this.startVert = startVert;
    }

    public Vertex getTargetVert() {
        return targetVert;
    }

    public void setTargetVert(Vertex targetVert) {
        this.targetVert = targetVert;
    }
}
