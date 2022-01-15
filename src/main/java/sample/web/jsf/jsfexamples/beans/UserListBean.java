package sample.web.jsf.jsfexamples.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.jsfexamples.model.User;
import sample.web.jsf.jsfexamples.utils.RestClient;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.GenericType;
import java.io.Serializable;
import java.net.URI;
import java.util.List;

@Named
@ViewScoped
@ManagedBean
@NoArgsConstructor
public class UserListBean implements Serializable {

    @Inject
    private EditUserBean editUserBean;

    @Getter @Setter
    private String search = "";

    @Getter
    private List<User> foundUsers;

    @PostConstruct
    public void init() {
        findUsersByLogin();
    }

    public String editUser(User user) {
        editUserBean.setUser(user);
        return "editUser";
    }

    public void ajaxHandler(){
        System.out.println("ajaxHandler");
        findUsersByLogin();
    }

    public void toggleUserActivation(User user) {
        boolean isActive = user.isActive();
        String userId = user.getId().toString();

        if (isActive) {
            RestClient.client.target(URI.create("http://localhost:2137/api/user/deactivate/" + userId)).request().head();
        }
        else {
            RestClient.client.target(URI.create("http://localhost:2137/api/user/activate/" + userId)).request().head();
        }
        findUsersByLogin();
    }

    public void findUsersByLogin() {

        if (search.isEmpty()) {
            foundUsers = RestClient.client.target(URI.create("http://localhost:2137/api/user/all")).request().get(new GenericType<List<User>>() {});
        }
        else {
            foundUsers = RestClient.client.target(URI.create("http://localhost:2137/api/user/search/" + search)).request().get(new GenericType<List<User>>() {});
        }

    }

}
