package domain.middlewares;

import domain.services.ServicioJWT;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.io.IOException;

public class JwtMiddleware {

    public static Response validar(Request request, Response response) throws IOException {
        String jwt = request.headers("jwt");
        if(request.headers("Access-Control-Request-Headers") == null){
            if(!ServicioJWT.instancia().validar(jwt, response)){
                Spark.halt(401, "No estás autorizado");
            }
        }
        return response;
    }
}
