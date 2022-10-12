package org.puj.ingesoft.entities.productos;

public class Topico extends Producto {
    protected Double concentracion;

    public Topico(String nombre, String codigo, Integer cantidadDisponible, Double precioUnidad, Integer cantidadVendida, String laboratorio, Double concentracion) {
        super(nombre, codigo, cantidadDisponible, precioUnidad, cantidadVendida, laboratorio, Tipo.TOPICOS);
        this.concentracion = concentracion;
    }

    public Double getConcentracion() {
        return concentracion;
    }


    public void setConcentracion(Double concentracion) {
        this.concentracion = concentracion;
    }

}
