package sample.web.jsf.beans;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.model.HotelRoom;
import sample.web.jsf.model.User;
import sample.web.jsf.utils.RestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.Entity;

@Named
@RequestScoped
@NoArgsConstructor
public class AddRoomBean {

    @Getter
    private HotelRoom room = new HotelRoom();



    public void save() {
        RestClient.target("room").request().post(Entity.json(room));
        // TODO dodać jakieś przekierowanie do potwierdzenia
        // return "roomConfirm";
    }

}
