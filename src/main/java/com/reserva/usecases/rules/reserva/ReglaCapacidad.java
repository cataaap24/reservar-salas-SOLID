package com.reserva.usecases.rules.reserva;

import com.reserva.entities.Reserva;
import com.reserva.entities.Sala;
import com.reserva.usecases.dto.OperationResult;

public class ReglaCapacidad implements ReglaReserva {
    @Override
    public OperationResult validar(Reserva reserva, Sala sala) {
        if (reserva.getCantidadAsistentes () > sala.getCapacidad () ) {
            return OperationResult.fail (
                    "La cantidad de asistentes supera la capacidad ."
            );
        }
        if (reserva.getCantidadAsistentes() <= 0) {
            return OperationResult.fail("La cantidad debe ser mayor o 0");
        }
        return OperationResult.ok("Regla cumplida.");
    }
}
