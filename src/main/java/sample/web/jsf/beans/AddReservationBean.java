package sample.web.jsf.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.model.Reservation;
import sample.web.jsf.restclient.ReservationRestClient;
import sample.web.jsf.restclient.RestClient;
import sample.web.jsf.utils.JwtUtils;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.UUID;

@Named
@SessionScoped
@NoArgsConstructor
public class AddReservationBean implements Serializable {

    @Getter
    private final Reservation reservation = new Reservation();

    @Getter @Setter
    private String roomId = "";

    @Getter @Setter
    private String userId = JwtUtils.getUserId();


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
