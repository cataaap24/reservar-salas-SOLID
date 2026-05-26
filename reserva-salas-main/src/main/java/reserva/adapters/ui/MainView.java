package reserva.adapters.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import reserva.entities.TipoSala;
import reserva.usecases.dto.OperationResult;
import reserva.usecases.services.ReservaApp;

public class MainView {

    @FXML private TextField txtNombre;
    @FXML private TextField txtAforo;
    @FXML private ComboBox<TipoSala> cbTipo;

    private final ReservaApp reservaApp = new ReservaApp();

    @FXML
    public void initialize() {
        cbTipo.getItems().setAll(TipoSala.values());
    }

    @FXML
    public void onGuardarClick(ActionEvent event) {
        try {
            String nombre = txtNombre.getText();
            TipoSala tipo = cbTipo.getValue();

            if (txtAforo.getText() == null || txtAforo.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Por favor, ingrese el aforo de la sala.");
            }
            int aforo = Integer.parseInt(txtAforo.getText());

            if (tipo == null) {
                throw new IllegalArgumentException("Por favor, seleccione un tipo de sala.");
            }

            OperationResult resultado = reservaApp.registrarSala(nombre, tipo, aforo);

            if (resultado.isSuccess()) {
                mostrarMensaje("Éxito", resultado.getMessage(), Alert.AlertType.INFORMATION);
                limpiarCampos();
            } else {
                mostrarMensaje("Error de Validación", resultado.getMessage(), Alert.AlertType.WARNING);
            }

        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "El aforo debe ser un número entero válido.", Alert.AlertType.ERROR);
        } catch (IllegalArgumentException e) {
            mostrarMensaje("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void mostrarMensaje(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtAforo.clear();
        cbTipo.getSelectionModel().clearSelection();
    }
}