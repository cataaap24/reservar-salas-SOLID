package com.reserva.adapters.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.reserva.usecases.services.ReservaApp;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        ReservaApp reservaApp = new ReservaApp();

        ReservaView reservaView = new ReservaView(reservaApp);

        Scene scene = new Scene(reservaView.crearVista(), 980, 680);

        stage.setTitle("Reserva de Salas - Proyecto base para refactorización SOLID");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}