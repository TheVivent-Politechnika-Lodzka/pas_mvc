package sample.web.jsf.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.model.User;
import sample.web.jsf.utils.RestClient;

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

    private String passwordRepeat;

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public String save() {


        if (!(getPasswordRepeat().equals(user.getPassword()))){
            passwordRepeat = "";
            return "";
        }

        if (user.getPassword() == ""){
            user.setPassword(null);
        }

        if (user == null) {
            throw new IllegalStateException("Proba ominiecia listy");
        }

        Response res = RestClient.target("user/" + user.getId().toString()).request().post(Entity.json(user));
        user = null;

        if (res.getStatus() != 200) {
            throw new IllegalStateException("Nie udało się zapisać użytkownika");
        }
        passwordRepeat = "";
        return "userList";
    }


}
