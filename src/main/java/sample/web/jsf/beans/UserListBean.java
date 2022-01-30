package sample.web.jsf.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.model.User;
import sample.web.jsf.restclient.UserRestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
@NoArgsConstructor
public class UserListBean implements Serializable {

    @Inject
    private EditUserBean editUserBean;

    @Inject
    private UserDetailsBean userDetailsBean;

    @Getter @Setter
    private String search = "";

    @Getter
    private List<User> foundUsers;

    @PostConstruct
    public void init() {
        search();
    }

    public String userDetails(User user){
        userDetailsBean.setUser(user);
        return "userDetails";
    }

    public String editUser(User user) {
        editUserBean.setUser(user);
        return "editUser";
    }

    public void toggleUserActivation(User user) {
        boolean isActive = user.isActive();

        if (isActive) {
            UserRestClient.deactivate(user.getId());
        }
        else {
            UserRestClient.activate(user.getId());
        }
        search();
    }

    public void search() {

        if (search.isEmpty()) {
            foundUsers = UserRestClient.getAll();
        }
        else {
            foundUsers = UserRestClient.searchByLogin(search);
        }

    }

}
