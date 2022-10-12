package org.puj.ingesoft.views.PantallasPaciente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;

public class ContOrientacion {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Button volverM;

    @FXML
    void volver(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.MENU_PACIENTE);
        stage.setScene(Main.getScenes().get(NombrePantallas.MENU_PACIENTE).getScene());
    }

    public void update() {

    }


}
