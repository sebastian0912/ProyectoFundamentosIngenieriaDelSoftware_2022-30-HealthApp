package org.puj.ingesoft;

import org.junit.Assert;
import org.junit.Test;
import org.puj.ingesoft.controllers.medico.ContMedico;
import org.puj.ingesoft.controllers.paciente.ContPaciente;
import org.puj.ingesoft.controllers.paciente.ContProductos;
import org.puj.ingesoft.entities.*;
import org.puj.ingesoft.entities.productos.Pastilla;
import org.puj.ingesoft.entities.productos.Producto;
import org.puj.ingesoft.entities.productos.Topico;
import org.puj.ingesoft.views.PantallasPaciente.ContGestionarCita;

import javax.print.attribute.IntegerSyntax;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */

public class AppTest
{
    ContPaciente paca = new ContPaciente();
    ContMedico mec = new ContMedico();
    ContGestionarCita controladorcita = new ContGestionarCita();
    ContPaciente controladorpaciente = new ContPaciente();
    /**
     * Rigorous Test :-)
     */
    Date fecha= new Date(121,11,29);
    Date fecha1= new Date(68,2,12);
    Date fecha3 = new Date(120,12,15);
    Medico med = new Medico("Eduardo", 654365756, fecha1, 56, "111111", "eduardoA", "12345678", "medicina general" );
    Paciente paciente = new Paciente("Alicia", 57453234, "3106543245", "calle 45sur @32a 66");
    Cita cita = new Cita(paciente,fecha);
    Cita cita2 = new Cita(paciente, fecha3);
    UUID uid = UUID.randomUUID();
    Remision remision = new Remision(uid, med.getCedula(), paciente.getCedula(), "examen", "alergia" );
    ExamenLab exLab = new ExamenLab(uid, 654365756, remision);


    @Test
    //funcional - pruebas caja negra
    public void desagendarcitaxPaciente(){
        paciente.getCitasPaciente().put(fecha, cita);
        med.getCitas().put(fecha, cita);
        Cita valorEsperado = cita;
        //metodo buscar cita probado
        assertEquals(paciente.buscarCita(fecha), valorEsperado);
        //metodo desagendar cita probado
        med.desagendarCita(fecha);
        Paciente citaDisp = cita.getPaciente();
        Assert.assertEquals(citaDisp, null);
        //metodo eliminar cita probado
        paciente.eliminarCita(fecha);
        boolean  fechaCita = paciente.getCitasPaciente().containsKey(fecha);
        assertTrue(fechaCita != true);
    }


    @Test
    public void reagendarCita(){
        //este metodo usa el metodo de desagendarcita  el cual ya esta probado
        paciente.getCitasPaciente().put(fecha, cita);
        controladorpaciente.getPacientes().put(paciente.getCedula(), paciente);
        cita.setMedico(med);
        med.getCitasAsignadas().add(cita);
        med.getCitas().put(fecha, cita);
        controladorpaciente.reprogramarCita(cita2, cita);
        boolean existeCita = paciente.getCitasPaciente().containsKey(fecha3);
        assertTrue(existeCita);
    }

    @Test
    public void obtenerExamenes(){
        controladorpaciente.getPacientes().put(paciente.getCedula(), paciente);
        UUID id = UUID.randomUUID();
        paciente.getListaExamenes().put(id, exLab);
        Collection<ExamenLab> examenes;
        examenes = controladorpaciente.getExamenLab(paciente.getCedula());
      //  Collection<ExamenLab>esperado = new ArrayList<>();
        //esperado.add(paciente.getListaExamenes().get("examen1"));
        //assertArrayEquals(examenes, esperado);
       // ExamenLab actual = paciente.getListaExamenes().get("examen1");
        boolean actual =examenes.contains(exLab);
        assertTrue(actual == true);
    }

    @Test
    // probando prueba eliminar cita para prueba modificar cita
    public void eliminarCita(){
        paciente.getCitasPaciente().put(fecha, cita);
        paciente.eliminarCita(fecha);
        assertNull(paciente.getCitasPaciente().get(fecha));
    }

    @Test
    //probando agregar cita para metodo modificarCita
    public void agregarCita(){
        paciente.agregarCita(cita2);
        assertTrue(paciente.getCitasPaciente().containsKey(fecha3) == true);
    }

    @Test
    public void modificarCita(){
        controladorpaciente.getPacientes().put(paciente.getCedula(), paciente);
        controladorpaciente.modificarCitaPaciente(paciente.getCedula(), cita2, fecha);
        assertTrue(paciente.getCitasPaciente().containsKey(fecha3));
    }

    @Test
    public void testAgregarInyeccion(){
        ContProductos testCP = new ContProductos(paca.getContDatos());
        testCP.crearInyeccion("test", "test1",1,1.0,0,"test",1.0);
        assertTrue(testCP.getProductosDisponibles().containsKey("test1"));
    }
    @Test
    public void testAgregarJarabe(){
        ContProductos testCP = new ContProductos(paca.getContDatos());
        testCP.crearJarabe("test", "test1",1,1.0,0,"test",1.0,1.0);
        assertTrue(testCP.getProductosDisponibles().containsKey("test1"));
    }
    @Test
    public void testAgregarOrtopedico(){
        ContProductos testCP = new ContProductos(paca.getContDatos());
        testCP.crearOrtopedico("test", "test1",1,1.0,0,"test","test","test");
        assertTrue(testCP.getProductosDisponibles().containsKey("test1"));
    }
    @Test
    public void testAgregarPastilla(){
        ContProductos testCP = new ContProductos(paca.getContDatos());
        testCP.crearPastilla("test", "test1",1,1.0,0,"test",1,1.0);
        assertTrue(testCP.getProductosDisponibles().containsKey("test1"));
    }
    @Test
    public void testAgregarITopico(){
        ContProductos testCP = new ContProductos(paca.getContDatos());
        testCP.crearTopico("test", "test1",1,1.0,0,"test",1.0);
        assertTrue(testCP.getProductosDisponibles().containsKey("test1"));
    }

    @Test
    public void testCalcularPrecioItemsDiferentes(){
        ContProductos testCP = new ContProductos(paca.getContDatos());
        testCP.crearPastilla("test", "test1",10,1.0,0,"test",1,1.0);
        testCP.crearTopico("test", "test2",10,3.0,0,"test",1.0);
        Producto testP1 = new Pastilla("test", "test1",10,1.0,0,"test",1,1.0);
        Producto testP2 = new Topico("test", "test2",10,3.0,0,"test",1.0);
        CarritoCompras testCarro = new CarritoCompras();
        testCarro.agregarProducto(testP1,5);
        testCarro.agregarProducto(testP2,3);
        double t = testCarro.valorTotal();
        assertEquals(14, t,0);
    }

    @Test
    public void testCalcularPrecio(){
        ContProductos testCP = new ContProductos(paca.getContDatos());
        testCP.crearPastilla("test", "test1",10,1.0,0,"test",1,1.0);
        Producto testP = new Pastilla("test", "test1",10,1.0,0,"test",1,1.0);
        CarritoCompras testCarro = new CarritoCompras();
        testCarro.agregarProducto(testP,0);
        double t = testCarro.valorTotal();
        assertEquals(0, t,0);
    }

    @Test
    public void testCalcularPrecioUnaUnidad(){
        ContProductos testCP = new ContProductos(paca.getContDatos());
        testCP.crearPastilla("test", "test1",10,1.0,0,"test",1,1.0);
        Producto testP = new Pastilla("test", "test1",10,1.0,0,"test",1,1.0);
        CarritoCompras testCarro = new CarritoCompras();
        testCarro.agregarProducto(testP,1);
        double t = testCarro.valorTotal();
        assertEquals(1, t,0);
    }

    @Test
    public void testCalcularPrecio5Unidades(){
        ContProductos testCP = new ContProductos(paca.getContDatos());
        testCP.crearPastilla("test", "test1",10,1.0,0,"test",1,1.0);
        Producto testP = new Pastilla("test", "test1",10,1.0,0,"test",1,1.0);
        CarritoCompras testCarro = new CarritoCompras();
        testCarro.agregarProducto(testP,5);
        double t = testCarro.valorTotal();
        assertEquals(5, t,0);
    }

    @Test
    public void PacientesnNoVacio()  {
        Collection<Paciente> pacientes =  paca.getPacientes().values();
        assertTrue("Prueba Exitosa: La lista de paciente no viene vacia", pacientes.size() > 0);
    }

    @Test
    public void DoctoresVacio() {
        Collection<Medico> medicos = mec.getXMedicos(10);
        assertTrue("Prueba No Exitosa: La lista de paciente no viene vacia", medicos.size() == 0);
    }

    @Test
    public void MedicoNoEstaPorUsernameYContraseña() {
        Medico med2 =  mec.buscarMedico("medico456","hello");
        assertTrue("Prueba No Exitosa: Medico encontrado", med2 == null );
    }

    @Test
    public void MedicoNoEstaPorCedula() {
        Medico med2 =  mec.buscarMedico(447528);
        assertTrue("Prueba No Exitosa: Medico encontrado", med2 == null );
    }

    @Test
    public void CrearRemision() {
        Remision remi = new Remision(UUID.randomUUID(),146687, 741258, "Inflamacion de la garganta","Alergia");
        assertTrue("Prueba exitosa: Remision creada", paca.CrearRemision(remi) != null);;
    }

    @Test
    public void BuscarRemisionErronea() {
        assertTrue("Prueba No exitosa: Remision Encontrada", paca.BuscarExamen( UUID.randomUUID() ) == null );
    }

    @Test
    public void CrearExamen() {
        UUID idRemision = UUID.randomUUID();
        Remision remi = new Remision(idRemision,146687, 741258, "Inflamacion de la garganta","Alergia");
        ExamenLab examenLab = new ExamenLab(idRemision,741258, remi);
        assertTrue("Prueba exitosa: Examen de laboratorio creado creada", paca.CrearExamen(examenLab) != null);;
    }

    @Test
    public void BuscarExamenErronea() {
        assertTrue("Prueba No exitosa: Examen de laboratorio encontrado", paca.BuscarExamen2(UUID.randomUUID()) == null);
    }

    @Test
    public void asignarExamenAPacienteErronea() {
        UUID idRemision = UUID.randomUUID();
        Remision remi = new Remision(idRemision,146687, 741258, "Inflamacion de la garganta","Alergia");
        ExamenLab examenLab = new ExamenLab(idRemision,741258, remi);
        assertTrue("Prueba Exitosa: Examen no asignado ", paca.asignarExamen(741258,  examenLab, idRemision) == false);
    }

    @Test
    public void asignarExamenAPaciente() {
        Date fecha = new Date();
        Paciente pac = new Paciente("Felipe", 1, fecha, 16, "hello", "username");
        paca.getPacientes().put(1, pac);
        UUID idRemision = UUID.randomUUID();
        Remision remi = new Remision(idRemision, 146687, 1, "Inflamacion de la garganta", "Alergia");
        ExamenLab examenLab = new ExamenLab(idRemision, 1, remi);
        assertTrue("Prueba Exitosa: Examen no asignado correctamente ", paca.asignarExamen(1, examenLab, idRemision) != false);

        //pruebas de rendimiento

    }


    @Test
    public void testgetXMedicos(){
        int cantidad = mec.getMedicos().size();
        ArrayList<Medico> medicos = mec.getXMedicos(cantidad);
        assertEquals(cantidad, medicos.size());
    }

    @Test
    public void testGetCitasdeXMedicos(){
        int cantidad = controladorpaciente.getPacientes().size();
        Collection<Cita> citas = controladorpaciente.getCitasDeXMedicos(cantidad);
        assertEquals(cantidad, citas.size());
    }
    @Test
    public void testBuscarPacienteExitoso(){
        Paciente paciente = new Paciente("paciente", 123, new Date(), 24, "contraseña", "username");
        controladorpaciente.agregarPaciente(paciente);
        assertNotNull(controladorpaciente.buscarPaciente("username", "contraseña"));
    }

    @Test
    public void testBuscarPacienteFallido(){
        if(controladorpaciente.getPacientes().containsKey(123)){
            controladorpaciente.getPacientes().remove(123);
        }
        assertNull(controladorpaciente.buscarPaciente(123));
    }

    @Test
    public void testAgregarPaciente(){
        Paciente paciente = new Paciente("paciente", 123, new Date(), 24, "contraseña", "username");
        controladorpaciente.agregarPaciente(paciente);
        assertTrue(controladorpaciente.getPacientes().containsKey(paciente.getCedula()));
    }
}




