package microservices1.flight_app.hotels_service.service;

import lombok.RequiredArgsConstructor;
import microservices1.flight_app.hotels_service.dao.HotelDAOJpaImpl;
import microservices1.flight_app.hotels_service.model.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements iHotelService {

    private final HotelDAOJpaImpl hotelDAO;

    @Override
    public List<Hotel> getAllHotels() {
        return hotelDAO.getAllHotels()
                .stream()
                .filter(Hotel::isAvailable)
                .collect(Collectors.toList());
    }
}
