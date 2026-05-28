package reserva.usecases.rules.reserva;

import reserva.entities.Reserva;
import reserva.entities.Sala;
import reserva.usecases.dto.OperationResult;

public class ReglaHorario implements ReglaReserva {
    @Override
    public OperationResult validar(Reserva reserva, Sala sala) {
        int horaInicio = reserva.getHoraInicio();
        int horaFin = reserva.getHoraFin();
        if (horaInicio >= horaFin || horaInicio < 6 || horaFin > 22) {
            return OperationResult.fail("Horario inválido. Use horas entre 6 y 22.");
        }
        return OperationResult.ok("Horario válido");
    }
}
