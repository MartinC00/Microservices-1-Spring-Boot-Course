package microservices1.flight_app.flights_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
@Data
public class Flight {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idFlight;
    private String company;
    private LocalDateTime date;
    private double price;
    private int availableSeats;
}
