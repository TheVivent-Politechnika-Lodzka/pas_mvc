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
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.GenericType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
@NoArgsConstructor

public class RoomDetailsBean implements Serializable {

    @Inject
    private ReservationRestClient reservationRestClient;

    @Getter @Setter
    private HotelRoom room;

    @PostConstruct
    public void init() { getAllReservations(); }

    public List<Reservation> getAllReservations(){
        try {
            String roomId = room.getId().toString();
            return reservationRestClient.search("", roomId, true);
        }
        catch (Exception e) {
            return new ArrayList<>();
        }
    }

}
