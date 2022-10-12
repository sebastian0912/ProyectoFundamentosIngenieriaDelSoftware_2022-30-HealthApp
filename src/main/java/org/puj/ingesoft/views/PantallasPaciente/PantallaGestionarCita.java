package org.puj.ingesoft.views.PantallasPaciente;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.puj.ingesoft.controllers.autenticacion.ContAutenticacion;
import org.puj.ingesoft.views.Pantalla;
import org.puj.ingesoft.views.ViewMaker;

import java.io.IOException;

public class PantallaGestionarCita extends Pantalla implements ViewMaker {
    private final static String FXML_PATH = "./pantallaGestionarCita.fxml";
    private Stage stage;
    private ContGestionarCita controller;
    /**
     * Must inject a stage
     */
    public PantallaGestionarCita(Stage stage, ContGestionarCita controller) throws IOException {
        this.stage = stage;
        this.controller = controller;
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