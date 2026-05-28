package com.reserva.usecases.rules.sala;

import com.reserva.entities.Sala;
import com.reserva.usecases.dto.OperationResult;
import com.reserva.usecases.ports.SalaRepository;

public class ReglaSalaIdUnico implements ReglaSala {
    private final SalaRepository salaRepository;
    public ReglaSalaIdUnico(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }
    @Override
    public OperationResult validar(Sala sala) {
        if (salaRepository.buscarPorId(sala.getId()) != null) {
            return OperationResult.fail("Sala existente");
        }
        return OperationResult.ok("Id válido");
    }
}
