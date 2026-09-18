package lk.ijse.online.movie.booking.system.DAO;

import lk.ijse.online.movie.booking.system.Entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingDAO extends JpaRepository<BookingEntity, Long> {
    List<BookingEntity> findByUserId(Long userId);
    List<BookingEntity> findByShowId(Long showId);

}
