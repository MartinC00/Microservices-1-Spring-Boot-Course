package microservices1.flight_app.flights_service.controller;

import lombok.RequiredArgsConstructor;
import microservices1.flight_app.flights_service.model.Flight;
import microservices1.flight_app.flights_service.service.iFlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/flights")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*") // zuul needs it
public class FlightController {

    private final iFlightService flightService;

    @GetMapping
    public ResponseEntity<List<Flight>> getAllAvailableFlights(@RequestParam("availableSeats") int availableSeats){
        return ResponseEntity.ok(flightService.getAllAvailableFlights(availableSeats));
    }

    @PatchMapping("/{idFlight}/requiredSeats")
    public ResponseEntity<Flight> updateFlightSeats(@PathVariable int idFlight, @RequestBody Map<String, Integer> updateMap){
        return ResponseEntity.ok(flightService.updateFlightSeats(idFlight, updateMap.get("requiredSeats")));
    }

}
