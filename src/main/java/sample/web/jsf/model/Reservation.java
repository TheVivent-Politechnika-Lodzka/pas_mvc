package sample.web.jsf.model;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Reservation {

    @EqualsAndHashCode.Include
    @Getter @Setter
    private UUID id;

    @Getter @Setter
    private Instant startDate;

    @Getter @Setter
    private Instant endDate;

    @Getter @Setter
    private User user;

    @Getter @Setter
    private HotelRoom hotelRoom;

    public boolean isActive() {
        if (endDate != null) {
            return endDate.isAfter(Instant.now());
        }
        return true;
    }

}
