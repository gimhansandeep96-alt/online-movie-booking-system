package lk.ijse.online.movie.booking.system.DTO;

import lk.ijse.online.movie.booking.system.Enum.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private Long id;
    private Long userId;
    private Long showId;
    private Integer numberOfSeats;
    private Double totalPrice;
    private LocalDateTime bookingTime;
    private BookingStatus status;
}
