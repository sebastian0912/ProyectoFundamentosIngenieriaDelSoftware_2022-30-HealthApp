package org.puj.ingesoft.views.PantallasPaciente;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.puj.ingesoft.views.Pantalla;
import org.puj.ingesoft.views.PantallasPaciente.*;
import org.puj.ingesoft.views.ViewMaker;

import java.io.IOException;

public class PantallaPagoGeneral extends Pantalla implements ViewMaker {
    private final static String FXML_PATH = "./pantallaPagoGeneral.fxml";
    private Stage stage;
    private ContPantallaPagoGeneral controller;

    public PantallaPagoGeneral(Stage stage, ContPantallaPagoGeneral contPantallaPagoGeneral) throws IOException {
        this.controller = contPantallaPagoGeneral;
        this.stage = stage;
        makeScene();
    }

    @Override
    public void update()  {
        controller.update();
    }

    @Override
    public void makeScene() throws IOException {
        controller.setStage(stage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH));
        loader.setController(controller);
        Parent root = loader.load();
        super.scene = new Scene(root);
    }
}
