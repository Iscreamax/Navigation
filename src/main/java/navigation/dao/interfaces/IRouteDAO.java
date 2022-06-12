package navigation.dao.interfaces;

import navigation.dao.models.Route;

import java.util.List;

public interface IRouteDAO extends IBaseDAO<Route> {
    List<Route> getAllRoutes();
}
