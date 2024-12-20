package microservices1.flight_app.reservations_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reservations")
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReservation;
    private int idHotel;
    private int idFlight;
    private String name;
    private String dni;
    private int numberOfPassengers;
}
