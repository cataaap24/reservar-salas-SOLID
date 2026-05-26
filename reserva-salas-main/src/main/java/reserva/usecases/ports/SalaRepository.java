package java.reserva.usecases.ports;

import java.reserva.entities.Sala;
import java.util.List;

public interface SalaRepository {
    int getNextId();
    void guardar(Sala sala);
    Sala buscarPorNombre(String nombre);
    List<Sala> listarTodas();
}
