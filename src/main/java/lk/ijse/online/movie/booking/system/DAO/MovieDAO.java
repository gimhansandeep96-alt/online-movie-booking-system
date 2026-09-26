package lk.ijse.online.movie.booking.system.DAO;

import lk.ijse.online.movie.booking.system.Entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDAO extends JpaRepository<MovieEntity, Long> {
}
