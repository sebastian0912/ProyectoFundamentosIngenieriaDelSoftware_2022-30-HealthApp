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

public class ContInicio {

    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Button Ini;

    @FXML
    private Button Registro;

    @FXML
    void Inicio(ActionEvent event) throws IOException {
        Main.setActiveScene(NombrePantallas.INICIO_GENERAL);
        stage.setScene(Main.getScenes().get(NombrePantallas.INICIO_GENERAL).getScene());
    }

    @FXML
    void Registrarse(ActionEvent event) throws IOException {
        Main.setActiveScene(NombrePantallas.REGISTRO_GENERAL);
        stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_GENERAL).getScene());
    }

    public void update() {
    }
}
