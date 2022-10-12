package org.puj.ingesoft.views.PantallasRegistro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.puj.ingesoft.controllers.usuario.ContRegistro;
import org.puj.ingesoft.entities.Medico;
import org.puj.ingesoft.entities.Paciente;
import org.puj.ingesoft.entities.Usuario;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class ContRegistroMedico {

    private enum CODIGOS_FECHA {
        VALIDA,
        E_DIA,
        E_MES,
        E_ANIO,
        E_PARSE,
        E_DESPUES_DE_HOY
    }

    Stage stage;
    private ContRegistro controller;

    public ContRegistroMedico(ContRegistro controller)
    {
        this.controller = controller;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private TextField Cedula;

    @FXML
    private TextField Contraseña;

    @FXML
    private TextField Edad;

    @FXML
    private TextField Especializacion;

    @FXML
    private TextField Nombre;

    @FXML
    private TextField TarjeTPro;

    @FXML
    private TextField año;

    @FXML
    private TextField dia;

    @FXML
    private TextField mes;


    @FXML
    private Button registrarse;

    @FXML
    private TextField username;



    @FXML
    void cancelar(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.REGISTRO_GENERAL);
        stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_GENERAL).getScene());
    }

    public Boolean verificaredad(String mes, String anio, String edad){
        boolean correcto = false;
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
        boolean numerico;
        Integer edadVeridica;
        if (numerico = edad.matches("[+-]?\\d*(\\.\\d+)?")) {
            edadVeridica = (java.lang.Integer.valueOf(format.format(date)) - java.lang.Integer.valueOf(anio));
            if (java.lang.Integer.valueOf(formatMonth.format(date)) < java.lang.Integer.valueOf(mes)) {
                if (edadVeridica - 1 == java.lang.Integer.valueOf(edad)) {
                    correcto = true;
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error de edad");
                    alert.setContentText("La edad no coincide con la fecha de nacimiento");
                    Optional<ButtonType> result;
                    result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        Main.setActiveScene(NombrePantallas.REGISTRO_MEDICO);
                        stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_MEDICO).getScene());
                    }
                }
            } else {
                if (edadVeridica == java.lang.Integer.valueOf(edad)) {
                    correcto = true;
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error de edad");
                    alert.setContentText("La edad no coincide con la fecha de nacimiento");
                    Optional<ButtonType> result;
                    result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        Main.setActiveScene(NombrePantallas.REGISTRO_MEDICO);
                        stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_MEDICO).getScene());
                    }
                }
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error de edad");
            alert.setContentText("Ingrese solo números");
            Optional<ButtonType> result;
            result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Main.setActiveScene(NombrePantallas.REGISTRO_MEDICO);
                stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_MEDICO).getScene());
            }
        }
        return correcto;
    }

    public boolean verificarTarjeta(String tar){
        boolean tarcorrecto = false;
        boolean numerico;
        Integer edadVeridica;
        if (numerico = tar.matches("[0-9]+")) {
            tarcorrecto = true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Algo salió mal");
            alert.setContentText("El telefono solo puede ser númerico");
            Optional<ButtonType> result;
            result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Main.setActiveScene(NombrePantallas.REGISTRO_MEDICO);
                stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_MEDICO).getScene());
            }
        }
        return tarcorrecto;
    }

    @FXML
    void regMed(ActionEvent event) throws IOException {

        switch(fechaValida(Integer.parseInt(dia.getText()), Integer.parseInt(mes.getText()), Integer.parseInt(año.getText())))
        {
            case E_ANIO:
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error de fecha");
                alert.setContentText("El año ingresado  no es valido");
                Optional<ButtonType> result;
                result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Main.setActiveScene(NombrePantallas.REGISTRO_MEDICO);
                    stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_MEDICO).getScene());
                }
                break;
            case E_DIA:
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error de fecha");
                alert.setContentText("El día ingresado  no es valido");
                result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Main.setActiveScene(NombrePantallas.REGISTRO_MEDICO);
                    stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_MEDICO).getScene());
                }
                break;
            case E_MES:
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error de fecha");
                alert.setContentText("El mes ingresado  no es valido");
                result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Main.setActiveScene(NombrePantallas.REGISTRO_MEDICO);
                    stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_MEDICO).getScene());
                }
                break;
            case E_DESPUES_DE_HOY:
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error de fecha");
                alert.setContentText("La fecha ingresada no puede ser en el futuro");
                result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Main.setActiveScene(NombrePantallas.REGISTRO_MEDICO);
                    stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_MEDICO).getScene());
                }
                break;
            case E_PARSE:
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error de fecha");
                alert.setContentText("La fecha no es válida. Intente nuevamente");
                result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Main.setActiveScene(NombrePantallas.REGISTRO_MEDICO);
                    stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_MEDICO).getScene());
                }
                break;
            case VALIDA:
                String verificarEdad_annio = año.getText();
                String verificarEdad_mess = mes.getText();
                String diaa = dia.getText();
                String edad = Edad.getText();
                String fech = verificarEdad_annio + "/" + verificarEdad_mess + "/" + diaa;
                String tar = TarjeTPro.getText();
                SimpleDateFormat format = new SimpleDateFormat("YYYY/MM/dd");
                Date date = new Date();
                //  verificaredad(verificarEdad_mess, verificarEdad_annio, edad);
                try {
                    date = format.parse(fech);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.println("date en string: " + format.format(date.getTime()));
                System.out.println("date: " + date);
                String newFormat = format.format(date.getTime());


                try {

                        if (verificaredad(verificarEdad_mess, verificarEdad_annio, edad)) {
                            if (verificarTarjeta(tar)) {

                                Medico medico = new Medico(Nombre.getText(), java.lang.Integer.valueOf(Cedula.getText()), date, java.lang.Integer.valueOf(Edad.getText()), Contraseña.getText(), username.getText(), tar, Especializacion.getText());

                                controller.NewMedico(medico);
                                System.out.println("Medico Registrado Correctamente");
                                alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText(null);
                                alert.setTitle("REGISTRO EXITOSO");
                                alert.setContentText("Usted ha sido registrado exitosamente");
                                result = alert.showAndWait();
                                if (result.isPresent() && result.get() == ButtonType.OK) {
                                    Main.setActiveScene(NombrePantallas.INICIO);
                                    stage.setScene(Main.getScenes().get(NombrePantallas.INICIO).getScene());
                                }
                            }
                        }

                } catch (Exception e) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error de registro");
                    alert.setContentText("Se produjo un error, verifique  que todos os campos esten llenos o que nus datos esten correctamente diligenciados");
                    result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        Main.setActiveScene(NombrePantallas.REGISTRO_MEDICO);
                        stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_MEDICO).getScene());
                    }
                }
            default:
                break;
        }
    }

    private ContRegistroMedico.CODIGOS_FECHA fechaValida(int dia, int mes, int anio)
    {
        SimpleDateFormat format = new SimpleDateFormat("YYYY/MM/dd");
        String fech = anio + "/" + mes + "/" + dia;
        Date fecha = null;
        try {
            fecha = format.parse(fech);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(fecha == null)
            return ContRegistroMedico.CODIGOS_FECHA.E_PARSE;

        if(fecha.after(Calendar.getInstance().getTime()))
            return ContRegistroMedico.CODIGOS_FECHA.E_DESPUES_DE_HOY;

        if(dia <= 0 )
            return ContRegistroMedico.CODIGOS_FECHA.E_DIA;
        if((mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) && dia > 31 )
            return ContRegistroMedico.CODIGOS_FECHA.E_DIA;
        else if((mes == 2) && dia > 29)
            return ContRegistroMedico.CODIGOS_FECHA.E_DIA;
        else if((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30)
            return ContRegistroMedico.CODIGOS_FECHA.E_DIA;

        if(mes <= 0 || mes > 12)
            return ContRegistroMedico.CODIGOS_FECHA.E_MES;

        if(anio <= 1900)
            return ContRegistroMedico.CODIGOS_FECHA.E_ANIO;

        return ContRegistroMedico.CODIGOS_FECHA.VALIDA;
    }

    public void update() {
    }
}
