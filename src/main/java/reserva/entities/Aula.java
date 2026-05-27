package reserva.entities;

public class Aula extends Sala {
    public Aula(String id, String nombre, int capacidad, String ubicacion) {
        super(id, nombre, capacidad, ubicacion);
    }
    @Override
    public String getTipo() { return "AULA"; }
}

