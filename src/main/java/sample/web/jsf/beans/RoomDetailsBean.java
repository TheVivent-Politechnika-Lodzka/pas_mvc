package sample.web.jsf.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.model.HotelRoom;
import sample.web.jsf.model.Reservation;
import sample.web.jsf.restclient.ReservationRestClient;
import sample.web.jsf.restclient.RestClient;

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

            // TODO naprawić. Z jakiegoś powodu room jest tutaj null

            System.out.println("####################### UserDetailsBean.getAllReservations() #########################");
            System.out.println("####################### " + room + " #########################");

            String roomId = room.getId().toString();
            reservations = ReservationRestClient.search("", roomId, true);
        }
        catch (Exception e) {
        }
    }

}
