package com.reserva.usecases.ports;

import com.reserva.entities.Reserva;

public interface ReservaRepository {
    Reserva findById (String id);
    Reserva findByIdYFecha(String id, String fecha);
    void guardar( Reserva reserva );
}
