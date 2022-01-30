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
        return RestClient.target("room/" + id.toString()).request().get(HotelRoom.class);
    }

    public static HotelRoom getByNumber(int number) {
        return RestClient.target("room/number/" + number).request().get(HotelRoom.class);
    }

    public static Response delete(UUID id) {
        return RestClient.target("room/" + id.toString()).request().delete();
    }

    public static List<HotelRoom> getAll() {
        return RestClient.target("room/all").request().get(new GenericType<List<HotelRoom>>(){});
    }

    public static Response update(HotelRoom room) {
        String idStr = room.getId().toString();
        return RestClient.target("room/" + idStr ).request().post(Entity.json(room));
    }

    public static Response create(HotelRoom room) {
        return RestClient.target("room").request().post(Entity.json(room));
    }

}
