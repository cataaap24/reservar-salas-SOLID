package com.reserva.usecases.rules.reserva;

import com.reserva.entities.Reserva;
import com.reserva.entities.Sala;
import com.reserva.usecases.dto.OperationResult;

public class ReglaAuditorio implements ReglaReserva {
    @Override
    public OperationResult validar(Reserva reserva, Sala sala) {
        if (reserva.getCantidadAsistentes() > 30) {
            return OperationResult.fail("El auditorio requiere mínimo 30 asistentes.");
        }
        return OperationResult.ok("Cantidad de asistentes válida");
    }
}
