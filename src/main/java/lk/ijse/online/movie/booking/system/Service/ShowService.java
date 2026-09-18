package lk.ijse.online.movie.booking.system.Service;

import lk.ijse.online.movie.booking.system.DTO.ShowDTO;

import java.util.List;

public interface ShowService {
    ShowDTO createShow(ShowDTO showDTO);
    List<ShowDTO> getAllShows();
    ShowDTO getShowById(Long id);
    List<ShowDTO> getShowsByMovieId(Long movieId);
    List<ShowDTO> getShowsByTheatreId(Long theatreId);
    ShowDTO updateShow(Long id, ShowDTO showDTO);
    void deleteShow(Long id);
}
