package sample.web.jsf.restclient;

import sample.web.jsf.model.Reservation;
import sample.web.jsf.utils.JwtStore;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ReservationRestClient {

    @Inject
    private RestClient restClient;

    @Inject
    private JwtStore jwtStore;


    public Reservation getById(UUID id) {
        String idStr = id.toString();
        return restClient.target("reservation/get/id" + idStr).request()
                .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer()).get(Reservation.class);
    }

    public Response create(UUID userId, UUID roomId, Reservation reservation) {
        Response response;
        if ("CLIENT".equals(jwtStore.getRole())){
            response = restClient.target("reservation/create/logged/" + roomId)
                    .request()
                    .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer())
                    .post(Entity.json(reservation));
        }
        else {
            response = restClient.target("reservation/create/for/" + userId + "/" + roomId)
                    .request()
                    .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer())
                    .post(Entity.json(reservation));
        }

        return response;
    }

    public List<Reservation> getAll() {
        return restClient.target("reservation/get/all").request()
                .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer()).get(new GenericType<List<Reservation>>(){});
    }

    public List<Reservation> search(String userId, String roomId, boolean includeArchived){
        List<Reservation> response;
        if ("CLIENT".equals(jwtStore.getRole())){
            response = restClient.target("reservation/search/logged")
                    .queryParam("roomId", roomId)
                    .queryParam("archived", includeArchived)
                    .request()
                    .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer())
                    .get(new GenericType<List<Reservation>>() {});
        }else {

            response = restClient.target("reservation/search/admin")
                    .queryParam("clientId", userId)
                    .queryParam("roomId", roomId)
                    .queryParam("archived", includeArchived)
                    .request()
                    .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer())
                    .get(new GenericType<List<Reservation>>() {});
        }
        return response;
    }

    public Response endReservation(UUID id) {
        String idStr = id.toString();
        return restClient.target("reservation/end/" + idStr).request()
                .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer()).head();
    }



}
