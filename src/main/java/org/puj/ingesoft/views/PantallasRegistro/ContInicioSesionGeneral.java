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

public class ContInicioSesionGeneral {
    Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private Button IniMedico;

    @FXML
    private Button IniPaciente;

    @FXML
    void IniPaci(ActionEvent event){
        Main.setActiveScene(NombrePantallas.INICIO_AFILIADO);
        stage.setScene(Main.getScenes().get(NombrePantallas.INICIO_AFILIADO).getScene());
    }

    @FXML
    void InimEDICO(ActionEvent event){
        Main.setActiveScene(NombrePantallas.INICIO_MEDICO);
        stage.setScene(Main.getScenes().get(NombrePantallas.INICIO_MEDICO).getScene());
    }

    @FXML
    void volver(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.INICIO);
        stage.setScene(Main.getScenes().get(NombrePantallas.INICIO).getScene());
    }

    public void update() {
    }
}
