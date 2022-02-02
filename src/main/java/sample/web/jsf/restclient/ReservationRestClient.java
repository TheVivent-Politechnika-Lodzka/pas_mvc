package sample.web.jsf.restclient;

import sample.web.jsf.model.Reservation;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ReservationRestClient {


    public static Reservation getById(UUID id) {
        String idStr = id.toString();
        return RestClient.target("reservation/" + idStr).request()
                .header(RestClient.AUTHORIZATION_HEADER, RestClient.getJWT()).get(Reservation.class);
    }

    public static Response create(UUID userId, UUID roomId, Reservation reservation) {
        Response response = RestClient.target("reservation/" + userId + "/" + roomId)
                .request()
                .header(RestClient.AUTHORIZATION_HEADER, RestClient.getJWT())
                .post(Entity.json(reservation));
        return response;
    }

    public static List<Reservation> getAll() {
        return RestClient.target("reservation/all").request()
                .header(RestClient.AUTHORIZATION_HEADER, RestClient.getJWT()).get(new GenericType<List<Reservation>>(){});
    }

    public static List<Reservation> search(String userId, String roomId, boolean includeArchived){
        return RestClient.target("reservation/search")
                .queryParam("clientId", userId)
                .queryParam("roomId", roomId)
                .queryParam("archived", includeArchived)
                .request()
                .header(RestClient.AUTHORIZATION_HEADER, RestClient.getJWT()).get(new GenericType<List<Reservation>>() {});
    }

    public static Response endReservation(UUID id) {
        String idStr = id.toString();
        return RestClient.target("reservation/end/" + idStr).request()
                .header(RestClient.AUTHORIZATION_HEADER, RestClient.getJWT()).head();
    }



}
