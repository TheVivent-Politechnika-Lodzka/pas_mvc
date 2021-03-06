package sample.web.jsf.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.model.HotelRoom;
import sample.web.jsf.restclient.HotelRoomRestClient;
import sample.web.jsf.utils.ContextUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;

@Named
@RequestScoped
@NoArgsConstructor
public class RoomListBean {

    @Inject
    private HotelRoomRestClient hotelRoomRestClient;

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
        roomList = hotelRoomRestClient.getAll();
    }

    public void deleteRoom(){
        String id = ContextUtils.getRequestParams().get("deleteRoomId");

        hotelRoomRestClient.delete(UUID.fromString(id));
        getAllRooms();
    }

    public void search(){
        if (searchString == null || searchString.isEmpty()){
            getAllRooms();
            return;
        }

        try {
            UUID id = UUID.fromString(searchString);
            HotelRoom room = hotelRoomRestClient.getById(id);
            roomList.clear();
            roomList.add(room);
            return;
        } catch (Exception e){}
        try {
            int number = Integer.parseInt(searchString);
            HotelRoom room = hotelRoomRestClient.getByNumber(number);
            roomList.clear();
            roomList.add(room);
            return;
        } catch (Exception e){}

        roomList.clear();
    }

}
