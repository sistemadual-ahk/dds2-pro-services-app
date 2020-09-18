package server;

import domain.controllers.ConsumidorRestController;
import domain.controllers.PrestadorRestController;
import domain.controllers.ServicioRestController;
import domain.controllers.TrabajoRestController;
import domain.middlewares.JwtMiddleware;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.EqualsHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .withHelper("equals", new EqualsHelper())
                .build();
    }

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        crossOrigin();
        Router.configure();
    }

    private static void crossOrigin(){
        Spark.options("/*",
                (request, response) -> {
                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";

                });
        Spark.before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,");
            response.header("Access-Control-Allow-Credentials", "true");
            response.header("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
            response.header("Access-control-expose-headers", "jwt");
        });
    }

    private static void configure(){
        Spark.before("/api/*", (request, response) ->response.type("application/json"));

        Spark.before("/api/*", JwtMiddleware::validar);

        Spark.get("/api/servicios", ServicioRestController::listar);

        Spark.get("/api/servicio/:id", ServicioRestController::detalle);

        Spark.get("/api/prestador/:id", PrestadorRestController::detalle);

        Spark.put("/api/prestador/:id", PrestadorRestController::modificar);

        Spark.get("/api/prestador/:id/trabajos", TrabajoRestController::listarTrabajosPrestador);

        Spark.get("/api/consumidor/:id", ConsumidorRestController::detalle);

        Spark.put("/api/consumidor/:id", ConsumidorRestController::modificar);

        Spark.get("/api/consumidor/:id/trabajos", TrabajoRestController::listarTrabajosConsumidor);
    }
}
