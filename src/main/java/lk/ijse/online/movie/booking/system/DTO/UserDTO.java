package lk.ijse.online.movie.booking.system.DTO;

import lk.ijse.online.movie.booking.system.Enum.role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private role role;
}
