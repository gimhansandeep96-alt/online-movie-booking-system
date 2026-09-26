package lk.ijse.online.movie.booking.system.Service;

import lk.ijse.online.movie.booking.system.DTO.AuthDTO;
import lk.ijse.online.movie.booking.system.DTO.AuthResponseDTO;
import lk.ijse.online.movie.booking.system.DTO.UserDTO;

public interface AuthService {
    AuthResponseDTO register(UserDTO userDTO);
    AuthResponseDTO login(AuthDTO authDTO);
}
