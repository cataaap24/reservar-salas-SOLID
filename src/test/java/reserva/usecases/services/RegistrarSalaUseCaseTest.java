package reserva.usecases.services;

import com.reserva.usecases.factories.SalaFactory;
import org.junit.jupiter.api.Test;
import com.reserva.entities.Sala;
import com.reserva.infrastructure.repositories.InMemorySalaRepository;
import com.reserva.usecases.ports.SalaRepository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegistrarSalaUseCaseTest {
    @Test
    void registrarSalaNormal() {
        SalaRepository salaRepository = new InMemorySalaRepository();
        Sala sala = SalaFactory.crear("S1", "Aula 1", "AULA", 20, "Bloque A");
        boolean nombreUnico = salaRepository.buscarPorNombre(sala.getNombre()) == null;
        assertTrue(nombreUnico);
    }

    @Test
    void registrarSalaIdNoUnico() {
        SalaRepository salaRepository = new InMemorySalaRepository();
        Sala sala = SalaFactory.crear("S1", "Sala 1", "AULA", 20,  "Bloque A");
        salaRepository.guardar(sala);
        Sala sala2 = SalaFactory.crear("S1", "Sala 2", "AULA", 20, "Bloque A");
        boolean idUnico = salaRepository.buscarPorId(sala2.getId()) == null;
        assertFalse(idUnico);
    }

    @Test
    void registrarSalaNombreNoUnico() {
        SalaRepository salaRepository = new InMemorySalaRepository();
        Sala sala = SalaFactory.crear("S1", "Sala 1", "AULA", 20,  "Bloque A");
        salaRepository.guardar(sala);
        Sala sala2 = SalaFactory.crear("S2", "Sala 1", "AULA", 20, "Bloque A");
        boolean nombreUnico = salaRepository.buscarPorNombre(sala2.getNombre()) == null;
        assertFalse(nombreUnico);
    }
}