package org.puj.ingesoft.views.PantallasRegistro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.puj.ingesoft.controllers.paciente.ContPaciente;
import org.puj.ingesoft.controllers.autenticacion.ContAutenticacion;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;

import java.io.IOException;
import java.util.Optional;


public class ContInicioSesionAfiliado{
    private ContPaciente controller;
    private ContAutenticacion contAutenticacion;


    Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ContInicioSesionAfiliado(ContPaciente controller, ContAutenticacion contAutenticacion)
    {
        this.controller = controller;
        this.contAutenticacion = contAutenticacion;
    }

    @FXML
    private PasswordField contrasena;

    @FXML
    private Button inicio;

    @FXML
    private TextField username;


    @FXML
    void iniciar(ActionEvent event) throws IOException {

        if (contAutenticacion.buscarPaciente(username.getText(), contrasena.getText())){
            contAutenticacion.setPacienteActivo(contAutenticacion.buscarPaciente2(username.getText(), contrasena.getText()));
            Main.setActiveScene(NombrePantallas.MENU_PACIENTE);
            stage.setScene(Main.getScenes().get(NombrePantallas.MENU_PACIENTE).getScene());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error de autenticación");
            alert.setContentText("Usuario no registrado");
            Optional<ButtonType>result;
            result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK)
            {
                Main.setActiveScene(NombrePantallas.REGISTRO_GENERAL);
                stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_GENERAL).getScene());
            }
            System.out.println("Contraseña o username incorrectos. Intente nuevamente o registrese en el sistema");
        }

    }


    @FXML
    void cancelar(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.INICIO_GENERAL);
        stage.setScene(Main.getScenes().get(NombrePantallas.INICIO_GENERAL).getScene());
    }


    public void update() {
        contrasena.setText("");
    }
}



