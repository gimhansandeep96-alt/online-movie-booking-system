package lk.ijse.online.movie.booking.system.Service.impl;

import lk.ijse.online.movie.booking.system.DAO.MovieDAO;
import lk.ijse.online.movie.booking.system.DTO.MovieDTO;
import lk.ijse.online.movie.booking.system.Entity.MovieEntity;
import lk.ijse.online.movie.booking.system.Service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieDAO movieDAO;

    @Override
    public MovieDTO createMovie(MovieDTO movieDTO) {
        MovieEntity movie = mapToEntity(movieDTO);
        MovieEntity savedMovie = movieDAO.save(movie);
        return mapToDTO(savedMovie);
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        return movieDAO.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MovieDTO getMovieById(Long id) {
        MovieEntity movie = movieDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
        return mapToDTO(movie);
    }

    @Override
    public MovieDTO updateMovie(Long id, MovieDTO movieDTO) {
        MovieEntity existingMovie = movieDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));

        existingMovie.setTitle(movieDTO.getTitle());
        existingMovie.setDescription(movieDTO.getDescription());
        existingMovie.setDuration(movieDTO.getDuration());
        existingMovie.setLanguage(movieDTO.getLanguage());
        existingMovie.setGenre(movieDTO.getGenre());
        existingMovie.setReleaseDate(movieDTO.getReleaseDate());
        existingMovie.setStatus(movieDTO.getStatus());

        MovieEntity updatedMovie = movieDAO.save(existingMovie);
        return mapToDTO(updatedMovie);
    }

    @Override
    public void deleteMovie(Long id) {
        if (!movieDAO.existsById(id)) {
            throw new RuntimeException("Movie not found with id: " + id);
        }
        movieDAO.deleteById(id);
    }

    private MovieDTO mapToDTO(MovieEntity movie) {
        return MovieDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .duration(movie.getDuration())
                .language(movie.getLanguage())
                .genre(movie.getGenre())
                .releaseDate(movie.getReleaseDate())
                .status(movie.getStatus())
                .build();
    }

    private MovieEntity mapToEntity(MovieDTO dto) {
        return MovieEntity.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .duration(dto.getDuration())
                .language(dto.getLanguage())
                .genre(dto.getGenre())
                .releaseDate(dto.getReleaseDate())
                .status(dto.getStatus())
                .build();
    }
}
