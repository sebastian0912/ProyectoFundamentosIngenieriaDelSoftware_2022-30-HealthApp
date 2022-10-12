package org.puj.ingesoft.views.PantallasPaciente;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.puj.ingesoft.controllers.autenticacion.ContAutenticacion;
import org.puj.ingesoft.entities.Compra;
import org.puj.ingesoft.entities.Pago;
import org.puj.ingesoft.entities.Pago.METODO_PAGO;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;

import java.time.LocalDate;
import java.util.Timer;

public class ContPantallaPagar {

    private Stage stage;
    private ContAutenticacion autenticacion;
    private Compra compra = new Compra();
    private Timer t;

    public ContPantallaPagar(ContAutenticacion autenticacion){
        this.autenticacion = autenticacion;
        t = new java.util.Timer();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }


    @FXML
    private RadioButton RButPSE;

    @FXML
    private RadioButton RButPayU;

    @FXML
    private RadioButton RButTarjeta;

    @FXML
    private Button butCancelar;

    @FXML
    private Button butContinuar;

    @FXML
    private CheckBox cbUsarInfo;

    @FXML
    private DatePicker dpFechaExpiracion;

    @FXML
    private Label lblDescripcion;

    @FXML
    private Label lblFecha;

    @FXML
    private Label lblImpuesto;

    @FXML
    private Label lblValorNeto;

    @FXML
    private Label lblMensaje;

    @FXML
    private Label lblValorTotal;

    @FXML
    private Pane paneInfo;

    @FXML
    private Pane paneTarjeta;

    @FXML
    private PasswordField pfCodigoSeguridad;

    @FXML
    private Rectangle rectPSE;

    @FXML
    private Rectangle rectPayU;

    @FXML
    private Rectangle rectTarjeta;

    @FXML
    private TextField tfDireccionEnvio;

    @FXML
    private TextField tfNombreRecibe;

    @FXML
    private TextField tfNombreTitular;

    @FXML
    private TextField tfNumeroTarjeta;

    @FXML
    private TextField tfTelefono;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private AnchorPane painMain;

    @FXML
    private ScrollPane scroller;

    @FXML
    void cancelar(ActionEvent event) {
        t.cancel();
        Main.setActiveScene(NombrePantallas.CARRITO);
        stage.setScene(Main.getScenes().get(NombrePantallas.CARRITO).getScene());
    }

    @FXML
    void actualizarUsarInfo(ActionEvent event) {
        updateContinuar(null);
        if(cbUsarInfo.isSelected())
            paneInfo.setDisable(true);
        else if(!cbUsarInfo.isSelected())
            paneInfo.setDisable(false);
    }

    @FXML
    void continuar(Event event) {
        if(updateContinuar(null))
        {
            progressBar.setDisable(false);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Su pago ha sido exitoso");
            a.showAndWait();

            if(a.getResult() == null || a.getResult().equals(ButtonType.OK)) {
                autenticacion.getPacienteActivo().getCarrito().venderCarrito();
                compra.getPago().setEstadoPago(Pago.ESTADO_PAGO.ACEPTADO);
                compra.setEstado(Compra.ESTADO_COMPRA.ACEPTADA);
                autenticacion.getPacienteActivo().agregarCompra(compra);
                Main.setActiveScene(NombrePantallas.MENU_PACIENTE);
                stage.setScene(Main.getScenes().get(NombrePantallas.MENU_PACIENTE).getScene());
            }

        }
    }

    @FXML
    void escogerPayU(Event event) {
        actualizarMetodoPago(1);
    }

    @FXML
    void escogerPSE(Event event) {
        actualizarMetodoPago(2);
    }

    @FXML
    void escogerTarjeta(Event event) {
        actualizarMetodoPago(3);
    }

    @FXML
    boolean updateContinuar(KeyEvent event) {
        boolean enabled = true;
        String mensaje = "";
        if(!cbUsarInfo.isSelected())
        {
            if(tfDireccionEnvio.getText().equals("")){
                mensaje = "Falta ingresar el campo 'Dirección de Envío'";
                enabled = false;
            }

            if(tfNombreRecibe.getText().equals("")){
                mensaje = "Falta ingresar el campo 'Nombre de quien recibe'";
                enabled = false;
            }

            if(tfTelefono.getText().equals("")){
                mensaje = "Falta ingresar el campo 'Teléfono'";
                enabled = false;
            }
            else if(!tfTelefono.getText().matches("[0-9]+")){
                mensaje = "Telefono solo puede contener números";
                enabled = false;
            }
            else if(tfTelefono.getText().length() < 8){
                mensaje = "Telefono debe tener por lo menos 6 dígitos";
                enabled = false;
            }
        }

        if(compra.getPago().getMetodoPago() == METODO_PAGO.TARJETA)
        {
            if(tfNumeroTarjeta.getText().equals("")) {
                mensaje = "Falta ingresar el campo 'Numero Tarjeta'";
                enabled = false;
            }
            else if(!tfNumeroTarjeta.getText().matches("[0-9]*")){
                mensaje = "El número de tarjeta debe contener solo números";
                enabled = false;
            }
            else if(tfNumeroTarjeta.getText().length() != 16) {
                mensaje = "El número de tarjeta debe tener 16 dígitos";
                enabled = false;
            }

            if(tfNombreTitular.getText().equals("")){
                mensaje = "Falta ingresar el campo 'Nombre de Titular'";
                enabled = false;
            }

            if(dpFechaExpiracion.getValue() == null){
                mensaje = "Falta ingresar el campo 'Fecha de Expiracion'";
                enabled = false;
            }
            else if( dpFechaExpiracion.getValue() != null && dpFechaExpiracion.getValue().compareTo(LocalDate.now()) < 0){
                mensaje = "La fecha de expiración de la tarjeta ya pasó";
                enabled = false;
            }

            if(pfCodigoSeguridad.getText().equals("")){
                mensaje = "Falta ingresar el campo 'Codigo de Seguridad'";
                enabled = false;
            }
            else if(!pfCodigoSeguridad.getText().matches("[0-9]*")){
                mensaje = "El código de seguridad unicamente puede contener números";
                enabled = false;
            }
            else if(pfCodigoSeguridad.getText().length() != 3){
                mensaje = "El código de seguridad debe tener 3 dígitos";
                enabled = false;
            }
        }


        if(!enabled)
        {
            butContinuar.setDisable(true);
        }
        else
        {
            butContinuar.setDisable(false);
            mensaje = "";
        }
        lblMensaje.setText(mensaje);
        return enabled;

    }

    private void actualizarMetodoPago(int metodo)
    {
        switch(metodo){
            case 1:
                RButPayU.setSelected(true);
                RButPSE.setSelected(false);
                RButTarjeta.setSelected(false);
                compra.getPago().setMetodoPago(METODO_PAGO.SERVICIO_PAY_U);
                paneTarjeta.setDisable(true);
                break;
            case 2:
                RButPayU.setSelected(false);
                RButPSE.setSelected(true);
                RButTarjeta.setSelected(false);
                compra.getPago().setMetodoPago(METODO_PAGO.SERVICO_PSE);
                paneTarjeta.setDisable(true);
                break;
            case 3:
                RButPayU.setSelected(false);
                RButPSE.setSelected(false);
                RButTarjeta.setSelected(true);
                compra.getPago().setMetodoPago(METODO_PAGO.TARJETA);
                paneTarjeta.setDisable(false);
                break;
            default:
                break;
        }
    }





    public void update()
    {
        compra.getPago().setMetodoPago(METODO_PAGO.SERVICIO_PAY_U);
        progressBar.setDisable(true);
        if(compra != null)
        {
            lblDescripcion.setText(compra.getPago().getDescripcionCorta());
            lblFecha.setText(compra.getPago().getFechaDePago().toString());
            lblImpuesto.setText("$"+compra.getPago().getImpuestos()+ " COP");
            lblValorNeto.setText("$"+compra.getPago().getValorNeto()+ " COP");
            lblValorTotal.setText("$"+compra.getPago().getValorTotal() + " COP");
        }
    }

}
