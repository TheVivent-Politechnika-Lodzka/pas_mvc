package sample.web.jsf.restclient;

import sample.web.jsf.model.User;
import sample.web.jsf.utils.JwtStore;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserRestClient {

    @Inject
    private RestClient restClient;

    @Inject
    private JwtStore jwtStore;

    public User getByLogin(String login) {
        return restClient.target("/user/login" + login).request()
                .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer()).get(User.class);
    }

    public User getById(UUID id) {
        String idStr = id.toString();
        return restClient.target("user/" + idStr).request()
                .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer()).get(User.class);
    }

    public Response activate(UUID id) {
        String idStr = id.toString();
        return restClient.target("user/activate/" + idStr).request()
                .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer()).head();
    }

    public Response deactivate(UUID id) {
        String idStr = id.toString();
        return restClient.target("user/deactivate/" + idStr).request()
                .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer()).head();
    }

    public List<User> getAll() {
        return restClient.target("user/all").request()
                .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer()).get(new GenericType<List<User>>(){});
    }

    public List<User> searchByLogin(String login) {
        return restClient.target("user/search/" + login).request()
                .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer()).get(new GenericType<List<User>>(){});
    }

    public Response update(User user) {
        String idStr = user.getId().toString();
        return restClient.target("user/" + idStr).request()
                .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer()).post(Entity.json(user));
    }

    public Response createClient(User user) {
        return restClient.target("user/create").request()
                .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer()).post(Entity.json(user));
    }

    public Response createUserAdmin(User user) {
        return restClient.target("user/createUserAdmin").request()
                .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer()).post(Entity.json(user));
    }

    public Response createResourceAdmin(User user) {
        return restClient.target("user/createResourceAdmin").request()
                .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer()).post(Entity.json(user));
    }

}
