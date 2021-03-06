package sample.web.jsf.beans;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.model.HotelRoom;
import sample.web.jsf.model.User;
import sample.web.jsf.restclient.HotelRoomRestClient;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

@Named
@RequestScoped
@NoArgsConstructor
public class AddRoomBean {

    @Inject
    private HotelRoomRestClient hotelRoomRestClient;

    @Getter
    private HotelRoom room = new HotelRoom();



    public String save() {

        Response response = hotelRoomRestClient.create(room);

        if (response.getStatus() == 201) {
            return "roomList";
        }

        return "";
        // TODO dodać jakieś przekierowanie do potwierdzenia
        // return "roomConfirm";
    }

}
