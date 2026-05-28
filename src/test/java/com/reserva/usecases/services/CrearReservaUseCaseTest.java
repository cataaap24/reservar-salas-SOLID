package com.reserva.usecases.services;

import com.reserva.entities.Reserva;
import com.reserva.entities.Sala;
import com.reserva.infrastructure.repositories.InMemoryReservaRepository;
import com.reserva.usecases.dto.OperationResult;
import com.reserva.usecases.factories.SalaFactory;
import com.reserva.usecases.ports.ReservaRepository;
import com.reserva.usecases.rules.reserva.ReglaDisponibilidad;
import com.reserva.usecases.rules.reserva.ReglaHorario;
import com.reserva.usecases.rules.reserva.ReglaReserva;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrearReservaUseCaseTest {
    @Test
    void crearReservaNormal() {
        ReservaRepository reservaRepository = new InMemoryReservaRepository();
        Sala sala = SalaFactory.crear("S001", "Aula 1", "AULA", 20, "Bloque A");
        Reserva reserva = new Reserva("R001", sala.getId(), "2026-05-25", 7, 14, "CLASE", "Arturo", 10);
        reservaRepository.guardar(reserva);
        assertEquals(reserva, reservaRepository.findById("R001"));
    }

    @Test
    void crearReservaIdNoUnico() {
        ReservaRepository reservaRepository = new InMemoryReservaRepository();
        Sala sala = SalaFactory.crear("S001", "Aula 1", "AULA", 20, "Bloque A");
        Reserva reserva = new Reserva("R001", sala.getId(), "2026-05-25", 7, 14, "CLASE", "Arturo", 10);
        reservaRepository.guardar(reserva);
        Reserva reserva_invalida = new Reserva("R001", sala.getId(), "2026-05-26", 7, 14, "CLASE", "Arturo", 10);
        boolean idUnico = reservaRepository.findById(reserva_invalida.getId()) == null;
        assertFalse(idUnico);
    }

    @Test
    void crearReservaAsistentesMinimosAuditorio() {
        ReservaRepository reservaRepository = new InMemoryReservaRepository();
        Sala sala = SalaFactory.crear("S001", "Auditorio", "AUDITORIO", 40, "Bloque A");
        Reserva reserva = new Reserva("R001", sala.getId(), "2026-05-25", 7, 14, "EVENTO", "Arturo", 10);
        boolean asistentes_suficientes = reserva.getCantidadAsistentes() > 30;
        assertFalse(asistentes_suficientes);
    }

    @Test
    void crearReservaActividadLaboratorio() {
        ReservaRepository reservaRepository = new InMemoryReservaRepository();
        Sala sala = SalaFactory.crear("S001", "Laboratorio Física", "LABORATORIO", 30, "Bloque Central");
        Reserva reserva = new Reserva("R001", sala.getId(), "2026-05-25", 7, 14, "CLASE", "Arturo", 10);
        boolean actividad_valida = reserva.getTipoActividad().equals("PRACTICA");
        assertFalse(actividad_valida);
    }

    @Test
    void crearReservaHorarioFueraDelInstitucional() {
        ReservaRepository reservaRepository = new InMemoryReservaRepository();
        Sala sala = SalaFactory.crear("S001", "Laboratorio Física", "LABORATORIO", 30, "Bloque Central");
        Reserva reserva = new Reserva("R001", sala.getId(), "2026-05-25", 1, 23, "CLASE", "Arturo", 10);
        ReglaReserva regla_horario = new ReglaHorario();
        OperationResult result = regla_horario.validar(reserva, sala);
        assertFalse(result.isSuccess());
    }

    @Test
    void crearReservaNoDisponibilidad() {
        ReservaRepository reservaRepository = new InMemoryReservaRepository();
        Sala sala = SalaFactory.crear("S001", "Laboratorio Física", "LABORATORIO", 30, "Bloque Central");
        Reserva reserva = new Reserva("R001", sala.getId(), "2026-05-25", 8, 12, "CLASE", "Arturo", 10);
        reservaRepository.guardar(reserva);
        Reserva reserva_invalida = new Reserva("R002", sala.getId(), "2026-05-25", 11, 14, "CLASE",  "Arturo", 10);
        ReglaReserva regla_disponibilidad = new ReglaDisponibilidad(reservaRepository);
        OperationResult result = regla_disponibilidad.validar(reserva, sala);
        assertFalse(result.isSuccess());
    }
}