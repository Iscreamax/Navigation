package navigation.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import navigation.dao.models.Bus;
import navigation.dao.models.BusStop;
import navigation.dao.models.City;
import navigation.dao.models.Route;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class WritingToJson {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {

        Bus bus = new Bus();
        BusStop busStop = new BusStop();
        City city = new City();
        Route route = new Route();


        Collection collection = new ArrayList();
        collection.add(bus);
        collection.add(busStop);
        collection.add(city);
        collection.add(route);

        String json = GSON.toJson(collection);

        try(FileWriter file = new FileWriter("myJSON.json")) {
            file.write(json.toString());
            file.flush();
        }

        catch (IOException e){
            e.printStackTrace();
        }

        System.out.println(json);
    }
}
