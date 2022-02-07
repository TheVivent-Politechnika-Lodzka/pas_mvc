package sample.web.jsf.restclient;

import sample.web.jsf.model.HotelRoom;
import sample.web.jsf.utils.JwtStore;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class HotelRoomRestClient {

    @Inject
    private RestClient restClient;

    @Inject
    JwtStore jwtStore;


    public HotelRoom getById(UUID id) {
        return restClient.target("room/get/id/" + id.toString()).request()
                .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer()).get(HotelRoom.class);
    }

    public HotelRoom getByNumber(int number) {
        return restClient.target("room/get/number/" + number).request()
                .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer()).get(HotelRoom.class);
    }

    public Response delete(UUID id) {
        return restClient.target("room/delete/" + id.toString()).request()
                .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer()).delete();
    }

    public List<HotelRoom> getAll() {

        return restClient.target("room/get/all").request()
                .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer()).get(new GenericType<List<HotelRoom>>(){});
    }

    public Response update(HotelRoom room) {
        String idStr = room.getId().toString();
        return restClient.target("room/update/" + idStr ).request()
                .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer()).post(Entity.json(room));
    }

    public Response create(HotelRoom room) {
        return restClient.target("room/create").request()
                .header(RestClient.AUTHORIZATION_HEADER, jwtStore.getTokenWithBearer()).post(Entity.json(room));
    }

}
