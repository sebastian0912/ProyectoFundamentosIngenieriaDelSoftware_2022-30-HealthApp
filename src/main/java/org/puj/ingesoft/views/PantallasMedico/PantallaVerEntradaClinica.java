package org.puj.ingesoft.views.PantallasMedico;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.puj.ingesoft.views.Pantalla;
import org.puj.ingesoft.views.ViewMaker;

import java.io.IOException;

public class PantallaVerEntradaClinica extends Pantalla implements ViewMaker {
    private final static String FXML_PATH = "./PantallaVerEntradaClinica.fxml";
    private Stage stage;
    private ContVerEntradaClinica controller;

    /**
     * Must inject a stage
     */
    public PantallaVerEntradaClinica(Stage stage, ContVerEntradaClinica controller) throws IOException {
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