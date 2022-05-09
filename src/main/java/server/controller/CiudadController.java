package server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.model.*;
import spark.Request;
import spark.Response;

import java.util.List;

public class CiudadController {

    static Logger logger = LoggerFactory.getLogger(Ciudad.class);

    private static ICiudadService service = new ImpCiudadService();
    private static JsonTransformer<Ciudad> jsonTransformer = new JsonTransformer<Ciudad>();

    public static List<Ciudad> getCiudades(Request req, Response res){
        logger.info("Receiving request for all ciudades");
        return service.getAll();
    }

    public static Result<Ciudad> addCiudad(Request request, Response res) {
        logger.info("Add new ciudad");

        String body = request.body();
        Ciudad p = jsonTransformer.getObjet(body,Ciudad.class);
        Result<Ciudad> result = service.add(p);

        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }

        return result;
    }
}
