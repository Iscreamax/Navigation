package navigation.realization;

import navigation.dao.impl.BusStopDAO;
import navigation.dao.interfaces.IBusStopDAO;
import navigation.dao.models.BusStop;

public class Djikstra {
    private static final int max = Integer.MAX_VALUE;
    private static final double[] dist = new double[20];
    private static final int[] prve = new int[20];
    public static IBusStopDAO busStopDAO = new BusStopDAO();
    public static IBusStopDAO bbusStopDAO = new BusStopDAO();
    private static final double[][] a = {
            {0, countDistance(1, 2), max, countDistance(1, 4), countDistance(1, 5), max, max, countDistance(1, 8), max, countDistance(1, 10), max, max, max, max, max, max, max, max, max, max},
            {countDistance(1, 2), 0, countDistance(1, 3), max, max, max, max, max, max, max, max, max, max, max, max, max, max, max, max, max},
            {max, countDistance(2, 3), 0, countDistance(3, 4), max, max, max, max, max, max, max, max, max, max, max, max, max, max, max, max},
            {countDistance(1, 4), max, countDistance(3, 4), 0, countDistance(4, 5), max, countDistance(4, 7), max, max, max, max, max, max, max, max, max, max, max, max, max},
            {countDistance(1, 5), max, max, countDistance(4, 5), 0, max, max, max, countDistance(5, 9), countDistance(5, 10), max, max, max, max, max, max, max, max, max, max},
            {max, max, max, max, max, 0, countDistance(6, 7), max, max, countDistance(6, 10), max, max, max, max, max, max, max, max, max, max},
            {max, max, max, countDistance(4, 7), max, countDistance(6, 7), 0, countDistance(7, 8), max, max, max, max, max, max, max, max, max, max, max, max},
            {countDistance(1, 8), max, max, max, max, max, countDistance(7, 8), 0, countDistance(8, 9), countDistance(8, 10), max, max, max, max, max, max, max, max, max, max, max, max},
            {max, max, max, max, countDistance(5, 9), max, max, countDistance(8, 9), 0, countDistance(9, 10), max, max, max, max, max, max, max, max, max, max, max, max},
            {countDistance(1, 10), max, max, max, countDistance(5, 10), countDistance(6, 10), max, countDistance(8, 10), countDistance(9, 10), 0, max, max, max, countDistance(10, 14), max, max, max, max, max, countDistance(10, 20)},
            {max, max, max, max, max, max, max, max, max, max, 0, countDistance(11, 12), max, max, max, max, max, max, max, countDistance(11, 20)},
            {max, max, max, max, max, max, max, max, max, max, countDistance(11, 12), 0, countDistance(12, 13), max, countDistance(12, 15), max, max, max, max, max},
            {max, max, max, max, max, max, max, max, max, max, max, countDistance(12, 13), 0, countDistance(13, 14), max, max, max, max, max, max},
            {max, max, max, max, max, max, max, max, max, countDistance(10, 14), max, max, countDistance(13, 14), 0, max, max, max, max, countDistance(14, 19), max},
            {max, max, max, max, max, max, max, max, max, max, max, countDistance(12, 15), max, max, 0, max, countDistance(15, 17), countDistance(15, 18), max, countDistance(15, 20)},
            {max, max, max, max, max, max, max, max, max, max, max, max, max, max, max, 0, max, countDistance(16, 18), countDistance(16, 19), max},
            {max, max, max, max, max, max, max, max, max, max, max, max, max, max, countDistance(15, 17), max, 0, countDistance(17, 18), max, max},
            {max, max, max, max, max, max, max, max, max, max, max, max, max, max, countDistance(15, 18), countDistance(16, 18), countDistance(17, 18), 0, countDistance(18, 19), max},
            {max, max, max, max, max, max, max, max, max, max, max, max, max, countDistance(14, 19), max, countDistance(16, 19), max, countDistance(18, 19), 0, countDistance(19, 20)},
            {max, max, max, max, max, max, max, max, max, countDistance(10, 20), countDistance(11, 20), max, max, max, countDistance(15, 20), max, max, max, countDistance(19, 20), 0}
    };


    public static double countDistance(int i, int y) {
        IBusStopDAO busStopDAO = new BusStopDAO();
        IBusStopDAO bbusStopDAO = new BusStopDAO();
        BusStop firstBusStop = busStopDAO.getEntityById(i);
        BusStop secondBusStop = bbusStopDAO.getEntityById(y);
        final int R = 6371;

        double latDistance = Math.toRadians(secondBusStop.getLatitude() - firstBusStop.getLatitude());
        double lonDistance = Math.toRadians(secondBusStop.getLongitude() - firstBusStop.getLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(firstBusStop.getLatitude())) * Math.cos(Math.toRadians(secondBusStop.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters


        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }

    public void dijkstra(int v, double[][] a, double[] dist, int[] prve) {
        int n = dist.length - 1;
        boolean[] s = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            dist[i] = a[v][i];
            s[i] = false;
            if (dist[i] < Integer.MAX_VALUE)
                prve[i] = v;
            else
                prve[i] = -1;
        }
        dist[v] = 0;
        s[v] = true;
        for (int i = 1; i <= n; i++) {
            double temp = Integer.MAX_VALUE;
            int u = v;
            for (int j = 1; j <= n; j++) {
                if ((!s[j]) && dist[j] < temp) {
                    temp = dist[j];
                }
            }
            s[u] = true;
            for (int j = 0; j <= n; j++) {
                if ((!s[j]) && a[u][j] < Integer.MAX_VALUE) {
                    double newDist = dist[u] + a[u][j];
                    if (newDist < dist[j]) {
                        dist[j] = newDist;
                        prve[j] = u;
                    }
                }
            }
        }

    }

    public void outPath(int m, int[] p, double[] d) {
        for (int i = 0; i < dist.length; i++) {
            if (d[i] < Integer.MAX_VALUE && i != m) {
                System.out.print("v" + i + "<--");
                int next = p[i];
                if (next != m) {
                    System.out.print("v" + next + "<--");
                    next = p[next];

                }
                System.out.println("v" + m + ":" + d[i]);
            } else if (i != m)
                System.out.println("v" + i + "<--" + "v" + m + ":no path");
        }
    }

    public static void main(String[] args) {

        Djikstra D = new Djikstra();
        D.dijkstra(9, a, dist, prve);
        D.outPath(9, prve, dist);
    }
}





