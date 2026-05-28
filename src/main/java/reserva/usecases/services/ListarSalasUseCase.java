package reserva.usecases.services;

import reserva.entities.Sala;
import reserva.usecases.dto.OperationResult;
import reserva.usecases.ports.SalaRepository;
import java.util.List;

public class ListarSalasUseCase {
    private final SalaRepository salaRepository;

    public ListarSalasUseCase(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public OperationResult execute() {
        List<Sala> salas = salaRepository.listarTodas();
        if (salas.isEmpty()) {
            return OperationResult.fail("No hay salas registradas en el sistema.");
        }

        String info = "--- LISTADO DE SALAS REGISTRADAS ---\n";
        for (Sala sala : salas) {
            info += "ID: " + sala.getId() + " | Nombre: " + sala.getNombre() + " | Tipo: " + sala.getTipo() + " | Capacidad: " + sala.getCapacidad() + " | Ubicación: " + sala.getUbicacion() + "\n";
        }

        return OperationResult.ok(info);
    }
}