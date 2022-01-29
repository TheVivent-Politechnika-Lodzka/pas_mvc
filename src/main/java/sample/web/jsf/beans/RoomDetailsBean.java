package sample.web.jsf.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.model.HotelRoom;
import sample.web.jsf.model.Reservation;
import sample.web.jsf.utils.RestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.core.GenericType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
@NoArgsConstructor

public class RoomDetailsBean implements Serializable {

    @Getter
    @Setter
    private HotelRoom room;

    @Getter
    private List<Reservation> reservations = new ArrayList<Reservation>();

    @PostConstruct
    public void init() { getAllReservations(); }

    private void getAllReservations(){
        try {
            reservations = RestClient.target("reservation/search")
                    .queryParam("roomId", room.getId().toString())
                    .request().get(new GenericType<List<Reservation>>() {});
        }
        catch (Exception e) {
        }
    }

}
