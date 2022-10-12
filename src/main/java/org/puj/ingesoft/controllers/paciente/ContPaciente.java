package org.puj.ingesoft.controllers.paciente;


import org.puj.ingesoft.controllers.medico.ContMedico;
import org.puj.ingesoft.controllers.persistencia.ContDatos;
import org.puj.ingesoft.entities.*;

import java.util.*;

public class ContPaciente {
    ContMedico contMedico = new ContMedico();

    ContDatos contDatos;
    ContLaboratorio contLaboratorio;

    private static HashMap<Integer, Paciente> pacientes = new HashMap<>();


    public ContPaciente(ContDatos contDatos, ContMedico contMedico){
        this.contDatos = contDatos;
        this.contMedico = contMedico;
    }

    //Paciente
    public void agregarPaciente(String nombre, int cedula, Date fechaNacimiento, int edad, String contraseña, String username, String telefono, String direccion, String rh)
    {
        Paciente paciente = new Paciente(nombre, cedula, fechaNacimiento, edad, contraseña, username, telefono, direccion, rh);
        pacientes.put(cedula, paciente);
    }
    public void agregarPaciente(Paciente paciente) {

        pacientes.put(paciente.getCedula(), paciente);
    }

    public void eliminarPaciente(int cedula)
    {
        pacientes.remove(cedula);
    }

    public void modificarPaciente(String nombre, int cedula, Date fechaNacimiento, int edad, String contraseña, String username)
    {
        pacientes.get(cedula).actualizarDatos(nombre, fechaNacimiento, edad, contraseña, username);
    }


    //Citas

    public Cita buscarCita(int cedula, Date fechaCita)
    {
        return pacientes.get(cedula).buscarCita(fechaCita);
    }

    public void agregarCitaPaciente(int cedula, Cita cita)
    {
        //Se busca el paciente y se le añade la cita dada
        pacientes.get(cedula).agregarCita(cita);
    }

    public void modificarCitaPaciente(int cedula, Cita citaNueva, Date viejaCita)
    {
        Paciente p = pacientes.get(cedula);
        p.eliminarCita(viejaCita);
        p.agregarCita(citaNueva);
    }

    public void desagendarCitaPciente(int cedula, Date fechaCita)
    {
        Cita c = buscarCita(cedula, fechaCita);
        Medico m = c.getMedico();
        m.desagendarCita(fechaCita);
        eliminarCitaPaciente(cedula, fechaCita);
    }

    public void reprogramarCita(Cita nueva, Cita vieja) {
        int cedula = vieja.getPaciente().getCedula();
        desagendarCitaPciente(cedula, vieja.getFecha());
        agregarCitaPaciente(cedula, nueva);
    }

    public void eliminarCitaPaciente(int cedula, Date fechaCita)
    {
        pacientes.get(cedula).eliminarCita(fechaCita);
    }

    public static Paciente buscarPaciente(int cedula){
        return pacientes.get(cedula);}

    public Collection<Cita> getCitasPaciente(int cedula)
    {
        return pacientes.get(cedula).getCitasPaciente().values();
    }

    //ContMedico

    public Collection<Cita> getCitasDeXMedicos(int cantidad)
    {
        ArrayList<Medico> medicos = contMedico.getXMedicos(cantidad);
        Collection<Cita> citasDisponibles = new ArrayList<>();
        for(Medico m: medicos)
        {
            Iterator<Cita> iterator = m.getCitas().values().iterator();
            while(iterator.hasNext())
            
            {
                Cita cita = iterator.next();
                if(cita.getPaciente() == null)
                {
                    citasDisponibles.add(cita);
                }
            }
        }
        return citasDisponibles;
    }

    public Collection<ExamenLab> getExamenLab(Integer cedula)
    {
        Collection<ExamenLab> citasDisponibles = new ArrayList<>();
        for(Paciente pac: pacientes.values()){
            if (pac.getCedula() == cedula) {
                for(ExamenLab exam: pac.getListaExamenes().values()) {
                    citasDisponibles.add(exam);
                }
            }
        }
        return citasDisponibles;
    }




    public Paciente buscarPaciente(String username, String contraseña) {
        Collection<Paciente> pacs = pacientes.values();
        System.out.println("Cantidad pacientes: "+pacs.size());
        for(Paciente p: pacs)
        {
            System.out.println("Se intento: "+ p.getUsername() +" con: "+ username + " y "+p.getContraseña()+" con: "+contraseña);
            if(p.getUsername().equals(username) && p.getContraseña().equals(contraseña))
                return p;
        }
        return null;
    }

    public void guardarPacientes()
    {
        contDatos.guardarDatosPacientes(pacientes.values());
    }

    public Remision CrearRemision(Remision remi2){
        Remision remi = new Remision( remi2);
        return remi;
    };

    public ExamenLab CrearExamen(ExamenLab exam){
        ExamenLab lab = new ExamenLab( exam);
        return lab;
    };

    public ExamenLab BuscarExamen2(UUID codigo){
        for(Paciente pac: this.getPacientes().values()){
            for(ExamenLab exam: pac.getListaExamenes().values()){
                if(exam.getCodigo() == codigo){
                    return exam;
                }
            }
        }
        return null;
    };

    public boolean asignarExamen(int cedula, ExamenLab lab, UUID codigo){
        for(Paciente pac: this.getPacientes().values()){
            if(pac.getCedula() == cedula){
                pac.getListaExamenes().put(codigo, lab);
                return  true;
            }
        }
        return false;
    }

    public Remision BuscarExamen(UUID codigo){
        for(Paciente pac: this.getPacientes().values()){
            for(ExamenLab exam: pac.getListaExamenes().values()){
                if(exam.getRemisionAsociada().getIdRemision() == codigo){
                    return exam.getRemisionAsociada();
                }
            }
        }
        return null;
    };




    public  HashMap<Integer, Paciente> getPacientes() {
        return pacientes;
    }

    public  void setPacientes(HashMap<Integer, Paciente> pacientes) {
        ContPaciente.pacientes = pacientes;
    }

    public ContMedico getContMedico() {
        return contMedico;
    }

    public void setContMedico(ContMedico contMedico) {
        this.contMedico = contMedico;
    }

    public ContDatos getContDatos() {
        return contDatos;
    }

    public void setContDatos(ContDatos contDatos) {
        this.contDatos = contDatos;
    }

    public ContLaboratorio getContLaboratorio() {
        return contLaboratorio;
    }

    public void setContLaboratorio(ContLaboratorio contLaboratorio) {
        this.contLaboratorio = contLaboratorio;
    }

    public ContPaciente() {
    }
}
