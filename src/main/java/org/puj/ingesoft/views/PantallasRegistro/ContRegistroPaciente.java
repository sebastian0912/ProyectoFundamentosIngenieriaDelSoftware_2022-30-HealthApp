package org.puj.ingesoft.views.PantallasRegistro;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.puj.ingesoft.controllers.persistencia.ContDatos;
import org.puj.ingesoft.controllers.usuario.ContRegistro;
import org.puj.ingesoft.entities.*;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;



import java.io.*;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.*;

public class ContRegistroPaciente {

    private enum CODIGOS_FECHA {
        VALIDA,
        E_DIA,
        E_MES,
        E_ANIO,
        E_PARSE,
        E_DESPUES_DE_HOY
    }
    private ContRegistro controller;
    Stage stage;
    HashMap<String, TextField> componentes = new HashMap<String, TextField>();

    public ContRegistroPaciente(ContRegistro controller) {
        this.controller = controller;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private TextField CedulaP;
    

    @FXML
    private TextField DireccionP;

    @FXML
    private TextField EdadP;

    @FXML
    private TextField NombreP;

    @FXML
    private TextField TelefonoP;

    @FXML
    private TextField Username;

    @FXML
    private TextField año;

    @FXML
    private TextField dia;

    @FXML
    private TextField mes;

    @FXML
    private Button registrarse;

    @FXML
    private TextField rhP;

    @FXML
    private TextField cra;



    @FXML
    private TextField num3;

    @FXML
    private TextField tipodir;

    @FXML
    private TextField nume1;

    @FXML
    private TextField nume2;

    @FXML
    private PasswordField Contrasena;




    @FXML
    void ingresarFechaNacimiento(ActionEvent event) {

    }

    public void componentes() {
        componentes.put("cedula", CedulaP);
        componentes.put("contrasena", Contrasena);
        componentes.put("direccion", DireccionP);
        componentes.put("edad", EdadP);
        componentes.put("nombre", NombreP);
        componentes.put("telefono", TelefonoP);
        componentes.put("username", Username);
        componentes.put("año", año);
        componentes.put("dia", dia);
        componentes.put("mes", mes);
        componentes.put("rh", rhP);
    }

    public Integer parsear(String a) {
        Integer number = Integer.parseInt(a);
        return number;
    }

    public void limpiarCampo(TextField text) {
        text.setText(null);
    }

    public void buscarClave(HashMap componentes, String clave) {
        TextField textfield;
        Object field = null;
        for (Object txt : componentes.keySet()) {
            if (componentes.containsKey(clave)) {
                field = componentes.get(clave);
            }
        }
        textfield = (TextField) field;
        limpiarCampo(textfield);
    }
    public boolean rh_valido(String rh) {
        boolean rhvalido = false;
        ArrayList<String> rhs = new ArrayList<String>();
        //String rh_aux = "";
        rhs.add("A");
        rhs.add("B");
        rhs.add("O");
        rhs.add("AB");
        for (int i = 0; i < rhs.size(); i++) {
            if (rh.equals(rhs.get(i))) {
                rhvalido = true;
            }
        }
              /*  Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error de tipo sanguineo");
                alert.setContentText("Los grupos sanguineos validos son A, B, O, AB. Ingrese su grupo sanguineo en mayúscula");
                Optional<ButtonType> result;
                result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {

                  // stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_PACIENTE));
                }
                */
                return rhvalido;
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
                        Main.setActiveScene(NombrePantallas.REGISTRO_PACIENTE);
                        stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_PACIENTE).getScene());
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
                        Main.setActiveScene(NombrePantallas.REGISTRO_PACIENTE);
                        stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_PACIENTE).getScene());
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
                Main.setActiveScene(NombrePantallas.REGISTRO_PACIENTE);
                stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_PACIENTE).getScene());
            }
        }
        return correcto;
    }

    public boolean verificarTel(String tel){
        boolean telcorrecto = false;
        boolean numerico;
        Integer edadVeridica;
        if (numerico = tel.matches("[+-]?\\d*(\\.\\d+)?")) {
           telcorrecto = true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Algo salió mal");
            alert.setContentText("El telefono solo puede ser númerico");
            Optional<ButtonType> result;
            result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Main.setActiveScene(NombrePantallas.REGISTRO_PACIENTE);
                stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_PACIENTE).getScene());
            }
        }
        return telcorrecto;
    }

    public String direccionPac(String cll, String num1, String num2, String num3)
    {
        String formatoDireccion = cll + num1 + num2 + "-" + num3;
        return formatoDireccion;
    }

    @FXML
    void RegistraPaciente(ActionEvent event) throws IOException {
        componentes();

        String annio = año.getText();
        String mess = mes.getText();
        String diaa = dia.getText();
        String rh = new String();
         rh = rhP.getText();
         String edad = EdadP.getText();
         String tel = TelefonoP.getText();
         String cra = tipodir.getText();
         String num1 = nume1.getText();
         String num2 = nume2.getText();
         String nume3 = num3.getText();
         String direc = new String();
        switch(fechaValida(Integer.parseInt(diaa), Integer.parseInt(mess), Integer.parseInt(annio)))
        {
            case E_ANIO:
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error de fecha");
                alert.setContentText("El año ingresado  no es valido");
                Optional<ButtonType> result;
                result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Main.setActiveScene(NombrePantallas.REGISTRO_PACIENTE);
                    stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_PACIENTE).getScene());
                }
                break;
            case E_DIA:
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error de fecha");
                alert.setContentText("El día ingresado  no es valido");
                result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Main.setActiveScene(NombrePantallas.REGISTRO_PACIENTE);
                    stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_PACIENTE).getScene());
                }
                break;
            case E_MES:
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error de fecha");
                alert.setContentText("El mes ingresado  no es valido");
                result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Main.setActiveScene(NombrePantallas.REGISTRO_PACIENTE);
                    stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_PACIENTE).getScene());
                }
                break;
            case E_DESPUES_DE_HOY:
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error de fecha");
                alert.setContentText("La fecha ingresada no puede ser en el futuro");
                result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Main.setActiveScene(NombrePantallas.REGISTRO_PACIENTE);
                    stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_PACIENTE).getScene());
                }
                break;
            case E_PARSE:
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error de fecha");
                alert.setContentText("La fecha no es válida. Intente nuevamente");
                result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Main.setActiveScene(NombrePantallas.REGISTRO_PACIENTE);
                    stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_PACIENTE).getScene());
                }
                break;
            case VALIDA:
                String fech = annio + "/" + mess + "/" + diaa;
                String verificarEdad_annio = annio;
                String verificarEdad_mess = mess;
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
                    if ( rh_valido(rh)) {
                        if (verificaredad(verificarEdad_mess, verificarEdad_annio, edad)) {
                            if (verificarTel(tel)) {
                                direc = direccionPac(cra, num1, num2, nume3);
                                System.out.println("direccion: " + direc);
                                Paciente pac = new Paciente(NombreP.getText(), java.lang.Integer.valueOf(CedulaP.getText()), date, java.lang.Integer.valueOf(EdadP.getText()), Contrasena.getText(), Username.getText(), TelefonoP.getText(), direc, rhP.getText());
                                String rutaPaciente = "./PacientesRegistrados.json";
                                controller.NewPaciente(pac);
                                System.out.println("Paciente Registrado Correctamente");
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
                    }else{
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setHeaderText(null);
                        alerta.setTitle("Error de tipo sanguineo");
                        alerta.setContentText("Los grupos sanguineos validos son A, B, O, AB. Ingrese su grupo sanguineo en mayúscula");
                        Optional<ButtonType> resulto;
                        resulto = alerta.showAndWait();
                        if (resulto.isPresent() && resulto.get() == ButtonType.OK) {

                            // stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_PACIENTE));
                        }
                    }
                } catch (Exception e) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error de registro");
                    alert.setContentText("Se produjo un error, verifique  que todos os campos esten llenos o que nus datos esten correctamente diligenciados");
                    result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        Main.setActiveScene(NombrePantallas.REGISTRO_PACIENTE);
                        stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_PACIENTE).getScene());
                    }
                }
            default:
                break;
        }

    }


    private CODIGOS_FECHA fechaValida(int dia, int mes, int anio)
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
            return CODIGOS_FECHA.E_PARSE;

        if(fecha.after(Calendar.getInstance().getTime()))
            return CODIGOS_FECHA.E_DESPUES_DE_HOY;

        if(dia <= 0 )
            return CODIGOS_FECHA.E_DIA;
        if((mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) && dia > 31 )
            return CODIGOS_FECHA.E_DIA;
        else if((mes == 2) && dia > 29)
            return CODIGOS_FECHA.E_DIA;
        else if((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30)
            return CODIGOS_FECHA.E_DIA;

        if(mes <= 0 || mes > 12)
            return CODIGOS_FECHA.E_MES;

        if(anio <= 1900)
            return CODIGOS_FECHA.E_ANIO;

        return CODIGOS_FECHA.VALIDA;
    }

    @FXML
    void cancelar(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.REGISTRO_GENERAL);
        stage.setScene(Main.getScenes().get(NombrePantallas.REGISTRO_GENERAL).getScene());
    }

    public void update() {
    }

}

