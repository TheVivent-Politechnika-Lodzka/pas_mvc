package sample.web.jsf.restclient;

import sample.web.jsf.model.CredentialsData;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class AuthRestClient {

    @Inject
    private RestClient restClient;


    public Response login(CredentialsData credentials) {
        Response res = restClient.target("auth/login").request().post(Entity.json(credentials));
        return res;
    }

}
