package microservices1.flight_app.hotels_service.dao;

import lombok.RequiredArgsConstructor;
import microservices1.flight_app.hotels_service.model.Hotel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HotelDAOJpaImpl implements iHotelDAO{

    private final iHotelDAOJpa hotelDAOJpa;

    @Override
    public List<Hotel> getAllHotels() {
        return hotelDAOJpa.findAll();
    }
}
