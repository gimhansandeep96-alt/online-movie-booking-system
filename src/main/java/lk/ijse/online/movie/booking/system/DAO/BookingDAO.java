package lk.ijse.online.movie.booking.system.DAO;

import lk.ijse.online.movie.booking.system.Entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookingDAO extends JpaRepository<BookingEntity, Long> {
    List<BookingEntity> findByUserId(Long userId);
    List<BookingEntity> findByShowId(Long showId);

}
