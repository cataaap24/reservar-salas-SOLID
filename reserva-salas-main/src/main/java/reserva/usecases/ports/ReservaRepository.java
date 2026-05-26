package java.reserva.usecases.ports;

import java.reserva.entities.Reserva;

public interface ReservaRepository {
    Reserva findById (String id);
    void save ( Reserva reserva );
}
