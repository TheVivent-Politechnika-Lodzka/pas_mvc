package sample.web.jsf.jsfexamples.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.web.jsf.jsfexamples.model.Client;
import sample.web.jsf.jsfexamples.model.HotelRoom;
import sample.web.jsf.jsfexamples.utils.RestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.core.GenericType;
import java.util.List;

@Named
@RequestScoped
@NoArgsConstructor
public class roomListBean {


    @Getter
    private List<HotelRoom> roomList;

    @PostConstruct
    public void init(){
        getAllRooms();
    }

    public void getAllRooms() {
        roomList = RestClient.client.target("http://localhost:2137/api/room/all").request().get(new GenericType<List<HotelRoom>>() {});
    }

}
