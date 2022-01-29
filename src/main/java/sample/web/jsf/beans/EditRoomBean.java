package sample.web.jsf.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.model.HotelRoom;
import sample.web.jsf.utils.RestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.io.Serializable;

@Named
@SessionScoped
@NoArgsConstructor
public class EditRoomBean implements Serializable {


    @Getter @Setter
    private HotelRoom room;

    public String save() {
        if (room == null) {
            throw new IllegalStateException("Proba ominiecia listy");
        }

        Response res = RestClient.target("room/" + room.getId().toString())
                .request().post(Entity.json(room));
        room = null;

        if (res.getStatus() != 200) {
            throw new IllegalStateException("Nie udało się zapisać pokoju, powód: " + res.getStatus());
        }

        return "roomList";
    }


}
