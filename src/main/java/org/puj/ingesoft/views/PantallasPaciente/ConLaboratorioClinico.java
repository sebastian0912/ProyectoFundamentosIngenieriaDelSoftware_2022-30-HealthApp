package org.puj.ingesoft.views.PantallasPaciente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.puj.ingesoft.controllers.paciente.ContPaciente;
import org.puj.ingesoft.controllers.autenticacion.ContAutenticacion;
import org.puj.ingesoft.entities.ExamenLab;
import org.puj.ingesoft.entities.Paciente;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;


public class ConLaboratorioClinico {
        private Stage stage;
        private ContPaciente contPaciente = new ContPaciente();
        private ContAutenticacion autenticacion;


        public ConLaboratorioClinico(ContPaciente contPaciente, ContAutenticacion contAutenticacion) {
                this.contPaciente = contPaciente;
                this.autenticacion = contAutenticacion;
        }
        public Stage getStage() {
                return stage;
        }


        public void setStage(Stage stage) {
                this.stage = stage;
        }

        @FXML
        private TableView<ExamenLab> tablaExamenes;

        @FXML
        private TableColumn<ExamenLab, Date> Fecha;

        @FXML
        private TableColumn<ExamenLab, UUID> NumeroExamen;

        @FXML
        private Button acepta;

        @FXML
        private Button cancela;

        @FXML
        private Label identificacionUsuario;

        @FXML
        private Label nombreUsuario;


        @FXML
        void aceptar(ActionEvent event) {

        }

        @FXML
        void cancelar(ActionEvent event) {
                for(Paciente pac: contPaciente.getPacientes().values()){
                        if(Integer.valueOf(identificacionUsuario.getText()) == pac.getCedula() ){
                                for(ExamenLab exam: pac.getListaExamenes().values()){
                                        System.out.println("Exam "+ exam.getCodigo()+"Fecha: "+ exam.getFechaToma() +"Resultado"+ exam.getResultado());
                                }

                        }
                }
                Main.setActiveScene(NombrePantallas.MENU_PACIENTE);
                stage.setScene(Main.getScenes().get(NombrePantallas.MENU_PACIENTE).getScene());
        }


        public void limpiarVentanaDisponibilidad(){
                tablaExamenes.getItems().clear();
        }

        public void pintar() {
                limpiarVentanaDisponibilidad();

                Collection<ExamenLab> citasDisponibles = contPaciente.getExamenLab(Integer.valueOf(autenticacion.getPacienteActivo().getCedula()));
                System.out.println("Metodo pintar iniciado lc");
                tablaExamenes.getItems().addAll(citasDisponibles);
                MouseEvent seleccionar = null;
        }


        public void update() {
                if(autenticacion.getPacienteActivo() != null)
                        pintar();
                nombreUsuario.setText(autenticacion.getPacienteActivo().getNombre());
                identificacionUsuario.setText(autenticacion.getPacienteActivo().getCedula().toString());
        }
}


