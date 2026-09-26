package lk.ijse.online.movie.booking.system.Controller;

import lk.ijse.online.movie.booking.system.DTO.AuthDTO;
import lk.ijse.online.movie.booking.system.DTO.AuthResponseDTO;
import lk.ijse.online.movie.booking.system.DTO.UserDTO;
import lk.ijse.online.movie.booking.system.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(authService.register(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthDTO authDTO) {
        return ResponseEntity.ok(authService.login(authDTO));
    }
}
