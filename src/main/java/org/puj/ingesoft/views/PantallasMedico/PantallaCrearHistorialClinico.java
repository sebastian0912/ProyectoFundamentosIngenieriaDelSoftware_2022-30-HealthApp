package org.puj.ingesoft.views.PantallasMedico;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.puj.ingesoft.views.Pantalla;
import org.puj.ingesoft.views.ViewMaker;
import java.io.IOException;

public class PantallaCrearHistorialClinico extends Pantalla implements ViewMaker {
    private final static String FXML_PATH = "./PantallaCrearHistorialClinico.fxml";
    private Stage stage;
    private ContCrearHistorialClinico controller;

    /**
     * Must inject a stage
     */
    public PantallaCrearHistorialClinico(Stage stage, ContCrearHistorialClinico controller) throws IOException {
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