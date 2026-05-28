package com.reserva.usecases.rules.reserva;

import com.reserva.entities.Reserva;
import com.reserva.entities.Sala;
import com.reserva.usecases.dto.OperationResult;

public class ReglaLaboratorio implements ReglaReserva{
    @Override
    public OperationResult validar(Reserva reserva, Sala sala) {
        if (!reserva.getTipoActividad().equals("PRACTICA")) {
            return OperationResult.fail("El laboratorio solo se puede reservar para actividades de tipo PRACTICA.");
        }
        return OperationResult.ok("Tipo de actividad válida");
    }
}
