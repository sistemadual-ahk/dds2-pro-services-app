package domain.controllers;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class ServicioRestController {

    public static String listar(Request request, Response response){

        //busco todos los servicios
        Gson gson = new Gson();
        return gson.toJson(1);
    }

    public static String detalle(Request request, Response response) {
        return null;
    }
}
