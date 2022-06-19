/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.tda.lista;

import controlador.tda.lista.exception.PosicionException;
import java.lang.reflect.Field;
import modelo.Auto;
import static vista.FrmAuto.generarPlaca;



/**
 *
 * @author sebastian
 */
//E   T    K   V
//E = T

public class ListaEnlazada<E> {
    
    private NodoLista<E> cabecera;
     private Class clazz;
    private Integer size;
      public static final Integer ASCENDENTE = 1;
    public static final Integer DESENDENTE = 2;
 
    public NodoLista<E> getCabecera() {
        return cabecera;
    }

    public void setCabecera(NodoLista<E> cabecera) {
        this.cabecera = cabecera;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
    
    
    /**
     * Constructor de la clase se inicializa la lista en null y el tamanio en 0
     */
    public ListaEnlazada() {
        cabecera = null;
        size = 0;
    }

    /**
     * Permite ver si la lista esta vacia
     *
     * @return Boolean true si esta vacia, false si esta llena
     */
    public Boolean estaVacia() {
        return cabecera == null;
    }

    private void insertar(E dato) {
        NodoLista<E> nuevo = new NodoLista<>(dato, null);
        if (estaVacia()) {
            cabecera = nuevo;
        } else {
            NodoLista<E> aux = cabecera;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }
        size++;
    }

    public void insertarCabecera(E dato) {
        if (estaVacia()) {
            insertar(dato);
        } else {
            NodoLista<E> nuevo = new NodoLista<>(dato, null);
           
            nuevo.setSiguiente(cabecera);
            cabecera = nuevo;
            size++;
        }
    }

    public void insertar(E dato, Integer pos) throws PosicionException {
        //lista size = 1
        if (estaVacia()) {
            insertar(dato);
        } else if (pos >= 0 && pos < size) {
            NodoLista<E> nuevo = new NodoLista<>(dato, null);
            if (pos == (size - 1)) {
                insertar(dato);
               
            } else {
               
                NodoLista<E> aux = cabecera;
                for (int i = 0; i < pos - 1; i++) {
                    aux = aux.getSiguiente();
                }
                NodoLista<E> siguiente = aux.getSiguiente();
                aux.setSiguiente(nuevo);
                nuevo.setSiguiente(siguiente);
                size++;
            }

        } else {
            throw new PosicionException("Error en insertar: No existe la posicion dada");
        }
    }

    public void imprimir() {
        System.out.println("**************************");
        NodoLista<E> aux = cabecera;
        for (int i = 0; i < getSize(); i++) {
            System.out.print(aux.getDato().toString() + "\t");
            aux = aux.getSiguiente();
        }
        System.out.println("\n" + "**************************");
    }

    public Integer getSize() {
        return size;
    }

    /**
     * Metodo que permite obtener un dato segun la posicion
     *
     * @param pos posicion en la lista
     * @return Elemento
     */
    public E obtenerDato(Integer pos) throws PosicionException {
        if (!estaVacia()) {
            if (pos >= 0 && pos < size) {
                E dato = null;
                if (pos == 0) {
                    dato = cabecera.getDato();
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos; i++) {
                        aux = aux.getSiguiente();
                    }
                    dato = aux.getDato();
                }

                return dato;
            } else {
                throw new PosicionException("Error en obtener dato: No existe la posicion dada");
            }

        } else {
            throw new PosicionException("Error en obtener dato: La lista esta vacia, por endde no hay esa posicion");
        }
    }

    public E eliminarDato(Integer pos) throws PosicionException {
       E auxDato = null;
        if (!estaVacia()) {
            if (pos >= 0 && pos < size) {
                if (pos == 0) {
                    auxDato = cabecera.getDato();
                    cabecera = cabecera.getSiguiente();
                    size--;
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos - 1; i++) {
                        aux = aux.getSiguiente();
                    }
                    auxDato = aux.getDato();
                    NodoLista<E> proximo = aux.getSiguiente();
                    aux.setSiguiente(proximo.getSiguiente());
                    size--;
                }
                  return auxDato;
            } else {
                throw new PosicionException("Error en eliminar dato: No existe la posicion dada");
            }

        } else {
            throw new PosicionException("Error en eliminar dato: La lista esta vacia, por ende no hay esa posicion");
        }
    }

    public void vaciar() {
        cabecera = null;
        size = 0;
        //en c utilizamos el free
        //malloc
    }
    
    public void modificarDato(Integer pos, E datoM) throws PosicionException {
        if (!estaVacia()) {
            if (pos >= 0 && pos < size) {
               // E dato = null;
                if (pos == 0) {
                    cabecera.setDato(datoM);
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos; i++) {
                        aux = aux.getSiguiente();
                    }
                    aux.setDato(datoM);
                }

                
            } else {
                throw new PosicionException("Error en obtener dato: No existe la posicion dada");
            }

        } else {
            throw new PosicionException("Error en obtener dato: La lista esta vacia, por endde no hay esa posicion");
        }
    }
        private Field getField(String nombre) {
        for (Field field : clazz.getDeclaredFields()) {
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
    
        public ListaEnlazada<E> QuisortClase(String atributo, int primero, int ultimo, Integer direccion) {
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
                        modificarDato( i,obtenerDato(j));
                        modificarDato( j,auxiliar);
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
                        modificarDato( i,obtenerDato(j));
                        modificarDato( j,auxiliar);
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
