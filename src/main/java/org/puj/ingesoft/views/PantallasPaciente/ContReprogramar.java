package org.puj.ingesoft.views.PantallasPaciente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.puj.ingesoft.controllers.paciente.ContPaciente;
import org.puj.ingesoft.entities.Cita;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

public class ContReprogramar {

        Stage stage;
        ContPaciente contPaciente;
        Cita auxNueva;
        Cita auxVieja;

        public void setAuxVieja(Cita auxVieja) {
                this.auxVieja = auxVieja;
        }

        public ContReprogramar(ContPaciente contPaciente)
        {
                this.contPaciente = contPaciente;
        }

        public void setStage(Stage stage) {
                this.stage = stage;
        }
        @FXML
        private Button cancelarReprogramar;

        @FXML
        private TableColumn<Cita, Date> dateColumn;

        @FXML
        private TableColumn<Cita, String> medCentColumn;

        @FXML
        private TableColumn<Cita, String> medColumn;

        @FXML
        private Button reprogramarBoton;

        @FXML
        private Label reprogramarLabel;

        @FXML
        private TableView<Cita> tablaReprogramar;

        public void init() {

                pintarCitas();
                MouseEvent event = null;
                //seleccionarCitaexist(event);
        }

        public void pintarCitas(){
                limpiarCitas();
                Collection<Cita> citasDisponibles = contPaciente.getCitasDeXMedicos(15);
                System.out.println("Num citas para reprog: "+ citasDisponibles.size());
                this.tablaReprogramar.getItems().addAll(citasDisponibles);
        }

        public void limpiarCitas(){
                tablaReprogramar.getItems().clear();
        }
        @FXML
        void reprogramarCita(ActionEvent event) {
                if(auxNueva != null && auxVieja != null)
                {
                        contPaciente.reprogramarCita(auxNueva, auxVieja);
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Reprogramación de cita");
                        alert.setContentText("su cita será reprogramada");
                        alert.show();
                        Main.setActiveScene(NombrePantallas.CITAS_EXISTENTES);
                        stage.setScene(Main.getScenes().get(NombrePantallas.CITAS_EXISTENTES).getScene());
                }
                else
                {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Reprogramación de cita");
                        alert.setContentText("Debe seleccionar una cita primero");
                        alert.show();
                }


        }
        @FXML
        void salirReprogramar(ActionEvent event){
                Main.setActiveScene(NombrePantallas.CITAS_EXISTENTES);
                stage.setScene(Main.getScenes().get(NombrePantallas.CITAS_EXISTENTES).getScene());
        }

        @FXML
        void seleccionarCitaexist(MouseEvent event) throws IOException {
                System.out.println("Mouse event");
                auxNueva = this.tablaReprogramar.getSelectionModel().getSelectedItem();
                if (auxNueva != null){
                        String nombreMed = auxNueva.getNombreMedico();
                        System.out.println("nombre: " + nombreMed + "\n");
                        Date fechaCita = auxNueva.getFecha();
                        System.out.println("fecha: " + fechaCita + "\n");
                        String centroMed = auxNueva.getCentroMedico();
                        System.out.println("centroMed: " + nombreMed + "\n");
                        ActionEvent evento = null;
                        reprogramarCita(evento);
                }
        }


    public void update() {
    }
}

