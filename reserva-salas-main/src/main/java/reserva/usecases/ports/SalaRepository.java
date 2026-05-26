package reserva.usecases.ports;

import reserva.entities.Sala;

import java.util.List;
import java.util.Optional;

public interface SalaRepository {
    int getNextId();
    void guardar(Sala sala);
    Sala buscarPorNombre(String nombre);
    List<Sala> listarTodas();
}
