package sample.web.jsf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.UUID;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class HotelRoom {

    @EqualsAndHashCode.Include
    @Getter @Setter
    private UUID id;

    @Getter @Setter
    @Positive
    private int roomNumber;

    @Getter @Setter
    @Positive
    private int price;

    @Getter @Setter
    @Positive
    private int capacity;

    @Getter @Setter
    @NotBlank
    private String description;

    @Getter @Setter
    private boolean isAllocated = false;

    @Getter @Setter
    @JsonIgnore
    private boolean isActive = true;

    public HotelRoom(UUID id, int roomNumber, int price, int capacity, String description) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.price = price;
        this.capacity = capacity;
        this.description = description;
    }

}
