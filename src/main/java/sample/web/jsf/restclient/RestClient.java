package sample.web.jsf.restclient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@ApplicationScoped
public class RestClient {

    public final static Client client = ClientBuilder.newClient();

    public static WebTarget target(String url) {
        return client.target("http://localhost:2137/api/" + url);
    }



}
