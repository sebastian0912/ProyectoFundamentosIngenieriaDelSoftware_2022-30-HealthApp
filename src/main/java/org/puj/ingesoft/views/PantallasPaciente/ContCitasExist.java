package org.puj.ingesoft.views.PantallasPaciente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.puj.ingesoft.controllers.paciente.ContPaciente;
import org.puj.ingesoft.controllers.autenticacion.ContAutenticacion;
import org.puj.ingesoft.entities.Cita;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

public class ContCitasExist {

    private ContPaciente contPaciente;
    private ContReprogramar contReprogramar;
    private ContAutenticacion autenticacion;
    private Stage stage;
    private Cita citaEscogida;

    public ContCitasExist(ContPaciente contPaciente, ContReprogramar contReprogramar, ContAutenticacion autenticacion)
    {
        this.contPaciente = contPaciente;
        this.contReprogramar = contReprogramar;
        this.autenticacion = autenticacion;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Button botonCancelar;

    @FXML
    private Button botonNuevaCita;

    @FXML
    private Button botonReprogramar;

    @FXML
    private Button botonSalir;

    @FXML
    private Label citasAgentadasLabel;

    @FXML
    private TableColumn<Cita, Date> fechCita;

    @FXML
    private TableColumn<Cita, String> medico;

    @FXML
    private TableColumn<Cita, String> modalidad;

    @FXML
    private Separator separador;

    @FXML
    private Group grupoBotonesCita;

    @FXML
    private TableView<Cita> tablaCitasExist;


    @FXML
    void cancelarCita(ActionEvent event) {
        if(citaEscogida != null && citaEscogida.getPaciente() != null)
        {
            contPaciente.desagendarCitaPciente(citaEscogida.getPaciente().getCedula(), citaEscogida.getFecha());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Cancelar cita");
            alert.setContentText("su cita sera cancelada");
            alert.show();
            pintarCitasExistentes();
        }
        else
        {
            System.out.println("Debe escoger una cita primero"); //TODO crear la alerta que notifica esto
        }
    }

    @FXML
    void nuevaCita(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.GESTIONAR_CITA);
        stage.setScene(Main.getScenes().get(NombrePantallas.GESTIONAR_CITA).getScene());
    }

    @FXML
    void reprogramarCita(ActionEvent event) throws IOException {
        if(citaEscogida != null && citaEscogida.getPaciente() != null)
        {
            Main.setActiveScene(NombrePantallas.REPROGRAMAR_CITA);
            stage.setScene(Main.getScenes().get(NombrePantallas.REPROGRAMAR_CITA).getScene());
            contReprogramar.pintarCitas();
            contReprogramar.setAuxVieja(citaEscogida); //Se envia la cita escogida para reprogramar a la siguiente pantalla
        }
        else
        {
            //TODO crear alerta de que esta null
            System.out.println("Cita no escogida para repr");
        }
    }

    @FXML
    void salir(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.MENU_PACIENTE);
        stage.setScene(Main.getScenes().get(NombrePantallas.MENU_PACIENTE).getScene());
    }

    public void pintarCitasExistentes(){
        if(autenticacion.getPacienteActivo()!=null)
        {
            tablaCitasExist.getItems().clear();
            Collection<Cita> citasPaciente = contPaciente.getCitasPaciente(autenticacion.getPacienteActivo().getCedula());
            tablaCitasExist.getItems().addAll(citasPaciente);
        }
    }

    public void limpiarVentana(){
        tablaCitasExist.getItems().clear();
    }


    @FXML
    void seleccionarCitaexist(MouseEvent event) throws IOException {
        citaEscogida = this.tablaCitasExist.getSelectionModel().getSelectedItem(); //con este objeto puede desde el controlador de paciente reprogramar o cancelar la cita

    }

    public void update() {
        pintarCitasExistentes();
    }
}
