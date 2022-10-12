package org.puj.ingesoft.views.PantallasMedico;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.puj.ingesoft.controllers.autenticacion.ContAutenticacion;
import org.puj.ingesoft.controllers.medico.ContMedico;
import org.puj.ingesoft.entities.Cita;

import javafx.event.ActionEvent;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;

import java.util.*;

public class ContCitasAgendadas {

    private Stage stage;
    private Cita citaAux;
    private ContMedico contMedico;
    private ContAutenticacion autenticacion;
    public void setStage(Stage stage){
        this.stage = stage;
    }

    public ContCitasAgendadas(ContMedico contMedico, ContAutenticacion autenticacion){
        this.contMedico = contMedico;
        this.autenticacion = autenticacion;
    }

    public Cita getCitaAux() {
        return citaAux;
    }

    public void setCitaAux(Cita citaAux) {
        this.citaAux = citaAux;
    }

    public ContMedico getContMedico() {
        return contMedico;
    }

    public void setContMedico(ContMedico contMedico) {
        this.contMedico = contMedico;
    }

    public ContAutenticacion getAutenticacion() {
        return autenticacion;
    }

    public void setAutenticacion(ContAutenticacion autenticacion) {
        this.autenticacion = autenticacion;
    }

    @FXML
    private TableView<Cita> tablaCitas;

    @FXML
    private TableColumn<Cita, Date> columnaFecha;

    @FXML
    private TableColumn<Cita, String> columnaPaciente;

    @FXML
    private Button botonEntradaClinica;

    @FXML
    private Button botonVolver;

    @FXML
    void seleccionarEntradaClinica(ActionEvent event) {
        citaAux = this.tablaCitas.getSelectionModel().getSelectedItem();
        if(citaAux == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar una cita.");
            alert.showAndWait();
        }else{
            if(citaAux.getPaciente().getHistorialClinico() == null){
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setHeaderText(null);
                alert2.setTitle("Atencion!");
                alert2.setContentText("El paciente de la cita seleccionada no tiene historial clinico. Se creará el historial clinico del paciente a continuacion.");
                alert2.showAndWait();
                Main.setActiveScene(NombrePantallas.CREAR_HISTORIAL_CLINICO);
                stage.setScene(Main.getScenes().get(NombrePantallas.CREAR_HISTORIAL_CLINICO).getScene());
            }else{
                Main.setActiveScene(NombrePantallas.ENTRADA_CLINICA);
                stage.setScene(Main.getScenes().get(NombrePantallas.ENTRADA_CLINICA).getScene());
            }
        }
    }

    @FXML
    void seleccionarVolver(ActionEvent event) {
        tablaCitas.getItems().clear();
        Main.setActiveScene(NombrePantallas.MENU_MEDICO);
        stage.setScene(Main.getScenes().get(NombrePantallas.MENU_MEDICO).getScene());
    }

    public void update() {
        if(tablaCitas == null)
            tablaCitas = new TableView<Cita>();
        if(autenticacion.getMedicoActivo()!=null)
        {
            tablaCitas.getItems().clear();
            Collection<Cita> citasMed = contMedico.getCitasAsignadas(autenticacion.getMedicoActivo().getCedula());
            tablaCitas.getItems().addAll(citasMed);
        }
    }
}

