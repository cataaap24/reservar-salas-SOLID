package java.reserva.usecases.services;

import java.reserva.entities.Sala;
import java.reserva.entities.TipoSala;
import java.reserva.usecases.dto.OperationResult;
import java.reserva.usecases.ports.SalaRepository;

public class RegistrarSalaUseCase {
    private final SalaRepository salaRepository;

    public RegistrarSalaUseCase(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public OperationResult ejecutar(String nombre, TipoSala tipoSala, int aforo) {

        if (salaRepository.buscarPorNombre(nombre) != null) {
            return OperationResult.fail("Ya existe una sala con ese nombre");
        }
        try {
            Sala nuevaSala = new Sala(salaRepository.getNextId(), nombre, tipoSala, aforo);
            salaRepository.guardar(nuevaSala);
            return OperationResult.ok("Sala " + nuevaSala.getNombre() + " (" + nuevaSala.getId() + ") registrada exitosamente.");

        } catch (IllegalArgumentException e) {
            return OperationResult.fail(e.getMessage());
        }
    }
}
