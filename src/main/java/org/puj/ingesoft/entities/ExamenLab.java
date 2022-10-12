package org.puj.ingesoft.entities;
import org.puj.ingesoft.controllers.paciente.ContPaciente;

import java.util.Date;
import java.util.UUID;


public class ExamenLab {
    private Paciente paciente;
    private Date fechaToma;
    private UUID codigo; //
    private String resultado;
    private Remision remisionAsociada;
    /*
    implementar en el controlador la funcionalidad de ver elcexamen desde la aplicacion
    o descargarlo a través de una contraseña
     */
    public ExamenLab(UUID codigo, int cedulaPac, Remision remi) {
        this.fechaToma = new Date();
        this.codigo = codigo;
        this.resultado = "Sufre de gastroenteritis";
        this.paciente = ContPaciente.buscarPaciente(cedulaPac);
        this.remisionAsociada  = remi;
    }

    public ExamenLab(ExamenLab exam) {
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getFechaToma() {
        return fechaToma;
    }

    public void setFechaToma(Date fechaToma) {
        this.fechaToma = fechaToma;
    }

    public UUID getCodigo() {
        return codigo;
    }

    public void setCodigo(UUID codigo) {
        this.codigo = codigo;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Remision getRemisionAsociada() {
        return remisionAsociada;
    }

    public void setRemisionAsociada(Remision remisionAsociada) {
        this.remisionAsociada = remisionAsociada;
    }
}
