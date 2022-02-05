package sample.web.jsf.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.model.Reservation;
import sample.web.jsf.restclient.ReservationRestClient;
import sample.web.jsf.utils.JwtStore;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.UUID;

@Named
@SessionScoped
@NoArgsConstructor
public class AddReservationBean implements Serializable {

    @Inject
    private JwtStore jwtStore;

    @Inject
    private ReservationRestClient reservationRestClient;

    @Getter
    private final Reservation reservation = new Reservation();

    @Getter @Setter
    private String roomId = "";

    @Getter @Setter
    private String userId = jwtStore.getUserId();


    public String save() {
        try {
            UUID roomActualId = UUID.fromString(roomId);
            UUID userActualId = UUID.fromString(userId);


        Response response = reservationRestClient.create(userActualId, roomActualId, reservation);

        if (response.getStatus() == 201) {
            return "reservationList";
        }

        } catch (Exception e) {
            return "";
        }

        return "";
    }

}
