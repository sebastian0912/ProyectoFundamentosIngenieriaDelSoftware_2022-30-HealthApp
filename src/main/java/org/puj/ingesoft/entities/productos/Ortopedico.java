package org.puj.ingesoft.entities.productos;

public class Ortopedico extends Producto {
    protected String marca;
    protected String talla;

    public Ortopedico(String nombre, String codigo, Integer cantidadDisponible, Double precioUnidad, Integer cantidadVendida, String laboratorio, String marca, String talla) {
        super(nombre, codigo, cantidadDisponible, precioUnidad, cantidadVendida, laboratorio, Tipo.ORTOPEDICOS);
        this.marca = marca;
        this.talla = talla;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }
}
