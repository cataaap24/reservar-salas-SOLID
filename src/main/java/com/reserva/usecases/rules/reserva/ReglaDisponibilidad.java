package com.reserva.usecases.rules.reserva;

import com.reserva.entities.Reserva;
import com.reserva.entities.Sala;
import com.reserva.usecases.dto.OperationResult;
import com.reserva.usecases.ports.ReservaRepository;

public class ReglaDisponibilidad implements ReglaReserva {
    private final ReservaRepository reservaRepository;

    public  ReglaDisponibilidad(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public OperationResult validar(Reserva reserva, Sala sala) {
        String id = reserva.getId();
        String fecha = reserva.getFecha();
        Reserva existente = reservaRepository.findByIdYFecha(id, fecha);
        if (!reserva.isCancelada() && existente != null) {
            //Se cruzan
            if (reserva.getHoraInicio() < existente.getHoraFin()
                    && reserva.getHoraFin() > existente.getHoraInicio()) {
                return OperationResult.fail("La sala ya tiene una reserva en ese horario");
            }
        }
        return OperationResult.ok("Hay disponibilidad de la sala");
    }
}
