package org.puj.ingesoft.entities;

import org.puj.ingesoft.entities.productos.Producto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Compra {
    private final double impuestos = 0.19;

    public Compra() {

    }

    public enum ESTADO_COMPRA{
        PENDIENTE, //Realmente nuca estará pendiente... la respuesta de los servicios de pagos será inmediata ya que no están implementados
        ACEPTADA;
    }
    private Double precioTotal;
    private String referencia;
    private Pago pago;
    private ESTADO_COMPRA estado;
    private HashMap<String, Double> itemsComprados = new HashMap<>();

    public Compra(Double precioTotal, String referencia, ESTADO_COMPRA estado) {
        this.precioTotal = precioTotal;
        this.referencia = referencia;
        this.estado = estado;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public ESTADO_COMPRA getEstado() {
        return estado;
    }

    public void setEstado(ESTADO_COMPRA estado) {
        this.estado = estado;
    }

    public HashMap<String, Double> getItemsComprados() {
        return itemsComprados;
    }

    public void setItemsComprados(HashMap<String, Double> itemsComprados) {
        this.itemsComprados = itemsComprados;
    }
    public void agregarItem(String descripcion, double valor)
    {
        itemsComprados.put(descripcion, valor);
    }

    public void generarPago()
    {
        String desc = "";
        String descCorta = "";
        for(String item: itemsComprados.keySet())
        {
            desc = desc + item + "...... " + itemsComprados.get(item) + "\n";
            descCorta = descCorta + ", " + item;
        }
        descCorta = descCorta.substring(2, descCorta.length());
        this.pago = new Pago(precioTotal, precioTotal*impuestos, precioTotal + precioTotal*impuestos, Calendar.getInstance().getTime(), null, null, desc, descCorta);
    }
}
