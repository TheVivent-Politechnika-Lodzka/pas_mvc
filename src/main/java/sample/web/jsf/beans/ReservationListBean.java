package sample.web.jsf.beans;

import lombok.Getter;
import lombok.Setter;
import sample.web.jsf.model.Reservation;
import sample.web.jsf.restclient.ReservationRestClient;
import sample.web.jsf.utils.JwtStore;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class ReservationListBean {

    @Inject
    private JwtStore jwtStore;

    @Inject
    private ReservationRestClient reservationRestClient;

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
        if("CLIENT".equals(jwtStore.getRole())) {
            userId = jwtStore.getUserId();
        }
        search();
    }

    public void search() {
        try {
            reservations = reservationRestClient.search(userId, roomId, archived);
        }
        catch (Exception e) {
            reservations = new ArrayList<>();
        }
    }

    private void getAllReservations() {
        try {
            reservations = reservationRestClient.getAll();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endReservation(Reservation reservation) {
        try {
            reservationRestClient.endReservation(reservation.getId());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        getAllReservations();
    }


}
