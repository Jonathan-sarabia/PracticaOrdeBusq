/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.tda.lista;

import controlador.tda.lista.exception.PosicionException;
import java.lang.reflect.Field;


/**
 *
 * @author sebastian
 */

//@XmlAccessorType(XmlAccessType.FIELD)
public class ListaEnlazadaServices <E> {
    
    private ListaEnlazada<E> lista;

    
    
    public ListaEnlazada<E> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<E> lista) {
        this.lista = lista;
    }
    
    public ListaEnlazadaServices() {
        this.lista = new ListaEnlazada<>();
    }
    
    public Boolean insertarAlInicio(E dato) {       
            lista.insertarCabecera(dato);
            return true;
       
    }
    public Boolean insertarAlFinal(E dato) {
        try {
            //lista.getSize() 1 
            lista.insertar(dato, lista.getSize() - 1);
            return true;
        } catch (PosicionException e) {
            System.out.println(e);
        }
        return false;
    }
    public Boolean insertar(E dato, Integer pos) {
        try {
            lista.insertar(dato, pos);
            return true;
        } catch (PosicionException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public Integer getSize() {
        return lista.getSize();
    }
    
    public E obtenerDato(Integer pos) {
        try {
            return lista.obtenerDato(pos);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public Boolean eliminarCabecera() {
        try {
            lista.eliminarDato(0);
            return true;
        } catch (PosicionException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public Boolean eliminarUltimo() {
        try {
            lista.eliminarDato(lista.getSize() - 1);
            return true;
        } catch (PosicionException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public Boolean eliminarPosicion(Integer pos) {
        try {
            lista.eliminarDato(pos);
            return true;
        } catch (PosicionException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public Boolean modificarDatoPosicion(Integer pos, E dato) {
        try {
            lista.modificarDato(pos, dato);
            return true;
        } catch (PosicionException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public void limpiarLista() {
        lista.vaciar();        
    }
      private Field getField(String nombre) {
        for (Field field : lista.getClass().getDeclaredFields()) {
            if (field.getName().equalsIgnoreCase(nombre)) {
                field.setAccessible(true);
                return field;
            }
        }
        return null;
    }
    
    public Object testReflect(E dato, String atributo) throws Exception {
        return this.getField(atributo).get(dato);
    }
    
        public ListaEnlazadaServices<E> QuisortClase(String atributo, int primero, int ultimo, Integer direccion) {
        try {
            int i, j, central;
            Object pivote;
            central = (primero + ultimo) / 2;
            pivote = obtenerDato(central);
            i = primero;
            j = ultimo;
            if (pivote instanceof Number) {
                do {
                    if (direccion.intValue() == ListaEnlazada.ASCENDENTE) {
                        while (((Number) testReflect(obtenerDato(central), atributo)).doubleValue() >= ((Number) testReflect(obtenerDato(i), atributo)).doubleValue()) {
                            i++;
                        }
                        while (((Number) testReflect(obtenerDato(j), atributo)).doubleValue() > ((Number) testReflect(obtenerDato(central), atributo)).doubleValue()) {
                            j--;
                        }
                    } else {
                        while (((Number) testReflect(obtenerDato(i), atributo)).doubleValue() > ((Number) testReflect(obtenerDato(central), atributo)).doubleValue()) {
                            i++;
                        }
                        while (((Number) testReflect(obtenerDato(j), atributo)).doubleValue() < ((Number) testReflect(obtenerDato(central), atributo)).doubleValue()) {
                            j--;
                        }
                    }

                    if (i <= j) {
                        E auxiliar = obtenerDato(i);
                       lista.modificarDato( i,obtenerDato(j));
                        lista.modificarDato( j,auxiliar);
                        i++;
                        j--;
                    }
                } while (i <= j);

            } else {
                do {
                    if (direccion.intValue() == ListaEnlazada.ASCENDENTE) {
                        while (testReflect(obtenerDato(central), atributo).toString().compareTo(testReflect(obtenerDato(i), atributo).toString()) > 0) {
                            i++;
                        }
                        while (testReflect(obtenerDato(j), atributo).toString().compareTo(testReflect(obtenerDato(central), atributo).toString()) > 0) {
                            j--;
                        }
                    } else {
                        while (testReflect(obtenerDato(central), atributo).toString().compareTo(testReflect(obtenerDato(i), atributo).toString()) < 0) {
                            i++;
                        }
                        while (testReflect(obtenerDato(j), atributo).toString().compareTo(testReflect(obtenerDato(central), atributo).toString()) < 0) {
                            j--;
                        }
                    }
                    if (i <= j) {
                        E auxiliar = obtenerDato(i);
                        lista.modificarDato( i,obtenerDato(j));
                       lista.modificarDato( j,auxiliar);
                        i++;
                        j--;
                    }
                } while (i <= j);

            }
            if (primero < j) {
                QuisortClase(atributo, primero, j, direccion);
            }
            if (i < ultimo) {
                QuisortClase(atributo, i, ultimo, direccion);
            }
        } catch (Exception e) {
            System.out.println("Error quiscksort" + e);
        }
        return this;
    }
    
    
    
}
