package org.puj.ingesoft.controllers.persistencia;


import org.puj.ingesoft.controllers.paciente.ContPaciente;
import org.puj.ingesoft.controllers.medico.ContMedico;
import org.puj.ingesoft.controllers.paciente.ContProductos;
import org.puj.ingesoft.controllers.usuario.ContRegistro;
import org.puj.ingesoft.entities.*;
import org.puj.ingesoft.entities.productos.*;
import org.puj.ingesoft.entities.productos.Producto.Tipo;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class ContDatos {
    private final String PACIENTES_LECTURA_PATH = "./pacientes_temp.json";
    private final String PACIENTES_ENCRIPTADOS_PATH = "./DB_P.db";
    private final String MEDICOS_LECTURA_PATH = "./medicos_temp.json";
    private final String MEDICOS_ENCRIPTADOS_PATH = "./DB_M.db";
    private final String PUBLIC_KEY = "pk163287";
    private final String PATH_INVENTARIO = "./inventario.json";

    private ContRegistro registros;
    private ContMedico contMedico;
    private ContPaciente contPaciente;

    public ContProductos getContProductos() {
        return contProductos;
    }

    public void setContProductos(ContProductos contProductos) {
        this.contProductos = contProductos;
    }

    private ContProductos contProductos;


    public void setContMedico(ContMedico contMedico) {
        this.contMedico = contMedico;
    }

    public void setContPaciente(ContPaciente contPaciente) {
        this.contPaciente = contPaciente;
    }


    public ContRegistro getRegistros() {
        return registros;
    }

    public void setRegistros(ContRegistro registros) {
        this.registros = registros;
    }


    public void leerPacientes() {
        try {
            SeguridadArchivos.encryptDecrypt(PUBLIC_KEY, Cipher.DECRYPT_MODE, PACIENTES_ENCRIPTADOS_PATH, PACIENTES_LECTURA_PATH);
        } catch (IOException e) {
            System.out.println("IOEXCEPT P");
        } catch (InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | NoSuchAlgorithmException e) {
            System.out.println("ERROR DE DESCIFRADO");
        }
        JSONParser parser = new JSONParser();
        try {
            FileReader fr = new FileReader(PACIENTES_LECTURA_PATH);
            Object obj = parser.parse(fr);
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray arrPac = (JSONArray) jsonObject.get("Pacientes");
            System.out.println("Cantidad pacientes en archivo: " + arrPac.size());
            for (int i = 0; i < arrPac.size(); i++) {
                JSONObject objPac = (JSONObject) arrPac.get(i);

                String nom = objPac.get("nombre").toString();
                int cedula = Integer.parseInt(objPac.get("cedula").toString());
                Date date = new Date();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = formatter.parse(objPac.get("fechaN").toString());
                } catch (java.text.ParseException e) {
                    System.out.println("Parse exception: in date");
                }

                int edad = Integer.parseInt(objPac.get("edad").toString());

                String contraseña = objPac.get("contraseña").toString();
                String username = objPac.get("username").toString();
                String telefono = objPac.get("telefono") == null ? null : objPac.get("telefono").toString();
                String direccion = objPac.get("direccion") == null ? null : objPac.get("direccion").toString();
                String rh = objPac.get("rh") == null ? null : objPac.get("rh").toString();


                Paciente p = new Paciente(nom, cedula, date, edad, contraseña, username, telefono, direccion, rh);
                p.setHistorialClinico( new HistorialClinico());

                JSONArray arrHistorial = (JSONArray) objPac.get("historialClinico");
                for(int j = 0; j < arrHistorial.size(); j++)
                {
                    JSONObject objEntrada = (JSONObject) arrHistorial.get(j);
                    String descripcion = objEntrada.get("descripcion").toString();
                    Date date2 = new Date();
                    try {
                        date2 = formatter.parse(objEntrada.get("fecha").toString());
                    } catch (java.text.ParseException e) {
                        System.out.println("Parse exception: in date");
                    }
                    TipoEntradaClinica tipoEntrada = TipoEntradaClinica.valueOf(objEntrada.get("tipoEntrada").toString());
                    String farmaco = objEntrada.get("farmaco").toString();
                    int cedulaMed = Integer.parseInt(objEntrada.get("cedulaMed").toString());
                    String idCitaRemision = objEntrada.get("idCitaRemision").toString();
                    EntradaClinica ec = new EntradaClinica();
                    ec.setDescripcion(descripcion);
                    ec.setEntrada(tipoEntrada);
                    ec.setFecha(date2);
                    ec.setFarmaco(farmaco);
                    ec.setMedicoEntrada(contMedico.buscarMedico(cedulaMed));
                    ec.setNombreMedico(ec.getMedicoEntrada().getNombre());
                    Cita c = null;
                    for(Cita cita: ec.getMedicoEntrada().getCitas().values())
                    {
                        if(cita.getIdCita().toString().equals(idCitaRemision))
                        {
                            c = cita;
                            break;
                        }
                    }
                    if(c != null)
                        ec.setRemision(c.getRemision());
                    else
                        ec.setRemision(null);
                    p.getHistorialClinico().getListaEntradas().add(ec);
                }

                p.setCarrito(new CarritoCompras());
                JSONArray arrProd = (JSONArray) objPac.get("carrito");
                for(int j = 0; j < arrProd.size(); j++)
                {
                    JSONObject objProd = (JSONObject) arrProd.get(j);
                    Producto prod = contProductos.buscarProducto(objProd.get("codigoProd").toString());
                    if(prod != null)
                    {
                        p.getCarrito().agregarProducto(prod, Integer.parseInt(objProd.get("cantidadProd").toString()));
                    }
                }

                p.setListaExamenes(new HashMap<>());
                JSONArray arrExa= (JSONArray) objPac.get("examenes");
                for(int j = 0; j < arrExa.size(); j++)
                {
                    JSONObject objExa = (JSONObject) arrExa.get(j);
                    UUID idExa = (UUID)objExa.get("codigoExamen");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date dat = new Date();
                    try{
                        dat = dateFormat.parse(objExa.get("fecha").toString());
                    } catch (java.text.ParseException e) {
                        System.out.println("Parse exception: in date");
                    }
                    String resultado = objExa.get("resultado").toString();
                    UUID idRemision = (UUID) objExa.get("idRemision");
                    Remision rem = null;
                    for(EntradaClinica e: p.getHistorialClinico().getListaEntradas())
                    {
                        if(e.getRemision().getIdRemision().equals(idRemision))
                            rem = e.getRemision();
                    }
                    ExamenLab exa = new ExamenLab(idExa, p.getCedula(), rem);
                    p.getListaExamenes().put(exa.getCodigo(), exa);
                }

                p.setComprasPaciente(new HashMap<>());
                JSONArray arrCompras = (JSONArray) objPac.get("compras");
                for(int j = 0; j < arrCompras.size(); j++)
                {
                    JSONObject objCompra = (JSONObject) arrCompras.get(j);
                    String referencia = objCompra.get("referenciaCompra").toString();
                    JSONObject objPago = (JSONObject) objCompra.get("pago");
                    double valorNeto = Double.parseDouble(objPago.get("valorNeto").toString());
                    double impuestos = Double.parseDouble(objPago.get("impuestos").toString());
                    double valorTotal = Double.parseDouble(objPago.get("valorTotal").toString());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date fechaPago = dateFormat.parse(objPago.get("fechaPago").toString());
                    Pago.ESTADO_PAGO estado = Pago.ESTADO_PAGO.valueOf(objPago.get("estadoPago").toString());
                    Pago.METODO_PAGO metodo = Pago.METODO_PAGO.valueOf(objPago.get("metodoPago").toString());
                    String descripcion = objPago.get("descripcion").toString();
                    String descripcionCorta = objPago.get("descripcionCorta").toString();
                    Compra.ESTADO_COMPRA estadoCompra = Compra.ESTADO_COMPRA.valueOf(objCompra.get("estadoCompra").toString());

                    Pago pago = new Pago(valorNeto, impuestos, valorTotal, fechaPago,estado, metodo, descripcion, descripcionCorta);
                    Compra compra = new Compra(valorNeto, referencia, estadoCompra);
                    compra.setPago(pago);
                    p.getComprasPaciente().put(referencia, compra);
                }

                p.setPqrs(new ArrayList<>());
                JSONArray arrPQR = (JSONArray) objPac.get("pqrs");
                for(int j = 0; j < arrPQR.size(); j++)
                {
                    JSONObject objPQR = (JSONObject) arrPQR.get(j);
                    String motio = objPQR.get("motivo").toString();
                    String descripcion = objPQR.get("descripcion").toString();
                    PQR pqrs = new PQR();
                    pqrs.setMotivo(motio);
                    pqrs.setDescripcion(descripcion);
                    pqrs.setPaciente(p);
                    p.getPqrs().add(pqrs);
                }
                contPaciente.agregarPaciente(p);
            }
            fr.close();
            File file = new File(PACIENTES_LECTURA_PATH);

            if (!file.delete()) {
                System.out.println("Failed to delete the temp file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found ex");
        } catch (IOException e) {
            System.out.println("IO ex");
        } catch (java.text.ParseException e) {
            System.out.println("Problema parse con date");
        } catch (ParseException e) {
            System.out.println("No se pudo parse el archivo");
        }
        System.out.println("Pacientes cargados");
    }

    public void guardarDatosPacientes(Collection<Paciente> pacientes)//Puede pedir la lista entera de pacientes como collection o hashmap desde contPacientes
    {
        JSONArray list = new JSONArray();
        JSONObject objList = new JSONObject();
        for (Paciente pac : pacientes) {
            JSONObject objPac = new JSONObject();
            objPac.put("nombre", pac.getNombre());
            objPac.put("cedula", pac.getCedula());
            if (pac.getFechaNacimiento() == null) {
                objPac.put("fechaN", "null");
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                objPac.put("fechaN", dateFormat.format(pac.getFechaNacimiento()));
            }
            objPac.put("edad", pac.getEdad());
            objPac.put("username", pac.getUsername());
            objPac.put("contraseña", pac.getContraseña());
            objPac.put("telefono", pac.getTelefono());
            objPac.put("direccion", pac.getDireccion());
            objPac.put("rh", pac.getRh());
            JSONArray listaHistorial = new JSONArray();
            if(pac.getHistorialClinico()!=null)
            {
                for(EntradaClinica e: pac.getHistorialClinico().getListaEntradas())
                {
                    JSONObject objEntrada = new JSONObject();
                    objEntrada.put("descripcion", e.getDescripcion());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    objEntrada.put("fecha", dateFormat.format(e.getFecha()));
                    objEntrada.put("tipoEntrada", e.getEntrada().name());
                    objEntrada.put("farmaco", e.getFarmaco());
                    objEntrada.put("cedulaMed", e.getMedicoEntrada().getCedula());
                    objEntrada.put("idCitaRemision", e.getRemision().getCita().getIdCita());
                    listaHistorial.add(objEntrada);
                }
            }
            objPac.put("historialClinico", listaHistorial);

            JSONArray listaCarrito = new JSONArray();
            if(pac.getCarrito() != null)
            {
                for(Producto prod: pac.getCarrito().getListaProductos().keySet())
                {
                    JSONObject objProducto = new JSONObject();
                    objProducto.put("codigoProd", prod.getCodigo());
                    objProducto.put("cantidadProd", pac.getCarrito().getListaProductos().get(prod));
                    listaCarrito.add(objProducto);
                }
            }
            objPac.put("carrito", listaCarrito);

            JSONArray listaExamenes = new JSONArray();
            if(pac.getListaExamenes() != null)
            {
                for(ExamenLab exa: pac.getListaExamenes().values())
                {
                    JSONObject objExam = new JSONObject();
                    objExam.put("codigoExamen", exa.getCodigo());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    objExam.put("fecha", dateFormat.format(exa.getFechaToma()));
                    objExam.put("resultado", exa.getResultado());
                    objExam.put("idRemision", exa.getRemisionAsociada().getIdRemision());
                    listaExamenes.add(objExam);
                }
            }
            objPac.put("examenes", listaExamenes);

            JSONArray listaCompras = new JSONArray();
            if(pac.getComprasPaciente() != null)
            {
                for(Compra comp: pac.getComprasPaciente().values())
                {
                    JSONObject objCompra = new JSONObject();
                    objCompra.put("referenciaCompra", comp.getReferencia());
                    JSONObject objPago = new JSONObject();
                    objPago.put("valorNeto", comp.getPago().getValorNeto());
                    objPago.put("impuestos", comp.getPago().getImpuestos());
                    objPago.put("valorTotal", comp.getPago().getValorTotal());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    objPago.put("fechaPago", dateFormat.format(comp.getPago().getFechaDePago()));
                    objPago.put("estadoPago", comp.getPago().getEstadoPago().name());
                    objPago.put("metodoPago", comp.getPago().getMetodoPago().name());
                    objPago.put("descripcion", comp.getPago().getDescripcion());
                    objPago.put("descripcionCorta", comp.getPago().getDescripcionCorta());
                    objCompra.put("pago", objPago);
                    objCompra.put("estadoCompra", comp.getEstado().name());
                    JSONArray listaItemsComprados = new JSONArray();
                    for(String s: comp.getItemsComprados().keySet())
                    {
                        JSONObject objItemComprado = new JSONObject();
                        objItemComprado.put("nombre", s);
                        objItemComprado.put("cantidad", comp.getItemsComprados().get(s));
                        listaItemsComprados.add(objItemComprado);
                    }
                    listaCompras.add(objCompra);

                }
            }

            objPac.put("compras", listaCompras);

            JSONArray listaPQRS = new JSONArray();
            if(pac.getPqrs() != null)
            {
                for(PQR pqr: pac.getPqrs())
                {
                    JSONObject objPQRS = new JSONObject();
                    objPQRS.put("motivo", pqr.getMotivo());
                    objPQRS.put("descripcion", pqr.getDescripcion());
                    listaPQRS.add(objPQRS);
                }
                objPac.put("pqrs", listaPQRS);
            }

            list.add(objPac);
        }

        objList.put("Pacientes", list);


        try {
            FileWriter file = new FileWriter(PACIENTES_LECTURA_PATH, false);
            file.write(objList.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            //TODO manejar el error
        }

        try {
            SeguridadArchivos.encryptDecrypt(PUBLIC_KEY, Cipher.ENCRYPT_MODE, PACIENTES_LECTURA_PATH, PACIENTES_ENCRIPTADOS_PATH);
        } catch (InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | NoSuchAlgorithmException | IOException e) {
            System.out.println("ERROR DE CIFRADO");
        }

        System.out.println("Datos Pacientes guardados en archivo");

    }

    public void leerMedicos() {
        try {
            SeguridadArchivos.encryptDecrypt(PUBLIC_KEY, Cipher.DECRYPT_MODE, MEDICOS_ENCRIPTADOS_PATH, MEDICOS_LECTURA_PATH);
        } catch (IOException e) {
            System.out.println("IOEXCEPT M");
        } catch (InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | NoSuchAlgorithmException e) {
            System.out.println("ERROR DE DESCIFRADO");
        }

        JSONParser parser = new JSONParser();
        try {
            FileReader fr = new FileReader(MEDICOS_LECTURA_PATH);
            Object obj = parser.parse(fr);
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray arrMed = (JSONArray) jsonObject.get("Medicos");

            for (int i = 0; i < arrMed.size(); i++) {
                JSONObject objMed = (JSONObject) arrMed.get(i);

                String nom = objMed.get("nombre").toString();
                int cedula = Integer.parseInt(objMed.get("cedula").toString());
                Date date = new Date();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = formatter.parse(objMed.get("fechaN").toString());
                } catch (java.text.ParseException e) {
                    System.out.println("Parse exception: in date");
                }

                int edad = Integer.parseInt(objMed.get("edad").toString());
                String contraseña = objMed.get("contraseña").toString();
                String username = objMed.get("username").toString();
                String tarjetaProfecional = objMed.get("tarjetaP").toString();
                String especializacion = objMed.get("especializacion").toString();

                Medico m = new Medico(nom, cedula, date, edad, contraseña, username, tarjetaProfecional, especializacion);

                //Leer y agregar citas al medico si tiene

                JSONArray arrCitas = (JSONArray) objMed.get("Citas");
                if(arrCitas != null)
                {

                    for (int j = 0; j < arrCitas.size(); j++) {

                        JSONObject objCita = (JSONObject) arrCitas.get(j);
                        String cedulaP = objCita.get("cedulaP").toString();
                        String modalidad = null;
                        String centroMedico = null;
                        String motivoCita = null;
                        Paciente p = null;
                        if(!cedulaP.equals("null")) //Si está agendada
                        {
                            if(objCita.get("modalidad")!= null)
                                modalidad = objCita.get("modalidad").toString();
                            if(objCita.get("centroM")!= null)
                                centroMedico = objCita.get("centroM").toString();

                            if(objCita.get("motivo")!= null)
                                motivoCita = objCita.get("motivo").toString();
                            p = contPaciente.buscarPaciente(Integer.parseInt(cedulaP));
                        }
                        try {
                            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            date = formatter2.parse(objCita.get("fecha").toString());
                        } catch (java.text.ParseException e) {
                            System.out.println("Parse exception: in date");
                        }
                        Cita c = new Cita(p,m,date,modalidad,centroMedico,motivoCita,null,null);
                        c.setIdCita(objCita.get("UUID").toString());
                        m.agregarCita(c);
                        if(p != null)
                            contPaciente.agregarCitaPaciente(p.getCedula(), c);
                    }
                }
                else
                    System.out.println("Arreglo de ctas es null");
                contMedico.agregarMedico(m);
            }

            fr.close();
            File file = new File(MEDICOS_LECTURA_PATH);

            if (!file.delete()) {
                System.out.println("Failed to delete the temp file.");
            }

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } catch (ParseException e) {
            if(e.getMessage()== null)
                System.out.println("Archivo de persistencia de médicos vacio");
            else
                e.printStackTrace();
        }

        System.out.println("Medicos y citas cargadas\n");
    }

    public void guardarDatosMedicos(Collection<Medico> medicos)//Puede pedir la lista entera de medicos como collection o hashmap desde contMedicos
    {
        JSONArray listM = new JSONArray();
        JSONObject jsonOb = new JSONObject();
        for (Medico med : medicos) {
            JSONObject objMed = new JSONObject();
            objMed.put("cedula", med.getCedula());
            objMed.put("nombre", med.getNombre());
            objMed.put("tarjetaP", med.getTarjetaProfesional());
            objMed.put("edad", med.getEdad());
            objMed.put("especializacion", med.getEspecializacion());
            objMed.put("username", med.getUsername());
            objMed.put("contraseña", med.getContraseña());
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
            if (med.getFechaNacimiento() == null) {
                objMed.put("fechaN", "null");
            } else {
                objMed.put("fechaN", dateFormat.format(med.getFechaNacimiento()));
            }
            JSONArray listCitas = new JSONArray();
            int cont = 0;
            for (Cita cita : med.getCitas().values()) {
                cont++;
                JSONObject objCita = new JSONObject();
                objCita.put("UUID", cita.getIdCita().toString());
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
                objCita.put("fecha", dateFormat2.format(cita.getFecha()));
                if(cita.getPaciente() == null)
                {
                    objCita.put("cedulaP", "null");
                    objCita.put("centroM", "null");
                    objCita.put("motivo", "null");
                    objCita.put("modalidad", "null");
                }
                else
                {
                    objCita.put("cedulaP", cita.getPaciente().getCedula().toString());
                    objCita.put("centroM", cita.getCentroMedico());
                    objCita.put("motivo", cita.getMotivoCita());
                    objCita.put("modalidad", cita.getModalidad());
                }

                listCitas.add(objCita);
            }
            objMed.put("Citas", listCitas);
            listM.add(objMed);
        }
        jsonOb.put("Medicos", listM);


        try {
            FileWriter out = new FileWriter(MEDICOS_LECTURA_PATH, false);
            out.write(jsonOb.toJSONString());
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            SeguridadArchivos.encryptDecrypt(PUBLIC_KEY, Cipher.ENCRYPT_MODE, MEDICOS_LECTURA_PATH, MEDICOS_ENCRIPTADOS_PATH);
        } catch (InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | NoSuchAlgorithmException | IOException e) {
            System.out.println("ERROR DE CIFRADO");
        }

        System.out.println("Datos medicos guardados en archivo");

    }

    public void cargarInventario(){
        JSONParser parser = new JSONParser();
        try {
            FileReader fr = new FileReader(PATH_INVENTARIO);
            Object obj = parser.parse(fr);
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray arrProd = (JSONArray) jsonObject.get("Productos");
            for(int i = 0; i < arrProd.size(); i++)
            {
                JSONObject objProd = (JSONObject) arrProd.get(i);
                String nombre = objProd.get("nombre").toString();
                String codigo = objProd.get("codigo").toString();
                int cantidadDisponible = Integer.parseInt(objProd.get("cantidadDisponible").toString());
                double precioUnidad = Double.parseDouble(objProd.get("precioUnidad").toString());
                int cantidadVendida = Integer.parseInt(objProd.get("cantidadVendida").toString());
                String laboratorio = objProd.get("laboratorio").toString();
                Tipo tipo = Tipo.valueOf(objProd.get("tipo").toString());
                switch(tipo)
                {
                    case PASTILLAS:
                        int numeroPastilla = Integer.parseInt(objProd.get("numeroPastillas").toString());
                        double miligramos = Double.parseDouble(objProd.get("miligramos").toString());
                        Pastilla pa = new Pastilla(nombre, codigo, cantidadDisponible, precioUnidad, cantidadVendida, laboratorio, numeroPastilla, miligramos);
                        contProductos.agregarProducto(pa);
                        break;
                    case JARABES:
                        double cantidadTotal = Double.parseDouble(objProd.get("cantidadTotal").toString());
                        double dosis = Double.parseDouble(objProd.get("dosis").toString());
                        Jarabe ja = new Jarabe(nombre, codigo, cantidadDisponible, precioUnidad, cantidadVendida, laboratorio,cantidadTotal, dosis);
                        contProductos.agregarProducto(ja);
                        break;
                    case TOPICOS:
                        double concentracion = Double.parseDouble(objProd.get("concentracion").toString());
                        Topico to = new Topico(nombre, codigo, cantidadDisponible, precioUnidad, cantidadVendida, laboratorio,concentracion);
                       contProductos.agregarProducto(to);
                        break;
                    case INYECCIONES:
                        double mililitros = Double.parseDouble(objProd.get("mililitros").toString());
                        Inyeccion in = new Inyeccion(nombre, codigo, cantidadDisponible, precioUnidad, cantidadVendida, laboratorio, mililitros);
                        contProductos.agregarProducto(in);
                        break;
                    case ORTOPEDICOS:
                        String marca = objProd.get("marca").toString();
                        String talla = objProd.get("talla").toString();
                        Ortopedico or = new Ortopedico(nombre, codigo, cantidadDisponible, precioUnidad, cantidadVendida, laboratorio, marca, talla);
                        contProductos.agregarProducto(or);
                        break;
                    default:
                        break;
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found ex");
        } catch (IOException e) {
            System.out.println("IO ex");
        } catch (ParseException e) {
            System.out.println("No se pudo parse el archivo");
        }
        System.out.println("Inventario cargado");
    }

    public void guardarInventario(){
        JSONArray list = new JSONArray();
        JSONObject objList = new JSONObject();
        for(Producto p: contProductos.getProductosDisponibles().values())
        {
            JSONObject objProd = new JSONObject();
            objProd.put("nombre", p.getNombre());
            objProd.put("codigo",p.getCodigo());
            objProd.put("cantidadDisponible",p.getCantidadDisponible());
            objProd.put("precioUnidad",p.getPrecioUnidad());
            objProd.put("cantidadVendida",p.getCantidadVendida());
            objProd.put("laboratorio", p.getNomLaboratorio());
            objProd.put("tipo",p.getTipo().name());
            objProd.put("numeroPastillas","null");
            objProd.put("miligramos","null");
            objProd.put("cantidadTotal","null");
            objProd.put("concentracion","null");
            objProd.put("mililitros","null");
            objProd.put("marca","null");
            objProd.put("talla","null");
            switch(p.getTipoProducto())
            {
                case PASTILLAS:
                    Pastilla pa = (Pastilla)p;
                    objProd.put("numeroPastillas",pa.getNumeroPastilla());
                    objProd.put("miligramos",pa.getMiligramos());
                    break;
                case JARABES:
                    Jarabe ja = (Jarabe) p;
                    objProd.put("cantidadTotal",ja.getCantidadTotal());
                    objProd.put("dosis",ja.getDosis());
                    break;
                case TOPICOS:
                    Topico to = (Topico) p;
                    objProd.put("concentracion",to.getConcentracion());
                    break;
                case INYECCIONES:
                    Inyeccion in = (Inyeccion) p;
                    objProd.put("mililitros",in.getMililitros());
                    break;
                case ORTOPEDICOS:
                    Ortopedico or = (Ortopedico) p;
                    objProd.put("marca",or.getMarca());
                    objProd.put("talla",or.getTalla());
                    break;
                default:
                    break;

            }
            list.add(objProd);
        }
        objList.put("Productos", list);

        try {
            FileWriter file = new FileWriter(PATH_INVENTARIO, false);
            String s = objList.toJSONString();
            file.write(s);
            file.flush();
            file.close();

        } catch (IOException e) {
            //TODO manejar el error
        }
        catch(Exception e){

            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Datos Inventario guardados en archivo");

    }
}
