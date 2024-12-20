package microservices1.flight_app.hotels_service.service;

import microservices1.flight_app.hotels_service.model.Hotel;
import java.util.List;

public interface iHotelService {
    List<Hotel> getAllHotels();
}
