package reserva.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaTest {

    @Test
    void getId() {
        Sala sala = new Sala(1, "Sala 1", TipoSala.valueOf("AULA"), 20);
        assertEquals(1, sala.getId());
    }

    @Test
    void getNombre() {
        Sala sala = new Sala(1, "Sala 1", TipoSala.valueOf("AULA"), 20);
        assertEquals("Sala 1", sala.getNombre());
    }

    @Test
    void getTipoSala() {
        Sala sala = new Sala(1, "Sala 1", TipoSala.valueOf("AULA"), 20);
        assertEquals(TipoSala.AULA, sala.getTipoSala());
    }

    @Test
    void getAforo() {
        Sala sala = new Sala(1, "Sala 1", TipoSala.valueOf("AULA"), 20);
        assertEquals(20, sala.getAforo());
    }

}