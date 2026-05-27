package reserva.usecases.ports;

import reserva.entities.Reserva;

import java.util.List;

public interface ReservaRepository {
    Reserva findById (String id);
    Reserva findByIdYFecha(String id, String fecha);
    void save ( Reserva reserva );

    List<Reserva> findAll();
}
