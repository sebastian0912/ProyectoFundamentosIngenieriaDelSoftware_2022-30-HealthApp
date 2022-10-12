package org.puj.ingesoft.controllers.usuario;

import org.puj.ingesoft.controllers.paciente.ContPaciente;
import org.puj.ingesoft.controllers.medico.ContMedico;
import org.puj.ingesoft.entities.*;
import org.puj.ingesoft.views.PantallasRegistro.ContRegistroPaciente;

public class ContRegistro {
    private ContRegistroPaciente paciente;
    private ContPaciente contPaciente;
    private ContMedico contMedico;

    public ContRegistro(ContPaciente contPaciente, ContMedico contMedico)
    {
        this.contPaciente = contPaciente;
        this.contMedico = contMedico;
    }

    public Medico NewMedico(Medico med) {
        contMedico.registrarMedico(med);
        return med;
    }

    public Paciente NewPaciente(Paciente pac) {
        contPaciente.agregarPaciente(pac);
        return pac;
    }

    public boolean buscarPaciente(String username, String contraseña){
        if(contPaciente.buscarPaciente(username, contraseña) != null)
            return true;
        return false;
    }



}
