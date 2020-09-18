package domain.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface JWTService {
    @Headers("Content-Type: application/json")
    @POST("validate")
    Call<JWT> validar(@Body JWT jwt);
}
