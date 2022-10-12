package org.puj.ingesoft.views.PantallasRegistro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.puj.ingesoft.controllers.autenticacion.ContAutenticacion;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.IntStream;


public class ContInicioSesionMedico {

    ContAutenticacion contAutenticacion;

    Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ContInicioSesionMedico(ContAutenticacion contAutenticacion)
    {
        this.contAutenticacion = contAutenticacion;
    }
    @FXML
    private PasswordField contrasena;

    @FXML
    private Button inicioM;

    @FXML
    private TextField username;

    @FXML
    void iniciarM(ActionEvent event) {
        try{
            if(contAutenticacion.buscarmedico(username.getText(), contrasena.getText())){
                Main.setActiveScene(NombrePantallas.MENU_MEDICO);
                stage.setScene(Main.getScenes().get(NombrePantallas.MENU_MEDICO).getScene());
            }
            else{
                System.out.println("Contraseña o username incorrectos. Intente nuevamente o registrese en el sistema");
            }
        }catch (Exception e){
            System.out.println("Medico no registrado");
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
