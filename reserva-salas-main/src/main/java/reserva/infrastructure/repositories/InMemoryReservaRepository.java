package java.reserva.infrastructure.repositories;

import java.reserva.entities.Reserva;
import java.reserva.usecases.ports.ReservaRepository;
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

    @Override
    public void save(Reserva reserva){
        reservas.add(reserva);
    }

}
