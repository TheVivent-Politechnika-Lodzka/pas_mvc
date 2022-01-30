package sample.web.jsf.beans;

import lombok.Getter;
import lombok.Setter;
import sample.web.jsf.model.Reservation;
import sample.web.jsf.restclient.ReservationRestClient;
import sample.web.jsf.restclient.RestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.core.GenericType;
import java.util.List;

@Named
@RequestScoped
public class ReservationListBean {

    @Getter
    private List<Reservation> reservations;

    @Getter @Setter
    private String userId = "";

    @Getter @Setter
    private String roomId = "";

    @Getter @Setter
    private boolean archived = true;


    @PostConstruct
    public void init() {
        getAllReservations();
    }

    public void search() {
        try {
            reservations = ReservationRestClient.search(userId, roomId, archived);
        }
        catch (Exception e) {
            reservations.clear();
        }
    }

    private void getAllReservations() {
        try {
            reservations = ReservationRestClient.getAll();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endReservation(Reservation reservation) {
        try {
            ReservationRestClient.endReservation(reservation.getId());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        getAllReservations();
    }


}
