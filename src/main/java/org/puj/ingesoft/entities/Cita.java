package org.puj.ingesoft.entities;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class Cita {

    private UUID idCita;
    private Paciente paciente;
    private Medico medico;
    private String nombreMedico;
    private String nombrePaciente;
    private Date fecha;
    private String modalidad;
    private String centroMedico;
    private String motivoCita;
    private String observaciones;
    private EntradaClinica entradaClinicaP;
    private Remision remision;

    public Cita( Paciente paciente, Medico medico, Date fecha, String modalidad, String centroMedico, String motivoCita, String observaciones, EntradaClinica entradaClinicaP) {
        this.idCita = UUID.randomUUID();
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.modalidad = modalidad;
        this.centroMedico = centroMedico;
        this.motivoCita = motivoCita;
        this.observaciones = observaciones;
        this.entradaClinicaP = entradaClinicaP;
        if(medico != null)
            this.nombreMedico = medico.getNombre();
        if(paciente != null)
            this.nombrePaciente = paciente.getNombre();
    }

    public Cita(Paciente paciente, Date fecha) {
        this.paciente = paciente;
        this.fecha = fecha;
    }

    public UUID getIdCita() {
        return idCita;
    }
    public void setIdCita(String id){
        this.idCita = UUID.fromString(id);
    }


    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getCentroMedico() {
        return centroMedico;
    }

    public void setCentroMedico(String centroMedico) {
        this.centroMedico = centroMedico;
    }

    public String getMotivoCita() {
        return motivoCita;
    }

    public void setMotivoCita(String motivoCita) {
        this.motivoCita = motivoCita;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public EntradaClinica getEntradaClinicaP() {
        return entradaClinicaP;
    }

    public void setEntradaClinicaP(EntradaClinica entradaClinicaP) {
        this.entradaClinicaP = entradaClinicaP;
    }

    public Remision getRemision(){return this.remision;}

    public void setRemision(Remision remision){this.remision = remision;}

    public String getNombreMedico() {
        return nombreMedico;
    }

    public String getNombrePaciente(){return nombrePaciente;}

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public void setNombrePaciente(String nombrePaciente){this.nombrePaciente = nombrePaciente;}

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
         return "Cita\n" +
                "idCita=" + idCita +
                //", paciente=" + this.paciente.toString() +
                ", medico=" + medico.toString() +
                ", fecha=" + sdf.format(this.fecha.getTime()) +
                ", modalidad='" + modalidad + '\'' +
                ", centroMedico='" + centroMedico + '\'' ;
                //", motivoCita='" + motivoCita + '\'' +
                //", observaciones='" + observaciones + '\'' +
                //", entradaClinicaP=" + entradaClinicaP.toString() +
                //", remision=" + remision.toString();
    }


}
