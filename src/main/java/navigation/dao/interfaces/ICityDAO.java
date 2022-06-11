package navigation.dao.interfaces;

import navigation.dao.models.City;

import java.util.List;

public interface ICityDAO extends IBaseDAO<City>{
    List<City> showAll();
}
