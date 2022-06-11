package navigation;

import navigation.dao.impl.BusStopDAO;
import navigation.dao.interfaces.IBusStopDAO;
import navigation.realization.Djikstra;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        LOGGER.info(Djikstra.countDistance(1,18)+"   \n"+Djikstra.countDistance(2,5));

    }
}
