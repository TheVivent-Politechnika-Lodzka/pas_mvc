package sample.web.jsf.jsfexamples.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.jsfexamples.model.Client;
import sample.web.jsf.jsfexamples.model.User;
import sample.web.jsf.jsfexamples.utils.RestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Named
@ViewScoped
@ManagedBean
@NoArgsConstructor
public class UserListBean implements Serializable {

    private HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    private HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

    @Getter @Setter
    private String search = "";

    @Getter @Setter
    private List<Client> foundUsers;

    @Getter @Setter
    private int test = 0;


    @Getter @Setter
    private String selectedId;

    @PostConstruct
    public void init() {
        findUsersByLogin();

    }

    public void testPlus(){
        test++;
    }

    public void save(Client user){

    }

    public void toggleUserActivation(Client user) {
        boolean isActive = user.isActive();
        String userId = user.getId().toString();

        if (isActive) {
            RestClient.client.target(URI.create("http://localhost:2137/api/user/deactivate/" + userId)).request().head();
        }
        else {
            RestClient.client.target(URI.create("http://localhost:2137/api/user/activate/" + userId)).request().head();
        }
        findUsersByLogin();
    }

    public void updateSearch(AjaxBehaviorEvent event) {
        findUsersByLogin();
    }

    public void findUsersByLogin() {

        if (search.isEmpty()) {
            foundUsers = RestClient.client.target(URI.create("http://localhost:2137/api/user/all")).request().get(new GenericType<List<Client>>() {});
        }
        else {
            foundUsers = RestClient.client.target(URI.create("http://localhost:2137/api/user/search/" + search)).request().get(new GenericType<List<Client>>() {});
        }

    }

}
