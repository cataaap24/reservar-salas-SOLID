package reserva.entities;

public abstract class Sala {
    private String id;
    private String nombre;
    private int capacidad;
    private String ubicacion;
    private boolean activa;

    public Sala(String id, String nombre, int capacidad, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.activa = true;
    }
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public int getCapacidad() { return capacidad; }
    public boolean isActiva() {
        return activa;
    }
    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
