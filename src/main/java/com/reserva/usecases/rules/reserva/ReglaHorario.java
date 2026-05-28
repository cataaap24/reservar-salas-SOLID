package com.reserva.usecases.rules.reserva;

import com.reserva.entities.Reserva;
import com.reserva.entities.Sala;
import com.reserva.usecases.dto.OperationResult;

public class ReglaHorario implements ReglaReserva {
    @Override
    public OperationResult validar(Reserva reserva, Sala sala) {
        int horaInicio = reserva.getHoraInicio();
        int horaFin = reserva.getHoraFin();
        if (horaInicio >= horaFin || horaInicio < 6 || horaFin > 22) {
            return OperationResult.fail("Horario inválido. Use horas entre 6 y 22.");
        }
        return OperationResult.ok("Horario válido");
    }
}
