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
        return RestClient.target("/user/login" + login).request().get(User.class);
    }

    public static User getById(UUID id) {
        String idStr = id.toString();
        return RestClient.target("user/" + idStr).request().get(User.class);
    }

    public static Response activate(UUID id) {
        String idStr = id.toString();
        return RestClient.target("user/activate/" + idStr).request().head();
    }

    public static Response deactivate(UUID id) {
        String idStr = id.toString();
        return RestClient.target("user/deactivate/" + idStr).request().head();
    }

    public static List<User> getAll() {
        return RestClient.target("user/all").request().get(new GenericType<List<User>>(){});
    }

    public static List<User> searchByLogin(String login) {
        return RestClient.target("user/search/" + login).request().get(new GenericType<List<User>>(){});
    }

    public static Response update(User user) {
        String idStr = user.getId().toString();
        return RestClient.target("user/" + idStr).request().post(Entity.json(user));
    }

    public static UUID createClient(User user) {
        Response response = RestClient.target("user/create").request().post(Entity.json(user));
        String id  = response.getLocation().toString();
        id = id.substring(id.lastIndexOf("/") + 1);
        return UUID.fromString(id);
    }

    public static UUID createUserAdmin(User user) {
        Response response = RestClient.target("user/createUserAdmin").request().post(Entity.json(user));
        String id  = response.getLocation().toString();
        id = id.substring(id.lastIndexOf("/") + 1);
        return UUID.fromString(id);
    }

    public static UUID createResourceAdmin(User user) {
        Response response = RestClient.target("user/createResourceAdmin").request().post(Entity.json(user));
        String id  = response.getLocation().toString();
        id = id.substring(id.lastIndexOf("/") + 1);
        return UUID.fromString(id);
    }

}
