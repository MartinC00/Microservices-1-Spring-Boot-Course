package microservices1.flight_app.reservations_service.service;

import microservices1.flight_app.reservations_service.model.Reservation;

import java.util.List;

public interface iReservationService {
    List<Reservation> getAllReservations();
    Reservation addReservation(Reservation reservation);
}
