package sample.web.jsf.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.model.Reservation;
import sample.web.jsf.model.User;
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
public class UserDetailsBean implements Serializable {

    @Getter @Setter
    private User user;

    @PostConstruct
    public void init() { getAllReservations(); }

    public List<Reservation> getAllReservations(){
        try {

            String userId = user.getId().toString();
            return ReservationRestClient.search(userId, "", true);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Reservation>();
        }
    }
}
