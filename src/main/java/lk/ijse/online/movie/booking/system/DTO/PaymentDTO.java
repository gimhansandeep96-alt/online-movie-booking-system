package lk.ijse.online.movie.booking.system.DTO;

import lk.ijse.online.movie.booking.system.Enum.PaymentMethod;
import lk.ijse.online.movie.booking.system.Enum.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private Long id;
    private Long bookingId;
    private Double amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private String transactionId;
    private LocalDateTime paymentTime;

}
