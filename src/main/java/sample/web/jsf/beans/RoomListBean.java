package sample.web.jsf.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.web.jsf.model.HotelRoom;
import sample.web.jsf.utils.RestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.GenericType;
import java.util.List;

@Named
@RequestScoped
@NoArgsConstructor
public class RoomListBean {

    @Inject
    private EditRoomBean editRoomBean;

    @Getter
    private List<HotelRoom> roomList;

    @Inject
    private RoomDetailsBean roomDetailsBean;

    @PostConstruct
    public void init(){
        getAllRooms();
    }

    public String editRoom(HotelRoom room){
        editRoomBean.setRoom(room);
        return "editRoom";
    }

    public String roomDetails(HotelRoom room){
        roomDetailsBean.setRoom(room);
        return "roomDetails";
    }

    public void getAllRooms() {
        roomList = RestClient.target("room/all").request().get(new GenericType<List<HotelRoom>>() {});
    }

}
