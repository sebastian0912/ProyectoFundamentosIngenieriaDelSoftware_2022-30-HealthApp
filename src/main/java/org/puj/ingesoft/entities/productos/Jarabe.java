package org.puj.ingesoft.entities.productos;

public class Jarabe extends Producto {

    protected Double cantidadTotal;
    protected Double dosis;

    public Jarabe(String nombre, String codigo, Integer cantidadDisponible, Double precioUnidad, Integer cantidadVendida, String nomLaboratorio, Double cantidadTotal, Double dosis) {
        super(nombre, codigo, cantidadDisponible, precioUnidad, cantidadVendida, nomLaboratorio, Tipo.JARABES);
        this.cantidadTotal = cantidadTotal;
        this.dosis = dosis;
    }
    public Double getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Double cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public Double getDosis() {
        return dosis;
    }

    public void setDosis(Double dosis) {
        this.dosis = dosis;
    }
}
