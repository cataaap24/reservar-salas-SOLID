package reserva.entities;

public class Sala {
    private int id;
    private final String nombre;
    private final TipoSala tipoSala;
    private final int aforo;


    public Sala(int id, String nombre, TipoSala tipoSala, int aforo) {
        validarEstructura(nombre, aforo);
        this.nombre = nombre.trim();
        this.tipoSala = tipoSala;
        this.aforo = aforo;
        this.id = id;
    }

    private void validarEstructura(String nombre, int aforo) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la sala no puede estar vacío.");
        }
        if (aforo <= 0) {
            throw new IllegalArgumentException("El aforo debe ser un número entero positivo.");
        }
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public TipoSala getTipoSala() { return tipoSala; }
    public int getAforo() { return aforo; }

    public void setId(int id) { this.id = id; }

}
