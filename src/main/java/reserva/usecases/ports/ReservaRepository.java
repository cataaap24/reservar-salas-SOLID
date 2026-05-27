package reserva.usecases.ports;

import reserva.entities.Reserva;

public interface ReservaRepository {
    Reserva findById (String id);
    Reserva findByIdYFecha(String id, String fecha);
    void save ( Reserva reserva );
}
