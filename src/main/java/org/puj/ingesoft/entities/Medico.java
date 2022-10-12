package org.puj.ingesoft.entities;
import java.util.*;


public class Medico extends Usuario {
    private String tarjetaProfesional;
    private String especializacion;
    private HashMap<Integer, Paciente> pacientes = new HashMap<>();
    private HashMap<Date, Cita> citas = new HashMap<>();
    private ArrayList<Remision>remisiones = new ArrayList<>();


    public Medico(String nombre, Integer cedula, Date fechaNacimiento, Integer edad, String contraseña, String username, String tarjetaProfesional, String especializacion) {
      super(nombre, cedula, fechaNacimiento, edad, contraseña, username);
      this.tarjetaProfesional = tarjetaProfesional;
      this.especializacion = especializacion;

     }

     public Paciente buscarPaciente(int cedula)
     {
         return pacientes.get(cedula);
     }

     public void agregarPaciente(Paciente paciente)
     {
         pacientes.put(paciente.getCedula(), paciente);
     }

     public void eliminarPaciente(int cedula)
     {
         pacientes.remove(cedula);
     }

     public Cita buscarCita(Date fecha)
     {
         return citas.get(fecha);
     }

     public void agregarCita(Cita cita)
     {
         citas.put(cita.getFecha(), cita);
     }

     public void eliminarCita(Date fecha)
     {
         citas.remove(fecha);
     }

     public void desagendarCita(Date fecha)
     {
         Cita c = citas.get(fecha);
         c.setPaciente(null);
         c.setEntradaClinicaP(null);
         c.setObservaciones(null);
         c.setMotivoCita(null);
         c.setRemision(null);
     }

     //Getters y setters

    public String getTarjetaProfesional() {
        return tarjetaProfesional;
    }

    public void setTarjetaProfesional(String tarjetaProfesional) {
        this.tarjetaProfesional = tarjetaProfesional;
    }

    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }

    public HashMap<Integer, Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(HashMap<Integer, Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public HashMap<Date, Cita> getCitas() {
        return citas;
    }

    public void setCitas(HashMap<Date, Cita> citas) {
        this.citas = citas;
    }

    public ArrayList<Remision> getRemisiones() {
        return remisiones;
    }

    public void setRemisiones(ArrayList<Remision> remisiones) {
        this.remisiones = remisiones;
    }

    public Collection<Cita> getCitasAsignadas()
    {
        Collection<Cita> citasAsignadas = new ArrayList<>();
        for(Cita c: citas.values())
        {
            if(c.getPaciente() != null)
                citasAsignadas.add(c);
        }
        return citasAsignadas;
    }

    public void generarCitas() {
        //TODO arreglar método para que las citas sean en horarios decentes y no cualquiera dependiendo de la hora de creación del médico
        //Crea varias citas disponibles
        Calendar cal = Calendar.getInstance();
        for(int i = 0; i < 40; i++)
        {
            // Paciente paciente, Medico medico, Date fecha, String modalidad, String centroMedico, String motivoCita, String observaciones, EntradaClinica entradaClinicaP
            cal.add(Calendar.MINUTE, 30);
            //System.out.println(cal.getTime());
            Cita cita = new Cita(null, this, cal.getTime(), "Presencial", "C1", null, null, null);
            citas.put(cita.getFecha(), cita);
        }
    }


}


