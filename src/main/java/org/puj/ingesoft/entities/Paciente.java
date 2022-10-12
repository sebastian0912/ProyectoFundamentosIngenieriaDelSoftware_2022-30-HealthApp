package org.puj.ingesoft.entities;

import org.puj.ingesoft.entities.productos.BonoCita;

import java.util.*;

public class Paciente extends Usuario {
    //una lista de examenes de
   private String telefono;
   private String direccion;
   private String rh;
   private HistorialClinico historialClinico = null;
   private CarritoCompras carrito;
   private HashMap<Date, Cita> citasPaciente = new HashMap<>();
   //private HashMap<UUID, Remision> remisionesPaciente = new HashMap<>();
   private HashMap<UUID, ExamenLab>listaExamenes = new HashMap<>();
   private HashMap<String, Compra> comprasPaciente = new HashMap<>();
   private ArrayList<PQR>pqrs = new ArrayList<>();
   private ArrayList<BonoCita> bonosDisponibles = new ArrayList<>();


   public Paciente(String nombre, Integer cedula, Date fechaNacimiento, Integer edad, String contraseña, String username) {
      super(nombre, cedula, fechaNacimiento, edad, contraseña, username);
   }

   public Paciente(String nombre, Integer cedula, Date fechaNacimiento, Integer edad, String contraseña, String username, String telefono, String direccion, String rh) {
      super(nombre, cedula, fechaNacimiento, edad, contraseña, username);
      this.telefono = telefono;
      this.direccion = direccion;
      this.rh = rh;
      this.carrito = new CarritoCompras();
   }

   public Paciente(String nombre, Integer cedula, String telefono, String direccion) {
      super(nombre, cedula);
      this.telefono = telefono;
      this.direccion = direccion;
   }

   public Cita buscarCita(Date fecha)
   {
      return citasPaciente.get(fecha);
   }

   public void agregarCita(Cita cita)
   {
      cita.setPaciente(this);
      cita.setNombrePaciente(this.getNombre());
      citasPaciente.put(cita.getFecha(), cita);
   }

   public void eliminarCita(Date fecha)
   {
      citasPaciente.remove(fecha);
   }

   public void agregarBono(BonoCita bono){
      bonosDisponibles.add(bono);
   }

   public BonoCita usarBono(){
      if(bonosDisponibles.isEmpty())
         return null;
      return bonosDisponibles.remove(0);
   }

   //Setters & Getters
   public String getTelefono() {
      return telefono;
   }

   public void setTelefono(String telefono) {
      this.telefono = telefono;
   }

   public String getDireccion() {
      return direccion;
   }

   public void setDireccion(String direccion) {
      this.direccion = direccion;
   }

   public String getRh() {
      return rh;
   }

   public void setRh(String rh) {
      this.rh = rh;
   }

   public HistorialClinico getHistorialClinico() {
      return historialClinico;
   }

   public void setHistorialClinico(HistorialClinico historialClinico) {
      this.historialClinico = historialClinico;
   }

   public CarritoCompras getCarrito() {
      return carrito;
   }

   public void setCarrito(CarritoCompras carrito) {
      this.carrito = carrito;
   }

   public HashMap<Date, Cita> getCitasPaciente() {
      return citasPaciente;
   }

   public void setCitasPaciente(HashMap<Date, Cita> citasPaciente) {
      this.citasPaciente = citasPaciente;
   }

   public HashMap<UUID, ExamenLab> getListaExamenes() {
      return listaExamenes;
   }

   public void setListaExamenes(HashMap<UUID, ExamenLab> listaExamenes) {
      this.listaExamenes = listaExamenes;
   }

   public ArrayList<PQR> getPqrs() {
      return pqrs;
   }

   public void setPqrs(ArrayList<PQR> pqrs) {
      this.pqrs = pqrs;
   }

   public HashMap<String, Compra> getComprasPaciente() {
      return comprasPaciente;
   }

   public void setComprasPaciente(HashMap<String, Compra> comprasPaciente) {
      this.comprasPaciente = comprasPaciente;
   }

   public ArrayList<BonoCita> getBonosDisponibles() {
      return bonosDisponibles;
   }

   public void setBonosDisponibles(ArrayList<BonoCita> bonosDisponibles) {
      this.bonosDisponibles = bonosDisponibles;
   }


    public void agregarCompra(Compra compra) {
      this.comprasPaciente.put(compra.getReferencia(), compra);
    }
}