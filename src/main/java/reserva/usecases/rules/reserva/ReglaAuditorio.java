package reserva.usecases.rules.reserva;

import reserva.entities.Reserva;
import reserva.entities.Sala;
import reserva.usecases.dto.OperationResult;

public class ReglaAuditorio implements ReglaReserva {
    @Override
    public OperationResult validar(Reserva reserva, Sala sala) {
        if (reserva.getCantidadAsistentes() > 30) {
            return OperationResult.fail("El auditorio requiere mínimo 30 asistentes.");
        }
        return OperationResult.ok("Cantidad de asistentes válida");
    }
}
