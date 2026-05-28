package com.reserva.usecases.rules.sala;

import com.reserva.entities.Sala;
import com.reserva.usecases.dto.OperationResult;

public interface ReglaSala {
    OperationResult validar(Sala sala);
}
