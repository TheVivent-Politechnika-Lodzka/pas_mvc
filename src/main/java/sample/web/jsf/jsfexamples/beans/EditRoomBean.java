package sample.web.jsf.jsfexamples.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.jsf.jsfexamples.model.HotelRoom;
import sample.web.jsf.jsfexamples.utils.RestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

@Named
@RequestScoped
@NoArgsConstructor
public class EditRoomBean {

    private HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    private HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

    @Getter
    private HotelRoom room = null;

    @Getter @Setter
    private String roomId = null;

    @Getter @Setter
    private int number;

    @Getter @Setter
    private int price;

    @Getter @Setter
    private int capacity;

    @Getter @Setter
    private String description;

    @PostConstruct
    public void init() {
        getRoom();
        if (room != null) {
            loadFromRoom();
        }
    }

    public void save() {

        HotelRoom roomToSend = new HotelRoom();
        roomToSend.setRoomNumber(number);
        roomToSend.setPrice(price);
        roomToSend.setCapacity(capacity);
        roomToSend.setDescription(description);



        Response response = RestClient.client.target("http://localhost:2137/api/room/" + roomId)
                .request().post(Entity.json(roomToSend));
    }

    private void getRoom() {
        String id = req.getParameter("id");
        try {
            room = RestClient.client.target("http://localhost:2137/api/room/" + id).request().get(HotelRoom.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFromRoom() {
        roomId = room.getId().toString();
        number = room.getRoomNumber();
        price = room.getPrice();
        capacity = room.getCapacity();
        description = room.getDescription();
    }


}
