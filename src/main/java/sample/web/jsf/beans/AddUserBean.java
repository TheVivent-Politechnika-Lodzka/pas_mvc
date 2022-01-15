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
        RestClient.target("user/create").request().post(Entity.json(user));
        // TODO dodać przekierowanie do potwierdzenia
        // return "userConfirm";
    }

}
