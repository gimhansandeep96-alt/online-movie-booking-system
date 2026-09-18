package lk.ijse.online.movie.booking.system.Service.impl;

import lk.ijse.online.movie.booking.system.DAO.MovieDAO;
import lk.ijse.online.movie.booking.system.DAO.ShowDAO;
import lk.ijse.online.movie.booking.system.DAO.TheatreDAO;
import lk.ijse.online.movie.booking.system.DTO.ShowDTO;
import lk.ijse.online.movie.booking.system.Entity.MovieEntity;
import lk.ijse.online.movie.booking.system.Entity.ShowEntity;
import lk.ijse.online.movie.booking.system.Entity.TheatreEntity;
import lk.ijse.online.movie.booking.system.Service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {
    private final ShowDAO showDAO;
    private final MovieDAO movieDAO;
    private final TheatreDAO theatreDAO;

    @Override
    public ShowDTO createShow(ShowDTO showDTO) {
        ShowEntity show = mapToEntity(showDTO);
        ShowEntity savedShow = showDAO.save(show);
        return mapToDTO(savedShow);
    }

    @Override
    public List<ShowDTO> getAllShows() {
        return showDAO.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ShowDTO getShowById(Long id) {
        ShowEntity show = showDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Show not found with id: " + id));
        return mapToDTO(show);
    }

    @Override
    public List<ShowDTO> getShowsByMovieId(Long movieId) {
        return showDAO.findByMovieId(movieId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowDTO> getShowsByTheatreId(Long theatreId) {
        return showDAO.findByTheatreId(theatreId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ShowDTO updateShow(Long id, ShowDTO showDTO) {
        ShowEntity existingShow = showDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Show not found with id: " + id));

        MovieEntity movie = movieDAO.findById(showDTO.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + showDTO.getMovieId()));

        TheatreEntity theatre = theatreDAO.findById(showDTO.getTheatreId())
                .orElseThrow(() -> new RuntimeException("Theatre not found with id: " + showDTO.getTheatreId()));

        existingShow.setMovie(movie);
        existingShow.setTheatre(theatre);
        existingShow.setStartTime(showDTO.getStartTime());
        existingShow.setEndTime(showDTO.getEndTime());
        existingShow.setTicketPrice(showDTO.getTicketPrice());
        existingShow.setStatus(showDTO.getStatus());

        ShowEntity updatedShow = showDAO.save(existingShow);
        return mapToDTO(updatedShow);
    }

    @Override
    public void deleteShow(Long id) {
        if (!showDAO.existsById(id)) {
            throw new RuntimeException("Show not found with id: " + id);
        }
        showDAO.deleteById(id);
    }

    private ShowDTO mapToDTO(ShowEntity show) {
        return ShowDTO.builder()
                .id(show.getId())
                .movieId(show.getMovie().getId())
                .theatreId(show.getTheatre().getId())
                .startTime(show.getStartTime())
                .endTime(show.getEndTime())
                .ticketPrice(show.getTicketPrice())
                .status(show.getStatus())
                .build();
    }

    private ShowEntity mapToEntity(ShowDTO dto) {
        MovieEntity movie = movieDAO.findById(dto.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + dto.getMovieId()));

        TheatreEntity theatre = theatreDAO.findById(dto.getTheatreId())
                .orElseThrow(() -> new RuntimeException("Theatre not found with id: " + dto.getTheatreId()));

        return ShowEntity.builder()
                .id(dto.getId())
                .movie(movie)
                .theatre(theatre)
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .ticketPrice(dto.getTicketPrice())
                .status(dto.getStatus())
                .build();
    }


}
