package sample.web.jsf.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.model.Reservation;
import sample.web.jsf.restclient.ReservationRestClient;
import sample.web.jsf.restclient.RestClient;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Named
@RequestScoped
@NoArgsConstructor
public class AddReservationBean {

    @Getter
    private final Reservation reservation = new Reservation();

    @Getter @Setter
    private String roomId = "";

    @Getter @Setter
    private String userId = "";


    public String save() {
        try {
            UUID roomActualId = UUID.fromString(roomId);
            UUID userActualId = UUID.fromString(userId);


        Response response = ReservationRestClient.create(userActualId, roomActualId, reservation);

        if (response.getStatus() == 201) {
            return "reservationList";
        }

        } catch (Exception e) {
            return "";
        }

        return "";
    }

}
