package com.reserva.usecases.services;

import com.reserva.entities.Auditorio;
import com.reserva.entities.Aula;
import com.reserva.entities.Laboratorio;
import com.reserva.entities.Reserva;
import com.reserva.infrastructure.repositories.InMemoryReservaRepository;
import com.reserva.usecases.dto.OperationResult;
import com.reserva.usecases.ports.ReservaRepository;
import com.reserva.usecases.ports.SalaRepository;
import com.reserva.infrastructure.repositories.InMemorySalaRepository;
import com.reserva.usecases.rules.reserva.*;
import com.reserva.usecases.rules.sala.ReglaIdUnico;
import com.reserva.usecases.rules.sala.ReglaNombreNoRepetido;
import com.reserva.usecases.rules.sala.ReglaSala;
import com.reserva.usecases.rules.reserva.*;
import com.reserva.usecases.rules.sala.*;

import java.util.ArrayList;
import java.util.List;

public class ReservaApp {
    private final ReservaRepository reservaRepository;
    private final SalaRepository salaRepository;
    private final RegistrarSalaUseCase registrarSalaUseCase;
    private final List<ReglaReserva> reglasReservas;
    private final List<ReglaSala> reglasSalas;

    public ReservaApp() {
        this.salaRepository = new InMemorySalaRepository();
        this.reservaRepository = new InMemoryReservaRepository();
        this.reglasReservas = new ArrayList<>();
        this.reglasSalas = new ArrayList<>();

        //Reglas generales para las salas
        this.reglasSalas.add(new ReglaIdUnico(salaRepository));
        this.reglasSalas.add(new ReglaNombreNoRepetido(salaRepository));

        //Reglas generales para las reservas
        this.reglasReservas.add(new ReglaAuditorio());
        this.reglasReservas.add(new ReglaCapacidad());
        this.reglasReservas.add(new ReglaDisponibilidad(reservaRepository));
        this.reglasReservas.add(new ReglaHorario());
        this.reglasReservas.add(new ReglaLaboratorio());

        cargarDatosIniciales();

        this.registrarSalaUseCase = new RegistrarSalaUseCase(salaRepository, reglasSalas);

        //Demás casos de uso ->
    }

    private void cargarDatosIniciales() {
        salaRepository.guardar(new Aula("S001", "Aula 101", 35, "Bloque A"));
        salaRepository.guardar(new Laboratorio("S002", "Lab Sistemas", 25, "Bloque B"));
        salaRepository.guardar(new Auditorio("S003", "Auditorio Principal", 120,
                "Bloque Central"));
        reservaRepository.guardar(new Reserva("R001", "S001", "2026-05-25", 8, 10,
                        "CLASE", "Ing. Pérez", 30));
        reservaRepository.guardar(new Reserva("R002", "S002", "2026-05-25", 10, 12,
                "PRACTICA", "Ing. Gómez", 20));

    }

    public OperationResult registrarSala(String id, String nombre, String tipo, int capacidad, String ubicacion) {
        return registrarSalaUseCase.ejecutar(id, nombre, tipo, capacidad, ubicacion);
    }
}