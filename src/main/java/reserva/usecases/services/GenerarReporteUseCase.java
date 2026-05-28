package reserva.usecases.services;

import reserva.usecases.dto.OperationResult;
import reserva.usecases.ports.ReservaRepository;
import reserva.usecases.ports.SalaRepository;
import reserva.entities.Reserva;

public class GenerarReporteUseCase {
    private final SalaRepository salaRepository;
    private final ReservaRepository reservaRepository;

    public GenerarReporteUseCase(SalaRepository salaRepository, ReservaRepository reservaRepository) {
        this.salaRepository = salaRepository;
        this.reservaRepository = reservaRepository;
    }

    public OperationResult execute() {
        int totalSalas = salaRepository.listarTodas().size();
        long activas = reservaRepository.findAll().stream().filter(r -> !r.isCancelada()).count();
        long canceladas = reservaRepository.findAll().stream().filter(Reserva::isCancelada).count();

        String reporte = "==================================================\n" +
                "        REPORTE GENERAL INSTITUCIONAL SOLID       \n" +
                "==================================================\n" +
                " Total de salas de la infraestructura: " + totalSalas + "\n" +
                " Reservas activas y vigentes: " + activas + "\n" +
                " Reservas canceladas e historizadas: " + canceladas + "\n" +
                "==================================================";

        return OperationResult.ok(reporte);
    }
}