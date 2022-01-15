package sample.web.jsf.beans;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.model.User;
import sample.web.jsf.utils.RestClient;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.Entity;

@Named
@RequestScoped
@NoArgsConstructor
public class AddUserBean {

    @Getter @Setter
    User user = new User();


    public void save() {
//        RestClient.client.target("http://localhost:8080/users").request().post(user);
        RestClient.target("user/create").request().post(Entity.json(user));

//        return "success";
    }

}
