package lk.ijse.online.movie.booking.system.DAO;

import lk.ijse.online.movie.booking.system.Entity.TheatreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreDAO extends JpaRepository<TheatreEntity, Long> {
}
