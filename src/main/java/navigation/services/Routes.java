package navigation.services;

import navigation.dao.impl.BusDAO;
import navigation.dao.impl.BusStopDAO;
import navigation.dao.impl.CityDAO;
import navigation.dao.impl.RouteDAO;
import navigation.dao.interfaces.IBusDAO;
import navigation.dao.interfaces.IBusStopDAO;
import navigation.dao.interfaces.ICityDAO;
import navigation.dao.interfaces.IRouteDAO;
import navigation.dao.models.Bus;
import navigation.dao.models.BusStop;
import navigation.dao.models.City;
import navigation.dao.models.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Routes {
    private static final Logger LOGGER = LogManager.getLogger(Routes.class);

    public static List<Route> showTransportRoutes(String busNumber) {
        IBusDAO busDAO = new BusDAO();
        ICityDAO cityDAO = new CityDAO();
        IRouteDAO routeDAO = new RouteDAO();
        IBusStopDAO busStopDAO = new BusStopDAO();
        List<Route> routes = new ArrayList<>();
        List<Bus> buses = busDAO.showAll();
        routes = routeDAO.showAll();
        Bus bus = buses.stream().filter(f -> f.getNumber().equals(busNumber)).toList().get(0);
        LOGGER.info(bus);
        routeDAO.showAll().stream().filter(f -> f.getBusId() == bus.getId()).map(f -> {
            String busName = busDAO.getEntityById(f.getBusId()).getNumber();
            String busStopName = busStopDAO.getEntityById(f.getBusStopId()).getName();
            String cityName = busStopDAO.getEntityById(f.getBusStopId()).getCity().getName();
            return "Bus: " + busName + ", BusStop: " +
                    busStopName + ", City: " + cityName + ", BusId: " + f.getBusId() + ", BusStopId: " + f.getBusStopId();
        }).forEach(LOGGER::info);
        return routeDAO.showAll().stream().filter(f -> f.getBusId() == bus.getId()).toList();
    }

    public static List<BusStop> showBusStops(Bus bus) {
        List<BusStop> busStops = new ArrayList<>();
        IBusStopDAO busStopDAO = new BusStopDAO();
        IRouteDAO routeDAO = new RouteDAO();
        List<Route> routes = routeDAO.showAll().stream().filter(f -> f.getBusId() == bus.getId()).toList();
        for (Route r : routes) {
            IBusStopDAO busStopDAO1 = new BusStopDAO();
            BusStop busStop = busStopDAO1.getEntityById(r.getBusStopId());
            busStops.add(busStop);
        }
        LOGGER.info("Selected routes: " + "\n" + routes);
        LOGGER.info("Available stops for the Bus: " + bus + "\n");
        busStops.forEach(LOGGER::info);
        return busStops;
    }


    public static List<Bus> showAvailableBuses(BusStop busStop) {
        IRouteDAO routeDAO = new RouteDAO();
        List<Bus> buses = new ArrayList<>();

        List<Route> routes = routeDAO.showAll().stream().filter(f -> f.getBusStopId() == busStop.getId()).toList();
        for (Route r : routes) {
            IBusDAO busDAO = new BusDAO();
            Bus bus = busDAO.getEntityById(r.getBusId());
            buses.add(bus);
        }
        buses.forEach(LOGGER::info);
        return buses;

    }




    public static void main(String[] args) {
//         LOGGER.info(showTransportRoutes("I999"));
//        IBusStopDAO busStopDAO = new BusStopDAO();
//        BusStop busStop = busStopDAO.getEntityById(12);
//        IBusDAO busDAO = new BusDAO();
//        Bus bus = busDAO.getEntityById(1);
        //showBusStops(bus);
//        LOGGER.info(busStop);
//        showAvailableBuses(busStop);
        // busStopDAO.showAll();
    }
}
