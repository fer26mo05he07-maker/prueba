/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_controlador;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import pkg_modelo.DAORenta;
import pkg_modelo.Renta;
import pkg_vista.VistaMenu;
import pkg_vista.VistaRenta;

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

        vista.btnGuardar.addActionListener(e -> guardarDatos());
        vista.btnConsultar.addActionListener(e -> consultarDatos());
        vista.btnActualizar.addActionListener(e -> actualizarDatos());
        vista.btnEliminar.addActionListener(e -> eliminarDatos());

        vista.tabla.getSelectionModel().addListSelectionListener(e -> seleccionarDatos());
    }

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

            DAODisco daoDisco = new DAODisco();

            Disco disco = daoDisco.buscarPorId(idDisco);

            if (disco == null) {

                JOptionPane.showMessageDialog(
                        vista,
                        "El disco no existe."
                );

                return;
            }

            if (cantidad <= 0) {

                JOptionPane.showMessageDialog(
                        vista,
                        "La cantidad debe ser mayor a cero."
                );

                return;
            }

            if (cantidad > disco.getExistencia()) {

                throw new SinExistencia(
                        "No hay suficientes discos disponibles. "
                        + "Existencia: " + disco.getExistencia()
                );
            }

            Renta r = new Renta(
                    0,
                    idCliente,
                    idDisco,
                    fecha,
                    cantidad
            );

            dao.insertar(r);

            int nuevaExistencia =
                    disco.getExistencia() - cantidad;

            daoDisco.actualizarExistencia(
                    idDisco,
                    nuevaExistencia
            );

            limpiarDatos();

            JOptionPane.showMessageDialog(
                    vista,
                    "Renta guardada correctamente."
            );

            consultarDatos();

        } catch (SinExistencias ex) {

            JOptionPane.showMessageDialog(
                    vista,
                    ex.getMessage()
            );

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Error al guardar: " + ex.getMessage()
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Cliente, disco y cantidad deben ser números."
            );
        }
    }

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

            JOptionPane.showMessageDialog(
                    vista,
                    "Error al consultar: " + ex.getMessage()
            );
        }
    }

    public void seleccionarDatos() {

        int fila = vista.tabla.getSelectedRow();

        if (fila >= 0) {

            vista.idRentaSeleccionada =
                    Integer.parseInt(
                            vista.tabla.getValueAt(fila, 0).toString()
                    );

            vista.txtIdCliente.setText(
                    vista.tabla.getValueAt(fila, 1).toString()
            );

            vista.txtIdDisco.setText(
                    vista.tabla.getValueAt(fila, 2).toString()
            );

            vista.txtFecha.setText(
                    vista.tabla.getValueAt(fila, 3).toString()
            );

            vista.txtCantidad.setText(
                    vista.tabla.getValueAt(fila, 4).toString()
            );
        }
    }

    public void actualizarDatos() {

        try {

            if (vista.idRentaSeleccionada == -1) {

                JOptionPane.showMessageDialog(
                        vista,
                        "Selecciona una renta para actualizar."
                );

                return;
            }

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

            Renta r = new Renta(
                    vista.idRentaSeleccionada,
                    idCliente,
                    idDisco,
                    fecha,
                    cantidad
            );

            dao.actualizar(r);

            limpiarDatos();

            vista.idRentaSeleccionada = -1;

            JOptionPane.showMessageDialog(
                    vista,
                    "Renta actualizada."
            );

            consultarDatos();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Error al actualizar: " + ex.getMessage()
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Cliente, disco y cantidad deben ser números."
            );
        }
    }

    public void eliminarDatos() {

        try {

            if (vista.idRentaSeleccionada == -1) {

                JOptionPane.showMessageDialog(
                        vista,
                        "Selecciona una renta para eliminar."
                );

                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(
                    vista,
                    "¿Estás seguro de eliminar esta renta?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {

                dao.eliminar(vista.idRentaSeleccionada);

                vista.idRentaSeleccionada = -1;

                limpiarDatos();

                JOptionPane.showMessageDialog(
                        vista,
                        "Renta eliminada."
                );

                consultarDatos();
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Error al eliminar: " + ex.getMessage()
            );
        }
    }

    public void limpiarDatos() {

        vista.txtIdCliente.setText("");
        vista.txtIdDisco.setText("");
        vista.txtFecha.setText("");
        vista.txtCantidad.setText("");
    }
    
     public void regresarMenu(){
            VistaMenu menu = new VistaMenu();
    menu.setVisible(true);
    vista.dispose();
    }
}