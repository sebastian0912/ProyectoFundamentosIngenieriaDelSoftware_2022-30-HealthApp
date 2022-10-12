package org.puj.ingesoft.entities;

import org.puj.ingesoft.entities.productos.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CarritoCompras {



    public enum Estado {
        INACTIVO,
        ENPROCESO,
        EJECUTADO
    }

    public CarritoCompras()
    {

    }

    private HashMap<Producto, Integer> listaProductos = new HashMap<Producto, Integer>();
    private Estado estadocarrito;

    public HashMap<Producto, Integer> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(HashMap<Producto, Integer> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Estado getEstadocarrito() {
        return estadocarrito;
    }

    public void setEstadocarrito(Estado estadocarrito) {
        this.estadocarrito = estadocarrito;
    }


    public double valorTotal()
    {
        double v = 0;
        for(Producto p: listaProductos.keySet())
        {
            v += (p.getPrecioUnidad() * listaProductos.get(p));
        }
        return v;
    }

    public void agregarProducto(Producto p, int cantidad)
    {
        if(listaProductos.keySet().contains(p))
        {
            int aux = listaProductos.get(p) + cantidad;
            listaProductos.remove(p);
            p.venderUnidades(cantidad);

            listaProductos.put(p, aux);
        }
        else
        {
            p.venderUnidades(cantidad);
            listaProductos.put(p, cantidad);
        }
    }

    public boolean quitarProducto(Producto p)
    {
        if(!listaProductos.containsKey(p))
            return false;
        if(listaProductos.get(p) > 1)
        {
            int aux = listaProductos.get(p) -1;
            p.devolverUnidades(1);
            listaProductos.remove(p);
            listaProductos.put(p, aux);
        }
        else if(listaProductos.get(p) <= 1)
        {
            listaProductos.remove(p);
        }
        return true;
    }

    public void vaciarCarrito() {
        for(Producto p: listaProductos.keySet())
        {
            p.devolverUnidades(listaProductos.get(p));
        }
        listaProductos.clear();
    }

    public void venderCarrito() {
        listaProductos.clear();
    }

    public boolean isEmpty() {
        return this.listaProductos.isEmpty();
    }

}
