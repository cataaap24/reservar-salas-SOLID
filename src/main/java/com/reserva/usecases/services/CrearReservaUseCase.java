package com.reserva.usecases.services;

import com.reserva.entities.Reserva;
import com.reserva.entities.Sala;
import com.reserva.usecases.dto.OperationResult;
import com.reserva.usecases.ports.ReservaRepository;
import com.reserva.usecases.ports.SalaRepository;
import com.reserva.usecases.rules.reserva.ReglaReserva;

import java.util.List;

public class CrearReservaUseCase {

    private final ReservaRepository reservaRepository;
    private final SalaRepository salaRepository;
    private final List<ReglaReserva> reglasReserva;

    public CrearReservaUseCase(ReservaRepository reservaRepository, SalaRepository salaRepository, List<ReglaReserva> reglasReserva) {
        this.reservaRepository = reservaRepository;
        this.salaRepository = salaRepository;
        this.reglasReserva = reglasReserva;
    }

    public OperationResult ejecutar(String id, String salaId, String fecha,
                                    int horaInicio, int horaFin, String tipoActividad,
                                    String responsable, int cantidadAsistentes) {

        //Verificar campos

        if (id == null || id.isBlank()) return OperationResult.fail("El ID de la reserva no puede estar vacío");
        if (salaId == null || salaId.isBlank()) return OperationResult.fail("El ID de la sala no puede estar vacío");
        if (fecha == null || fecha.isBlank()) return OperationResult.fail("El campo 'Fecha' no puede estar vacío");
        if (responsable == null || responsable.isBlank()) return OperationResult.fail("El campo 'Responsable' no puede estar vacío");

        Sala sala = salaRepository.buscarPorId(salaId);

        //Verificar existencia de la sala

        if (sala == null) return OperationResult.fail("La sala no existe");

        Reserva reserva = new Reserva(id, salaId, fecha, horaInicio, horaFin, tipoActividad, responsable, cantidadAsistentes);

        //Verificar reglas

        for (ReglaReserva reglaReserva : reglasReserva) {
            OperationResult result = reglaReserva.validar(reserva, sala);
            if (!result.isSuccess()) return result;
        }

        //Generar persistencia

        reservaRepository.guardar(reserva);
        return OperationResult.ok("Reserva registrada exitosamente");
    }
}
