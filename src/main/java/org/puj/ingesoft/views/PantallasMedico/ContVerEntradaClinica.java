package org.puj.ingesoft.views.PantallasMedico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;

public class ContVerEntradaClinica {

    private Stage stage;
    ContVerHistorialClinico contVerHistorialClinico;

    public ContVerEntradaClinica(ContVerHistorialClinico contVerHistorialClinico) {
        this.contVerHistorialClinico = contVerHistorialClinico;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Button botonVolver;

    @FXML
    private Label labelEntrada;

    @FXML
    private Label labelFecha;

    @FXML
    private Label labelDescripcion;

    @FXML
    private Label labelFarmacos;

    @FXML
    private Label labelMedico;

    @FXML
    void volver(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.VER_HISTORIAL_CLINICO);
        stage.setScene(Main.getScenes().get(NombrePantallas.VER_HISTORIAL_CLINICO).getScene());
    }

    public void update(){
        this.labelMedico.setText(contVerHistorialClinico.entradaSeleccionada.getNombreMedico());
        this.labelEntrada.setText(contVerHistorialClinico.entradaSeleccionada.getEntrada().toString());
        this.labelFecha.setText(contVerHistorialClinico.entradaSeleccionada.getFecha().toString());
        this.labelDescripcion.setText(contVerHistorialClinico.entradaSeleccionada.getDescripcion());
        this.labelFarmacos.setText(contVerHistorialClinico.entradaSeleccionada.getFarmaco());
    }
}
