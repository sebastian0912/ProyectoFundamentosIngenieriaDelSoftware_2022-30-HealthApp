package org.puj.ingesoft.views.PantallasMedico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.puj.ingesoft.entities.EntradaClinica;
import org.puj.ingesoft.entities.TipoEntradaClinica;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;
import java.net.URL;
import java.util.ResourceBundle;

public class ContEntradaClinica implements Initializable {

    private Stage stage;
    private EntradaClinica auxiliar;
    private ContCitasAgendadas contCitasAgendadas;
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ContEntradaClinica(ContCitasAgendadas contCitasAgendadas) {
        this.contCitasAgendadas = contCitasAgendadas;
    }

    public EntradaClinica getAuxiliar() {
        return auxiliar;
    }

    public void setAuxiliar(EntradaClinica auxiliar) {
        this.auxiliar = auxiliar;
    }

    @FXML
    private TextField textoDescripcíon;

    @FXML
    private TextField textoFarmaco;

    @FXML
    private Button botonTerminarEntrada;

    @FXML
    private Button botonVerHistorial;

    @FXML
    private Button botonVolver;

    @FXML
    private Label labelNombrePaciente;

    @FXML
    private Label labelIdPaciente;

    @FXML
    private ChoiceBox<TipoEntradaClinica> choiceBoxTipoEntrada;

    @FXML
    void terminarEntrada(ActionEvent event) {
        auxiliar = new EntradaClinica();
        auxiliar.setDescripcion(this.textoDescripcíon.getText());
        auxiliar.setFarmaco(this.textoFarmaco.getText());
        auxiliar.setEntrada(this.choiceBoxTipoEntrada.getValue());
        auxiliar.setFecha(contCitasAgendadas.getCitaAux().getFecha());
        auxiliar.setMedicoEntrada(contCitasAgendadas.getCitaAux().getMedico());
        auxiliar.setNombreMedico(auxiliar.getMedicoEntrada().getNombre());
        contCitasAgendadas.getCitaAux().getPaciente().getHistorialClinico().getListaEntradas().add(auxiliar);
        contCitasAgendadas.getAutenticacion().getMedicoActivo().getCitas().remove(contCitasAgendadas.getCitaAux().getFecha());
        Main.setActiveScene(NombrePantallas.CITAS_AGENDADAS);
        stage.setScene(Main.getScenes().get(NombrePantallas.CITAS_AGENDADAS).getScene());
        contCitasAgendadas.update();
    }

    @FXML
    void verHistorialClinico(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.VER_HISTORIAL_CLINICO);
        stage.setScene(Main.getScenes().get(NombrePantallas.VER_HISTORIAL_CLINICO).getScene());
    }

    @FXML
    void volver(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.CITAS_AGENDADAS);
        stage.setScene(Main.getScenes().get(NombrePantallas.CITAS_AGENDADAS).getScene());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        this.choiceBoxTipoEntrada.getItems().add(TipoEntradaClinica.CITA);
        this.choiceBoxTipoEntrada.getItems().add(TipoEntradaClinica.URGENCIAS);
    }

    public void update() {
        this.textoDescripcíon.setText(" ");
        this.textoFarmaco.setText(" ");
        this.labelNombrePaciente.setText(contCitasAgendadas.getCitaAux().getPaciente().getNombre());
        this.labelIdPaciente.setText(contCitasAgendadas.getCitaAux().getPaciente().getCedula()+"");
    }
}
