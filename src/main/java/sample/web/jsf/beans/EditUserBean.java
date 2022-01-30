package sample.web.jsf.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.model.User;
import sample.web.jsf.restclient.RestClient;
import sample.web.jsf.restclient.UserRestClient;

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

    @Getter @Setter
    private String passwordRepeat;

    public String save() {

        if (!(getPasswordRepeat().equals(user.getPassword()))){
            passwordRepeat = "";
            return "";
        }

        if (user.getPassword().equals("")){
            user.setPassword(null);
        }

        if (user == null) {
            throw new IllegalStateException("Proba ominiecia listy");
        }

        Response res = UserRestClient.update(user);
        user = null;

        if (res.getStatus() != 200) {
            throw new IllegalStateException("Nie udało się zapisać użytkownika");
        }
        passwordRepeat = "";
        return "userList";
    }


}
