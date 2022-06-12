package navigation.realization;

import navigation.dao.impl.BusDAO;
import navigation.dao.interfaces.IBusDAO;
import navigation.dao.models.Bus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Time {
    private static final Logger LOGGER = LogManager.getLogger(Time.class);
    private static Calendar time = new GregorianCalendar();
    private static SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");

    public static void showAvailableTransport() {
        try(Scanner sc = new Scanner(System.in)) {
            LOGGER.info("Enter current time: (HH.mm)");
            time.setTime(sdf.parse(sc.next()));
        } catch (ParseException e) {
            LOGGER.info("Incorrect value", e);
        }
        IBusDAO iBusDAO = new BusDAO();
        List<Bus> buses = iBusDAO.showAll();
        Calendar sTime = new GregorianCalendar();
        Calendar eTime = new GregorianCalendar();
        List<Bus> sBuses = new ArrayList<>();
        for (Bus b : buses) {
            try {
                sTime.setTime(sdf.parse(b.getStartTime()));
                eTime.setTime(sdf.parse(b.getEndTime()));
            } catch (ParseException e) {
                LOGGER.info(e);
            }
            if (sTime.before(time) && eTime.after(time)) sBuses.add(b);
        }
        sBuses.forEach(LOGGER::info);
    }

    public static void main(String[] args) {
        showAvailableTransport();
    }
}
