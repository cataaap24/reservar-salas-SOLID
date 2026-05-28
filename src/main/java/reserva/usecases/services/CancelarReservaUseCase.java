package reserva.usecases.services;

import reserva.entities.Reserva;
import reserva.usecases.dto.OperationResult;
import reserva.usecases.ports.ReservaRepository;

public class CancelarReservaUseCase {
    private final ReservaRepository reservaRepository;

    public CancelarReservaUseCase(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public OperationResult execute(String id) {
        if (id == null || id.trim().isEmpty()){
            return OperationResult.fail("Error: el ID no puede estar vacío.");
        }

        Reserva reserva = reservaRepository.findById(id);
        if (reserva == null){
            return OperationResult.fail("No se puede cancelar. La reserva no existe.");
        }
        if (reserva.isCancelada()) {
            return OperationResult.fail("No se puede cancelar. La reserva ya se encuentra cancelada.");
        }

        reserva.setCancelada(true);
        return OperationResult.ok("Reserva cancelada correctamente.");
    }
}