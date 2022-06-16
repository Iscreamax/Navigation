package navigation.parsers.jaxb;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class TimeConverter extends XmlAdapter<String, Calendar> {
    private static final Logger LOGGER = LogManager.getLogger(TimeConverter.class);
    private static final String CUSTOM_TIME_STRING = "HH.mm";
    private SimpleDateFormat sdf = new SimpleDateFormat(CUSTOM_TIME_STRING);

    @Override
    public Calendar unmarshal(String s) {
        Calendar time = new GregorianCalendar();
        try {
            time.setTime(sdf.parse(s));
        } catch (ParseException e) {
            LOGGER.info(e);
        }
        return time;
    }

    @Override
    public String marshal(Calendar calendar) {
        return calendar.get(Calendar.HOUR_OF_DAY) + "." + calendar.get(Calendar.MINUTE);
    }
}
