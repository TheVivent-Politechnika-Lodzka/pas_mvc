package sample.web.jsf.restclient;

import sample.web.jsf.model.HotelRoom;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class HotelRoomRestClient {

    public static HotelRoom getById(UUID id) {
        return RestClient.target("room/" + id.toString()).request()
                .header(RestClient.AUTHORIZATION_HEADER, RestClient.getJWT()).get(HotelRoom.class);
    }

    public static HotelRoom getByNumber(int number) {
        return RestClient.target("room/number/" + number).request()
                .header(RestClient.AUTHORIZATION_HEADER, RestClient.getJWT()).get(HotelRoom.class);
    }

    public static Response delete(UUID id) {
        return RestClient.target("room/" + id.toString()).request()
                .header(RestClient.AUTHORIZATION_HEADER, RestClient.getJWT()).delete();
    }

    public static List<HotelRoom> getAll() {
        System.out.println(RestClient.getJWT());

        return RestClient.target("room/all").request()
                .header(RestClient.AUTHORIZATION_HEADER, RestClient.getJWT()).get(new GenericType<List<HotelRoom>>(){});
    }

    public static Response update(HotelRoom room) {
        String idStr = room.getId().toString();
        return RestClient.target("room/" + idStr ).request()
                .header(RestClient.AUTHORIZATION_HEADER, RestClient.getJWT()).post(Entity.json(room));
    }

    public static Response create(HotelRoom room) {
        return RestClient.target("room").request()
                .header(RestClient.AUTHORIZATION_HEADER, RestClient.getJWT()).post(Entity.json(room));
    }

}
