package sample.web.jsf.jsfexamples.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.jsfexamples.model.User;
import sample.web.jsf.jsfexamples.utils.RestClient;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.io.Serializable;

@Named
@SessionScoped
@NoArgsConstructor
public class EditUserBean implements Serializable {

    @Getter @Setter
    private User user;

    public String save() {
        if (user == null) {
            throw new IllegalStateException("Proba ominiecia listy");
        }

        Response res = RestClient.client.target("http://localhost:2137/api/user/" + user.getId().toString()).request().post(Entity.json(user));
        user = null;

        if (res.getStatus() != 200) {
            throw new IllegalStateException("Nie udało się zapisać użytkownika");
        }

        return "userList";
    }


}
