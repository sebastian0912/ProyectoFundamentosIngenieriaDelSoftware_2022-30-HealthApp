package org.puj.ingesoft.entities.productos;

public class Producto {

    public enum Tipo {
        TODOS,
        PASTILLAS,
        JARABES,
        INYECCIONES,
        TOPICOS,
        ORTOPEDICOS;
    }

    private String nombre;
    private String  codigo;
    private Integer cantidadDisponible;
    private Double precioUnidad;
    private Integer cantidadVendida;
    private String nomLaboratorio;

    private Tipo tipo;
    private String tipoEnString;


    public Producto(String nombre, String codigo, Integer cantidadDisponible, Double precioUnidad, Integer cantidadVendida, String nomLaboratorio, Tipo tipo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.cantidadDisponible = cantidadDisponible;
        this.precioUnidad = precioUnidad;
        this.cantidadVendida = cantidadVendida;
        this.nomLaboratorio = nomLaboratorio;
        this.tipo = tipo;
        tipoEnString = tipo.toString();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(Double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public Integer getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(Integer cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public String getNomLaboratorio() {
        return nomLaboratorio;
    }

    public void setNomLaboratorio(String nomLaboratorio) {
        this.nomLaboratorio = nomLaboratorio;
    }

    public Tipo getTipoProducto() {
        return tipo;
    }

    public void setTipoProducto(Tipo tipo) {
        this.tipo = tipo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getTipoEnString() {
        return tipoEnString;
    }

    public void setTipoEnString(String tipoEnString) {
        this.tipoEnString = tipoEnString;
    }

    public void agregarUnidades(int unidades) {
        cantidadDisponible += unidades;
    }

    public void venderUnidades(int unidades) {
        cantidadVendida += unidades;
        cantidadDisponible -= unidades;
    }
    public void devolverUnidades(int unidades) {
        cantidadDisponible += unidades;
        cantidadVendida -= unidades;
    }

}
