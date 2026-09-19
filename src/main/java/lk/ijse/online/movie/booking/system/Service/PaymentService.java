package lk.ijse.online.movie.booking.system.Service;

import lk.ijse.online.movie.booking.system.DTO.PaymentDTO;

import java.util.List;

public interface PaymentService {
    PaymentDTO processPayment(PaymentDTO paymentDTO);
    PaymentDTO getPaymentById(Long id);
    PaymentDTO getPaymentByBookingId(Long bookingId);
    List<PaymentDTO> getAllPayments();
}
