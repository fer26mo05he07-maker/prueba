/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba;

import pkg_controlador.ControlCliente;
import pkg_vista.VistaCliente;

/**
 *
 * @author fer26
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        
    VistaCliente vista = new VistaCliente();

    ControlCliente controlador = new ControlCliente(vista);

    vista.setVisible(true);
    }
}
    

