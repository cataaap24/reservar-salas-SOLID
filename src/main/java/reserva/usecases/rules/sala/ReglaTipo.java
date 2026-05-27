package reserva.usecases.rules.sala;

import reserva.entities.Sala;
import reserva.usecases.dto.OperationResult;

import java.util.Arrays;
import java.util.List;

public class ReglaTipo implements ReglaSala {
    private final List<String> tipos_validos = Arrays.asList("AULA", "AUDITORIO", "LABORATORIO");
    public OperationResult validar(Sala sala) {
        if (tipos_validos.contains(sala.getTipo())) {
            return OperationResult.fail("Tipo de sala no válido. Use AULA, LABORATORIO o AUDITORIO.");
        }
        return OperationResult.ok("Tipo de sala valido");
    }
}
