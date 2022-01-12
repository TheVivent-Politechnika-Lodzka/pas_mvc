package sample.web.jsf.jsfexamples.beans;

import lombok.Getter;
import lombok.Setter;
import sample.web.jsf.jsfexamples.model.Client;
import sample.web.jsf.jsfexamples.model.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Named
@RequestScoped
public class UserListBean {

    private static javax.ws.rs.client.Client client = ClientBuilder.newClient();

    @Getter @Setter
    private String search = "";

    @Getter @Setter
    private List<Client> foundUsers;

    public UserListBean (){}

    @PostConstruct
    public void init() {
        findUsersByLogin();
    }

    public void toggleUserActivation() {
        // TODO nie wiem jak zrobić aktywowanie/deaktywowanie użytkownika
        boolean isActive = false;
        String userId = "random string";
        return;

//        if (isActive) {
//            client.target(URI.create("http://localhost:2137/api/user/deactivate/" + userId)).request().put(null);
//        }
//        else {
//            client.target(URI.create("http://localhost:2137/api/user/activate/" + userId)).request().put(null);
//        }
//        findUsersByLogin();
    }

    public void getAllUsers() {
        try{
            foundUsers = client.target(URI.create("http://localhost:2137/api/user/all")).request().get(new GenericType<List<Client>>() {});
        }
        catch (Exception e) {
            foundUsers = null;
        }
    }

    public void findUsersByLogin() {
        try {
            foundUsers = client.target(URI.create("http://localhost:2137/api/user/search/" + search)).request().get(new GenericType<List<Client>>() {});
        }
        catch (Exception e) {
            getAllUsers();
        }
    }

}
