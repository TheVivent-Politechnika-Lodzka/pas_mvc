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

    @Getter
    private List<Reservation> reservations = new ArrayList<Reservation>();

    @PostConstruct
    public void init() { getAllReservations(); }

    private void getAllReservations(){
        try {

            // TODO naprawić. Z jakiegoś powodu user jest tutaj null

            System.out.println("####################### UserDetailsBean.getAllReservations() #########################");
            System.out.println("####################### " + user + " #########################");


            String userId = user.getId().toString();
            reservations = ReservationRestClient.search(userId, "", true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
