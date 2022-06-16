package navigation.services;

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
    public static final Calendar TIME = new GregorianCalendar();
    private static final Calendar START_RUSH_HOUR = new GregorianCalendar();
    private static final Calendar END_RUSH_HOUR = new GregorianCalendar();
    private static SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");
    public static Scanner SC;

    public static List<Bus> showAvailableTransport() {
        try {
            START_RUSH_HOUR.setTime(sdf.parse("17.00"));
            END_RUSH_HOUR.setTime(sdf.parse("19.00"));
        } catch (ParseException e) {
            LOGGER.info(e);
        }
        Scanner sc = new Scanner(System.in);
            LOGGER.info("Enter current time: (HH.mm)");
            try {
                TIME.setTime(sdf.parse(sc.next()));
            } catch (ParseException e) {
                LOGGER.info(e);
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
            if ((sTime.before(TIME) || sTime.compareTo(TIME) == 0) &&
                    ((eTime.after(TIME)) || eTime.compareTo(TIME) == 0)) {
                sBuses.add(b);
            }
        }

        if ((TIME.after(START_RUSH_HOUR) || TIME.compareTo(START_RUSH_HOUR) == 0) &&
                (TIME.before(END_RUSH_HOUR) || TIME.compareTo(END_RUSH_HOUR) == 0)) {
            for (Bus b : sBuses) {
                b.setMaxCountOfPassengers(b.getMaxCountOfPassengers() - 40);
                if (b.getMaxCountOfPassengers() < 1) sBuses.remove(b);
            }
        }
        for (Bus b : sBuses) {
            LOGGER.info("The bus: " + b.getNumber() + " is available. Seats available: "
                    + b.getMaxCountOfPassengers() + " Working time: " + b.getStartTime() + "-" + b.getEndTime());
        }
        LOGGER.info("-----------------------------------------------------------------------------");
        for (Bus b : sBuses) {
            buses.remove(b);
        }
        for (Bus b : buses) {
            if (b.getMaxCountOfPassengers() < 1) {
                LOGGER.info("The bus: " + b.getNumber() +
                        " is unavailable. Reason: Rush Hours. Seats available is: " + b.getMaxCountOfPassengers());
            } else {
                LOGGER.info("The bus: " + b.getNumber() +
                        " is unavailable. Reason: Working time is: " + b.getStartTime() + "-" + b.getEndTime());
            }
        }
        return sBuses;
    }


}
