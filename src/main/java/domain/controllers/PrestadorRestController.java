package domain.controllers;

import com.google.gson.Gson;
import domain.controllers.auth.JwtAuthHelper;
import domain.entities.actores.Prestador;
import domain.repositories.Repository;
import domain.repositories.daos.DAOHibernate;
import spark.Request;
import spark.Response;

public class PrestadorRestController {
    private static Repository<Prestador> repo;

    static{
        repo = new Repository<>(new DAOHibernate(), Prestador.class);
    }
    public static String detalle(Request request, Response response) {
        Integer id = JwtAuthHelper.getIdUser(request.headers("jwt"));
        Prestador prestador = repo.find(id);
        return new Gson().toJson(prestador);
    }

    public static String modificar(Request request, Response response) {
        return null;
    }
}
