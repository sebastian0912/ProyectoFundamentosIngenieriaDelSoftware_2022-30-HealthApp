package org.puj.ingesoft.views;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.puj.ingesoft.controllers.ContControladores;
import org.puj.ingesoft.views.PantallasMedico.*;
import org.puj.ingesoft.views.PantallasPaciente.*;
import org.puj.ingesoft.views.PantallasPaciente.ContVerMedicamentos;
import org.puj.ingesoft.views.PantallasPaciente.PantallaVerMedicamentos;
import org.puj.ingesoft.views.PantallasRegistro.*;

//Importar todas las pantallas


/**
 * Builds all scenes and display the main one.
 *
 * Código modificado de: https://github.com/ksnortum/javafx-multi-scene
 * @author Knute Snortum
 * @version 2018-05-24
 */
public class Main extends Application {

    private final static String ICON_NAME = "./icono.png";

    /** Holds the various scenes to switch between */
    private static Map<NombrePantallas, Pantalla> scenes = new HashMap<>();
    private static NombrePantallas activeScene;

    @Override
    public void start(Stage stage) {
        final ContControladores controller = new ContControladores();

        //Se crean e inicializan todos los controladores
        ContInicio contInicio = new ContInicio();
        ContInicioSesionAfiliado contInicioAfiliado = new ContInicioSesionAfiliado(controller.getContPaciente(), controller.getContAutenticacion());
        ContInicioSesionGeneral contInicioGeneral = new ContInicioSesionGeneral();
        ContInicioSesionMedico contInicioMedico = new ContInicioSesionMedico(controller.getContAutenticacion());
        ContRegistroMedico contRegistroMedico = new ContRegistroMedico(controller.getContRegistro());
        ContRegistroG contRegistroGeneral = new ContRegistroG();
        ContRegistroPaciente contRegistroPaciente = new ContRegistroPaciente(controller.getContRegistro());
        ConLaboratorioClinico contLaboratorioClinico = new ConLaboratorioClinico(controller.getContPaciente(), controller.getContAutenticacion());

        ContReprogramar contReprogramar = new ContReprogramar(controller.getContPaciente());
        ContCitasExist contCitasExistentes = new ContCitasExist(controller.getContPaciente(), contReprogramar, controller.getContAutenticacion());
        ContConfirmaCita contConfirmarCita = new ContConfirmaCita(controller.getContAutenticacion());
        ContDisponibilidad contDisponibilidad = new ContDisponibilidad(controller.getContPaciente(), controller.getContAutenticacion(), contConfirmarCita);
        ContGestionarCita contGestionarCita = new ContGestionarCita(contDisponibilidad, controller.getContAutenticacion());
        ContMenuPaciente contMenuPaciente = new ContMenuPaciente(controller.getContAutenticacion());
        ContOrientacion contOrientacion = new ContOrientacion();
        ContPQRS contPQRS = new ContPQRS();

        ContCitasAgendadas contCitasAgendadas = new ContCitasAgendadas(controller.getContMedico(), controller.getContAutenticacion());
        ContMenuMedico contMenuMedico = new ContMenuMedico(controller.getContAutenticacion());
        ContCrearHistorialClinico contCrearHistorialClinico = new ContCrearHistorialClinico(contCitasAgendadas);
        ContVerHistorialClinico contVerHistorialClinico = new ContVerHistorialClinico(contCitasAgendadas);
        ContVerEntradaClinica contVerEntradaClinica = new ContVerEntradaClinica(contVerHistorialClinico);
        ContEntradaClinica contEntradaClinica = new ContEntradaClinica(contCitasAgendadas);
        ContRemision contRemision = new ContRemision();

        ContVerMedicamentos contVerMedicamentos = new ContVerMedicamentos(controller.getContProductos(), controller.getContAutenticacion());
        ContPantallaPagar contPantallaPagar = new ContPantallaPagar(controller.getContAutenticacion());
        ContPantallaCarrito contPantallaCarrito = new ContPantallaCarrito(controller.getContAutenticacion(), contPantallaPagar);
        ContPantallaPagoGeneral  contPantallaPagoGeneral = new ContPantallaPagoGeneral(controller.getContAutenticacion(), contPantallaPagar);

        controller.getContDatos().cargarInventario();
        controller.getContDatos().leerPacientes();
        controller.getContDatos().leerMedicos();

        try{
            // Se crea y guarda todas las escenas que se necesitan, pasandoles los controladores que necesitarán
            scenes.put(NombrePantallas.INICIO, new PantallaInicio(stage, contInicio));
            scenes.put(NombrePantallas.INICIO_AFILIADO, new PantallaInicioAfiliado(stage, contInicioAfiliado));
            scenes.put(NombrePantallas.INICIO_GENERAL, new PantallaInicioGeneral(stage, contInicioGeneral));
            scenes.put(NombrePantallas.INICIO_MEDICO, new PantallaInicioMedico(stage, contInicioMedico));
            scenes.put(NombrePantallas.REGISTRO_GENERAL, new PantallaRegistroGeneral(stage, contRegistroGeneral));
            scenes.put(NombrePantallas.REGISTRO_MEDICO, new PantallaRegistroMedico(stage, contRegistroMedico));
            scenes.put(NombrePantallas.REGISTRO_PACIENTE, new PantallaRegistroPaciente(stage, contRegistroPaciente));
            scenes.put(NombrePantallas.LABORATORIOCLINICO, new PantallaLaboratorio(stage, contLaboratorioClinico));
            scenes.put(NombrePantallas.MENU_PACIENTE, new PantallaMenuPaciente(stage, contMenuPaciente));
            scenes.put(NombrePantallas.CITAS_EXISTENTES, new PantallaCitasExistentes(stage, contCitasExistentes));
            scenes.put(NombrePantallas.CONFIRMAR_CITA, new PantallaConfirmarCita(stage, contConfirmarCita));
            scenes.put(NombrePantallas.DISPONIBILIDAD, new PantallaDisponibilidad(stage, contDisponibilidad));
            scenes.put(NombrePantallas.GESTIONAR_CITA, new PantallaGestionarCita(stage, contGestionarCita));
            scenes.put(NombrePantallas.REPROGRAMAR_CITA, new PantallaReprogramar(stage, contReprogramar));
            scenes.put(NombrePantallas.ORIENTACION, new PantallaOrientacion(stage, contOrientacion));
            scenes.put(NombrePantallas.PQRS, new PantallaPQRS(stage, contPQRS));

            scenes.put(NombrePantallas.MENU_MEDICO, new PantallaMenuMedico(stage, contMenuMedico));
            scenes.put(NombrePantallas.CITAS_AGENDADAS, new PantallaCitasAgendadas(stage, contCitasAgendadas));
            scenes.put(NombrePantallas.CREAR_HISTORIAL_CLINICO, new PantallaCrearHistorialClinico(stage, contCrearHistorialClinico));
            scenes.put(NombrePantallas.ENTRADA_CLINICA, new PantallaEntradaClinica(stage, contEntradaClinica));
            scenes.put(NombrePantallas.VER_HISTORIAL_CLINICO, new PantallaVerHistorialClinico(stage, contVerHistorialClinico));
            scenes.put(NombrePantallas.VER_ENTRADA_CLINICA, new PantallaVerEntradaClinica(stage, contVerEntradaClinica));
            scenes.put(NombrePantallas.REMISION, new PantallaRemision(stage, contRemision));
            scenes.put(NombrePantallas.VER_MEDICAMENTOS, new PantallaVerMedicamentos(stage, contVerMedicamentos));
            scenes.put(NombrePantallas.CARRITO, new PantallaCarrito(stage, contPantallaCarrito));
            scenes.put(NombrePantallas.PAGOS_GENERAL, new PantallaPagoGeneral(stage, contPantallaPagoGeneral));
            scenes.put(NombrePantallas.PAGAR, new PantallaPagar(stage, contPantallaPagar));
        }catch (IOException e) {
            System.out.println("Error al cargar y crear una pantalla");
            e.printStackTrace();
        }

        // Start with the main scene
        activeScene = NombrePantallas.INICIO;
        stage.setScene(scenes.get(activeScene).getScene());
        stage.getIcons().add(new Image(getClass().getResourceAsStream(ICON_NAME)));
        stage.setTitle("HealthApp");
        stage.show();
        stage.sceneProperty().addListener((observable)->{
            scenes.get(activeScene).update();
        });


        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                controller.getContPaciente().guardarPacientes();
                controller.getContMedico().guardarMedicos();
                controller.getContProductos().guardarInventario();
                Platform.exit();
                System.exit(0);
            }
        });

    }


    public static void setActiveScene(NombrePantallas nom)
    {
        activeScene = nom;
    }

    /** Returns a Map of the scenes by {@link NombrePantallas} */
    public static Map<NombrePantallas, Pantalla> getScenes() {
        return scenes;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
