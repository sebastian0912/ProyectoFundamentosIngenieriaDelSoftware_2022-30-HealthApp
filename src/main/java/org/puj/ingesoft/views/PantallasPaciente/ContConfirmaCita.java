package org.puj.ingesoft.views.PantallasPaciente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.puj.ingesoft.controllers.autenticacion.ContAutenticacion;
import org.puj.ingesoft.entities.Cita;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ContConfirmaCita {
     private Stage stage;
     private Cita citaActual;
     private ContAutenticacion autenticacion;

    public ContConfirmaCita(ContAutenticacion autenticacion)
     {
         this.autenticacion = autenticacion;
     }

     public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCitaActual(Cita c){
        this.citaActual = c;
    }

     @FXML
     private Label centroMedicoCita;

     @FXML
     private Label fechcaCita;

     @FXML
     private Label infoCentMed;

     @FXML
     private Label infoFechaCita;

     @FXML
     private Label infoModCita;

     @FXML
     private Label infoNombreMed;

     @FXML
     private Label medicoCita;

     @FXML
     private Label modalidad;

     @FXML
     private Label titleInfoCita;

     @FXML
     private Button volverM;


    @FXML
     void volver(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.DISPONIBILIDAD);
        stage.setScene(Main.getScenes().get(NombrePantallas.DISPONIBILIDAD).getScene());
     }

     @FXML
     void confirmarCita(ActionEvent event){
         autenticacion.getPacienteActivo().agregarCita(citaActual);
         Main.setActiveScene(NombrePantallas.CITAS_EXISTENTES);
         stage.setScene(Main.getScenes().get(NombrePantallas.CITAS_EXISTENTES).getScene());
     }


     public void setInfo(Cita c)
     {
         SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
         citaActual = c;
         centroMedicoCita.setText(c.getCentroMedico());
         infoFechaCita.setText(format.format(c.getFecha()));
     }

    public void update() {
    }
}

