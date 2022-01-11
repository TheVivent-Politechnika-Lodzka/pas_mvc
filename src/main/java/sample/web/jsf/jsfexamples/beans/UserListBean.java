package sample.web.jsf.jsfexamples.beans;

import lombok.Getter;
import lombok.Setter;
import sample.web.jsf.jsfexamples.model.Client;
import sample.web.jsf.jsfexamples.model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import java.net.URI;
import java.util.List;

@Named
@RequestScoped
public class UserListBean {

    private static javax.ws.rs.client.Client client = ClientBuilder.newClient();

    @Getter @Setter
    private String search = "";

    private List<Client> foundUsers;

    public UserListBean (){}

    public String getUsersAsString() {
        StringBuilder sb = new StringBuilder();
        if (foundUsers != null) {
            for (Client user : foundUsers) {
                sb.append(user.getLogin()).append("\n");
            }
        }
        return sb.toString();
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
