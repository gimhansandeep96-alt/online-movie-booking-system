package lk.ijse.online.movie.booking.system.Service;

import lk.ijse.online.movie.booking.system.DTO.TheatreDTO;

import java.util.List;

public interface TheatreService {
    TheatreDTO createTheatre(TheatreDTO theatreDTO);
    List<TheatreDTO> getAllTheatres();
    TheatreDTO getTheatreById(Long id);
    TheatreDTO updateTheatre(Long id, TheatreDTO theatreDTO);
    void deleteTheatre(Long id);
}
