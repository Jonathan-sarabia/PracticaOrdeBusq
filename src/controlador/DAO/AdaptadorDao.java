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

}
