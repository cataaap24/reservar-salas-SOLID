package reserva.adapters.ui;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import reserva.entities.Reserva;
import reserva.usecases.dto.OperationResult;
import reserva.usecases.services.ReservaApp;

public class ReservaView {

    // Fachada única del sistema
    private final ReservaApp reservaApp;

    private TextField txtSalaId;
    private TextField txtSalaNombre;
    private ComboBox<String> cbSalaTipo; // El menú desplegable como String plano
    private TextField txtSalaCapacidad;
    private TextField txtSalaUbicacion;

    private TextField txtReservaId;
    private TextField txtReservaSalaId;
    private TextField txtFecha;
    private TextField txtHoraInicio;
    private TextField txtHoraFin;
    private TextField txtTipoActividad;
    private TextField txtResponsable;
    private TextField txtAsistentes;

    private TextArea txtResultado;

    // Recibe la fachada por el constructor
    public ReservaView(ReservaApp reservaApp) {
        this.reservaApp = reservaApp;
    }

    public Parent crearVista() {
        Label titulo = new Label("Sistema de Reserva de Salas");
        titulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        GridPane formularioSalas = crearFormularioSalas();
        GridPane formularioReservas = crearFormularioReservas();

        HBox botonesSalas = crearBotonesSalas();
        HBox botonesReservas = crearBotonesReservas();

        txtResultado = new TextArea();
        txtResultado.setEditable(false);
        txtResultado.setPrefHeight(230);

        VBox root = new VBox(12);
        root.setPadding(new Insets(15));
        root.getChildren().addAll(
                titulo,
                new Label("Datos de sala"),
                formularioSalas,
                botonesSalas,
                new Label("Datos de reserva"),
                formularioReservas,
                botonesReservas,
                new Label("Resultado"),
                txtResultado
        );

        return root;
    }

    private GridPane crearFormularioSalas() {
        txtSalaId = new TextField();
        txtSalaNombre = new TextField();
        cbSalaTipo = new ComboBox<>(); // Instanciamos el desplegable
        txtSalaCapacidad = new TextField();
        txtSalaUbicacion = new TextField();

        // Cargamos las opciones válidas en el menú desplegable
        cbSalaTipo.getItems().setAll("AULA", "LABORATORIO", "AUDITORIO");
        cbSalaTipo.setPromptText("Seleccione tipo");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(8);

        grid.add(new Label("ID sala:"), 0, 0);
        grid.add(txtSalaId, 1, 0);

        grid.add(new Label("Nombre:"), 2, 0);
        grid.add(txtSalaNombre, 3, 0);

        grid.add(new Label("Tipo:"), 0, 1);
        grid.add(cbSalaTipo, 1, 1); // Agregamos el ComboBox en lugar del TextField

        grid.add(new Label("Capacidad:"), 2, 1);
        grid.add(txtSalaCapacidad, 3, 1);

        grid.add(new Label("Ubicación:"), 0, 2);
        grid.add(txtSalaUbicacion, 1, 2);

        return grid;
    }

    private GridPane crearFormularioReservas() {
        txtReservaId = new TextField();
        txtReservaSalaId = new TextField();
        txtFecha = new TextField();
        txtHoraInicio = new TextField();
        txtHoraFin = new TextField();
        txtTipoActividad = new TextField();
        txtResponsable = new TextField();
        txtAsistentes = new TextField();

        txtFecha.setPromptText("2026-05-25");
        txtTipoActividad.setPromptText("CLASE, PRACTICA, EVENTO");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(8);

        grid.add(new Label("ID reserva:"), 0, 0);
        grid.add(txtReservaId, 1, 0);

        grid.add(new Label("ID sala:"), 2, 0);
        grid.add(txtReservaSalaId, 3, 0);

        grid.add(new Label("Fecha:"), 0, 1);
        grid.add(txtFecha, 1, 1);

        grid.add(new Label("Hora inicio:"), 2, 1);
        grid.add(txtHoraInicio, 3, 1);

        grid.add(new Label("Hora fin:"), 0, 2);
        grid.add(txtHoraFin, 1, 2);

        grid.add(new Label("Tipo actividad:"), 2, 2);
        grid.add(txtTipoActividad, 3, 2);

        grid.add(new Label("Responsable:"), 0, 3);
        grid.add(txtResponsable, 1, 3);

        grid.add(new Label("Asistentes:"), 2, 3);
        grid.add(txtAsistentes, 3, 3);

        return grid;
    }

    private HBox crearBotonesSalas() {
        Button btnRegistrarSala = new Button("Registrar sala");
        Button btnListarSalas = new Button("Listar salas");

        btnRegistrarSala.setOnAction(e -> registrarSala());
        btnListarSalas.setOnAction(e -> listarSalas());

        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(btnRegistrarSala, btnListarSalas);

        return hbox;
    }

    private HBox crearBotonesReservas() {
        Button btnCrearReserva = new Button("Crear reserva");
        Button btnConsultarReserva = new Button("Consultar reserva");
        Button btnCancelarReserva = new Button("Cancelar reserva");
        Button btnListarReservas = new Button("Listar reservas");
        Button btnReporte = new Button("Reporte");
        Button btnLimpiar = new Button("Limpiar");

        btnCrearReserva.setOnAction(e -> crearReserva());
        btnConsultarReserva.setOnAction(e -> consultarReserva());
        btnCancelarReserva.setOnAction(e -> cancelarReserva());
        btnListarReservas.setOnAction(e -> listarReservas());
        btnReporte.setOnAction(e -> generarReporte());
        btnLimpiar.setOnAction(e -> limpiarCampos());

        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(
                btnCrearReserva,
                btnConsultarReserva,
                btnCancelarReserva,
                btnListarReservas,
                btnReporte,
                btnLimpiar
        );

        return hbox;
    }

    private void registrarSala() {
        try {
            String id = txtSalaId.getText();
            String nombre = txtSalaNombre.getText();
            String tipo = cbSalaTipo.getValue(); // Capturamos la selección del menú desplegable
            String ubicacion = txtSalaUbicacion.getText();

            if (tipo == null) {
                txtResultado.setText("Error: Por favor, seleccione un tipo de sala en el menú desplegable.");
                return;
            }

            if (txtSalaCapacidad.getText() == null || txtSalaCapacidad.getText().trim().isEmpty()) {
                txtResultado.setText("Error: Por favor, ingrese el aforo de la sala.");
                return;
            }
            int capacidad = Integer.parseInt(txtSalaCapacidad.getText());

            // Invocación a la fachada
            OperationResult resultado = reservaApp.registrarSala(id, nombre, tipo, capacidad, ubicacion);
            txtResultado.setText(resultado.getMessage());
        } catch (NumberFormatException e) {
            txtResultado.setText("Error: la capacidad debe ser un número entero.");
        }
    }

    private void crearReserva() {
        try {
            String id = txtReservaId.getText();
            String salaId = txtReservaSalaId.getText();
            String fecha = txtFecha.getText();
            int horaInicio = Integer.parseInt(txtHoraInicio.getText());
            int horaFin = Integer.parseInt(txtHoraFin.getText());
            String tipoActividad = txtTipoActividad.getText();
            String responsable = txtResponsable.getText();
            int asistentes = Integer.parseInt(txtAsistentes.getText());

            Reserva nuevaReserva = new Reserva(id, salaId, fecha, horaInicio, horaFin, tipoActividad, responsable, asistentes);

            OperationResult resultado = reservaApp.crearReserva(nuevaReserva);
            txtResultado.setText(resultado.getMessage());
        } catch (NumberFormatException e) {
            txtResultado.setText("Error: las horas y la cantidad de asistentes deben ser números enteros.");
        }
    }

    private void consultarReserva() {
        OperationResult resultado = reservaApp.consultarReserva(txtReservaId.getText());
        txtResultado.setText(resultado.getMessage());
    }

    private void cancelarReserva() {
        OperationResult resultado = reservaApp.cancelarReserva(txtReservaId.getText());
        txtResultado.setText(resultado.getMessage());
    }

    private void listarSalas() {
        OperationResult resultado = reservaApp.listarSalas();
        txtResultado.setText(resultado.getMessage());
    }

    private void listarReservas() {
        OperationResult resultado = reservaApp.listarReservas();
        txtResultado.setText(resultado.getMessage());
    }

    private void generarReporte() {
        OperationResult resultado = reservaApp.generarReporte();
        txtResultado.setText(resultado.getMessage());
    }

    private void limpiarCampos() {
        txtSalaId.clear();
        txtSalaNombre.clear();
        cbSalaTipo.getSelectionModel().clearSelection(); // Limpia el menú desplegable
        txtSalaCapacidad.clear();
        txtSalaUbicacion.clear();

        txtReservaId.clear();
        txtReservaSalaId.clear();
        txtFecha.clear();
        txtHoraInicio.clear();
        txtHoraFin.clear();
        txtTipoActividad.clear();
        txtResponsable.clear();
        txtAsistentes.clear();

        txtResultado.clear();
    }
}