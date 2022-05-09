import server.controller.CiudadController;
import server.model.JsonTransformer;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        post(API.ADD_CIUDAD, CiudadController::addCiudad,new JsonTransformer<>());
        get(API.CIUDADES, CiudadController::getCiudades, new JsonTransformer<>());
    }
}
