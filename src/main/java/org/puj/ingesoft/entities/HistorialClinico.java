package org.puj.ingesoft.entities;
import java.util.ArrayList;
import java.util.UUID;

public class HistorialClinico {
    private Paciente paciente;
    private Integer idPaciente; //con la cedula del paciente
    private UUID idHistoria;
    private String antecedentes;
    private String diagnostico; //anamnesis y exploracion
    private String descripcion;
    private ArrayList<EntradaClinica> listaEntradas = new ArrayList<EntradaClinica>();

    //Constructor

    public HistorialClinico() {
    }

    public HistorialClinico(Paciente paciente, String antecedentes, String diagnostico, String descripcion) {
        this.paciente = paciente;
        this.idPaciente = paciente.getCedula();
        this.antecedentes = antecedentes;
        this.diagnostico = diagnostico;
        this.descripcion = descripcion;
    }


    //Accessors


    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public UUID getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(UUID idHistoria) {
        this.idHistoria = idHistoria;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public ArrayList<EntradaClinica> getListaEntradas() {
        return listaEntradas;
    }

    public void setListaEntradas(ArrayList<EntradaClinica> listaEntradas) {
        this.listaEntradas = listaEntradas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //Métodos
}
