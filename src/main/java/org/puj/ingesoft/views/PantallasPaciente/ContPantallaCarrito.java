package org.puj.ingesoft.views.PantallasPaciente;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;
import org.puj.ingesoft.controllers.autenticacion.ContAutenticacion;
import org.puj.ingesoft.entities.Compra;
import org.puj.ingesoft.entities.Paciente;
import org.puj.ingesoft.entities.productos.Producto;
import org.puj.ingesoft.views.Main;
import org.puj.ingesoft.views.NombrePantallas;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.*;

public class ContPantallaCarrito implements Initializable{

    Stage stage;
    Paciente pacienteAct;
    ContAutenticacion contAutenticacion;
    ContPantallaPagar contPagar;
    Producto productoEscogido;
    static Integer contCantidad = 0;
    static Integer contProductos = 0;

    public ContPantallaCarrito(ContAutenticacion contAutenticacion, ContPantallaPagar contPagar) {
        this.contAutenticacion = contAutenticacion;
        this.contPagar = contPagar;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    @FXML
    private Pane PaneCantidad;

    @FXML
    private Button butComprarCarrito;

    @FXML
    private Button butVaciarCarrito;

    @FXML
    private Button butVolver;

    @FXML
    private Button butMas;

    @FXML
    private Button butMenos;

    @FXML
    private TableView<Pair<Producto, Integer>> listaMeds;

    @FXML
    private TableColumn<Pair<Producto, Integer>, String> colLab;

    @FXML
    private TableColumn<Pair<Producto, Integer>, String> colNombre;

    @FXML
    private TableColumn<Pair<Producto, Integer>, String> colPrecio;

    @FXML
    private TableColumn<Pair<Producto, Integer>, String> colCantidad;

    @FXML
    private TableColumn<Pair<Producto, Integer>, String> colTipo;

    @FXML
    private GridPane grid;

    @FXML
    private Label nomPantalla;

    @FXML
    private Label numItems;

    @FXML
    private Label labelPrecioTot;

    @FXML
    void escoger(Event event) {
        if(listaMeds.getSelectionModel().getSelectedItem() != null)
            this.productoEscogido = listaMeds.getSelectionModel().getSelectedItem().getKey();
        if(this.productoEscogido != null)
        {
            this.PaneCantidad.setDisable(false);
            numItems.setText(String.valueOf(pacienteAct.getCarrito().getListaProductos().get(productoEscogido)));
        }
    }

    @FXML
    void agregarItem(ActionEvent event) {
        Paciente p = contAutenticacion.getPacienteActivo();
        if(productoEscogido.getCantidadDisponible() >= 1)
        {
            p.getCarrito().agregarProducto(productoEscogido, 1);
            Pair<Producto, Integer> selected = listaMeds.getSelectionModel().getSelectedItem();
            update();
            numItems.setText(String.valueOf(pacienteAct.getCarrito().getListaProductos().get(productoEscogido)));
            listaMeds.getSelectionModel().select(selected);
            calcularPrecioTotal();
        }
        else
        {
            //TODO alertar que no hay suficiente de este producto en stock
        }
    }

    @FXML
    void quitarItem(ActionEvent event) {
        boolean continuar = true;
        if(contAutenticacion.getPacienteActivo().getCarrito().getListaProductos().get(productoEscogido) == 1)
        {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Está a punto de quitar la última unidad de este producto de su carrito\nDesea continuar?");
            a.showAndWait();
            if(a.getResult().getButtonData().isDefaultButton())
            {
                continuar = true;
                update();
                this.PaneCantidad.setDisable(true);
            }
            else
            {
                continuar = false;
            }
        }
        if(continuar)
        {
            pacienteAct = contAutenticacion.getPacienteActivo();
            if(pacienteAct.getCarrito().quitarProducto(productoEscogido))
            {
                Pair<Producto, Integer> selected = listaMeds.getSelectionModel().getSelectedItem();
                update();
                if(pacienteAct.getCarrito().getListaProductos().containsKey(productoEscogido))
                    numItems.setText(String.valueOf(pacienteAct.getCarrito().getListaProductos().get(productoEscogido)));
                else
                    numItems.setText("0");
                listaMeds.getSelectionModel().select(selected);
            }
            else
            {
                //TODO alertar que no hay suficiente de este producto en el carrito (sería un estado de error)
            }
        }

    }

    @FXML
    void comprarCarrito(ActionEvent event) {
        if(!pacienteAct.getCarrito().isEmpty())
        {
            UUID uuid;
            uuid = UUID.randomUUID();
            Compra c = new Compra(contAutenticacion.getPacienteActivo().getCarrito().valorTotal(), uuid.toString(), Compra.ESTADO_COMPRA.PENDIENTE);
            c.setItemsComprados(new HashMap<>());
            for(Producto p: pacienteAct.getCarrito().getListaProductos().keySet())
            {
                c.getItemsComprados().put(p.getNombre() + " (" + pacienteAct.getCarrito().getListaProductos().get(p) + ")", pacienteAct.getCarrito().getListaProductos().get(p) *p.getPrecioUnidad());

            }
            c.generarPago();
            contPagar.setCompra(c);
            Main.setActiveScene(NombrePantallas.PAGAR);
            stage.setScene(Main.getScenes().get(NombrePantallas.PAGAR).getScene());
        }
        else
        {
            //TODO notificar que el carrito está vacío
            System.out.println("Carro vacío");
        }
    }

    @FXML
    void vaciarCarrito(ActionEvent event) {
        pacienteAct = contAutenticacion.getPacienteActivo();
        if(pacienteAct != null){
            listaMeds.getItems().clear();
            pacienteAct.getCarrito().vaciarCarrito();
            System.out.println("Carrito de compras del paciente activo :" + pacienteAct.getCarrito().getListaProductos().size());
        }
    }

    @FXML
    void volver(ActionEvent event) {
        Main.setActiveScene(NombrePantallas.VER_MEDICAMENTOS);
        this.stage.setScene(Main.getScenes().get(NombrePantallas.VER_MEDICAMENTOS).getScene());
    }

    public void calcularPrecioTotal(){
        double total = 0d;
        if(pacienteAct != null){
            total = pacienteAct.getCarrito().valorTotal();
        }
        labelPrecioTot.setText("$" + total + " COP");
    }

    public void llenarTableView(){
        pacienteAct = contAutenticacion.getPacienteActivo();
        if(pacienteAct != null){
        }
        listaMeds.getItems().clear();
        ArrayList<Pair<Producto,Integer>> prods = new ArrayList<>();
        for(Producto p : pacienteAct.getCarrito().getListaProductos().keySet())
        {
            prods.add(new Pair<>(p, pacienteAct.getCarrito().getListaProductos().get(p)));
        }
        listaMeds.getItems().addAll(prods);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colLab.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pair<Producto,Integer>, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Pair<Producto,Integer>, String> p) {
                if (p.getValue() != null) {
                    return new SimpleStringProperty(p.getValue().getKey().getNomLaboratorio());
                } else {
                    return new SimpleStringProperty("<Sin Lab>");
                }
            }
        });
        colNombre.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pair<Producto,Integer>, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Pair<Producto,Integer>, String> p) {
                if (p.getValue() != null) {
                    return new SimpleStringProperty(p.getValue().getKey().getNombre());
                } else {
                    return new SimpleStringProperty("<Sin Nombre>");
                }
            }
        });
        colPrecio.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pair<Producto,Integer>, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Pair<Producto,Integer>, String> p) {
                if (p.getValue() != null) {
                    return new SimpleStringProperty(p.getValue().getKey().getPrecioUnidad().toString());
                } else {
                    return new SimpleStringProperty("<Sin Precio>");
                }
            }
        });
        colTipo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pair<Producto,Integer>, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Pair<Producto,Integer>, String> p) {
                if (p.getValue() != null) {
                    return new SimpleStringProperty(p.getValue().getKey().getTipoEnString());
                } else {
                    return new SimpleStringProperty("<Sin Tipo>");
                }
            }
        });
        colCantidad.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pair<Producto,Integer>, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Pair<Producto,Integer>, String> p) {
                if (p.getValue() != null) {
                    return new SimpleStringProperty(p.getValue().getValue().toString());
                } else {
                    return new SimpleStringProperty("<Sin Cantidad>");
                }
            }
        });
    }

    public void update() {
        pacienteAct = contAutenticacion.getPacienteActivo();
        llenarTableView();
        calcularPrecioTotal();

    }

    public Paciente getPacienteAct() {
        return pacienteAct;
    }

    public void setPacienteAct(Paciente pacienteAct) {
        this.pacienteAct = pacienteAct;
    }
}
