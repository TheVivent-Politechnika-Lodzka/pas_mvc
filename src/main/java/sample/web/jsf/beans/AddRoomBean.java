package sample.web.jsf.beans;


import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.web.jsf.model.HotelRoom;
import sample.web.jsf.restclient.HotelRoomRestClient;
import sample.web.jsf.restclient.RestClient;

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
        HotelRoomRestClient.create(room);
        // TODO dodać jakieś przekierowanie do potwierdzenia
        // return "roomConfirm";
    }

}
