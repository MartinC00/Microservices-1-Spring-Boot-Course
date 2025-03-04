package microservices1.flight_app.reservations_service.service;

import lombok.RequiredArgsConstructor;
import microservices1.flight_app.reservations_service.dao.iReservationDAO;
import microservices1.flight_app.reservations_service.model.Reservation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements iReservationService {

    private final iReservationDAO reservationDAO;
    private final RestTemplate restTemplate;
    private final String flightsServiceUrl = "http://flights-service/api/flights";

    @Override
    public List<Reservation> getAllReservations() {
        return reservationDAO.getAllReservations();
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        String completeUrl = flightsServiceUrl + "/" + reservation.getIdFlight() + "/requiredSeats";

        Map<String,Integer> body = Map.of("requiredSeats", reservation.getNumberOfPassengers());

        restTemplate.exchange(completeUrl, HttpMethod.PATCH, new HttpEntity<>(body), Void.class);

        return reservationDAO.addReservation(reservation);
    }
}
