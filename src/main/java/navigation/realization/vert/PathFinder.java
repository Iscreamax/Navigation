package navigation.realization.vert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;


public class PathFinder {

    public PathFinder() {
    }

    public void ShortestP(Vertex sourceV) {
        sourceV.setDist(0);
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue();
        priorityQueue.add(sourceV);
        sourceV.setVisited(true);

        while (!priorityQueue.isEmpty()) {
            Vertex actualVertex = priorityQueue.poll();
            for (Edge edge : actualVertex.getList()) {
                Vertex v = edge.getTargetVert();

                if (!v.Visited()) {
                    double newDistance = actualVertex.getDist() + edge.getWeight();
                    if (newDistance < v.getDist()) {
                        priorityQueue.remove(v);
                        v.setDist(newDistance);
                        v.setBusNumber(edge.getBusNumber());
                        v.setPr(actualVertex);
                        priorityQueue.add(v);
                    }
                }
            }
            actualVertex.setVisited(true);
        }
    }

    public List<Vertex> getShortestPathTo(Vertex targetVertex) {
        List<Vertex> path = new ArrayList();
        for (Vertex vertex = targetVertex; vertex != null; vertex = vertex.getPr()) {

            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    }

}
