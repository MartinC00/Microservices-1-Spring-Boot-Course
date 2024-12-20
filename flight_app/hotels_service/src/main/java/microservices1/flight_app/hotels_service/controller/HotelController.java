package microservices1.flight_app.hotels_service.controller;

import lombok.RequiredArgsConstructor;
import microservices1.flight_app.hotels_service.model.Hotel;
import microservices1.flight_app.hotels_service.service.iHotelService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/hotels")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*") //zuul needs it
public class HotelController {

    private final iHotelService hotelService;

    @GetMapping
    public List<Hotel> getAllHotels(){
        return hotelService.getAllHotels();
    }
}
