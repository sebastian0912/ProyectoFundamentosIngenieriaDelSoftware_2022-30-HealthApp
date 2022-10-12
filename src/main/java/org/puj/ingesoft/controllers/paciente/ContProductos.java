package org.puj.ingesoft.controllers.paciente;

import org.puj.ingesoft.controllers.persistencia.ContDatos;
import org.puj.ingesoft.entities.productos.*;

import java.util.HashMap;
import java.util.Map;

public class ContProductos {

    private ContDatos contDatos;

    Map<String, Producto> productosDisponibles; // <Código, Producto>

    public ContProductos(ContDatos contDatos) {
         productosDisponibles = new HashMap<>();
         this.contDatos = contDatos;
         //Cargar productos
    }


    public Map<String, Producto> getProductosDisponibles() {
        return productosDisponibles;
    }

    public void setProductosDisponibles(Map<String, Producto> productosDisponibles) {
        this.productosDisponibles = productosDisponibles;
    }

    public void agregarUnidades(String codigo, int unidades) {
        productosDisponibles.get(codigo).agregarUnidades(unidades);
    }

    public void venderUnidades(String codigo, int unidades) {
        productosDisponibles.get(codigo).venderUnidades(unidades);
    }

    public void agregarProducto(Producto p) {
        productosDisponibles.put(p.getCodigo(), p);
    }

    public void crearInyeccion(String nombre, String codigo, Integer cantidadDisponible, Double precioUnidad, Integer cantidadVendida, String nomLaboratorio, Double mililitros) {
        Producto p = new Inyeccion(nombre, codigo, cantidadDisponible, precioUnidad, cantidadVendida, nomLaboratorio, mililitros);
        this.agregarProducto(p);
    }

    public void crearJarabe(String nombre, String codigo, Integer cantidadDisponible, Double precioUnidad, Integer cantidadVendida, String nomLaboratorio, Double cantidadTotal, Double dosis){
        Producto p = new Jarabe(nombre, codigo, cantidadDisponible, precioUnidad, cantidadVendida, nomLaboratorio,  cantidadTotal, dosis);
        this.agregarProducto(p);
    }

    public void crearOrtopedico(String nombre, String codigo, Integer cantidadDisponible, Double precioUnidad, Integer cantidadVendida, String nomLaboratorio,  String marca, String talla){
        Producto p = new Ortopedico(nombre, codigo, cantidadDisponible, precioUnidad, cantidadVendida, nomLaboratorio,  marca, talla);
        this.agregarProducto(p);
    }

    public void crearPastilla(String nombre, String codigo, Integer cantidadDisponible, Double precioUnidad, Integer cantidadVendida, String laboratorio,  Integer numeroPastilla, Double miligramos){
        Producto p = new Pastilla(nombre, codigo, cantidadDisponible, precioUnidad, cantidadVendida, laboratorio,  numeroPastilla, miligramos);
        this.agregarProducto(p);
    }

    public void crearTopico(String nombre, String codigo, Integer cantidadDisponible, Double precioUnidad, Integer cantidadVendida, String laboratorio, Double concentracion){
        Producto p = new Topico(nombre, codigo, cantidadDisponible, precioUnidad, cantidadVendida, laboratorio, concentracion);
        this.agregarProducto(p);
    }

    public void guardarInventario()
    {
        contDatos.guardarInventario();
    }

    public Producto buscarProducto(String codigoProd) {
        for(Producto p: this.productosDisponibles.values())
        {
            if(p.getCodigo().equals(codigoProd))
                return p;
        }
        return null;
    }
}
