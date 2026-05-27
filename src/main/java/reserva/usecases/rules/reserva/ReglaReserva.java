package reserva.usecases.rules.reserva;

import reserva.entities.Reserva;
import reserva.usecases.dto.OperationResult;
import reserva.entities.Sala;

public interface ReglaReserva {
    OperationResult validar(Reserva reserva, Sala sala);
}
