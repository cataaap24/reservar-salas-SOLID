package com.reserva.infrastructure.repositories;

import com.reserva.entities.Reserva;
import com.reserva.usecases.ports.ReservaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryReservaRepository implements ReservaRepository {
    private List<Reserva> reservas;
    public InMemoryReservaRepository() {
        reservas = new ArrayList<Reserva>();
    }

    @Override
    public Reserva findById (String id){
        for (Reserva reserva : reservas){
            if (Objects.equals(reserva.getId(), id)){
                return reserva;
            }
        }
        return null;
    }

    @Override
    public Reserva findByIdYFecha(String id, String fecha) {
        for (Reserva reserva : reservas) {
            if (Objects.equals(reserva.getId(), id) && Objects.equals(reserva.getFecha(), fecha)) {
                return reserva;
            }
        }
        return null;
    }

    @Override
    public void guardar(Reserva reserva){
        reservas.add(reserva);
    }
}
