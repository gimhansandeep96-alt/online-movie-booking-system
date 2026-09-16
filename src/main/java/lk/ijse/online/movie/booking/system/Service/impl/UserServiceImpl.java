package lk.ijse.online.movie.booking.system.Service.impl;

import lk.ijse.online.movie.booking.system.DAO.UserDAO;
import lk.ijse.online.movie.booking.system.DTO.UserDTO;
import lk.ijse.online.movie.booking.system.Entity.UserEntity;
import lk.ijse.online.movie.booking.system.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Override
    public List<UserDTO> getAllUsers() {
        return userDAO.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        UserEntity user = userDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return mapToDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        UserEntity user = userDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setName(userDTO.getName());
        UserEntity updatedUser = userDAO.save(user);
        return mapToDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userDAO.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userDAO.deleteById(id);
    }

    private UserDTO mapToDTO(UserEntity user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
