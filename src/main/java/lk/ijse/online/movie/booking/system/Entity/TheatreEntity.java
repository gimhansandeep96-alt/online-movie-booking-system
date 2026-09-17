package lk.ijse.online.movie.booking.system.Entity;

import jakarta.persistence.*;
import lk.ijse.online.movie.booking.system.Enum.Theatre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theatres")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheatreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String location;

    private Integer totalCapacity;

    @Enumerated(EnumType.STRING)
    private Theatre type;


}
