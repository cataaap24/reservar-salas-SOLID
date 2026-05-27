package reserva.usecases.rules.reserva;

import reserva.entities.Reserva;
import reserva.entities.Sala;
import reserva.usecases.dto.OperationResult;
import reserva.usecases.ports.ReservaRepository;

import java.util.ArrayList;
import java.util.List;

public class ReglaDisponibilidad implements ReglaReserva {
    private final ReservaRepository reservaRepository;

    public  ReglaDisponibilidad(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public OperationResult validar(Reserva reserva, Sala sala) {
        String id = reserva.getId();
        String fecha = reserva.getFecha();
        Reserva existente = reservaRepository.findByIdYFecha(id, fecha);
        if (!reserva.isCancelada() && existente != null) {
            //Se cruzan
            if (reserva.getHoraInicio() < existente.getHoraFin()
                    && reserva.getHoraFin() > existente.getHoraInicio()) {
                return OperationResult.fail("La sala ya tiene una reserva en ese horario");
            }
        }
        return OperationResult.ok("Hay disponibilidad de la sala");
    }
}
