package org.puj.ingesoft.views.PantallasPaciente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.puj.ingesoft.controllers.paciente.ContPaciente;
import org.puj.ingesoft.controllers.autenticacion.ContAutenticacion;
import org.puj.ingesoft.entities.Cita;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;

import java.util.Collection;
import java.util.Date;

public class ContDisponibilidad {

    private ContPaciente contPaciente;
    private ContAutenticacion contAutenticacion;
    private ContConfirmaCita contConfirmarCita;
    private Cita aux;

    private String motivo;
    private String modalidad;
    private String centroMedico;

    public ContDisponibilidad(ContPaciente contPaciente, ContAutenticacion contAutenticacion, ContConfirmaCita contConfirmarCita)
    {
        this.contPaciente = contPaciente;
        this.contAutenticacion = contAutenticacion;
        this.contConfirmarCita = contConfirmarCita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getCentroMedico() {
        return centroMedico;
    }

    public void setCentroMedico(String centroMedico) {
        this.centroMedico = centroMedico;
    }

    Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }


    @FXML
    private Button aceptarCita; //TODO cambiar texto para que no sea aceptar sino ver info

    @FXML
    private ScrollBar deslizar;

    @FXML
    private Button volverAmenu;

    @FXML
    private TableView<Cita> tablaDisponibilidad;

    @FXML
    private TableColumn<Cita, Date> date = new TableColumn<Cita, Date>("Fecha");
    @FXML
    private TableColumn<Cita, String> nameCentMed = new TableColumn<Cita, String>("CentroMedico");

    @FXML
    private TableColumn<Cita, String> nameMed = new TableColumn<Cita, String>("Nombre");

    public void pintar(){
        limpiarVentanaDisponibilidad();
        if(contAutenticacion.getPacienteActivo() != null)
        {
            Collection<Cita> citasDisponibles = contPaciente.getCitasDeXMedicos(15);
            System.out.println("Metodo pintar iniciado cd");
            tablaDisponibilidad.getItems().addAll(citasDisponibles);
            MouseEvent seleccionar = null;
            seleccionarCita(seleccionar);
        }
        
    }


    public void limpiarVentanaDisponibilidad(){
        tablaDisponibilidad.getItems().clear();
    }

    @FXML
    void volverMenu(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.GESTIONAR_CITA);
        stage.setScene(Main.getScenes().get(NombrePantallas.GESTIONAR_CITA).getScene());
    }

    @FXML
    void seleccionarCita(MouseEvent event){
        aux = this.tablaDisponibilidad.getSelectionModel().getSelectedItem();
    }



    public void infoCita(ActionEvent actionEvent) {
        if(aux != null) {
            aux.setMotivoCita(motivo);
            aux.setModalidad(modalidad);
            aux.setCentroMedico(centroMedico);
            contConfirmarCita.setInfo(aux);
            Main.setActiveScene(NombrePantallas.CONFIRMAR_CITA);
            stage.setScene(Main.getScenes().get(NombrePantallas.CONFIRMAR_CITA).getScene());
        }
        else
        {
            System.out.println("Debe seleccionar una cita primero"); //TODO crear alerta para notificar esto
        }
    }

    public void update() {
        pintar();
    }
}
