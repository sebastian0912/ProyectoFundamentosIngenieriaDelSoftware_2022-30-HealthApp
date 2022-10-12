package org.puj.ingesoft.controllers.autenticacion;

import org.puj.ingesoft.controllers.paciente.ContPaciente;
import org.puj.ingesoft.controllers.medico.ContMedico;
import org.puj.ingesoft.entities.Medico;
import org.puj.ingesoft.entities.Paciente;

public class ContAutenticacion {
    private ContPaciente contPaciente;
    private ContMedico contMedico;

    private Paciente pacienteActivo;
    private Medico medicoActivo;

    public ContAutenticacion(ContPaciente contPaciente, ContMedico contMedico)
    {
        this.contPaciente = contPaciente;
        this.contMedico = contMedico;
    }
    public void setPacienteActivo(Paciente p)
    {
        this.pacienteActivo = p;
    }

    public void setMedicoActivo(Medico m)
    {
        this.medicoActivo = m;
    }

    public boolean buscarmedico(String username, String contraseña){

        medicoActivo = contMedico.buscarMedico(username, contraseña);
            if(  medicoActivo != null ){
                return true;
            }
            else{
                return false;
            }

    }
    public Medico buscarmedico2(String username, String contraseña){

        if(  contMedico.buscarMedico(username, contraseña) != null ){
            return contMedico.buscarMedico(username, contraseña);
        }
        else{
            return null;
        }

    }

    public boolean buscarPaciente(String username, String contraseña){

        if(  contPaciente.buscarPaciente(username, contraseña) != null ){
            return true;
        }
        else{
            return false;
        }
    }
    public Paciente buscarPaciente2(String username, String contraseña){

        pacienteActivo = contPaciente.buscarPaciente(username, contraseña);
        if(  pacienteActivo != null ){
            return pacienteActivo;
        }
        else{
            return null;
        }
    }

    public Paciente buscarPaciente3(Integer cedula){
        if(  contPaciente.buscarPaciente(cedula) != null ){
            return contPaciente.buscarPaciente(cedula);
        }
        else{
            return null;
        }
    }

    public ContPaciente getContPaciente() {
        return contPaciente;
    }

    public void setContPaciente(ContPaciente contPaciente) {
        this.contPaciente = contPaciente;
    }

    public ContMedico getContMedico() {
        return contMedico;
    }

    public void setContMedico(ContMedico contMedico) {
        this.contMedico = contMedico;
    }

    public Paciente getPacienteActivo() {
        return pacienteActivo;
    }

    public Medico getMedicoActivo(){
        return medicoActivo;
    }
}
