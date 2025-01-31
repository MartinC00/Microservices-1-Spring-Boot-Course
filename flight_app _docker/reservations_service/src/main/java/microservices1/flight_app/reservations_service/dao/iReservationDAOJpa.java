package microservices1.flight_app.reservations_service.dao;

import microservices1.flight_app.reservations_service.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iReservationDAOJpa extends JpaRepository<Reservation, Integer> {
}
