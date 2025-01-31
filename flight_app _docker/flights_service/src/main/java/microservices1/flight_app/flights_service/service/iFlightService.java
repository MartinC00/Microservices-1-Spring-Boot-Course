package microservices1.flight_app.flights_service.service;

import microservices1.flight_app.flights_service.model.Flight;

import java.util.List;

public interface iFlightService {

    List<Flight> getAllAvailableFlights(int availableSeats);

    Flight updateFlightSeats(int idFlight, int requiredSeats);
}
