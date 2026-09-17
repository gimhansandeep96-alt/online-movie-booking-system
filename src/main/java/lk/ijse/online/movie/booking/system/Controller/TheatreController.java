package lk.ijse.online.movie.booking.system.Controller;

import lk.ijse.online.movie.booking.system.DTO.TheatreDTO;
import lk.ijse.online.movie.booking.system.Service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatres")
@RequiredArgsConstructor
public class TheatreController {
    private final TheatreService theatreService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TheatreDTO> createTheatre(@RequestBody TheatreDTO theatreDTO) {
        return new ResponseEntity<>(theatreService.createTheatre(theatreDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TheatreDTO>> getAllTheatres() {
        return ResponseEntity.ok(theatreService.getAllTheatres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TheatreDTO> getTheatreById(@PathVariable Long id) {
        return ResponseEntity.ok(theatreService.getTheatreById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TheatreDTO> updateTheatre(@PathVariable Long id, @RequestBody TheatreDTO theatreDTO) {
        return ResponseEntity.ok(theatreService.updateTheatre(id, theatreDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTheatre(@PathVariable Long id) {
        theatreService.deleteTheatre(id);
        return ResponseEntity.noContent().build();
    }
}
