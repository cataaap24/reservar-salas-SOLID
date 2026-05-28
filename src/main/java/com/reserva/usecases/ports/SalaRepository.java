package com.reserva.usecases.ports;

import com.reserva.entities.Sala;
import java.util.List;

public interface SalaRepository {
    int getNextId();
    void guardar(Sala sala);
    Sala buscarPorNombre(String nombre);
    Sala buscarPorId(String id);
    List<Sala> listarTodas();
}
