package org.puj.ingesoft.views.PantallasPaciente;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.puj.ingesoft.controllers.autenticacion.ContAutenticacion;
import org.puj.ingesoft.controllers.paciente.ContProductos;
import org.puj.ingesoft.entities.productos.Producto;
import javafx.event.ActionEvent;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;


public class ContVerMedicamentos implements Initializable {

    Stage stage;
    Producto productoEscogido;
    ContProductos controller;
    ContAutenticacion autenticacion;

    public ContVerMedicamentos(ContProductos controller, ContAutenticacion autenticacion)
    {
        this.controller = controller;
        this.autenticacion = autenticacion;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private TextField buscar;

    @FXML
    private Button butAgregarCarrito;

    @FXML
    private Button butVerCarrito;

    @FXML
    private Button butVolver;

    @FXML
    private TableView<Producto> listaMedicamentos;

    @FXML
    private TableColumn<Producto, String> colCodigo;

    @FXML
    private TableColumn<Producto, Producto.Tipo> colTipo;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, String> colLab;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private TableColumn<Producto, Integer> colDisponibles;

    @FXML
    private GridPane grid;

    @FXML
    private Label nomPantalla;

    @FXML
    private AnchorPane pantallaMedicamentos;

    @FXML
    private ChoiceBox<Producto.Tipo> tipoMedicamento;

    private TextInputDialog td;



    @FXML
    void buscar(KeyEvent event) {
        actualizarFiltro();
    }

    @FXML
    void agregarCarrito(ActionEvent event) {
        Optional<String> result = td.showAndWait();
        if(result.isPresent()) {
            int cant = Integer.parseInt(result.get());
            if(cant <= productoEscogido.getCantidadDisponible())
            {
                productoEscogido.venderUnidades(cant);
                autenticacion.getPacienteActivo().getCarrito().agregarProducto(productoEscogido, cant);
            }
            else
            {
                //TODO alerta que no hay suficientes unidades en stock
                System.out.println("No hay suficientes unidades... Unidades pedidas: " + cant + "   Unidades disponibles: " + productoEscogido.getCantidadDisponible());
            }
        }
    }

    @FXML
    void verCarrito(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.CARRITO);
        this.stage.setScene(Main.getScenes().get(NombrePantallas.CARRITO).getScene());
    }

    @FXML
    void volver(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.MENU_PACIENTE);
        this.stage.setScene(Main.getScenes().get(NombrePantallas.MENU_PACIENTE).getScene());
    }

    @FXML
    void filtrarTipo(ActionEvent event)
    {
        actualizarFiltro();
    }

    @FXML
    void seleccionarProducto(MouseEvent event) {
        this.productoEscogido = listaMedicamentos.getSelectionModel().getSelectedItem();
        if(this.productoEscogido != null)
            this.butAgregarCarrito.setDisable(false);
    }

    private Boolean validarInput() {
        if(!td.getEditor().getText().matches("[0-9]+"))
            return false;
        return true;
    }

    private Boolean validarCantidad()
    {
        if(Integer.parseInt(td.getEditor().getText()) > productoEscogido.getCantidadDisponible())
            return false;
        return true;
    }

    private void actualizarFiltro()
    {
        listaMedicamentos.getItems().clear();
        Collection<Producto> prods = controller.getProductosDisponibles().values();
        ArrayList<Producto> productos = new ArrayList<>(prods);
        if(tipoMedicamento.getSelectionModel().getSelectedItem() != Producto.Tipo.TODOS)
        {
            productos.removeIf(p ->
                    p.getTipoProducto() != tipoMedicamento.getSelectionModel().getSelectedItem()
            );
        }
        if(!buscar.getText().equals(""))
        {
            productos.removeIf(p ->{
                boolean resp = (!p.getCodigo().toUpperCase().contains(buscar.getText().toUpperCase()) &&
                        !p.getNombre().toUpperCase().contains(buscar.getText().toUpperCase()) &&
                        !p.getNomLaboratorio().toUpperCase().contains(buscar.getText().toUpperCase()));
                System.out.println("Resp: "+ resp + " " + buscar.getText() + " " + p.getNombre() + " " + p.getNomLaboratorio() + " " + p.getCodigo());
                return resp;
            }
            );
        }
        this.productoEscogido = null;
        this.butAgregarCarrito.setDisable(true);
        listaMedicamentos.getItems().addAll(productos);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Producto.Tipo t : Producto.Tipo.values())
        {
            this.tipoMedicamento.getItems().add(t);
        }
        this.tipoMedicamento.getSelectionModel().selectFirst();
        this.butAgregarCarrito.setDisable(true);

        // create a text input dialog
        td = new TextInputDialog("");

        // setHeaderText
        td.setHeaderText("Ingrese la cantidad a añadir");
        ((Button)td.getDialogPane().lookupButton(ButtonType.OK)).addEventFilter(ActionEvent.ACTION, ae -> {
            if(!validarInput()){
                td.setHeaderText("Ingrese la cantidad a añadir\nSolo puede añadir números!");
                ae.consume(); //not valid
            }
            else if (!validarCantidad()) {
                td.setHeaderText("Ingrese la cantidad a añadir\nSolo hay " + productoEscogido.getCantidadDisponible() + " disponibles!");
                ae.consume(); //not valid
            }
        });
    }

    public void update()
    {
        actualizarFiltro();
    }

}

