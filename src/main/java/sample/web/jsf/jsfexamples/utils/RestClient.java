package sample.web.jsf.jsfexamples.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.ClientBuilder;

@ApplicationScoped
public class RestClient {

    public final static javax.ws.rs.client.Client client = ClientBuilder.newClient();


}
