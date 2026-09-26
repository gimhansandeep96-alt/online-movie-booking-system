package lk.ijse.online.movie.booking.system.Service.impl;

import lk.ijse.online.movie.booking.system.Config.JWTUtil;
import lk.ijse.online.movie.booking.system.DAO.UserDAO;
import lk.ijse.online.movie.booking.system.DTO.AuthDTO;
import lk.ijse.online.movie.booking.system.DTO.AuthResponseDTO;
import lk.ijse.online.movie.booking.system.DTO.UserDTO;
import lk.ijse.online.movie.booking.system.Entity.UserEntity;
import lk.ijse.online.movie.booking.system.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lk.ijse.online.movie.booking.system.Enum.role;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl  implements AuthService {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public AuthResponseDTO register(UserDTO userDTO) {
        if (userDAO.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email already registered!");
        }

        UserEntity user = UserEntity.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .role(userDTO.getRole() != null ? userDTO.getRole() : role.USER)
                .build();

        userDAO.save(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtUtil.generateToken(userDetails, user.getRole().name());

        return AuthResponseDTO.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

    @Override
    public AuthResponseDTO login(AuthDTO authDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword())
        );

        UserEntity user = userDAO.findByEmail(authDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtUtil.generateToken(userDetails, user.getRole().name());

        return AuthResponseDTO.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}
