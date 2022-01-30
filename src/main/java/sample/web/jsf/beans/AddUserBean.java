package sample.web.jsf.beans;


import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.web.jsf.model.User;
import sample.web.jsf.restclient.UserRestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.core.Response;

@Named
@RequestScoped
@NoArgsConstructor
public class AddUserBean {

    @Getter
    User user = new User();

    private String passwordRepeat;

    @PostConstruct
    public void init() {
        user = new User();
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public String save() {

        if (!(getPasswordRepeat().equals(user.getPassword())) || getPasswordRepeat() == null){
            return "";
        }

        Response response;

        switch (user.getPermissionLevel()) {
            case "USER_ADMIN":
                response = UserRestClient.createUserAdmin(user);
                break;
            case "RESOURCE_ADMIN":
                response = UserRestClient.createResourceAdmin(user);
                break;
            default:
                response = UserRestClient.createClient(user);
        }

        if (response.getStatus() == 201) {
            return "userList";
        }

        // TODO dodać przekierowanie do potwierdzenia
        // return "userConfirm";
        return "";
    }

}
