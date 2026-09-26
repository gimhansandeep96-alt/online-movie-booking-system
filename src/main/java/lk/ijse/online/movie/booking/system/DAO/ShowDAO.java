package lk.ijse.online.movie.booking.system.DAO;

import lk.ijse.online.movie.booking.system.Entity.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShowDAO  extends JpaRepository<ShowEntity, Long> {
    List<ShowEntity> findByMovieId(Long movieId);
    List<ShowEntity> findByTheatreId(Long theatreId);
}
