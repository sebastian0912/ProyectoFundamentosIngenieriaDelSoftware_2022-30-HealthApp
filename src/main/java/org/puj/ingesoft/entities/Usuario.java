package org.puj.ingesoft.entities;

import java.util.Date;

public class Usuario {
    private String nombre;
    private Integer cedula;
    private Date fechaNacimiento;
    private Integer edad;
    private String contraseña; // por evaluar la contraseña si es string o con libreria
    private String username;

    public Usuario(String nombre, Integer cedula, Date fechaNacimiento, Integer edad, String contraseña, String username) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.contraseña = contraseña;
        this.username = username;
    }

    public Usuario(String nombre, Integer cedula, Integer edad, String contraseña, String username) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.contraseña = contraseña;
        this.username = username;
    }

    public Usuario(String nombre, Integer cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void actualizarDatos(String nombre, Date fechaNacimiento, int edad, String contraseña, String username) {
        if(nombre != null)
            this.nombre = nombre;
        if(fechaNacimiento != null)
            this.fechaNacimiento = fechaNacimiento;
        if(edad != -1)
            this. edad = edad;
        if(contraseña != null)
            this.contraseña = contraseña;
        if(username != null)
            this.username = username;
    }
}
