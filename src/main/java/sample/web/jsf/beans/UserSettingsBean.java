package sample.web.jsf.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.model.User;
import sample.web.jsf.restclient.UserRestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import java.io.Serializable;

@Named
@SessionScoped
@NoArgsConstructor
public class UserSettingsBean implements Serializable {

    @Inject
    private UserRestClient userRestClient;

    @Getter @Setter
    private User user;

    @Getter @Setter
    private String passwordRepeat;

    public String save() {

        if (user == null) {
            throw new IllegalStateException("Proba ominiecia listy");
        }

        if (!(passwordRepeat.equals(user.getPassword()))){
            user = null;
            passwordRepeat = "";
            return "";
        }

        if (user.getPassword().equals("")){
            user.setPassword(null);
        }

        Response res = userRestClient.update(user);
        user = null;


        if (res.getStatus() != 200) {
            throw new IllegalStateException("Nie udało się zapisać użytkownika");
        }
        passwordRepeat = "";
        return "mainSite";
    }

}
