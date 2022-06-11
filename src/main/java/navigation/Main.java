package navigation;

import navigation.dao.impl.BusStopDAO;
import navigation.dao.interfaces.IBusStopDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        IBusStopDAO busStopDAO = new BusStopDAO();
        busStopDAO.showAll();
    }
}
