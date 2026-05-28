package com.reserva.usecases.services;

import com.reserva.entities.*;
import com.reserva.infrastructure.repositories.InMemoryReservaRepository;
import com.reserva.usecases.dto.OperationResult;
import com.reserva.usecases.ports.ReservaRepository;
import com.reserva.usecases.ports.SalaRepository;
import com.reserva.infrastructure.repositories.InMemorySalaRepository;
import com.reserva.usecases.rules.reserva.*;
import com.reserva.usecases.rules.sala.ReglaSalaIdUnico;
import com.reserva.usecases.rules.sala.ReglaNombreNoRepetido;
import com.reserva.usecases.rules.sala.ReglaSala;

import java.util.ArrayList;
import java.util.List;

public class ReservaApp {
    private final ReservaRepository reservaRepository;
    private final SalaRepository salaRepository;
    private final List<ReglaReserva> reglasReservas;
    private final List<ReglaSala> reglasSalas;

    private final RegistrarSalaUseCase registrarSalaUseCase;
    private final CrearReservaUseCase crearReservaUseCase;
    private final ConsultarReservaUseCase consultarReservaUseCase;
    private final CancelarReservaUseCase cancelarReservaUseCase;

    public ReservaApp() {
        this.salaRepository = new InMemorySalaRepository();
        this.reservaRepository = new InMemoryReservaRepository();
        this.reglasReservas = new ArrayList<>();
        this.reglasSalas = new ArrayList<>();

        //Reglas generales para las salas
        this.reglasSalas.add(new ReglaSalaIdUnico(salaRepository));
        this.reglasSalas.add(new ReglaNombreNoRepetido(salaRepository));

        //Reglas generales para las reservas
        this.reglasReservas.add(new ReglaReservaIdUnico(reservaRepository));
        this.reglasReservas.add(new ReglaAuditorio());
        this.reglasReservas.add(new ReglaCapacidad());
        this.reglasReservas.add(new ReglaDisponibilidad(reservaRepository));
        this.reglasReservas.add(new ReglaHorario());
        this.reglasReservas.add(new ReglaLaboratorio());

        cargarDatosIniciales();

        this.registrarSalaUseCase = new RegistrarSalaUseCase(salaRepository, reglasSalas);
        this.crearReservaUseCase = new CrearReservaUseCase(reservaRepository, salaRepository, reglasReservas);
        this.consultarReservaUseCase = new ConsultarReservaUseCase(salaRepository, reservaRepository);
        this.cancelarReservaUseCase = new CancelarReservaUseCase(reservaRepository);
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

    public OperationResult crearReserva(String id, String salaId, String fecha, int horaInicio, int horaFin, String tipoActividad, String responsable, int cantidadAsistentes) {
        return crearReservaUseCase.ejecutar(id, salaId, fecha, horaInicio, horaFin, tipoActividad, responsable, cantidadAsistentes);
    }

    public OperationResult consultarReserva(String id) {
        return consultarReservaUseCase.ejecutar(id);
    }

    public OperationResult cancelarReserva(String id) {
        return cancelarReservaUseCase.ejecutar(id);
    }

    public String listarSalas() {
        List<Sala> salas = salaRepository.listarTodas();
        if (salas.isEmpty()) {
            return "No hay salas registradas.";
        }

        String texto = "";
        texto = texto + "Listado de salas\n";
        texto = texto + "----------------\n";

        for (Sala sala : salas) {
            String estado;

            if (sala.isActiva()) {
                estado = "Activa";
            } else {
                estado = "Inactiva";
            }

            texto = texto + sala.getId() + " | "
                    + sala.getNombre() + " | "
                    + sala.getTipo() + " | Capacidad: "
                    + sala.getCapacidad() + " | "
                    + sala.getUbicacion() + " | "
                    + estado + "\n";
        }
        return texto;
    }

    public String listarReservas() {
        List<Reserva> reservas = reservaRepository.listarTodas();
        if (reservas.isEmpty()) {
            return "No hay reservas registradas.";
        }

        String texto = "";
        texto = texto + "Listado de reservas\n";
        texto = texto + "-------------------\n";

        for (Reserva reserva : reservas) {
            String estado;

            if (reserva.isCancelada()) {
                estado = "Cancelada";
            } else {
                estado = "Activa";
            }

            texto = texto + reserva.getId() + " | Sala: "
                    + reserva.getSalaId() + " | "
                    + reserva.getFecha() + " | "
                    + reserva.getHoraInicio() + ":00 - "
                    + reserva.getHoraFin() + ":00 | "
                    + reserva.getTipoActividad() + " | "
                    + estado + "\n";
        }

        return texto;
    }

    public String generarReporte() {
        List<Sala> salas = salaRepository.listarTodas();
        List<Reserva> reservas = reservaRepository.listarTodas();

        int totalAulas = 0;
        int totalLaboratorios = 0;
        int totalAuditorios = 0;
        int reservasActivas = 0;
        int reservasCanceladas = 0;

        for (Sala sala : salas) {
            if (sala.getTipo().equals("AULA")) {
                totalAulas++;
            } else if (sala.getTipo().equals("LABORATORIO")) {
                totalLaboratorios++;
            } else if (sala.getTipo().equals("AUDITORIO")) {
                totalAuditorios++;
            }
        }

        for (Reserva reserva : reservas) {
            if (reserva.isCancelada()) {
                reservasCanceladas++;
            } else {
                reservasActivas++;
            }
        }

        String texto = "";
        texto = texto + "Reporte general\n";
        texto = texto + "---------------\n";
        texto = texto + "Total de salas: " + salas.size() + "\n";
        texto = texto + "Aulas: " + totalAulas + "\n";
        texto = texto + "Laboratorios: " + totalLaboratorios + "\n";
        texto = texto + "Auditorios: " + totalAuditorios + "\n";
        texto = texto + "Total de reservas: " + reservas.size() + "\n";
        texto = texto + "Reservas activas: " + reservasActivas + "\n";
        texto = texto + "Reservas canceladas: " + reservasCanceladas + "\n";

        return texto;
    }
}