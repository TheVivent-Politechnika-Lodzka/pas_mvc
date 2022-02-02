package sample.web.jsf.restclient;

import sample.web.jsf.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserRestClient {

    public static User getByLogin(String login) {
        return RestClient.target("/user/login" + login).request()
                .header(RestClient.AUTHORIZATION_HEADER, RestClient.getJWT()).get(User.class);
    }

    public static User getById(UUID id) {
        String idStr = id.toString();
        return RestClient.target("user/" + idStr).request()
                .header(RestClient.AUTHORIZATION_HEADER, RestClient.getJWT()).get(User.class);
    }

    public static Response activate(UUID id) {
        String idStr = id.toString();
        return RestClient.target("user/activate/" + idStr).request()
                .header(RestClient.AUTHORIZATION_HEADER, RestClient.getJWT()).head();
    }

    public static Response deactivate(UUID id) {
        String idStr = id.toString();
        return RestClient.target("user/deactivate/" + idStr).request()
                .header(RestClient.AUTHORIZATION_HEADER, RestClient.getJWT()).head();
    }

    public static List<User> getAll() {
        return RestClient.target("user/all").request()
                .header(RestClient.AUTHORIZATION_HEADER, RestClient.getJWT()).get(new GenericType<List<User>>(){});
    }

    public static List<User> searchByLogin(String login) {
        return RestClient.target("user/search/" + login).request()
                .header(RestClient.AUTHORIZATION_HEADER, RestClient.getJWT()).get(new GenericType<List<User>>(){});
    }

    public static Response update(User user) {
        String idStr = user.getId().toString();
        return RestClient.target("user/" + idStr).request()
                .header(RestClient.AUTHORIZATION_HEADER, RestClient.getJWT()).post(Entity.json(user));
    }

    public static Response createClient(User user) {
        return RestClient.target("user/create").request()
                .header(RestClient.AUTHORIZATION_HEADER, RestClient.getJWT()).post(Entity.json(user));
    }

    public static Response createUserAdmin(User user) {
        return RestClient.target("user/createUserAdmin").request()
                .header(RestClient.AUTHORIZATION_HEADER, RestClient.getJWT()).post(Entity.json(user));
    }

    public static Response createResourceAdmin(User user) {
        return RestClient.target("user/createResourceAdmin").request()
                .header(RestClient.AUTHORIZATION_HEADER, RestClient.getJWT()).post(Entity.json(user));
    }

}
