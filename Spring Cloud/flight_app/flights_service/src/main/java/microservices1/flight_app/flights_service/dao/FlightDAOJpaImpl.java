package microservices1.flight_app.flights_service.dao;

import lombok.RequiredArgsConstructor;
import microservices1.flight_app.flights_service.model.Flight;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FlightDAOJpaImpl implements iFlightDAO{

    private final iFlightDAOJpa flightDAOJpa;

    @Override
    public List<Flight> getAllFlights() {
        return flightDAOJpa.findAll();
    }

    @Override
    public Flight updateFlight(Flight flight) {
        return flightDAOJpa.save(flight);
    }

    @Override
    public Flight getFlightById(int idFlight) {
        return flightDAOJpa.findById(idFlight).orElse(null);
    }
}
