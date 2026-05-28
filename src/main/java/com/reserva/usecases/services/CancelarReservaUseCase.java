package com.reserva.usecases.services;

import com.reserva.entities.Reserva;
import com.reserva.usecases.dto.OperationResult;
import com.reserva.usecases.ports.ReservaRepository;
import com.reserva.usecases.ports.SalaRepository;

public class CancelarReservaUseCase {
    private final ReservaRepository reservaRepository;

    public CancelarReservaUseCase(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public OperationResult ejecutar(String id) {
        //Verificar campos
        if (id == null || id.isBlank()) return OperationResult.fail("No se puede cancelar. La reserva no existe");

        Reserva reserva = reservaRepository.findById(id);

        if (reserva == null) return OperationResult.fail("Reserva no encontrada");

        //Verificar que no este cancelada previamente
        if (reserva.isCancelada()) return OperationResult.fail("La reserva ya esta cancelada");

        reserva.setCancelada(true);
        reservaRepository.guardar(reserva);

        return OperationResult.ok("Reserva cancelada correctamente");
    }
}
