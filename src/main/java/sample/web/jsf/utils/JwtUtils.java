package sample.web.jsf.utils;

import com.nimbusds.jwt.SignedJWT;
import sample.web.jsf.restclient.AuthRestClient;
import sample.web.jsf.restclient.RestClient;

public class JwtUtils {


    public static String getUserId() {
        String token = RestClient.getJWT();
        token = token.replace("Bearer ", "");
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getStringClaim("userId");
        } catch (Exception e) {
            return null;
        }
    }

}
