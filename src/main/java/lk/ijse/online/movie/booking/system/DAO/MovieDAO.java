package lk.ijse.online.movie.booking.system.DAO;

import lk.ijse.online.movie.booking.system.Entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDAO extends JpaRepository<MovieEntity, Long> {
}
