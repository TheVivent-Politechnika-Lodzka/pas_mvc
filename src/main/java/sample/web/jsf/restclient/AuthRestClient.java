package sample.web.jsf.restclient;

import sample.web.jsf.model.CredentialsData;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

public class AuthRestClient {


    public static Response login(CredentialsData credentials) {
        Response res = RestClient.target("auth/login").request().post(Entity.json(credentials));

        if (res.getStatus() == 202) {
            String token = res.readEntity(String.class);
            RestClient.setJWT(token);
        }

        return res;
    }

}
