package org.puj.ingesoft.views.PantallasRegistro;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.puj.ingesoft.views.Pantalla;
import org.puj.ingesoft.views.ViewMaker;

import java.io.IOException;

public class PantallaRegistroPaciente extends Pantalla implements ViewMaker{
    private final static String FXML_PATH = "./PantallaRegistroPaciente.fxml";
    private Stage stage;
    private ContRegistroPaciente controller;
    /**
     * Must inject a stage
     */
    public PantallaRegistroPaciente(Stage stage, ContRegistroPaciente controller) throws IOException {
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