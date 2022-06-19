/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorAuto;

import controlador.DAO.AdaptadorDao;
import controlador.tda.lista.ListaEnlazadaServices;
import javassist.bytecode.Bytecode;
import modelo.Auto;
import static vista.FrmAuto.generarPlaca;

/**
 *
 * @author jona
 */
public class AutoController extends AdaptadorDao<Auto> {
     private Auto auto;
     private ListaEnlazadaServices<Auto> lisautos = new  ListaEnlazadaServices<Auto>();
      
     

     public AutoController() {
          super(Auto.class);
          //listado();
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
    
     public static void main(String[] args) {
         Auto a = new Auto();
         AutoController ac =new  AutoController();
       
         
          
               String[] marcas = {"HONDA", "AUDI", "CHEVROLET", "TOYOTA", "NISSAN", "MERCEDES", "ASTON MARTIN"
               + "ALFA ROMEO", "BMW", "BYD", "DACIA0", "FERRARI", "FIAT", "FORD", "HYUNDAI", "JEEP", "JAGUAR", "KIA",
               "LADA", "LAMBORGHINI", "LEXUS", "MASERATI", "MORGAN", "OPEL", "PROSHE", "SEAT", "SMART",
               "SUSUKI", "TESLA", "VOLKSWAGEN", "VOLVO"};

          String[] colores = {"Blanco", "Negro", "Azul", "Amarillo", "Tomate", "Gris", "Verde", "Magenta", "Cafe", "Plata"};
               int i=0;
          while (i<100) {    
               try {
                    ac.getAuto().setColor(colores[(int) (Math.floor(Math.random() * ((colores.length - 1) - 0 + 1) + 0))]);
                    ac.getAuto().setModelo(marcas[(int) (Math.floor(Math.random() * ((marcas.length - 1) - 0 + 1) + 0))]);
                    ac.getAuto().setPlaca(generarPlaca());

                    ac.guardar();
                    i++;

                    } catch (Exception e) {
                    }
               
          }
     

     }
     public static String generarPlaca() {

          char placa[] = new char[9];
          placa[0] = generarConsonante();
          placa[1] = generarConsonante();
          placa[2] = generarVocal();
          placa[3] = '-';
          placa[4] = generarNumero();
          placa[5] = generarNumero();
          placa[6] = generarNumero();
          // placa[7] = '-';
          // placa[8] = generarVocal();

          return String.valueOf(placa);
     }

     public static char generarConsonante() {
          return generarRandomChar("BCDFGHJKLMNPQRSTVWXYZ");
     }

     public static char generarVocal() {
          return generarRandomChar("AEIOU");
     }

     public static char generarNumero() {
          return generarRandomChar("0123456789");
     }

     private static char generarRandomChar(String str) {
          char caracteres[] = str.toCharArray();
          int index = (int) (Math.random() * caracteres.length);
          return caracteres[index];
     }
     
     
     
}
