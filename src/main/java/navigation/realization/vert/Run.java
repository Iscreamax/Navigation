package navigation.realization.vert;

import navigation.dao.impl.BusDAO;
import navigation.dao.impl.BusStopDAO;
import navigation.dao.interfaces.IBusDAO;
import navigation.dao.interfaces.IBusStopDAO;
import navigation.dao.models.BusStop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Run {
    private static final Logger LOGGER = LogManager.getLogger(Run.class);

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

    public static void main(String[] args) {
        IBusStopDAO iBusStopDAO = new BusStopDAO();

        Vertex malinovka = new Vertex(iBusStopDAO.getEntityById(1).getName());
        Vertex petrovschina = new Vertex(iBusStopDAO.getEntityById(2).getName());
        Vertex mihalovo = new Vertex(iBusStopDAO.getEntityById(3).getName());
        Vertex grushevka = new Vertex(iBusStopDAO.getEntityById(4).getName());
        Vertex lenina = new Vertex(iBusStopDAO.getEntityById(5).getName());
        Vertex octobrs = new Vertex(iBusStopDAO.getEntityById(6).getName());
        Vertex pobedy = new Vertex(iBusStopDAO.getEntityById(7).getName());
        Vertex pervomayskay = new Vertex(iBusStopDAO.getEntityById(8).getName());
        Vertex partizanskay = new Vertex(iBusStopDAO.getEntityById(9).getName());
        Vertex mogilevskay = new Vertex(iBusStopDAO.getEntityById(10).getName());
        Vertex vokzal = new Vertex(iBusStopDAO.getEntityById(11).getName());
        Vertex truda = new Vertex(iBusStopDAO.getEntityById(12).getName());
        Vertex electrosety = new Vertex(iBusStopDAO.getEntityById(13).getName());
        Vertex kamenchikova = new Vertex(iBusStopDAO.getEntityById(14).getName());
        Vertex krupskay = new Vertex(iBusStopDAO.getEntityById(15).getName());
        Vertex nagornaya = new Vertex(iBusStopDAO.getEntityById(16).getName());
        Vertex gromyko = new Vertex(iBusStopDAO.getEntityById(17).getName());
        Vertex makayenka = new Vertex(iBusStopDAO.getEntityById(18).getName());
        Vertex kiseleva = new Vertex(iBusStopDAO.getEntityById(19).getName());
        Vertex kurchatova = new Vertex(iBusStopDAO.getEntityById(20).getName());

        IBusDAO iBusDAO = new BusDAO();
        //N111
        kiseleva.addNeighbour(new Edge(countDistance(19, 18), kiseleva, makayenka, iBusDAO.getEntityById(1).getNumber()));
        makayenka.addNeighbour(new Edge(countDistance(18, 15), makayenka, krupskay, iBusDAO.getEntityById(1).getNumber()));
        krupskay.addNeighbour(new Edge(countDistance(15, 12), krupskay, truda, iBusDAO.getEntityById(1).getNumber()));
        truda.addNeighbour(new Edge(countDistance(12, 13), truda, electrosety, iBusDAO.getEntityById(1).getNumber()));
        electrosety.addNeighbour(new Edge(countDistance(13, 14), electrosety, kamenchikova, iBusDAO.getEntityById(1).getNumber()));

        //N342
        malinovka.addNeighbour(new Edge(countDistance(1, 10), malinovka, mogilevskay, iBusDAO.getEntityById(2).getNumber()));
        mogilevskay.addNeighbour(new Edge(countDistance(10, 9), mogilevskay, partizanskay, iBusDAO.getEntityById(2).getNumber()));
        partizanskay.addNeighbour(new Edge(countDistance(9, 8), partizanskay, pervomayskay, iBusDAO.getEntityById(2).getNumber()));

        //N525
        malinovka.addNeighbour(new Edge(countDistance(1, 4), malinovka, grushevka, iBusDAO.getEntityById(3).getNumber()));
        grushevka.addNeighbour(new Edge(countDistance(4, 7), grushevka, pobedy, iBusDAO.getEntityById(3).getNumber()));
        pobedy.addNeighbour(new Edge(countDistance(7, 8), pobedy, pervomayskay, iBusDAO.getEntityById(3).getNumber()));
        pervomayskay.addNeighbour(new Edge(countDistance(8, 10), pervomayskay, mogilevskay, iBusDAO.getEntityById(3).getNumber()));

        //N774
        malinovka.addNeighbour(new Edge(countDistance(1, 5), malinovka, lenina, iBusDAO.getEntityById(4).getNumber()));
        lenina.addNeighbour(new Edge(countDistance(5, 10), lenina, mogilevskay, iBusDAO.getEntityById(4).getNumber()));
        mogilevskay.addNeighbour(new Edge(countDistance(10, 20), mogilevskay, kurchatova, iBusDAO.getEntityById(4).getNumber()));
        kurchatova.addNeighbour(new Edge(countDistance(20, 19), kurchatova, kiseleva, iBusDAO.getEntityById(4).getNumber()));

        //N999
        malinovka.addNeighbour(new Edge(countDistance(1, 2), malinovka, petrovschina, iBusDAO.getEntityById(5).getNumber()));
        petrovschina.addNeighbour(new Edge(countDistance(2, 3), petrovschina, mihalovo, iBusDAO.getEntityById(5).getNumber()));
        mihalovo.addNeighbour(new Edge(countDistance(3, 4), mihalovo, grushevka, iBusDAO.getEntityById(5).getNumber()));
        grushevka.addNeighbour(new Edge(countDistance(4, 5), grushevka, lenina, iBusDAO.getEntityById(5).getNumber()));
        lenina.addNeighbour(new Edge(countDistance(5, 9), lenina, partizanskay, iBusDAO.getEntityById(5).getNumber()));
        partizanskay.addNeighbour(new Edge(countDistance(9, 8), partizanskay, pervomayskay, iBusDAO.getEntityById(5).getNumber()));
        pervomayskay.addNeighbour(new Edge(countDistance(8, 7), pervomayskay, pobedy, iBusDAO.getEntityById(5).getNumber()));
        pobedy.addNeighbour(new Edge(countDistance(7, 6), pobedy, octobrs, iBusDAO.getEntityById(5).getNumber()));
        octobrs.addNeighbour(new Edge(countDistance(6, 10), octobrs, mogilevskay, iBusDAO.getEntityById(5).getNumber()));
        mogilevskay.addNeighbour(new Edge(countDistance(10, 14), mogilevskay, kamenchikova, iBusDAO.getEntityById(5).getNumber()));
        kamenchikova.addNeighbour(new Edge(countDistance(14, 13), kamenchikova, electrosety, iBusDAO.getEntityById(5).getNumber()));
        electrosety.addNeighbour(new Edge(countDistance(13, 12), electrosety, truda, iBusDAO.getEntityById(5).getNumber()));
        truda.addNeighbour(new Edge(countDistance(12, 11), truda, vokzal, iBusDAO.getEntityById(5).getNumber()));
        vokzal.addNeighbour(new Edge(countDistance(11, 20), vokzal, kurchatova, iBusDAO.getEntityById(5).getNumber()));
        kurchatova.addNeighbour(new Edge(countDistance(20, 15), kurchatova, krupskay, iBusDAO.getEntityById(5).getNumber()));
        krupskay.addNeighbour(new Edge(countDistance(15, 17), krupskay, gromyko, iBusDAO.getEntityById(5).getNumber()));
        gromyko.addNeighbour(new Edge(countDistance(17, 18), gromyko, makayenka, iBusDAO.getEntityById(5).getNumber()));
        makayenka.addNeighbour(new Edge(countDistance(18, 16), makayenka, nagornaya, iBusDAO.getEntityById(5).getNumber()));
        nagornaya.addNeighbour(new Edge(countDistance(16, 19), nagornaya, kiseleva, iBusDAO.getEntityById(5).getNumber()));


        PathFinder shortestPath = new PathFinder();

        shortestPath.ShortestP(grushevka);
        System.out.println(gromyko.getDist());

        List<Vertex> list = shortestPath.getShortestPathTo(gromyko);

        String bus = null;
        for (int i = 0; i < list.size(); i++) {

            if (i == 0) {
                LOGGER.info("At the bus stop :" + list.get(i));
                LOGGER.info("Get on the bus № " + list.get(i + 1).getBusNumber());
                bus = list.get(i + 1).getBusNumber();
            } else if (bus != list.get(i).getBusNumber() && i < list.size() - 2) {
                LOGGER.info("You will arrive at the bus stop :" + list.get(i));
                LOGGER.info("Transfer to the bus № " + list.get(i).getBusNumber());
                bus = list.get(i + 1).getBusNumber();
            } else if (i == list.size() - 1) {
                LOGGER.info("You have arrived at the bus stop :" + list.get(i));
                LOGGER.info("You have reached your destination!");
            } else {
                LOGGER.info("You will pass the bus stop  :" + list.get(i));
            }

        }

    }


}


