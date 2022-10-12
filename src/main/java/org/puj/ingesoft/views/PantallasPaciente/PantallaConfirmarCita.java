package org.puj.ingesoft.views.PantallasPaciente;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.puj.ingesoft.views.Pantalla;
import org.puj.ingesoft.views.ViewMaker;

import java.io.IOException;

public class PantallaConfirmarCita extends Pantalla implements ViewMaker {
    private final static String FXML_PATH = "./confirmaciónCita.fxml";
    private Stage stage;
    private ContConfirmaCita controller;
    /**
     * Must inject a stage
     */
    public PantallaConfirmarCita(Stage stage, ContConfirmaCita controller) throws IOException {
        this.controller = controller;
        this.stage = stage;
        makeScene();
    }


    @Override
    public void makeScene() throws IOException {
        controller.setStage(stage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH));
        loader.setController(controller);
        Parent root = loader.load();
        super.scene = new Scene(root);
    }

    @Override
    public void update() {
        controller.update();
    }
}