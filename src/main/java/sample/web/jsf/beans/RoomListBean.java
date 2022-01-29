package sample.web.jsf.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.model.HotelRoom;
import sample.web.jsf.utils.RestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.GenericType;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

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

    @Getter @Setter
    private String searchString;

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

    public void deleteRoom(HotelRoom room){
        RestClient.target("room/" + room.getId().toString()).request().delete();
        getAllRooms();
    }

    public void search(){
        if (searchString == null || searchString.isEmpty()){
            getAllRooms();
            return;
        }

        try {
            UUID.fromString(searchString);
            HotelRoom room = RestClient.target("room/" + searchString).request().get(HotelRoom.class);
            roomList.clear();
            roomList.add(room);
            return;
        } catch (Exception e){}
        try {
            Integer.parseInt(searchString);
            HotelRoom room = RestClient.target("room/number/" + searchString).request().get(HotelRoom.class);
            roomList.clear();
            roomList.add(room);
            return;
        } catch (Exception e){}

        roomList.clear();
    }

}
