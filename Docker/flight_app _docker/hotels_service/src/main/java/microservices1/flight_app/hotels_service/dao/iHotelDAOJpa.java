package microservices1.flight_app.hotels_service.dao;

import microservices1.flight_app.hotels_service.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iHotelDAOJpa extends JpaRepository<Hotel, Integer> {

}
