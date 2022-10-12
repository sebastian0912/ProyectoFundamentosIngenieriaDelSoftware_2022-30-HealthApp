package org.puj.ingesoft.views.PantallasPaciente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.puj.ingesoft.controllers.autenticacion.ContAutenticacion;
import org.puj.ingesoft.entities.Paciente;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;
import org.puj.ingesoft.views.Pantalla;

import java.io.IOException;

public class ContMenuPaciente {

    private Stage stage;

    private ContAutenticacion autenticacion;

    public ContMenuPaciente(ContAutenticacion autenticacion) {
        super();
        this.autenticacion = autenticacion;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Button botonCitas;

    @FXML
    private Button botonLab;

    @FXML
    private Button botonMedicamentos;

    @FXML
    private Button botonOrientacion;

    @FXML
    private Button botonPago;

    @FXML
    private Button botonServicioC;

    @FXML
    private Label identificacionUsuario;

    @FXML
    private Label nombreUsuario;

    @FXML
    void accederLaboratorio(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.LABORATORIOCLINICO);
        stage.setScene(Main.getScenes().get(NombrePantallas.LABORATORIOCLINICO).getScene());
    }

    @FXML
    void accederOrientacion(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.ORIENTACION);
        Pantalla pantalla = Main.getScenes().get(NombrePantallas.ORIENTACION);
        stage.setScene(pantalla.getScene());
    }

    @FXML
    void accederServicioCliente(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.PQRS);
        Pantalla pantalla = Main.getScenes().get(NombrePantallas.PQRS);
        stage.setScene(pantalla.getScene());
    }

    @FXML
    void comprarMedicamentos(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.VER_MEDICAMENTOS);
        stage.setScene(Main.getScenes().get(NombrePantallas.VER_MEDICAMENTOS).getScene());
    }

    @FXML
    void pagar(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.PAGOS_GENERAL);
        Scene s = Main.getScenes().get(NombrePantallas.PAGOS_GENERAL).getScene();
        stage.setScene(s);
    }

    @FXML
    void sacarCita(ActionEvent event) throws IOException { //TODO cambiar nombre del método a ver citas existentes o algo parecido
        Main.setActiveScene(NombrePantallas.CITAS_EXISTENTES);
        Scene s = Main.getScenes().get(NombrePantallas.CITAS_EXISTENTES).getScene();
        stage.setScene(s);
    }

    @FXML
    void logout(ActionEvent event) {
        autenticacion.setPacienteActivo(null);
        Main.setActiveScene(NombrePantallas.INICIO);
        stage.setScene(Main.getScenes().get(NombrePantallas.INICIO).getScene());
    }


    public void update() {
        Paciente p = autenticacion.getPacienteActivo();
        if(p!=null)
        {
            nombreUsuario.setText(p.getNombre());
            identificacionUsuario.setText(p.getCedula().toString());
        }
    }
}