/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_controlador;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import pkg_modelo.DAORenta;
import pkg_modelo.*;
import pkg_vista.VistaMenu;
import pkg_vista.*;

/**
 *
 * @author fer26
 */
public class ControlRenta {

    private VistaRenta vista;
    private DAORenta dao;

    public ControlRenta(VistaRenta vista) {

        this.vista = vista;
        this.dao = new DAORenta();

        this.vista.btnGuardar.addActionListener(e -> guardarDatos());
        this.vista.btnConsultar.addActionListener(e -> consultarDatos());
        this.vista.btnActualizar.addActionListener(e -> actualizarDatos());
        this.vista.btnEliminar.addActionListener(e -> eliminarDatos());
       this.vista.btnMenu.addActionListener(e -> regresarMenu());

    }

    // GUARDAR RENTA
    public void guardarDatos() {

        try {

            int idCliente = Integer.parseInt(
                    vista.txtIdCliente.getText().trim()
            );

            int idDisco = Integer.parseInt(
                    vista.txtIdDisco.getText().trim()
            );

            String fecha = vista.txtFecha.getText().trim();

            int cantidad = Integer.parseInt(
                    vista.txtCantidad.getText().trim()
            );

            Renta r = new Renta();

            r.setIdCliente(idCliente);
            r.setIdDisco(idDisco);
            r.setFecha(fecha);
            r.setCantidad(cantidad);

            dao.insertar(r);

            vista.txtIdCliente.setText("");
            vista.txtIdDisco.setText("");
            vista.txtFecha.setText("");
            vista.txtCantidad.setText("");

            JOptionPane.showMessageDialog(
                    vista,
                    "Renta guardada correctamente."
            );

            consultarDatos();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Error: " + ex.getMessage()
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Los ID y la cantidad deben ser números."
            );
        }
    }

    // CONSULTAR RENTAS
    public void consultarDatos() {

        try {

            vista.modeloTabla.setRowCount(0);

            for (Renta r : dao.consultar()) {

                vista.modeloTabla.addRow(new Object[]{
                    r.getIdRenta(),
                    r.getIdCliente(),
                    r.getIdDisco(),
                    r.getFecha(),
                    r.getCantidad()
                });
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog( vista, "Error: " + ex.getMessage());
        }
    }

    // SELECCIONAR RENTA
    public void seleccionarDatos() {

        int fila = vista.tabla.getSelectedRow();

        if (fila >= 0) {

            vista.idSeleccionado =
                    (int) vista.tabla.getValueAt(fila, 0);

            vista.txtIdCliente.setText(vista.tabla.getValueAt(fila, 1).toString());
            vista.txtIdDisco.setText( vista.tabla.getValueAt(fila, 2).toString());
            vista.txtFecha.setText(   vista.tabla.getValueAt(fila, 3).toString());
            /*vista.txtCantidad.setText(  vista.tableRenta.getValueAt(fila, 4).toString();*/
             this.vista.btnMenu.addActionListener(e -> regresarMenu ());
        }
    }

    // ACTUALIZAR RENTA
    public void actualizarDatos() {

        try {

            if (vista.idSeleccionado == -1) {

                JOptionPane.showMessageDialog( vista,   "Selecciona una renta." );
                return;
            }

            Renta r = new Renta();

            r.setIdRenta(vista.idSeleccionado);

            r.setIdCliente(
                    Integer.parseInt(
                            vista.txtIdCliente.getText().trim()
                    )
            );

            r.setIdDisco(
                    Integer.parseInt(
                            vista.txtIdDisco.getText().trim()
                    )
            );

            r.setFecha(
                    vista.txtFecha.getText().trim()
            );

            r.setCantidad(
                    Integer.parseInt(
                            vista.txtCantidad.getText().trim()
                    )
            );

            dao.actualizar(r);

            JOptionPane.showMessageDialog(
                    vista,
                    "Renta actualizada correctamente."
            );

            consultarDatos();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Error: " + ex.getMessage()
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Los ID y la cantidad deben ser números."
            );
        }
    }

    // ELIMINAR RENTA
    public void eliminarDatos() {

        try {

            if (vista.idSeleccionado == -1) {
                JOptionPane.showMessageDialog( vista, "Selecciona una renta."
                );

                return;
            }

            dao.eliminar(vista.idSeleccionado);

            JOptionPane.showMessageDialog(
                    vista,
                    "Renta eliminada correctamente."
            );

            consultarDatos();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Error: " + ex.getMessage()
            );
        }
    }
     public void regresarMenu(){
            VistaMenu menu = new VistaMenu();
    menu.setVisible(true);
    vista.dispose();
    }
}