package org.puj.ingesoft.views.PantallasPaciente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.puj.ingesoft.controllers.autenticacion.ContAutenticacion;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;

import java.io.IOException;

public class ContGestionarCita {
    private Stage stage;
    private ContDisponibilidad contDisponibilidad;
    private ContAutenticacion autenticacion;

    public ContGestionarCita(ContDisponibilidad contDisponibilidad,  ContAutenticacion autenticacion)
    {
        this.contDisponibilidad = contDisponibilidad;
        this.autenticacion = autenticacion;
    }

    public ContGestionarCita() {

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Label ModCita; //TODO cambiar esto para que sea un dropdown y se pueda seleccionar entre presencial o virtual para luego usar como filtro

    @FXML
    private Button disponibilidad;

    @FXML
    private Label nameCentro;

    @FXML
    private Label namePaciente;

    @FXML
    private TextField razonCita;

    @FXML
    private Button volver;

    @FXML
    private Button botonVerCitas;

    @FXML
    void escribirRazonMed(ActionEvent event) {

    }
    @FXML
    void verDisponibilidad(ActionEvent event) throws IOException, InterruptedException {
        if(razonCita.getText() != "")
        {
            Main.setActiveScene(NombrePantallas.DISPONIBILIDAD);
            stage.setScene(Main.getScenes().get(NombrePantallas.DISPONIBILIDAD).getScene());
            contDisponibilidad.setMotivo(razonCita.getText());
            contDisponibilidad.setModalidad(ModCita.getText());
            contDisponibilidad.setCentroMedico(nameCentro.getText());
        }
        else
        {
            System.out.println("Debe poner un motivo");//TODO crear alerta para avisar esto
        }

    }

    @FXML
    void volverCitas(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.CITAS_EXISTENTES);
        stage.setScene(Main.getScenes().get(NombrePantallas.CITAS_EXISTENTES).getScene());
    }


    public void update() {
        if(autenticacion.getPacienteActivo() != null)
        {
            namePaciente.setText(autenticacion.getPacienteActivo().getNombre());
            nameCentro.setText("Sanitas");//TODO El centro médico lo debe escoger la persona al escoger de las citas disponibles
            ModCita.setText("Presencial"); //TODO La modalidad se debería escoger y aplicar como un filtro al buscar las citas disponibles
        }
    }
}
