package org.puj.ingesoft.views.PantallasMedico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.puj.ingesoft.controllers.paciente.ContPaciente;
import org.puj.ingesoft.controllers.autenticacion.ContAutenticacion;
import org.puj.ingesoft.entities.ExamenLab;
import org.puj.ingesoft.entities.Paciente;
import org.puj.ingesoft.entities.Remision;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;

import java.util.UUID;

public class ContRemision {
    ContAutenticacion contAutenticacion;
    ContPaciente paca = new ContPaciente();
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private Button botonCancelar;

    @FXML
    private Button botonEnviarR;

    @FXML
    private TextField cedMed;

    @FXML
    private TextField denPac;

    @FXML
    private TextArea diag;

    @FXML
    private TextArea mot;

    @FXML
    void cancelarRemi(ActionEvent event) {

    }

    @FXML
    void enviarRemision(ActionEvent event) {
        //TODO checkear que no haya campos vacíos/inválidos
        UUID idRemision = UUID.randomUUID();
        Remision rem = new Remision(idRemision ,java.lang.Integer.valueOf(cedMed.getText()), java.lang.Integer.valueOf(denPac.getText()), mot.getText(), diag.getText());
        ExamenLab examenLab = new ExamenLab(idRemision, java.lang.Integer.valueOf(denPac.getText()), rem);
        for(Paciente pac: paca.getPacientes().values()){
            System.out.println("Nombre"+pac.getNombre());
            if(Integer.valueOf(denPac.getText()) == pac.getCedula()){
                pac.getListaExamenes().put(idRemision, examenLab);
            }
        }
        Main.setActiveScene(NombrePantallas.INICIO);
        stage.setScene(Main.getScenes().get(NombrePantallas.INICIO).getScene());
        /*
        contAutenticacion.buscarPaciente3(Integer.valueOf(denPac.getText())).getListaExamenes().put(String.valueOf(idRemision), examenLab);
        */
    }

    public void update() {
    }
}
