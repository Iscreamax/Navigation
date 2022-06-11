package navigation.dao.interfaces;

import navigation.dao.models.BusStop;

import java.util.List;

public interface IBusStopDAO extends IBaseDAO<BusStop> {
    List<BusStop> showAll();
}
