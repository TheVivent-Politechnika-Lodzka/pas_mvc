package sample.web.jsf.jsfexamples.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.jsfexamples.model.Client;
import sample.web.jsf.jsfexamples.model.HotelRoom;
import sample.web.jsf.jsfexamples.utils.RestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

@Named
@RequestScoped
@NoArgsConstructor
public class editUserBean {

    private HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    private HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

    @Getter
    private Client user = null;

    @Getter @Setter
    private String login = null;

    @Getter @Setter
    private String name = null;

    @Getter @Setter
    private String surname = null;

    @Getter @Setter
    private String userId = null;

    @PostConstruct
    public void init() {
        getUser();
        if (user != null) {
            loadFromUser();
        }
    }

    public void save() {

        Client clientToSend = new Client();
        clientToSend.setLogin(login);
        clientToSend.setName(name);
        clientToSend.setSurname(surname);

        Response response = RestClient.client.target("http://localhost:2137/api/user/" + userId)
                .request().post(Entity.json(clientToSend));
    }

    private void getUser() {
        String id = req.getParameter("id");
        try {
            user = RestClient.client.target("http://localhost:2137/api/user/" + id).request().get(Client.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFromUser() {
        userId = user.getId().toString();
        login = user.getLogin();
        name = user.getName();
        surname = user.getSurname();
    }


}
