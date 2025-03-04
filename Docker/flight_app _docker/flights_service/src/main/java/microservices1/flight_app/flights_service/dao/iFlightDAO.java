package microservices1.flight_app.flights_service.dao;

import microservices1.flight_app.flights_service.model.Flight;

import java.util.List;

public interface iFlightDAO {
    List<Flight> getAllFlights();
    Flight getFlightById(int idFlight);
    Flight updateFlight(Flight flight);
}
