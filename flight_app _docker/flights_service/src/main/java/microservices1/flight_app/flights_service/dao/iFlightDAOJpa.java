package microservices1.flight_app.flights_service.dao;

import microservices1.flight_app.flights_service.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iFlightDAOJpa extends JpaRepository<Flight, Integer> {
}
