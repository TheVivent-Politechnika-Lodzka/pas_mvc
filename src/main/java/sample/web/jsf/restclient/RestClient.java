package sample.web.jsf.restclient;

import sample.web.jsf.utils.ContextUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

@ApplicationScoped
public class RestClient {

    public final static Client client = ClientBuilder.newClient();
    public final static String AUTHORIZATION_HEADER = "Authorization";
    public final static String BEARER_PREFIX = "Bearer ";


    public static WebTarget target(String url) {

        return client.target("https://localhost:8181/api/" + url);
    }


    public static String getJWT() {
        String auth = (String) ContextUtils.getSessionAttribute(AUTHORIZATION_HEADER);
        return auth == null ? "" : auth;
    }

    public static void setJWT(String jwt) {
        ContextUtils.setSessionAttribute(AUTHORIZATION_HEADER, BEARER_PREFIX + jwt);
    }


}
