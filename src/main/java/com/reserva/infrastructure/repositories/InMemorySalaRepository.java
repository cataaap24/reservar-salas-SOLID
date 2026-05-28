package com.reserva.infrastructure.repositories;

import com.reserva.entities.Sala;
import com.reserva.usecases.ports.SalaRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemorySalaRepository implements SalaRepository {
    private int nextId;
    private List<Sala> salas;
    public InMemorySalaRepository() {
        salas = new ArrayList<Sala>();
        nextId = 1;
    }

    @Override
    public int getNextId() {
        int id = nextId;
        nextId = nextId + 1;
        return id;
    }

    @Override
    public void guardar(Sala sala) {
        salas.add(sala);
    }

    @Override
    public Sala buscarPorNombre(String nombre) {
        for (Sala sala : salas) {
            if (sala.getNombre().trim().equalsIgnoreCase(nombre)) {
                return sala;
            }
        }
        return null;
    }

    @Override
    public Sala buscarPorId(String id) {
        for (Sala sala : salas) {
            if (sala.getId().equals(id)) {
                return sala;
            }
        }
        return null;
    }

    @Override
    public List<Sala> listarTodas() {
        return Collections.unmodifiableList(salas);
    }
}
