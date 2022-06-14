package navigation.realization.vert;

import navigation.dao.impl.BusStopDAO;

import navigation.dao.interfaces.IBusStopDAO;
import navigation.dao.models.BusStop;


public class Distance {

    public static double countDistance(int i, int y) {
        IBusStopDAO firstBusStopDAO = new BusStopDAO();
        IBusStopDAO secondBusStopDAO = new BusStopDAO();
        BusStop firstBusStop = firstBusStopDAO.getEntityById(i);
        BusStop secondBusStop = secondBusStopDAO.getEntityById(y);
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
}


