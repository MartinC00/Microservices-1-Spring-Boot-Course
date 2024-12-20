package microservices1.flight_app.reservations_service.dao;

import microservices1.flight_app.reservations_service.model.Reservation;

import java.util.List;

public interface iReservationDAO {
    Reservation addReservation(Reservation reservation);
    List<Reservation> getAllReservations();
}
