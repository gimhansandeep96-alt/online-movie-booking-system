package lk.ijse.online.movie.booking.system.DTO;

import lk.ijse.online.movie.booking.system.Enum.ShowStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowDTO {
    private Long id;
    private Long movieId;
    private Long theatreId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double ticketPrice;
    private ShowStatus status;
}
