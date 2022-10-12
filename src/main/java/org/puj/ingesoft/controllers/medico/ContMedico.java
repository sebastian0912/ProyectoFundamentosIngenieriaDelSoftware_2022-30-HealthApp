package org.puj.ingesoft.controllers.medico;

import org.puj.ingesoft.controllers.persistencia.ContDatos;
import org.puj.ingesoft.entities.Cita;
import org.puj.ingesoft.entities.Medico;

import java.util.*;

public class ContMedico {

    ContDatos contDatos;
    private static  HashMap<Integer, Medico> medicos = new HashMap<>();

    public ContMedico(ContDatos contDatos)
    {
        //TODO --> eliminar médico de prueba para versión final
        Date fechaN = new Date();
        Medico medico = new Medico("Juan", 12345, fechaN, 32, "contraseña123", "medico123", "TarJ12345", "Ortopedia" );
        Medico medico2 = new Medico("Santiago", 447528, fechaN, 45, "hello", "medico456", "TarJ789456", "MedicinaGeneral" );

        medico.generarCitas();
        medicos.put(medico.getCedula(), medico);
        medicos.put(medico2.getCedula(), medico2);



        this.contDatos = contDatos;
    }

    public ContMedico() {

    }

    public void agregarMedico(Medico medico)
    {
        medicos.put(medico.getCedula(), medico);
    }

    public void registrarMedico(Medico medico)
    {
        medico.generarCitas();
        medicos.put(medico.getCedula(), medico);
    }

    public void agregarMedico(String nombre, int cedula, Date fechaNacimiento, int edad, String contraseña, String username, String tarjetaProfecional, String especializacion)
    {
        Medico medico = new Medico(nombre, cedula, fechaNacimiento, edad, contraseña, username, tarjetaProfecional, especializacion);
        medico.generarCitas();
        medicos.put(cedula, medico);
    }

    public void eliminarMedico(int cedula)
    {
        medicos.remove(cedula);
    }

    public void modificarMedico(String nombre, int cedula, Date fechaNacimiento, int edad, String contraseña, String username)
    {
        medicos.get(cedula).actualizarDatos(nombre, fechaNacimiento, edad, contraseña, username);
    }

    public ArrayList<Medico> getXMedicos(int cantidad)
    {
        Iterator<Medico> iterator = medicos.values().iterator();
        ArrayList<Medico> resp = new ArrayList<>();
        for(int i = 0; i < cantidad; i++)
        {
            if(iterator.hasNext())
            {
                resp.add(iterator.next());
            }
            else
                break;
        }
        return resp;
    }

    public static Medico buscarMedico(int cedula){
        return medicos.get(cedula);
    }


    public Medico buscarMedico(String username, String contraseña) {
        Collection<Medico> meds = medicos.values();
        for(Medico m: meds)
        {
            if(m.getUsername().equals(username) && m.getContraseña().equals(contraseña))
                return m;
        }
        return null;
    }

    public void guardarMedicos()
    {
        contDatos.guardarDatosMedicos(medicos.values());
    }





    public Collection<Cita> getCitasAsignadas(Integer cedula) {
        Medico m = buscarMedico(cedula);
        return m.getCitasAsignadas();
    }

    public static HashMap<Integer, Medico> getMedicos() {
        return medicos;
    }

    public static void setMedicos(HashMap<Integer, Medico> medicos) {
        ContMedico.medicos = medicos;
    }

}
