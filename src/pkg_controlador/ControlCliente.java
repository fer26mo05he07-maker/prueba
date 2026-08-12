/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_controlador;
import java.sql.*;
import javax.swing.JOptionPane;
import pkg_vista.VistaCliente;
import pkg_modelo.*;
import pkg_modelo.ReglaClienteException;
import pkg_vista.VistaMenu;
/**
 *
 * @author Arath
 */
public class ControlCliente {

    private VistaCliente vista;
    private DAOCliente dao;

    public ControlCliente(VistaCliente vista) {

        this.vista = vista;
        this.dao = new DAOCliente();

        this.vista.btnGuardar.addActionListener(e -> guardarDatos());
        this.vista.btnConsultar.addActionListener(e -> consultarDatos());
        this.vista.btnEliminar.addActionListener(e -> eliminarDatos());
        this.vista.btnActualizar.addActionListener(e -> actualizarDatos());
        this.vista.tabla.getSelectionModel().addListSelectionListener(e -> seleccionarDatos());
        this.vista.btnRegresar.addActionListener(e -> regresarMenu());
        
    }

    public void guardarDatos(){

    try{

        String nombre = vista.txtNombre.getText().trim();
        String telefono = vista.txtTelefono.getText().trim();
        String correo = vista.txtCorreo.getText().trim();

        if (nombre.isEmpty()) {
            throw new ReglaClienteException(
                    "El nombre del cliente no puede estar vacío."
            );
        }

        Cliente c = new Cliente(0, nombre, telefono, correo);

        dao.insertar(c);
        limpiarDatos();

        JOptionPane.showMessageDialog(vista,
            "Cliente guardado: " + nombre);

        consultarDatos();

    }catch (ReglaClienteException ex){

        JOptionPane.showMessageDialog(vista, ex.getMessage());

    }catch (SQLException ex){

        JOptionPane.showMessageDialog(vista,
            "Error al guardar: " + ex.getMessage()
        );
    }
}

    public void consultarDatos(){
        try{
            vista.modeloTabla.setRowCount(0);
            for (Cliente c : dao.consultar()){

                vista.modeloTabla.addRow(new Object[]{
                    c.getIdCliente(),
                    c.getNombre(),
                    c.getTelefono(),
                    c.getCorreo()
                });
            }

        }catch (SQLException ex){

            JOptionPane.showMessageDialog(vista,
                "Error al consultar: " + ex.getMessage()
            );
        }
    }

    public void seleccionarDatos(){
        int fila = vista.tabla.getSelectedRow();
        if (fila >= 0){

            vista.idClienteSeleccionado = Integer.parseInt(
                vista.tabla.getValueAt(fila, 0).toString());

            vista.txtNombre.setText(
                vista.tabla.getValueAt(fila, 1).toString());

            vista.txtTelefono.setText(
                vista.tabla.getValueAt(fila, 2).toString());

            vista.txtCorreo.setText(
                vista.tabla.getValueAt(fila, 3).toString());
        }
    }

    public void eliminarDatos(){
        try{
            if(vista.idClienteSeleccionado == -1){

                JOptionPane.showMessageDialog(vista,
                    "Selecciona un cliente para eliminar.");
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(vista,
                "¿Estás seguro de eliminar este cliente?",
                    "Confirmar", JOptionPane.YES_NO_OPTION);

            if(confirmacion == JOptionPane.YES_OPTION){
                dao.eliminar(vista.idClienteSeleccionado);
                vista.idClienteSeleccionado = -1;
                limpiarDatos();
                JOptionPane.showMessageDialog(vista,
                    "Cliente eliminado.");

                consultarDatos();
            }

        }catch (SQLException ex){
            JOptionPane.showMessageDialog(vista,
                "Error al eliminar: " + ex.getMessage());
        }
    }

    public void actualizarDatos(){
        try{
            if (vista.idClienteSeleccionado == -1){
                JOptionPane.showMessageDialog(vista, 
                "Selecciona un cliente para actualizar.");
                return;
            }

            String nombre = vista.txtNombre.getText().trim();
            String telefono = vista.txtTelefono.getText().trim();
            String correo = vista.txtCorreo.getText().trim();

            Cliente c = new Cliente(
                    vista.idClienteSeleccionado,
                    nombre,
                    telefono,
                    correo);

            dao.actualizar(c);
            limpiarDatos();
            vista.idClienteSeleccionado = -1;
            JOptionPane.showMessageDialog(vista, "Cliente actualizado: " + nombre);

            consultarDatos();

        } catch (SQLException ex){
            JOptionPane.showMessageDialog(vista, "Error al actualizar: " + ex.getMessage());
        }
    }

    public void limpiarDatos(){

        vista.txtNombre.setText("");
        vista.txtTelefono.setText("");
        vista.txtCorreo.setText("");
    }
      public void regresarMenu(){
            VistaMenu menu = new VistaMenu();
    menu.setVisible(true);
    vista.dispose();
    }
}