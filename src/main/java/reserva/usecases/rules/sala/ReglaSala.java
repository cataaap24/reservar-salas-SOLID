package reserva.usecases.rules.sala;

import reserva.entities.Sala;
import reserva.usecases.dto.OperationResult;

public interface ReglaSala {
    OperationResult validar(Sala sala);
}
