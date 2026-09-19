package lk.ijse.online.movie.booking.system.DAO;

import lk.ijse.online.movie.booking.system.Entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentDAO extends JpaRepository<PaymentEntity,Long> {
    Optional<PaymentEntity> findByBookingId(Long bookingId);
}
