package sample.web.jsf.beans;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.model.User;
import sample.web.jsf.utils.RestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.Entity;
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
                response = RestClient.target("user/createUserAdmin").request().post(Entity.json(user));
                break;
            case "RESOURCE_ADMIN":
                response = RestClient.target("user/createResourceAdmin").request().post(Entity.json(user));
                break;
            default:
                response = RestClient.target("user/create").request().post(Entity.json(user));
        }

        if (response.getStatus() == 201) {
            return "userList";
        }

        return "";
        // TODO dodać przekierowanie do potwierdzenia
        // return "userConfirm";
    }

}
