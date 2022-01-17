package sample.web.jsf.beans;

import lombok.Getter;
import sample.web.jsf.model.Reservation;
import sample.web.jsf.utils.RestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.core.GenericType;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class ReservationListBean {

    @Getter
    private List<Reservation> reservations;


    @PostConstruct
    public void init() {
        getAllReservations();
    }

    private void getAllReservations() {
        try {
            reservations = RestClient.target("reservation/all")
                    .request().get(new GenericType<List<Reservation>>() {});
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endReservation(Reservation reservation) {
        try {
            RestClient.target("reservation/end/" + reservation.getId())
                    .request().head();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        getAllReservations();
    }


}
