package lk.ijse.online.movie.booking.system.Service.impl;

import lk.ijse.online.movie.booking.system.DAO.BookingDAO;
import lk.ijse.online.movie.booking.system.DAO.ShowDAO;
import lk.ijse.online.movie.booking.system.DAO.UserDAO;
import lk.ijse.online.movie.booking.system.DTO.BookingDTO;
import lk.ijse.online.movie.booking.system.Entity.BookingEntity;
import lk.ijse.online.movie.booking.system.Entity.ShowEntity;
import lk.ijse.online.movie.booking.system.Entity.UserEntity;
import lk.ijse.online.movie.booking.system.Enum.BookingStatus;
import lk.ijse.online.movie.booking.system.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingDAO bookingDAO;
    private final UserDAO userDAO;
    private final ShowDAO showDAO;

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        UserEntity user = userDAO.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + bookingDTO.getUserId()));

        ShowEntity show = showDAO.findById(bookingDTO.getShowId())
                .orElseThrow(() -> new RuntimeException("Show not found with id: " + bookingDTO.getShowId()));

        Double calculatedTotal = show.getTicketPrice() * bookingDTO.getNumberOfSeats();

        BookingEntity booking = BookingEntity.builder()
                .user(user)
                .show(show)
                .numberOfSeats(bookingDTO.getNumberOfSeats())
                .totalPrice(calculatedTotal)
                .bookingTime(LocalDateTime.now())
                .status(BookingStatus.PENDING)
                .build();

        BookingEntity savedBooking = bookingDAO.save(booking);
        return mapToDTO(savedBooking);
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        return bookingDAO.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookingDTO getBookingById(Long id) {
        BookingEntity booking = bookingDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        return mapToDTO(booking);
    }

    @Override
    public List<BookingDTO> getBookingsByUserId(Long userId) {
        return bookingDAO.findByUserId(userId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> getBookingsByShowId(Long showId) {
        return bookingDAO.findByShowId(showId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookingDTO updateBookingStatus(Long id, String status) {
        BookingEntity booking = bookingDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        booking.setStatus(BookingStatus.valueOf(status.toUpperCase()));
        BookingEntity updatedBooking = bookingDAO.save(booking);
        return mapToDTO(updatedBooking);
    }

    @Override
    public void cancelBooking(Long id) {
        BookingEntity booking = bookingDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        booking.setStatus(BookingStatus.CANCELLED);
        bookingDAO.save(booking);
    }

    private BookingDTO mapToDTO(BookingEntity booking) {
        return BookingDTO.builder()
                .id(booking.getId())
                .userId(booking.getUser().getId())
                .showId(booking.getShow().getId())
                .numberOfSeats(booking.getNumberOfSeats())
                .totalPrice(booking.getTotalPrice())
                .bookingTime(booking.getBookingTime())
                .status(booking.getStatus())
                .build();
    }
}
