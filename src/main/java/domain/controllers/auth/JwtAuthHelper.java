package domain.controllers.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtAuthHelper {

    public static Integer getIdUser(String jwt){
        Integer id = null;
        try{
            DecodedJWT jwtDecode = JWT.decode(jwt);
            id = jwtDecode.getClaim("user_id").asInt();
        }
        catch (RuntimeException exception){
            System.out.println(exception.getMessage());
        }
        finally {
            return id;
        }
    }
}
