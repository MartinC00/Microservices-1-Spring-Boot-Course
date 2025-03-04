package microservices1.flight_app.hotels_service.dao;

import microservices1.flight_app.hotels_service.model.Hotel;

import java.util.List;

public interface iHotelDAO {
    List<Hotel> getAllHotels();
}
