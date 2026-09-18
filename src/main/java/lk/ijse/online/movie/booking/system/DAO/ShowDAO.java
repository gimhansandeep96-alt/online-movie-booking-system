package lk.ijse.online.movie.booking.system.DAO;

import lk.ijse.online.movie.booking.system.Entity.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowDAO  extends JpaRepository<ShowEntity, Long> {
    List<ShowEntity> findByMovieId(Long movieId);
    List<ShowEntity> findByTheatreId(Long theatreId);
}
