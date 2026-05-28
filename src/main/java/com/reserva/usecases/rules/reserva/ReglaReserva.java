package com.reserva.usecases.rules.reserva;

import com.reserva.entities.Reserva;
import com.reserva.usecases.dto.OperationResult;
import com.reserva.entities.Sala;

public interface ReglaReserva {
    OperationResult validar(Reserva reserva, Sala sala);
}
