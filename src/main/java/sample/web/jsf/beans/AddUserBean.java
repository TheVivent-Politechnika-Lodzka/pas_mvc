package sample.web.jsf.beans;


import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.web.jsf.model.User;
import sample.web.jsf.restclient.RestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.Entity;

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

    public void save() {

        if (!(getPasswordRepeat().equals(user.getPassword())) || getPasswordRepeat() == null){
            return;
        }

        switch (user.getPermissionLevel()) {
            case "USER_ADMIN":
                RestClient.target("user/createUserAdmin").request().post(Entity.json(user));
                break;
            case "RESOURCE_ADMIN":
                RestClient.target("user/createResourceAdmin").request().post(Entity.json(user));
                break;
            default:
                RestClient.target("user/create").request().post(Entity.json(user));
        }

        // TODO dodać przekierowanie do potwierdzenia
        // return "userConfirm";
    }

}
