package microservices1.flight_app.reservations_service.dao;

import lombok.RequiredArgsConstructor;
import microservices1.flight_app.reservations_service.model.Reservation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationDAOJpaImpl implements iReservationDAO{

    private final iReservationDAOJpa reservationDAOJpa;

    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationDAOJpa.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationDAOJpa.findAll();
    }
}
