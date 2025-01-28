package microservices1.flight_app.reservations_service.controller;

import lombok.RequiredArgsConstructor;
import microservices1.flight_app.reservations_service.model.Reservation;
import microservices1.flight_app.reservations_service.service.iReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/reservations")
@CrossOrigin(origins = "*") //zuul needs it
public class ReservationController {

    private final iReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation){
        return ResponseEntity.ok(reservationService.addReservation(reservation));
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations(){
        return ResponseEntity.ok(reservationService.getAllReservations());
    }
}
