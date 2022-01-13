package sample.web.jsf.jsfexamples.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.jsfexamples.model.Client;
import sample.web.jsf.jsfexamples.model.User;
import sample.web.jsf.jsfexamples.utils.RestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Named
@RequestScoped
@NoArgsConstructor
public class UserListBean implements Serializable {

    @Getter @Setter
    private String search = "";

    @Getter @Setter
    private List<Client> foundUsers;

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

    public void updateSearch(AjaxBehaviorEvent event) {
        findUsersByLogin();
    }

    public void findUsersByLogin() {

        if (search.isEmpty()) {
            foundUsers = RestClient.client.target(URI.create("http://localhost:2137/api/user/all")).request().get(new GenericType<List<Client>>() {});
        }
        else {
            foundUsers = RestClient.client.target(URI.create("http://localhost:2137/api/user/search/" + search)).request().get(new GenericType<List<Client>>() {});
        }

    }

}
