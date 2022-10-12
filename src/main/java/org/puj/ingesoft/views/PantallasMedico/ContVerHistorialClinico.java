package org.puj.ingesoft.views.PantallasMedico;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.puj.ingesoft.entities.Cita;
import org.puj.ingesoft.entities.EntradaClinica;
import org.puj.ingesoft.entities.TipoEntradaClinica;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ContVerHistorialClinico implements Initializable {

    private Stage stage;
    ContCitasAgendadas contCitasAgendadas;
    EntradaClinica entradaSeleccionada;
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ContVerHistorialClinico(ContCitasAgendadas contCitasAgendadas) {
        this.contCitasAgendadas = contCitasAgendadas;
    }

    @FXML
    private Label labelNombrePaciente;

    @FXML
    private Label labelIdPaciente;

    @FXML
    private Label labelAntecedentes;

    @FXML
    private Label labelDiagnostico;

    @FXML
    private Label labelDescripcion;

    @FXML
    private TableView<EntradaClinica> tablaEntradasClinicas;

    @FXML
    private TableColumn<Cita, Date> columnaFecha;

    @FXML
    private TableColumn<Cita, String> columnaMedico;

    @FXML
    private TableColumn<Cita, TipoEntradaClinica> columnaTipo;

    @FXML
    private Button botonVerEntradaClinica;

    @FXML
    private Button botonVolver;

    @FXML
    void verEntradaClinica(ActionEvent event) {
        entradaSeleccionada = this.tablaEntradasClinicas.getSelectionModel().getSelectedItem();
        if(entradaSeleccionada == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar una entrada clinica.");
            alert.showAndWait();
        }else{
            System.out.println("Entroooo");
            Main.setActiveScene(NombrePantallas.VER_ENTRADA_CLINICA);
            stage.setScene(Main.getScenes().get(NombrePantallas.VER_ENTRADA_CLINICA).getScene());
        }
    }

    @FXML
    void volver(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.ENTRADA_CLINICA);
        stage.setScene(Main.getScenes().get(NombrePantallas.ENTRADA_CLINICA).getScene());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        this.columnaFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
        this.columnaMedico.setCellValueFactory(new PropertyValueFactory("nombreMedico"));
        this.columnaTipo.setCellValueFactory(new PropertyValueFactory("entrada"));
    }

    public void update() {
        if(this.tablaEntradasClinicas == null){
            this.tablaEntradasClinicas = new TableView<EntradaClinica>();
        }
        this.tablaEntradasClinicas.getItems().clear();
        this.labelNombrePaciente.setText(contCitasAgendadas.getCitaAux().getPaciente().getNombre());
        this.labelIdPaciente.setText(contCitasAgendadas.getCitaAux().getPaciente().getCedula()+"");
        this.labelAntecedentes.setText(contCitasAgendadas.getCitaAux().getPaciente().getHistorialClinico().getAntecedentes());
        this.labelDiagnostico.setText(contCitasAgendadas.getCitaAux().getPaciente().getHistorialClinico().getDiagnostico());
        this.labelDescripcion.setText(contCitasAgendadas.getCitaAux().getPaciente().getHistorialClinico().getDescripcion());
        this.tablaEntradasClinicas.getItems().addAll(contCitasAgendadas.getCitaAux().getPaciente().getHistorialClinico().getListaEntradas());
    }
}
