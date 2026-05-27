package reserva.usecases.services;

import reserva.entities.*;
import java.util.List;
import reserva.usecases.dto.OperationResult;
import reserva.usecases.ports.SalaRepository;
import reserva.usecases.rules.sala.ReglaSala;

public class RegistrarSalaUseCase {
    private final SalaRepository salaRepository;
    private final List<ReglaSala> reglasSalas;

    public RegistrarSalaUseCase(SalaRepository salaRepository, List<ReglaSala> reglasSalas) {
        this.salaRepository = salaRepository;
        this.reglasSalas = reglasSalas;
    }

    public OperationResult execute(String id, String nombre, String tipo, int capacidad, String ubicacion) {
        Sala nuevaSala;
        switch (tipo.toUpperCase()) {
            case "AULA":
                nuevaSala = new Aula(id, nombre, capacidad, ubicacion);
                break;
            case "LABORATORIO":
                nuevaSala = new Laboratorio(id, nombre, capacidad, ubicacion);
                break;
            case "AUDITORIO":
                nuevaSala = new Auditorio(id, nombre, capacidad, ubicacion);
                break;
            default:
                return OperationResult.fail("Error: tipo de sala no soportado por el sistema.");
        }
        for (ReglaSala regla : reglasSalas) {
            OperationResult result = regla.validar(nuevaSala);
            if (!result.isSuccess()) {
                return result;
            }
        }
        salaRepository.guardar(nuevaSala);
        return OperationResult.ok("Sala registrada correctamente.");
    }
}