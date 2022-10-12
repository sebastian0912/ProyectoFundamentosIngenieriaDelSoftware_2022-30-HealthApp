package org.puj.ingesoft.entities;

import org.puj.ingesoft.controllers.paciente.ContPaciente;
import org.puj.ingesoft.controllers.medico.ContMedico;

import java.util.UUID;

public class Remision {
    private Cita cita;
    private UUID idRemision;
    private Medico remitente;
    private Paciente pacienteRemitido;
    private String motivoRemision;
    private String diagnostico;
    private ExamenLab examenLab;

    public Remision(UUID codigo,int cedulaMed, int cedulaPac, String motivoRemision, String diagnostico) {
        this.idRemision = codigo;
        this.remitente = ContMedico.buscarMedico(cedulaMed);
        this.pacienteRemitido = ContPaciente.buscarPaciente(cedulaPac);
        this.motivoRemision = motivoRemision;
        this.diagnostico = diagnostico;
    }

    public Remision(Remision remi2) {
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public UUID getIdRemision() {
        return idRemision;
    }

    public void setIdRemision(UUID idRemision) {
        this.idRemision = idRemision;
    }

    public Medico getRemitente() {
        return remitente;
    }

    public void setRemitente(Medico remitente) {
        this.remitente = remitente;
    }

    public Paciente getPacienteRemitido() {
        return pacienteRemitido;
    }

    public void setPacienteRemitido(Paciente pacienteRemitido) {
        this.pacienteRemitido = pacienteRemitido;
    }

    public String getMotivoRemision() {
        return motivoRemision;
    }

    public void setMotivoRemision(String motivoRemision) {
        this.motivoRemision = motivoRemision;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public ExamenLab getExamenLab() {
        return examenLab;
    }

    public void setExamenLab(ExamenLab examenLab) {
        this.examenLab = examenLab;
    }
}
