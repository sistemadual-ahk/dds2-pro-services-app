package domain.services;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioJWT {
    private static String baseUrl = "";
    private static ServicioJWT instancia = null;
    private Retrofit retrofit;

    private ServicioJWT(){
        this.retrofit =  new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServicioJWT instancia(){
        if(instancia == null){
            instancia = new ServicioJWT();
        }
        return instancia;
    }

    public Boolean validar(String jwt, spark.Response response) throws IOException {
        JWTService service = this.retrofit.create(JWTService.class);
        Response<JWT> respuesta = service.validar(new JWT(jwt)).execute();


        boolean statusOk = respuesta.code() == 200;

        if(statusOk &&  respuesta.body() != null && respuesta.body().jwt != null){
            response.header("jwt", respuesta.body().jwt);
        }
        return statusOk;
    }
}