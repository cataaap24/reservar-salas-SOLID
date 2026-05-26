package reserva.usecases.services;

import reserva.entities.TipoSala;
import reserva.usecases.dto.OperationResult;
import reserva.usecases.ports.SalaRepository;
import reserva.infrastructure.repositories.InMemorySalaRepository;

public class ReservaApp {

    private final SalaRepository salaRepository;
    private final RegistrarSalaUseCase registrarSalaUseCase;

    public ReservaApp() {
        this.salaRepository = new InMemorySalaRepository();
        this.registrarSalaUseCase = new RegistrarSalaUseCase(salaRepository);
    }

    public ReservaApp(SalaRepository salaRepository, RegistrarSalaUseCase registrarSalaUseCase) {
        this.salaRepository = salaRepository;
        this.registrarSalaUseCase = registrarSalaUseCase;
    }

    public OperationResult registrarSala(String nombre, TipoSala tipoSala, int aforo) {
        return registrarSalaUseCase.ejecutar(nombre, tipoSala, aforo);
    }

}