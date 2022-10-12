package org.puj.ingesoft.entities.productos;

public class Pastilla extends Producto {
    protected Integer numeroPastilla;
    protected Double miligramos;

    public Pastilla(String nombre, String codigo, Integer cantidadDisponible, Double precioUnidad, Integer cantidadVendida, String laboratorio, Integer numeroPastilla, Double miligramos) {
        super(nombre, codigo, cantidadDisponible, precioUnidad, cantidadVendida, laboratorio, Tipo.PASTILLAS);
        this.numeroPastilla = numeroPastilla;
        this.miligramos = miligramos;
    }

    public Integer getNumeroPastilla() {
        return numeroPastilla;
    }

    public void setNumeroPastilla(Integer numeroPastilla) {
        this.numeroPastilla = numeroPastilla;
    }

    public Double getMiligramos() {
        return miligramos;
    }

    public void setMiligramos(Double miligramos) {
        this.miligramos = miligramos;
    }
}
