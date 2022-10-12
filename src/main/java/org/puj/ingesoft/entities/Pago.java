package org.puj.ingesoft.entities;

import java.util.Date;

public class Pago {

    public enum ESTADO_PAGO{
        ACEPTADO,
        RECHAZADO, //Realmente nunca será diferente a aceptado ya que los servicios de pago no estarán implementados...
        PENDIENTE;
    }

    public enum METODO_PAGO{
        TARJETA,
        SERVICO_PSE,
        SERVICIO_PAY_U;
    }

    private double valorNeto;
    private double impuestos;
    private double valorTotal;
    private Date fechaDePago;
    private ESTADO_PAGO estadoPago;
    private METODO_PAGO metodoPago;
    private String descripcion;
    private String descripcionCorta;

    public Pago(double valorNeto, double impuestos, double valorTotal, Date fechaDePago, ESTADO_PAGO estadoPago, METODO_PAGO metodoPago, String descripcion, String descripcionCorta) {
        this.valorNeto = valorNeto;
        this.impuestos = impuestos;
        this.valorTotal = valorTotal;
        this.fechaDePago = fechaDePago;
        this.estadoPago = estadoPago;
        this.metodoPago = metodoPago;
        this.descripcion = descripcion;
        this.descripcionCorta = descripcionCorta;
    }

    public double getValorNeto() {
        return valorNeto;
    }

    public void setValorNeto(double valorNeto) {
        this.valorNeto = valorNeto;
    }

    public double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getFechaDePago() {
        return fechaDePago;
    }

    public void setFechaDePago(Date fechaDePago) {
        this.fechaDePago = fechaDePago;
    }

    public ESTADO_PAGO getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(ESTADO_PAGO estadoPago) {
        this.estadoPago = estadoPago;
    }

    public METODO_PAGO getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(METODO_PAGO metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }


}
