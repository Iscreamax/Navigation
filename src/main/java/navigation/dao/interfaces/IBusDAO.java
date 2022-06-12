package navigation.dao.interfaces;

import navigation.dao.models.Bus;

import java.util.List;

public interface IBusDAO extends IBaseDAO<Bus>{
    List<Bus> getAllBuses();
}
