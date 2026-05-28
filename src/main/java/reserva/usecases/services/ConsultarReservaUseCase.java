package reserva.usecases.services;

import reserva.entities.Reserva;
import reserva.entities.Sala;
import reserva.usecases.dto.OperationResult;
import reserva.usecases.ports.ReservaRepository;
import reserva.usecases.ports.SalaRepository;

public class ConsultarReservaUseCase {
    private final ReservaRepository reservaRepository;
    private final SalaRepository salaRepository;

    public ConsultarReservaUseCase(ReservaRepository reservaRepository, SalaRepository salaRepository) {
        this.reservaRepository = reservaRepository;
        this.salaRepository = salaRepository;
    }

    public OperationResult execute(String id) {
        if (id == null || id.trim().isEmpty()){
            return OperationResult.fail("Error: debe ingresar el ID de la reserva.");
        }

        Reserva reserva = reservaRepository.findById(id);
        if (reserva == null){
            return OperationResult.fail("Error: Reserva no encontrada.");
        }

        Sala sala = salaRepository.buscarPorId(reserva.getSalaId());

        String infoConcatenada = "Información de la reserva\n-------------------------\n"
                + "ID Reserva: " + reserva.getId() + "\n"
                + "Fecha: " + reserva.getFecha() + "\n"
                + "Horario: " + reserva.getHoraInicio() + " - " + reserva.getHoraFin() + "\n"
                + "Tipo de actividad: " + reserva.getTipoActividad() + "\n"
                + "Responsable: " + reserva.getResponsable() + "\n"
                + "Asistentes: " + reserva.getCantidadAsistentes() + "\n";

        if (sala != null) {
            infoConcatenada += "Sala asignada: " + sala.getNombre() + " (" + sala.getTipo() + ")\n" + "Ubicación: " + sala.getUbicacion() + "\n";
        }

        infoConcatenada += "Estado actual: " + (reserva.isCancelada() ? "CANCELADA" : "ACTIVA") + "\n";
        return OperationResult.ok(infoConcatenada);
    }
}