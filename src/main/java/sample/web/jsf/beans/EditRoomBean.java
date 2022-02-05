package sample.web.jsf.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.model.HotelRoom;
import sample.web.jsf.restclient.HotelRoomRestClient;
import sample.web.jsf.restclient.RestClient;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.io.Serializable;

@Named
@SessionScoped
@NoArgsConstructor
public class EditRoomBean implements Serializable {


    @Inject
    private HotelRoomRestClient hotelRoomRestClient;

    @Getter @Setter
    private HotelRoom room;

    public String save() {

        if (room == null) {
            throw new IllegalStateException("Proba ominiecia listy");
        }

        Response res = hotelRoomRestClient.update(room);
        room = null;

        if (res.getStatus() != 200) {
            throw new IllegalStateException("Nie udało się zapisać pokoju, powód: " + res.getStatus());
        }

        return "roomList";
    }


}
