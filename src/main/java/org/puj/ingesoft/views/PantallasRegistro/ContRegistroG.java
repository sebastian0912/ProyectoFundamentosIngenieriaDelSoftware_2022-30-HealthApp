package org.puj.ingesoft.views.PantallasRegistro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;

import java.io.IOException;
import java.net.URL;

public class ContRegistroG {
    Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private Button RegistroM;

    @FXML
    private Button RegistroP;

    @FXML
    void RegistrarseP(ActionEvent event){
        Main.setActiveScene(NombrePantallas.REGISTRO_PACIENTE);
        stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_PACIENTE).getScene());
    }

    @FXML
    void RegistroM(ActionEvent event){
        Main.setActiveScene(NombrePantallas.REGISTRO_MEDICO);
        stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_MEDICO).getScene());
    }

    @FXML
    void volver(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.INICIO);
        stage.setScene(Main.getScenes().get(NombrePantallas.INICIO).getScene());
    }

    public void update() {
    }
}
