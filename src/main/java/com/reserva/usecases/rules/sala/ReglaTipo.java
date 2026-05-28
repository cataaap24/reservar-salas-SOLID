package com.reserva.usecases.rules.sala;

import com.reserva.usecases.dto.OperationResult;

import java.util.Arrays;
import java.util.List;

public class ReglaTipo {
    private final List<String> tipos_validos = Arrays.asList("AULA", "AUDITORIO", "LABORATORIO");
    public OperationResult validar(String tipo) {
        if (!tipos_validos.contains(tipo)) {
            return OperationResult.fail("Tipo de sala no válido. Use AULA, LABORATORIO o AUDITORIO.");
        }
        return OperationResult.ok("Tipo de sala valido");
    }
}
