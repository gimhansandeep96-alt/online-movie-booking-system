package lk.ijse.online.movie.booking.system.Service.impl;

import lk.ijse.online.movie.booking.system.DAO.TheatreDAO;
import lk.ijse.online.movie.booking.system.DTO.TheatreDTO;
import lk.ijse.online.movie.booking.system.Entity.TheatreEntity;
import lk.ijse.online.movie.booking.system.Service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TheatreServiceIMPL implements TheatreService {
    private final TheatreDAO theatreDAO;

    @Override
    public TheatreDTO createTheatre(TheatreDTO theatreDTO) {
        TheatreEntity theatre = mapToEntity(theatreDTO);
        TheatreEntity savedTheatre = theatreDAO.save(theatre);
        return mapToDTO(savedTheatre);
    }

    @Override
    public List<TheatreDTO> getAllTheatres() {
        return theatreDAO.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TheatreDTO getTheatreById(Long id) {
        TheatreEntity theatre = theatreDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Theatre not found with id: " + id));
        return mapToDTO(theatre);
    }

    @Override
    public TheatreDTO updateTheatre(Long id, TheatreDTO theatreDTO) {
        TheatreEntity existingTheatre = theatreDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Theatre not found with id: " + id));

        existingTheatre.setName(theatreDTO.getName());
        existingTheatre.setLocation(theatreDTO.getLocation());
        existingTheatre.setTotalCapacity(theatreDTO.getTotalCapacity());
        existingTheatre.setType(theatreDTO.getType());

        TheatreEntity updatedTheatre = theatreDAO.save(existingTheatre);
        return mapToDTO(updatedTheatre);
    }

    @Override
    public void deleteTheatre(Long id) {
        if (!theatreDAO.existsById(id)) {
            throw new RuntimeException("Theatre not found with id: " + id);
        }
        theatreDAO.deleteById(id);
    }

    private TheatreDTO mapToDTO(TheatreEntity theatre) {
        return TheatreDTO.builder()
                .id(theatre.getId())
                .name(theatre.getName())
                .location(theatre.getLocation())
                .totalCapacity(theatre.getTotalCapacity())
                .type(theatre.getType())
                .build();
    }

    private TheatreEntity mapToEntity(TheatreDTO dto) {
        return TheatreEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .location(dto.getLocation())
                .totalCapacity(dto.getTotalCapacity())
                .type(dto.getType())
                .build();
    }
}
