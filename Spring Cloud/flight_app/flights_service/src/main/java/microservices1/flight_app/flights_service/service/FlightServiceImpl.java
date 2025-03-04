package microservices1.flight_app.flights_service.service;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import microservices1.flight_app.flights_service.dao.iFlightDAO;
import microservices1.flight_app.flights_service.model.Flight;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements iFlightService{

    private final iFlightDAO flightDAO;

    @Override
    public List<Flight> getAllAvailableFlights(int requiredSeats) {
        return flightDAO.getAllFlights().stream()
                .filter(f -> f.getAvailableSeats() >= requiredSeats)
                .collect(Collectors.toList());
    }

    @Override
    public Flight updateFlightSeats(int idFlight, int requiredSeats) {
        Flight flight = flightDAO.getFlightById(idFlight);
        if(flight != null){
            flight.setAvailableSeats(flight.getAvailableSeats() - requiredSeats);
            return flightDAO.updateFlight(flight);
        }
        else throw new NotFoundException("Flight not found.");
    }
}
