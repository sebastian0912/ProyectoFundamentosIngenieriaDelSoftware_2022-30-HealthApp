package org.puj.ingesoft.entities;
import java.util.Calendar;
import java.util.Date;

public class EntradaClinica {
    private String descripcion;
    private Date fecha;
    private TipoEntradaClinica entrada;
    private String farmaco;
    private String nombreMedico;
    private Medico medicoEntrada;
    private Remision remision;

    public EntradaClinica() {
        descripcion = "";
        fecha = Calendar.getInstance().getTime();
        entrada = TipoEntradaClinica.CITA;
        farmaco = "";
        nombreMedico = "";

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TipoEntradaClinica getEntrada() {
        return entrada;
    }

    public void setEntrada(TipoEntradaClinica entrada) {
        this.entrada = entrada;
    }

    public String getFarmaco() {
        return farmaco;
    }

    public void setFarmaco(String farmaco) {
        this.farmaco = farmaco;
    }

    public Medico getMedicoEntrada() {
        return medicoEntrada;
    }

    public void setMedicoEntrada(Medico medicoEntrada) {
        this.medicoEntrada = medicoEntrada;
    }

    public Remision getRemision() {
        return remision;
    }

    public void setRemision(Remision remision) {
        this.remision = remision;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }
}
