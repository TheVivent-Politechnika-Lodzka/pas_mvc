package sample.web.jsf.restclient;

import sample.web.jsf.utils.ContextUtils;
import sample.web.jsf.utils.JwtStore;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

@ApplicationScoped
public class RestClient {

    private final Client client = ClientBuilder.newClient();
    public final static String AUTHORIZATION_HEADER = "Authorization";
    public final static String BEARER_PREFIX = "Bearer ";


    @Inject
    JwtStore jwtStore;


    public WebTarget target(String url) {

        return client.target("https://localhost:8181/api/" + url);
    }

}
