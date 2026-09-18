package lk.ijse.online.movie.booking.system.Service;

import lk.ijse.online.movie.booking.system.DTO.BookingDTO;

import java.util.List;

public interface BookingService {
    BookingDTO createBooking(BookingDTO bookingDTO);
    List<BookingDTO> getAllBookings();
    BookingDTO getBookingById(Long id);
    List<BookingDTO> getBookingsByUserId(Long userId);
    List<BookingDTO> getBookingsByShowId(Long showId);
    BookingDTO updateBookingStatus(Long id, String status);
    void cancelBooking(Long id);
}
