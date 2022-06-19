/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.DAO;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import controlador.DAO.InterfazDao;
import controlador.tda.lista.ListaEnlazada;
import controlador.tda.lista.ListaEnlazadaServices;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;


/**
 *
 * @author sebastian
 */
public class AdaptadorDao<T> implements InterfazDao<T> {

    private XStream xstream;
    private Class<T> clazz;
    private String URL = "datos" + File.separatorChar;

    public AdaptadorDao(Class<T> clazz) {
        this.clazz = clazz;
        URL += this.clazz.getSimpleName() + ".json";
        xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.alias("lista", ListaEnlazada.class);
        xstream.setMode(XStream.NO_REFERENCES);

        xstream.addPermission(AnyTypePermission.ANY);

        xstream.addPermission(NullPermission.NULL);   // allow "null"
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES); // allow primitive types
    }

    @Override
    public void guardar(T dato) throws Exception {
        try {
            ListaEnlazadaServices<T> lista = listar();
            lista.insertarAlFinal(dato);

            xstream.toXML(lista.getLista(), new FileOutputStream(URL));
        } catch (Exception e) {
        }
    }

    @Override
    public void modificar(T dato, Integer pos) throws Exception {
        try {
            ListaEnlazadaServices<T> lista = listar();
        lista.getLista().modificarDato(pos, dato);
        xstream.toXML(lista.getLista(), new FileOutputStream(URL));
        } catch (Exception e) {
        }
    }

    @Override
    public ListaEnlazadaServices<T> listar() {

        ListaEnlazadaServices<T> listaAux = new ListaEnlazadaServices<T>();
        try {
            listaAux.setLista((ListaEnlazada<T>) xstream.fromXML(new FileReader(URL)));
        } catch (Exception e) {
            System.out.println("ERROR " + e);
            e.printStackTrace();
        }
        return listaAux;
    }
    
//    public ListaEnlazadaServices<T> QuisortClase(String atributo, int primero, int ultimo, Integer direccion) {
//        try {
//            int i, j, central;
//            Object pivote;
//            central = (primero + ultimo) / 2;
//            ListaEnlazada aux= new ListaEnlazada();
//            pivote = aux.obtenerDato(central);
//            i = primero;
//            j = ultimo;
//            if (pivote instanceof Number) {
//                do {
//                    if (direccion.intValue() == Lista.ASCENDENTE) {
//                        while (((Number) testReflect(consultarDatoPosicion(i), atributo)).doubleValue() < ((Number) testReflect(consultarDatoPosicion(central), atributo)).doubleValue()) {
//                            i++;
//                        }
//                        while (((Number) testReflect(consultarDatoPosicion(j), atributo)).doubleValue() > ((Number) testReflect(consultarDatoPosicion(central), atributo)).doubleValue()) {
//                            j--;
//                        }
//                    } else {
//                        while (((Number) testReflect(consultarDatoPosicion(i), atributo)).doubleValue() > ((Number) testReflect(consultarDatoPosicion(central), atributo)).doubleValue()) {
//                            i++;
//                        }
//                        while (((Number) testReflect(consultarDatoPosicion(j), atributo)).doubleValue() < ((Number) testReflect(consultarDatoPosicion(central), atributo)).doubleValue()) {
//                            j--;
//                        }
//                    }
//
//                    if (i <= j) {
//                        T auxiliar = consultarDatoPosicion(i);
//                        modificarPorPos(consultarDatoPosicion(j), i);
//                        modificarPorPos(auxiliar, j);
//                        i++;
//                        j--;
//                    }
//                } while (i <= j);
//
//            } else {
//                do {
//                    if (direccion.intValue() == Lista.ASCENDENTE) {
//                        while (testReflect(consultarDatoPosicion(central), atributo).toString().compareTo(testReflect(consultarDatoPosicion(i), atributo).toString()) > 0) {
//                            i++;
//                        }
//                        while (testReflect(consultarDatoPosicion(j), atributo).toString().compareTo(testReflect(consultarDatoPosicion(central), atributo).toString()) > 0) {
//                            j--;
//                        }
//                    } else {
//                        while (testReflect(consultarDatoPosicion(central), atributo).toString().compareTo(testReflect(consultarDatoPosicion(i), atributo).toString()) < 0) {
//                            i++;
//                        }
//                        while (testReflect(consultarDatoPosicion(j), atributo).toString().compareTo(testReflect(consultarDatoPosicion(central), atributo).toString()) < 0) {
//                            j--;
//                        }
//                    }
//                    if (i <= j) {
//                        T auxiliar = consultarDatoPosicion(i);
//                        modificarPorPos(consultarDatoPosicion(j), i);
//                        modificarPorPos(auxiliar, j);
//                        i++;
//                        j--;
//                    }
//                } while (i <= j);
//
//            }
//            if (primero < j) {
//                QuisortClase(atributo, primero, j, direccion);
//            }
//            if (i < ultimo) {
//                QuisortClase(atributo, i, ultimo, direccion);
//            }
//        } catch (Exception e) {
//            System.out.println("Error quiscksort" + e);
//        }
//        return this;
//    }

}
