package org.puj.ingesoft.views.PantallasPaciente;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.puj.ingesoft.controllers.paciente.ContPaciente;
import org.puj.ingesoft.controllers.autenticacion.ContAutenticacion;
import org.puj.ingesoft.controllers.paciente.ContPaciente;
import org.puj.ingesoft.entities.Compra;
import org.puj.ingesoft.entities.Paciente;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;
import org.puj.ingesoft.views.ViewMaker;

import java.io.IOException;
import java.util.UUID;

public class ContPantallaPagoGeneral  {
    private Stage stage;
    private ContPaciente contPaciente ;
    private ContAutenticacion autenticacion;
    private ContPantallaPagar contPantallaPagar;

    @FXML
    private Label identificacionUsuario;

    @FXML
    private Label nombreUsuario;

    public ContPantallaPagoGeneral(ContAutenticacion contAutenticacion, ContPantallaPagar contPantallaPagar) {
        super();
        this.autenticacion = contAutenticacion;
        this.contPantallaPagar = contPantallaPagar;
    }

    @FXML
    void pagoAfiliacion(ActionEvent event) {
        UUID referencia = UUID.randomUUID();
        Compra c = new Compra(150.0, referencia.toString(), Compra.ESTADO_COMPRA.PENDIENTE);
        contPantallaPagar.setCompra(c);
        Main.setActiveScene(NombrePantallas.PAGAR);
        stage.setScene(Main.getScenes().get(NombrePantallas.PAGAR).getScene());
    }

    @FXML
    void pagoCitaMedica(ActionEvent event) {
        UUID referencia = UUID.randomUUID();
        Compra c = new Compra(15.0, referencia.toString(), Compra.ESTADO_COMPRA.PENDIENTE);
        contPantallaPagar.setCompra(c);
        Main.setActiveScene(NombrePantallas.PAGAR);
        stage.setScene(Main.getScenes().get(NombrePantallas.PAGAR).getScene());
    }

    @FXML
    void volver(ActionEvent event){
        Main.setActiveScene(NombrePantallas.MENU_PACIENTE);
        stage.setScene(Main.getScenes().get(NombrePantallas.MENU_PACIENTE).getScene());
    }

    public Stage getStage() { return stage; }

    public void setStage(Stage stage) { this.stage = stage; }

    public void update() {
        if(autenticacion.getPacienteActivo() != null){
            nombreUsuario.setText(autenticacion.getPacienteActivo().getNombre());
            identificacionUsuario.setText(autenticacion.getPacienteActivo().getCedula().toString());
        }


    }
}
