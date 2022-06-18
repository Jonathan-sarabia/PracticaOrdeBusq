/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.DAO;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import com.thoughtworks.xstream.security.TypePermission;
import controlador.DAO.InterfazDao;

import controlador.tda.cola.ColaServices;
import controlador.tda.cola.Cola;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

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
        xstream.alias("lista", Cola.class);
        xstream.setMode(XStream.NO_REFERENCES);

        xstream.addPermission(AnyTypePermission.ANY);

        xstream.addPermission(NullPermission.NULL);   // allow "null"
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES); // allow primitive types
    }

    @Override
    public void guardar(T dato) throws Exception {
        try {
            ColaServices<T> lista = listar();
            lista.queue(dato);

            xstream.toXML(lista.getCola(), new FileOutputStream(URL));
        } catch (Exception e) {
        }
    }

    @Override
    public void modificar(T dato, Integer pos) throws Exception {
        try {
            ColaServices<T> lista = listar();
        lista.getCola().modificarDato(pos, dato);
        xstream.toXML(lista.getCola(), new FileOutputStream(URL));
        } catch (Exception e) {
        }
    }

    @Override
    public ColaServices<T> listar() {

        ColaServices<T> listaAux = new ColaServices<T>(0);
        try {
            listaAux.setCola((Cola<T>) xstream.fromXML(new FileReader(URL)));
        } catch (Exception e) {
            System.out.println("ERROR " + e);
            e.printStackTrace();
        }
        return listaAux;
    }

}
