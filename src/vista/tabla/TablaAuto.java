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
          return 4;
     }
     @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Nro";
            case 1: return "Placa";
            case 2: return "Modelo";
            case 3: return "Color";
            default: return null;
        }
    }

     @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Auto f = lista.getLista().obtenerDato(rowIndex);
            switch (columnIndex) {
                case 0:
                    return ( rowIndex + 1);
                case 1:
                    return f.getPlaca();
                case 2:
                    return f.getModelo();
                case 3:
                    return f.getColor();
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
        
        }
     
}
