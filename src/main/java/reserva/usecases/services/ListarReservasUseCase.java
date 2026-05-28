package reserva.usecases.services;

import reserva.entities.Reserva;
import reserva.usecases.dto.OperationResult;
import reserva.usecases.ports.ReservaRepository;
import java.util.List;

public class ListarReservasUseCase {
    private final ReservaRepository reservaRepository;

    public ListarReservasUseCase(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public OperationResult execute() {
        List<Reserva> reservas = reservaRepository.findAll();
        if (reservas.isEmpty()) {
            return OperationResult.fail("No hay reservas registradas en el sistema.");
        }

        String info = "--- LISTADO DE RESERVAS REGISTRADAS ---\n";
        for (Reserva r : reservas) {
            info += "ID: " + r.getId() + " | Sala ID: " + r.getSalaId() + " | Fecha: " + r.getFecha() + " | Horario: " + r.getHoraInicio() + ":00-" + r.getHoraFin() + ":00 | Actividad: " + r.getTipoActividad() + " | Estado: " + (r.isCancelada() ? "CANCELADA" : "ACTIVA") + "\n";
        }

        return OperationResult.ok(info);
    }
}