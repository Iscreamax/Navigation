package navigation;


import navigation.dao.impl.BusDAO;
import navigation.dao.impl.BusStopDAO;
import navigation.dao.interfaces.IBusDAO;
import navigation.dao.interfaces.IBusStopDAO;
import navigation.dao.models.Bus;
import navigation.realization.vert.Distance;
import navigation.realization.vert.Edge;
import navigation.realization.vert.PathFinder;
import navigation.realization.vert.Vertex;
import navigation.services.Time;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);


    public static void main(String[] args) {
        IBusStopDAO iBusStopDAO = new BusStopDAO();
        Map<String, Vertex> vertexes = new HashMap();
        Vertex malinovka = new Vertex(iBusStopDAO.getEntityById(1).getName());
        vertexes.put("Malinovka", malinovka);
        Vertex petrovschina = new Vertex(iBusStopDAO.getEntityById(2).getName());
        vertexes.put("Petrovschina", petrovschina);
        Vertex mihalovo = new Vertex(iBusStopDAO.getEntityById(3).getName());
        vertexes.put("Mihalovo", mihalovo);
        Vertex grushevka = new Vertex(iBusStopDAO.getEntityById(4).getName());
        vertexes.put("Grushevka", grushevka);
        Vertex lenina = new Vertex(iBusStopDAO.getEntityById(5).getName());
        vertexes.put("Lenina", lenina);
        Vertex octobrs = new Vertex(iBusStopDAO.getEntityById(6).getName());
        vertexes.put("Octobrs", octobrs);
        Vertex pobedy = new Vertex(iBusStopDAO.getEntityById(7).getName());
        vertexes.put("Pobedy", pobedy);
        Vertex pervomayskay = new Vertex(iBusStopDAO.getEntityById(8).getName());
        vertexes.put("Pervomayskay", pervomayskay);
        Vertex partizanskay = new Vertex(iBusStopDAO.getEntityById(9).getName());
        vertexes.put("Partizanskay", partizanskay);
        Vertex mogilevskay = new Vertex(iBusStopDAO.getEntityById(10).getName());
        vertexes.put("Mogilevskay", mogilevskay);
        Vertex vokzal = new Vertex(iBusStopDAO.getEntityById(11).getName());
        vertexes.put("Vokzal", vokzal);
        Vertex truda = new Vertex(iBusStopDAO.getEntityById(12).getName());
        vertexes.put("Truda", truda);
        Vertex electrosety = new Vertex(iBusStopDAO.getEntityById(13).getName());
        vertexes.put("Electrosety", electrosety);
        Vertex kamenchikova = new Vertex(iBusStopDAO.getEntityById(14).getName());
        vertexes.put("Kamenchikova", kamenchikova);
        Vertex krupskay = new Vertex(iBusStopDAO.getEntityById(15).getName());
        vertexes.put("Krupskay", krupskay);
        Vertex nagornaya = new Vertex(iBusStopDAO.getEntityById(16).getName());
        vertexes.put("Nagornaya", nagornaya);
        Vertex gromyko = new Vertex(iBusStopDAO.getEntityById(17).getName());
        vertexes.put("Gromyko", gromyko);
        Vertex makayenka = new Vertex(iBusStopDAO.getEntityById(18).getName());
        vertexes.put("Makayenka", makayenka);
        Vertex kiseleva = new Vertex(iBusStopDAO.getEntityById(19).getName());
        vertexes.put("Kiseleva", kiseleva);
        Vertex kurchatova = new Vertex(iBusStopDAO.getEntityById(20).getName());
        vertexes.put("Kurchatova", kurchatova);
        IBusDAO iBusDAO = new BusDAO();

        List<Vertex> listVert;
        List<Bus> list = Time.showAvailableTransport();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNumber().contains(iBusDAO.getEntityById(1).getNumber())) {
                kiseleva.addNeighbour(new Edge(Distance.countDistance(19, 18), kiseleva, makayenka, iBusDAO.getEntityById(1).getNumber()));
                makayenka.addNeighbour(new Edge(Distance.countDistance(18, 15), makayenka, krupskay, iBusDAO.getEntityById(1).getNumber()));
                krupskay.addNeighbour(new Edge(Distance.countDistance(15, 12), krupskay, truda, iBusDAO.getEntityById(1).getNumber()));
                truda.addNeighbour(new Edge(Distance.countDistance(12, 13), truda, electrosety, iBusDAO.getEntityById(1).getNumber()));
                electrosety.addNeighbour(new Edge(Distance.countDistance(13, 14), electrosety, kamenchikova, iBusDAO.getEntityById(1).getNumber()));
            } else if (list.get(i).getNumber().contains(iBusDAO.getEntityById(2).getNumber())) {
                malinovka.addNeighbour(new Edge(Distance.countDistance(1, 10), malinovka, mogilevskay, iBusDAO.getEntityById(2).getNumber()));
                mogilevskay.addNeighbour(new Edge(Distance.countDistance(10, 9), mogilevskay, partizanskay, iBusDAO.getEntityById(2).getNumber()));
                partizanskay.addNeighbour(new Edge(Distance.countDistance(9, 8), partizanskay, pervomayskay, iBusDAO.getEntityById(2).getNumber()));

            } else if (list.get(i).getNumber().contains(iBusDAO.getEntityById(3).getNumber())) {
                malinovka.addNeighbour(new Edge(Distance.countDistance(1, 4), malinovka, grushevka, iBusDAO.getEntityById(3).getNumber()));
                grushevka.addNeighbour(new Edge(Distance.countDistance(4, 7), grushevka, pobedy, iBusDAO.getEntityById(3).getNumber()));
                pobedy.addNeighbour(new Edge(Distance.countDistance(7, 8), pobedy, pervomayskay, iBusDAO.getEntityById(3).getNumber()));
                pervomayskay.addNeighbour(new Edge(Distance.countDistance(8, 10), pervomayskay, mogilevskay, iBusDAO.getEntityById(3).getNumber()));

            } else if (list.get(i).getNumber().contains(iBusDAO.getEntityById(4).getNumber())) {
                malinovka.addNeighbour(new Edge(Distance.countDistance(1, 5), malinovka, lenina, iBusDAO.getEntityById(4).getNumber()));
                lenina.addNeighbour(new Edge(Distance.countDistance(5, 10), lenina, mogilevskay, iBusDAO.getEntityById(4).getNumber()));
                mogilevskay.addNeighbour(new Edge(Distance.countDistance(10, 20), mogilevskay, kurchatova, iBusDAO.getEntityById(4).getNumber()));
                kurchatova.addNeighbour(new Edge(Distance.countDistance(20, 19), kurchatova, kiseleva, iBusDAO.getEntityById(4).getNumber()));

            } else if (list.get(i).getNumber().contains(iBusDAO.getEntityById(5).getNumber())) {
                malinovka.addNeighbour(new Edge(Distance.countDistance(1, 2), malinovka, petrovschina, iBusDAO.getEntityById(5).getNumber()));
                petrovschina.addNeighbour(new Edge(Distance.countDistance(2, 3), petrovschina, mihalovo, iBusDAO.getEntityById(5).getNumber()));
                mihalovo.addNeighbour(new Edge(Distance.countDistance(3, 4), mihalovo, grushevka, iBusDAO.getEntityById(5).getNumber()));
                grushevka.addNeighbour(new Edge(Distance.countDistance(4, 5), grushevka, lenina, iBusDAO.getEntityById(5).getNumber()));
                lenina.addNeighbour(new Edge(Distance.countDistance(5, 9), lenina, partizanskay, iBusDAO.getEntityById(5).getNumber()));
                partizanskay.addNeighbour(new Edge(Distance.countDistance(9, 8), partizanskay, pervomayskay, iBusDAO.getEntityById(5).getNumber()));
                pervomayskay.addNeighbour(new Edge(Distance.countDistance(8, 7), pervomayskay, pobedy, iBusDAO.getEntityById(5).getNumber()));
                pobedy.addNeighbour(new Edge(Distance.countDistance(7, 6), pobedy, octobrs, iBusDAO.getEntityById(5).getNumber()));
                octobrs.addNeighbour(new Edge(Distance.countDistance(6, 10), octobrs, mogilevskay, iBusDAO.getEntityById(5).getNumber()));
                mogilevskay.addNeighbour(new Edge(Distance.countDistance(10, 14), mogilevskay, kamenchikova, iBusDAO.getEntityById(5).getNumber()));
                kamenchikova.addNeighbour(new Edge(Distance.countDistance(14, 13), kamenchikova, electrosety, iBusDAO.getEntityById(5).getNumber()));
                electrosety.addNeighbour(new Edge(Distance.countDistance(13, 12), electrosety, truda, iBusDAO.getEntityById(5).getNumber()));
                truda.addNeighbour(new Edge(Distance.countDistance(12, 11), truda, vokzal, iBusDAO.getEntityById(5).getNumber()));
                vokzal.addNeighbour(new Edge(Distance.countDistance(11, 20), vokzal, kurchatova, iBusDAO.getEntityById(5).getNumber()));
                kurchatova.addNeighbour(new Edge(Distance.countDistance(20, 15), kurchatova, krupskay, iBusDAO.getEntityById(5).getNumber()));
                krupskay.addNeighbour(new Edge(Distance.countDistance(15, 17), krupskay, gromyko, iBusDAO.getEntityById(5).getNumber()));
                gromyko.addNeighbour(new Edge(Distance.countDistance(17, 18), gromyko, makayenka, iBusDAO.getEntityById(5).getNumber()));
                makayenka.addNeighbour(new Edge(Distance.countDistance(18, 16), makayenka, nagornaya, iBusDAO.getEntityById(5).getNumber()));
                nagornaya.addNeighbour(new Edge(Distance.countDistance(16, 19), nagornaya, kiseleva, iBusDAO.getEntityById(5).getNumber()));


            }

        }
        try (Scanner scan = new Scanner(System.in)) {
            IBusStopDAO busStopDAO = new BusStopDAO();
            LOGGER.info("Enter the stop you would like to travel from: ");
            String firstBusStop = scan.nextLine();
            if (!vertexes.containsKey(firstBusStop))
                throw new IllegalArgumentException("Incorrect value: " + firstBusStop);
            LOGGER.info(firstBusStop);
            PathFinder pathFinder = new PathFinder();
            pathFinder.ShortestP(vertexes.get(firstBusStop));
            LOGGER.info("Enter the stop where you would like to get to: ");
            String secondBusStop = scan.nextLine();
            if (!vertexes.containsKey(secondBusStop))
                throw new IllegalArgumentException("Incorrect value: " + secondBusStop);
            listVert = pathFinder.getShortestPathTo(vertexes.get(secondBusStop));
            LOGGER.info(secondBusStop);
            LOGGER.info("The shortest distance:" + vertexes.get(secondBusStop).getDist() + " m.");
        }

        String bus = null;
        for (int i = 0; i < listVert.size(); i++) {

            if (i == 0) {
                LOGGER.info("At the bus stop :" + listVert.get(i) + " - ");
                LOGGER.info("Get on the bus № " + listVert.get(i + 1).getBusNumber());
                bus = listVert.get(i + 1).getBusNumber();
            } else if (!bus.equals(listVert.get(i).getBusNumber()) && i < listVert.size() - 2) {
                LOGGER.info("You will arrive at the bus stop :" + listVert.get(i) + " - ");
                LOGGER.info("Transfer to the bus № " + listVert.get(i).getBusNumber());
                bus = listVert.get(i + 1).getBusNumber();
            } else if (i == listVert.size() - 1) {
                LOGGER.info("You will arrive at the bus stop :" + listVert.get(i) + " - ");
                LOGGER.info("You have reached your destination!");
            } else {
                LOGGER.info("You will pass the bus stop  :" + listVert.get(i) + " - ");
            }

        }
    }

}
