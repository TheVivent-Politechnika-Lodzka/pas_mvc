package sample.web.jsf.utils;

import com.nimbusds.jwt.SignedJWT;
import lombok.Getter;
import lombok.Setter;
import sample.web.jsf.restclient.AuthRestClient;
import sample.web.jsf.restclient.RestClient;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;

@SessionScoped
public class JwtStore implements Serializable {

    private static final String BEARER_PREFIX = "Bearer ";

    @Getter
    String token;

    @Getter
    String userId;

    @Getter
    String login;

    @Getter
    String role;

    public void setToken(String token) {
        token = token.replace(BEARER_PREFIX, "");
        try {

            SignedJWT jwt = SignedJWT.parse(token);
            // tutaj dla wygody można sobie wyciągnąć informacje z tokenu
            // jakie są otrzymywane od serwera
            this.login = jwt.getJWTClaimsSet().getSubject();
            this.userId = jwt.getJWTClaimsSet().getStringClaim("userId");
            this.role = jwt.getJWTClaimsSet().getStringClaim("permissionLevel");
            this.token = token;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public String getTokenWithBearer() {
        return BEARER_PREFIX + token;
    }

}
