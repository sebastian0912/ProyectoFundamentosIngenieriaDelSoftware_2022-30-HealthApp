package org.puj.ingesoft.entities.productos;

public class Inyeccion extends Producto {
    protected Double mililitros;

    public Inyeccion(String nombre, String codigo, Integer cantidadDisponible, Double precioUnidad, Integer cantidadVendida, String nomLaboratorio, Double mililitros) {
        super(nombre, codigo, cantidadDisponible, precioUnidad, cantidadVendida, nomLaboratorio, Tipo.INYECCIONES);
        this.mililitros = mililitros;
    }

    public Double getMililitros() {
        return mililitros;
    }

    public void setMililitros(Double mililitros) {
        this.mililitros = mililitros;
    }
}
