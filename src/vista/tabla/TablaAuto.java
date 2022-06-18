/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.tabla;

import controlador.tda.lista.ListaEnlazadaServices;
import javax.swing.table.AbstractTableModel;
import modelo.Auto;

/**
 *
 * @author jona
 */
public class TablaAuto extends AbstractTableModel{
     private ListaEnlazadaServices<Auto> lista = new  ListaEnlazadaServices<Auto>();

     public ListaEnlazadaServices<Auto> getLista() {
          return lista;
     }

     public void setLista(ListaEnlazadaServices<Auto> lista) {
          this.lista = lista;
     }
      

     @Override
     public int getRowCount() {
          return lista.getSize();
     }

     @Override
     public int getColumnCount() {
          return 3;
     }
     @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Placa";
            case 1: return "Modelo";
            case 2: return "Color";
            default: return null;
        }
    }

     @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Auto f = lista.getLista().obtenerDato(rowIndex);
            switch (columnIndex) {
                case 0:
                    return f.getPlaca();
                case 1:
                    return f.getModelo();
                case 2:
                    return f.getColor();
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
        
        }
     
}
