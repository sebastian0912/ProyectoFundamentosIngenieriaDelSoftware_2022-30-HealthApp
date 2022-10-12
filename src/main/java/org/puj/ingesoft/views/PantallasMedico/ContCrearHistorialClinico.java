package org.puj.ingesoft.views.PantallasMedico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.puj.ingesoft.entities.HistorialClinico;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;

public class ContCrearHistorialClinico {

    private Stage stage;
    HistorialClinico historialAuxiliar = new HistorialClinico();
    ContCitasAgendadas contCitasAgendadas;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ContCrearHistorialClinico(ContCitasAgendadas contCitasAgendadas) {
        this.contCitasAgendadas = contCitasAgendadas;
    }

    @FXML
    private Label labelNombrePaciente;

    @FXML
    private Label labelIdPaciente;

    @FXML
    private TextField textoAntecedentes;

    @FXML
    private TextField textoDiagnostico;

    @FXML
    private TextField textoDescripcion;

    @FXML
    private Button botonCrearHistorialClinico;

    @FXML
    private Button botonVolver;

    @FXML
    void crearHistorial(ActionEvent event) {
        historialAuxiliar.setAntecedentes(this.textoAntecedentes.getText());
        historialAuxiliar.setDiagnostico(this.textoDiagnostico.getText());
        historialAuxiliar.setDescripcion(this.textoDescripcion.getText());
        contCitasAgendadas.getCitaAux().getPaciente().setHistorialClinico(historialAuxiliar);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Creacion Exitosa");
        alert.setContentText("El Historial Clinico del paciente se ha creado exitosamente.");
        alert.showAndWait();
        Main.setActiveScene(NombrePantallas.ENTRADA_CLINICA);
        stage.setScene(Main.getScenes().get(NombrePantallas.ENTRADA_CLINICA).getScene());
    }

    @FXML
    void volver(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.CITAS_AGENDADAS);
        stage.setScene(Main.getScenes().get(NombrePantallas.CITAS_AGENDADAS).getScene());
    }

    public void update() {
        this.labelNombrePaciente.setText(contCitasAgendadas.getCitaAux().getPaciente().getNombre());
        this.labelIdPaciente.setText(contCitasAgendadas.getCitaAux().getPaciente().getCedula()+"");

    }
}
