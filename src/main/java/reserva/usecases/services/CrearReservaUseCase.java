package reserva.usecases.services;

import reserva.entities.Reserva;
import reserva.entities.Sala;
import java.util.List;
import reserva.usecases.dto.OperationResult;
import reserva.usecases.ports.ReservaRepository;
import reserva.usecases.ports.SalaRepository;
import reserva.usecases.rules.reserva.ReglaReserva;

public class CrearReservaUseCase {
    private final SalaRepository salaRepository;
    private final ReservaRepository reservaRepository;
    private final List<ReglaReserva> reglas;

    public CrearReservaUseCase(SalaRepository salaRepository, ReservaRepository reservaRepository, List<ReglaReserva> reglas) {
        this.salaRepository = salaRepository;
        this.reservaRepository = reservaRepository;
        this.reglas = reglas;
    }

    public OperationResult execute(Reserva reserva) {
        if (reserva.getId() == null || reserva.getId().trim().isEmpty()){
            return OperationResult.fail("Error: el ID de la reserva no puede estar vacío.");
        }
        if (reservaRepository.findById(reserva.getId()) != null){
            return OperationResult.fail("Error: ya existe una reserva con ese ID.");
        }

        Sala sala = salaRepository.buscarPorId(reserva.getSalaId());
        if (sala == null){
            return OperationResult.fail("Error: la sala no existe.");
        }
        if (!sala.isActiva()){
            return OperationResult.fail("Error: la sala no está activa.");
        }
        if (reserva.getFecha() == null || reserva.getFecha().trim().isEmpty()){
            return OperationResult.fail("Error: la fecha no puede estar vacía.");
        }
        if (reserva.getResponsable() == null || reserva.getResponsable().trim().isEmpty()){
            return OperationResult.fail("Error: el responsable no puede estar vacío.");
        }

        for (ReglaReserva regla : reglas) {
            OperationResult result = regla.validar(reserva, sala);
            if (!result.isSuccess()) return result;
        }

        reservaRepository.save(reserva);
        return OperationResult.ok("Reserva creada correctamente.");
    }
}