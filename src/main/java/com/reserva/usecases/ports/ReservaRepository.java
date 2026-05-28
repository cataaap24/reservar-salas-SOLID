package com.reserva.usecases.ports;

import com.reserva.entities.Reserva;

import java.util.List;

public interface ReservaRepository {
    Reserva findById (String id);
    Reserva findByIdSalaYFecha(String id, String fecha);
    void guardar( Reserva reserva );
    List<Reserva> listarTodas();
}
