package com.reserva.usecases.rules.reserva;

import com.reserva.entities.Reserva;
import com.reserva.entities.Sala;
import com.reserva.usecases.dto.OperationResult;
import com.reserva.usecases.ports.ReservaRepository;

public class ReglaReservaIdUnico implements ReglaReserva {
    private final ReservaRepository reservaRepository;

    public ReglaReservaIdUnico(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public OperationResult validar(Reserva reserva, Sala sala) {
        if (reservaRepository.findById(reserva.getId()) != null) {
            return OperationResult.fail("Ya hay una reserva asignada con este ID");
        }
        return OperationResult.ok("Id válido");
    }
}
