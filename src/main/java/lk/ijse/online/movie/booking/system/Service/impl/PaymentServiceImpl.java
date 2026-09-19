package lk.ijse.online.movie.booking.system.Service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.online.movie.booking.system.DAO.BookingDAO;
import lk.ijse.online.movie.booking.system.DAO.PaymentDAO;
import lk.ijse.online.movie.booking.system.DTO.PaymentDTO;
import lk.ijse.online.movie.booking.system.Entity.BookingEntity;
import lk.ijse.online.movie.booking.system.Entity.PaymentEntity;
import lk.ijse.online.movie.booking.system.Enum.BookingStatus;
import lk.ijse.online.movie.booking.system.Enum.PaymentStatus;
import lk.ijse.online.movie.booking.system.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl  implements PaymentService {
    private final PaymentDAO paymentDAO;
    private final BookingDAO bookingDAO;

    @Override
    @Transactional
    public PaymentDTO processPayment(PaymentDTO paymentDTO) {
        BookingEntity booking = bookingDAO.findById(paymentDTO.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + paymentDTO.getBookingId()));

        PaymentEntity payment = PaymentEntity.builder()
                .booking(booking)
                .amount(booking.getTotalPrice())
                .paymentMethod(paymentDTO.getPaymentMethod())
                .status(PaymentStatus.SUCCESS)
                .transactionId(UUID.randomUUID().toString())
                .paymentTime(LocalDateTime.now())
                .build();

        PaymentEntity savedPayment = paymentDAO.save(payment);

        // Payment එක success වු පසු Booking Status එක CONFIRMED කරනු ලැබේ
        booking.setStatus(BookingStatus.CONFIRMED);
        bookingDAO.save(booking);

        return mapToDTO(savedPayment);
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        PaymentEntity payment = paymentDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
        return mapToDTO(payment);
    }

    @Override
    public PaymentDTO getPaymentByBookingId(Long bookingId) {
        PaymentEntity payment = paymentDAO.findByBookingId(bookingId)
                .orElseThrow(() -> new RuntimeException("Payment not found for booking id: " + bookingId));
        return mapToDTO(payment);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentDAO.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private PaymentDTO mapToDTO(PaymentEntity payment) {
        return PaymentDTO.builder()
                .id(payment.getId())
                .bookingId(payment.getBooking().getId())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .status(payment.getStatus())
                .transactionId(payment.getTransactionId())
                .paymentTime(payment.getPaymentTime())
                .build();
    }
}
