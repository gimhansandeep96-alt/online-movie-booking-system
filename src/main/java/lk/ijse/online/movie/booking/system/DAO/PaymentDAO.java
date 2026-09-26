package lk.ijse.online.movie.booking.system.DAO;

import lk.ijse.online.movie.booking.system.Entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentDAO extends JpaRepository<PaymentEntity,Long> {
    Optional<PaymentEntity> findByBookingId(Long bookingId);
}
