package reserva.infrastructure.repositories;

import reserva.entities.Reserva;
import reserva.usecases.ports.ReservaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryReservaRepository implements ReservaRepository {
    private List<Reserva> reservas;
    public InMemoryReservaRepository() {
        reservas = new ArrayList<Reserva>();
    }

    @Override
    public Reserva findById (String id){
        for (Reserva reserva : reservas){
            if (Objects.equals(reserva.getId(), id)){
                return reserva;
            }
        }
        return null;
    }

    public Reserva findByFecha(String fecha) {
        for (Reserva reserva : reservas) {
            if (Objects.equals(reserva.getFecha(), fecha)) {
                return reserva;
            }
        }
        return null;
    }

    @Override
    public Reserva findByIdYFecha(String id, String fecha) {
        for (Reserva reserva : reservas) {
            if (Objects.equals(reserva.getId(), id) && Objects.equals(reserva.getFecha(), fecha)) {
                return reserva;
            }
        }
        return null;
    }

    @Override
    public void save(Reserva reserva){
        reservas.add(reserva);
    }

    @Override
    public List<Reserva> findAll(){
        return reservas;
    }
}
