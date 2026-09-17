package lk.ijse.online.movie.booking.system.DTO;

import lk.ijse.online.movie.booking.system.Enum.Theatre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheatreDTO {
    private Long id;
    private String name;
    private String location;
    private Integer totalCapacity;
    private Theatre type;
}
