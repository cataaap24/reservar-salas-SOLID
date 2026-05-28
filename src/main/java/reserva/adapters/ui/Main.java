package reserva.adapters.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import reserva.adapters.ui.ReservaView;
import reserva.usecases.services.ReservaApp;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        ReservaApp reservaApp = new ReservaApp();
        cargarDatosIniciales(reservaApp);
        ReservaView reservaView = new ReservaView(reservaApp);
        Scene scene = new Scene(reservaView.crearVista(), 980, 680);

        stage.setTitle("Reserva de Salas - Proyecto Refactorizado SOLID");
        stage.setScene(scene);
        stage.show();
    }

    private void cargarDatosIniciales(ReservaApp app) {
        app.registrarSala("S001", "Aula 101", "AULA", 35, "Bloque A");
        app.registrarSala("S002", "Laboratorio de Sistemas", "LABORATORIO", 25, "Bloque B");
        app.registrarSala("S003", "Auditorio Principal", "AUDITORIO", 120, "Bloque Central");
        app.crearReserva(new reserva.entities.Reserva("R001", "S001", "2026-05-25", 8, 10, "CLASE", "Ing. Pérez", 30));
    }

    public static void main(String[] args) {
        launch(args);
    }
}