/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorAuto;

import controlador.DAO.AdaptadorDao;
import controlador.tda.lista.ListaEnlazadaServices;
import modelo.Auto;

/**
 *
 * @author jona
 */
public class AutoController extends AdaptadorDao<Auto> {
     private Auto auto;
     private ListaEnlazadaServices<Auto> lisautos = new  ListaEnlazadaServices<Auto>();
     

     public AutoController() {
          super(Auto.class);
          listado();
     }

     public Auto getAuto() {
          if (this.auto== null) {
            this.auto = new Auto();
        }
        
          return auto;
     }

     public void setAuto(Auto auto) {
          this.auto = auto;
     }

     public ListaEnlazadaServices<Auto> getLisautos() {
          return lisautos;
     }

     public void setLisautos(ListaEnlazadaServices<Auto> lisautos) {
          this.lisautos = lisautos;
     }
     
     
     public Boolean guardar() {
        try {            
            //getMovie().set(listaTickets.getSize()+1);
            guardar(getAuto());
            return true;
        } catch (Exception e) {
            System.out.println("Error en guardar autor"+e);
        }
        return false;
    }
    public Boolean modificar(Integer pos) {
        try {            
            
            modificar(getAuto(), pos);
            return true;
        } catch (Exception e) {
            System.out.println("Error en modificar ticket"+e);
        }
        return false;
    }
    
    public ListaEnlazadaServices<Auto> listado() {
        setLisautos(listar());
        return lisautos;
    }
     
     
     
     
}
