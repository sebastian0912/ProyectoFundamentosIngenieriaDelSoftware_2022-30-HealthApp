package org.puj.ingesoft.views.PantallasMedico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.puj.ingesoft.controllers.autenticacion.ContAutenticacion;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContMenuMedico{

    private Stage stage;
    private ContAutenticacion autenticacion;
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ContMenuMedico(ContAutenticacion autenticacion){
        this.autenticacion = autenticacion;
    }

    @FXML
    private Button botonCitasAsig;

    @FXML
    private Button botonLab;

    @FXML
    private Button botonPacientes;

    @FXML
    private TextField identificacionUsuario;

    @FXML
    private TextField nombreUsuario;

    @FXML
    private Label identi;

    @FXML
    private Label nombre;



    @FXML
    void accederLaboratorio(ActionEvent event) throws IOException {
        Main.setActiveScene(NombrePantallas.REMISION);
        stage.setScene(Main.getScenes().get(NombrePantallas.REMISION).getScene());
    }

    @FXML
    void verCitasAsig(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.CITAS_AGENDADAS);
        stage.setScene(Main.getScenes().get(NombrePantallas.CITAS_AGENDADAS).getScene());
    }

    @FXML
    void verPacientes(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {
        autenticacion.setMedicoActivo(null);
        Main.setActiveScene(NombrePantallas.INICIO);
        stage.setScene(Main.getScenes().get(NombrePantallas.INICIO).getScene());
    }

    public void update() {
        nombre.setText(autenticacion.getMedicoActivo().getNombre());
        identi.setText(autenticacion.getMedicoActivo().getCedula().toString());
    }
}
