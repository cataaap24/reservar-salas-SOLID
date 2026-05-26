package reserva.usecases.services;

import org.junit.jupiter.api.Test;
import reserva.entities.Sala;
import reserva.entities.TipoSala;
import reserva.infrastructure.repositories.InMemorySalaRepository;
import reserva.usecases.ports.SalaRepository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegistrarSalaUseCaseTest {
    @Test
    void registrarSalaNormal() {
        SalaRepository salaRepository = new InMemorySalaRepository();
        Sala sala = new Sala(1, "Sala 1", TipoSala.valueOf("AULA"), 20);
        boolean nombreUnico = salaRepository.buscarPorNombre(sala.getNombre()) == null;
        assertTrue(nombreUnico);
    }

    @Test
    void registrarSalaNombreNoUnico() {
        SalaRepository salaRepository = new InMemorySalaRepository();
        Sala sala = new Sala(1, "Sala 1", TipoSala.valueOf("AULA"), 20);
        salaRepository.guardar(sala);
        Sala sala2 = new Sala(2, "Sala 1", TipoSala.valueOf("AULA"), 20);
        boolean nombreUnico = salaRepository.buscarPorNombre(sala2.getNombre()) == null;
        assertFalse(nombreUnico);
    }
}