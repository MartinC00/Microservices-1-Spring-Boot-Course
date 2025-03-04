package microservices1.flight_app.hotels_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "hotels")
@Data
public class Hotel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idHotel;
    private String name;
    private int category;
    private double price;
    private boolean available;
}
