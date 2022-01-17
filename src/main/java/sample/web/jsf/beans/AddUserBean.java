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

@Named
@RequestScoped
@NoArgsConstructor
public class AddUserBean {

    @Getter
    User user = new User();

    @PostConstruct
    public void init() {
        user = new User();
    }

    public void save() {

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
