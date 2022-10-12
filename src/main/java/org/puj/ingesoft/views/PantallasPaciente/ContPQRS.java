package org.puj.ingesoft.views.PantallasPaciente;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;

public class ContPQRS {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Button botonCancelar;

    @FXML
    private Button botonEnviarR;

    @FXML
    private TextArea diag;

    @FXML
    private Label identificacionUsuario;

    @FXML
    private Label nombreUsuario;

    @FXML
    void cancelarRemi(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.MENU_PACIENTE);
        stage.setScene(Main.getScenes().get(NombrePantallas.MENU_PACIENTE).getScene());
    }

    @FXML
    void enviarRemision(ActionEvent event) {

    }

    public void update() {

    }

}
