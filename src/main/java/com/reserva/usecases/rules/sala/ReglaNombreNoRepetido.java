package com.reserva.usecases.rules.sala;

import com.reserva.entities.Sala;
import com.reserva.usecases.dto.OperationResult;
import com.reserva.usecases.ports.SalaRepository;

public class ReglaNombreNoRepetido implements ReglaSala {
    private final SalaRepository salaRepository;

    public ReglaNombreNoRepetido(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    @Override
    public OperationResult validar(Sala sala) {
        if (salaRepository.buscarPorNombre(sala.getNombre()) != null) {
            return OperationResult.fail("Ya hay una sala con el nombre asignado");
        }
        return OperationResult.ok("Nombre válido");
    }
}
