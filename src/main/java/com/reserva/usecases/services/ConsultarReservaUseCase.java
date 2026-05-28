package com.reserva.usecases.services;

import com.reserva.entities.Reserva;
import com.reserva.entities.Sala;
import com.reserva.usecases.dto.OperationResult;
import com.reserva.usecases.ports.ReservaRepository;
import com.reserva.usecases.ports.SalaRepository;

public class ConsultarReservaUseCase {
    private final SalaRepository salaRepository;
    private final ReservaRepository reservaRepository;

    public ConsultarReservaUseCase(SalaRepository salaRepository, ReservaRepository reservaRepository) {
        this.salaRepository = salaRepository;
        this.reservaRepository = reservaRepository;
    }

    public OperationResult ejecutar(String id) {
        //Verificar campos
        if (id == null || id.isBlank()) return OperationResult.fail("El campo id no puede estar vacío");

        Reserva reserva = reservaRepository.findById(id);

        if (reserva == null) return OperationResult.fail("Reserva no encontrada");

        Sala sala = salaRepository.buscarPorId(reserva.getSalaId());

        String result = queryMessage(sala, reserva);
        return OperationResult.ok(result);
    }

    private String queryMessage(Sala sala, Reserva reserva) {
        String texto = "";
        texto = texto + "Información de la reserva\n";
        texto = texto + "-------------------------\n";
        texto = texto + "ID: " + reserva.getId() + "\n";
        texto = texto + "Fecha: " + reserva.getFecha() + "\n";
        texto = texto + "Horario: " + reserva.getHoraInicio() + ":00 - " + reserva.getHoraFin() + ":00\n";
        texto = texto + "Tipo de actividad: " + reserva.getTipoActividad() + "\n";
        texto = texto + "Responsable: " + reserva.getResponsable() + "\n";
        texto = texto + "Asistentes: " + reserva.getCantidadAsistentes() + "\n";

        if (sala != null) {
            texto = texto + "Sala: " + sala.getNombre() + "\n";
            texto = texto + "Tipo de sala: " + sala.getTipo() + "\n";
            texto = texto + "Ubicación: " + sala.getUbicacion() + "\n";
        }
        String estado = reserva.isCancelada() ? "Estado: Cancelada\n" : "Estado: Activa\n";
        texto = texto + estado;
        return texto;
    }
}
