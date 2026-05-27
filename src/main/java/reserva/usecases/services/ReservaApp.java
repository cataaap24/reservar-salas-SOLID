package reserva.usecases.services;

import java.util.ArrayList;
import java.util.List;
import reserva.entities.Reserva;
import reserva.usecases.dto.OperationResult;
import reserva.usecases.ports.ReservaRepository;
import reserva.usecases.ports.SalaRepository;
import reserva.infrastructure.repositories.InMemorySalaRepository;
import reserva.infrastructure.repositories.InMemoryReservaRepository;
import reserva.usecases.rules.sala.ReglaSala;
import reserva.usecases.rules.reserva.ReglaReserva;

import reserva.usecases.rules.sala.ReglaNombreNoRepetido;
import reserva.usecases.rules.sala.ReglaTipo;

import reserva.usecases.rules.reserva.ReglaAuditorio;
import reserva.usecases.rules.reserva.ReglaCapacidad;
import reserva.usecases.rules.reserva.ReglaDisponibilidad;
import reserva.usecases.rules.reserva.ReglaHorario;
import reserva.usecases.rules.reserva.ReglaLaboratorio;

public class ReservaApp {

    private final SalaRepository salaRepository;
    private final ReservaRepository reservaRepository;

    private final RegistrarSalaUseCase registrarSalaUseCase;
    private final CrearReservaUseCase crearReservaUseCase;
    private final ConsultarReservaUseCase consultarReservaUseCase;
    private final CancelarReservaUseCase cancelarReservaUseCase;
    private final ListarSalasUseCase listarSalasUseCase;
    private final ListarReservasUseCase listarReservasUseCase;
    private final GenerarReporteUseCase generarReporteUseCase;

    public ReservaApp() {
        this.salaRepository = new InMemorySalaRepository();
        this.reservaRepository = new InMemoryReservaRepository();
        List<ReglaSala> reglasSalas = new ArrayList<>();
        reglasSalas.add(new ReglaNombreNoRepetido(salaRepository));
        reglasSalas.add(new ReglaTipo());
        List<ReglaReserva> reglasReservas = new ArrayList<>();
        reglasReservas.add(new ReglaCapacidad());
        reglasReservas.add(new ReglaDisponibilidad(reservaRepository));
        reglasReservas.add(new ReglaHorario());
        reglasReservas.add(new ReglaAuditorio());
        reglasReservas.add(new ReglaLaboratorio());
        this.registrarSalaUseCase = new RegistrarSalaUseCase(salaRepository, reglasSalas);
        this.crearReservaUseCase = new CrearReservaUseCase(salaRepository, reservaRepository, reglasReservas);
        this.consultarReservaUseCase = new ConsultarReservaUseCase(reservaRepository, salaRepository);
        this.cancelarReservaUseCase = new CancelarReservaUseCase(reservaRepository);
        this.listarSalasUseCase = new ListarSalasUseCase(salaRepository);
        this.listarReservasUseCase = new ListarReservasUseCase(reservaRepository);
        this.generarReporteUseCase = new GenerarReporteUseCase(salaRepository, reservaRepository);
    }

    public OperationResult registrarSala(String id, String nombre, String tipo, int capacidad, String ubicacion) {
        return registrarSalaUseCase.execute(id, nombre, tipo, capacidad, ubicacion);
    }

    public OperationResult crearReserva(Reserva reserva) {
        return crearReservaUseCase.execute(reserva);
    }

    public OperationResult consultarReserva(String id) {
        return consultarReservaUseCase.execute(id);
    }

    public OperationResult cancelarReserva(String id) {
        return cancelarReservaUseCase.execute(id);
    }

    public OperationResult listarSalas() {
        return listarSalasUseCase.execute();
    }

    public OperationResult listarReservas() {
        return listarReservasUseCase.execute();
    }

    public OperationResult generarReporte() {
        return generarReporteUseCase.execute();
    }
}