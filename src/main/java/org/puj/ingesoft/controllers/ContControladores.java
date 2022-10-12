package org.puj.ingesoft.controllers;

import org.puj.ingesoft.controllers.paciente.*;
import org.puj.ingesoft.controllers.autenticacion.ContAutenticacion;
import org.puj.ingesoft.controllers.medico.ContMedico;
import org.puj.ingesoft.controllers.persistencia.ContDatos;
import org.puj.ingesoft.controllers.paciente.ContProductos;
import org.puj.ingesoft.controllers.usuario.ContRegistro;
import org.puj.ingesoft.views.PantallasMedico.ContRemision;

public class ContControladores {
    ContDatos contDatos = new ContDatos();
    ContMedico contMedico = new ContMedico(contDatos);
    ContPaciente contPaciente = new ContPaciente(contDatos, contMedico);
    ContRegistro contRegistro = new ContRegistro(contPaciente, contMedico);
    ContAutenticacion contAutenticacion = new ContAutenticacion(contPaciente, contMedico);
    ContLaboratorio contLaboratorio = new ContLaboratorio();
    ContCompra contCompra = new ContCompra();
    ContPago contPago = new ContPago();
    ContServCliente contServCliente = new ContServCliente();
    ContRemision contRemision = new ContRemision();


    ContProductos contProductos = new ContProductos(contDatos);


    public ContControladores()
    {
        contDatos.setContMedico(contMedico);
        contDatos.setContPaciente(contPaciente);
        contDatos.setContProductos(contProductos);
    }


    public ContDatos getContDatos() {
        return contDatos;
    }

    public ContRegistro getContRegistro() {
        return contRegistro;
    }

    public ContCompra getContCompra() {
        return contCompra;
    }

    public ContLaboratorio getContLaboratorio() {
        return contLaboratorio;
    }

    public ContPaciente getContPaciente() {
        return contPaciente;
    }

    public ContPago getContPago() {
        return contPago;
    }

    public ContProductos getContProductos() {
        return contProductos;
    }

    public ContServCliente getContServCliente() {
        return contServCliente;
    }

    public ContMedico getContMedico() {
        return contMedico;
    }

    public ContRemision getContRemision() {
        return contRemision;
    }

    public ContAutenticacion getContAutenticacion() {
        return contAutenticacion;
    }
}
