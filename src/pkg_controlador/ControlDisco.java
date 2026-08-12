/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_controlador;
import pkg_modelo.*;
import pkg_vista.*;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author fer26
 */
public class ControlDisco {
    
    private VistaDisco vista;
    private DAODisco dao;

    public ControlDisco(VistaDisco vista) {

        this.vista = vista;
        this.dao = new DAODisco();

        // Poner escuchador al btnGuardar
        this.vista.btnGuardar.addActionListener(e -> guardarDatos());

        // Poner escuchador al btnConsultar
        this.vista.btnConsultar.addActionListener(e -> consultarDatos());

        // Poner escuchador al btnEliminar
        this.vista.btnEliminar.addActionListener(e -> eliminarDatos());

        // Poner escuchador al btnActualizar
        this.vista.btnActualizar.addActionListener(e -> actualizarDatos());

        // Seleccionar datos de la tabla
        this.vista.tabla.getSelectionModel()
                .addListSelectionListener(e -> seleccionarDatos());
        //cambiar de vista
        
        this.vista.btnMenu.addActionListener(e -> regresarMenu ());

    }
    public void guardarDatos() {

    try {

        String titulo = vista.txtTitulo.getText().trim();
        String genero = vista.txtGenero.getText().trim();

        double precioRenta = Double.parseDouble(
                vista.txtPrecioRenta.getText().trim()
        );

        int existencia = Integer.parseInt(
                vista.txtExistencia.getText().trim()
        );

        Disco d = new Disco(
                0,
                titulo,
                genero,
                precioRenta,
                existencia
        );

        dao.insertar(d);

        limpiarDatos();

        JOptionPane.showMessageDialog(
                vista,
                "Disco guardado: " + titulo
        );

        consultarDatos();

    } catch (SQLException ex) {

        JOptionPane.showMessageDialog(
                vista,
                "Error al guardar: " + ex.getMessage()
        );

    } catch (NumberFormatException ex) {

        JOptionPane.showMessageDialog(
                vista,
                "Precio de renta y existencia deben ser números."
        );
    }
}

   public void consultarDatos() {

    try {

        vista.modeloTabla.setRowCount(0);

        for (Disco d : dao.consultar()) {

            vista.modeloTabla.addRow(new Object[]{
                d.getIdDisco(),
                d.getTitulo(),
                d.getGenero(),
                d.getPrecioRenta(),
                d.getExistencia()
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

            // Guardar ID seleccionado
            vista.idDiscoSeleccionado =
                    Integer.parseInt(
                            vista.tabla.getValueAt(fila, 0).toString()
                    );

            // Pasar datos de la tabla a los campos
            vista.txtTitulo.setText(
                    vista.tabla.getValueAt(fila, 1).toString()
            );

            vista.txtGenero.setText(
                    vista.tabla.getValueAt(fila, 2).toString()
            );

            vista.txtPrecioRenta.setText(
                    vista.tabla.getValueAt(fila, 3).toString()
            );

            vista.txtExistencia.setText(
                    vista.tabla.getValueAt(fila, 4).toString()
            );
        }
    }

    public void actualizarDatos() {

        try {

            // Verificar que haya un disco seleccionado
            if (vista.idDiscoSeleccionado == -1) {

                JOptionPane.showMessageDialog(
                        vista,
                        "Selecciona un disco para actualizar."
                );

                return;
            }

            // Recuperar datos de la vista
            String titulo = vista.txtTitulo.getText().trim();
            String genero = vista.txtGenero.getText().trim();

            double precioRenta = Double.parseDouble(
                    vista.txtPrecioRenta.getText().trim()
            );

            int existencia = Integer.parseInt(
                    vista.txtExistencia.getText().trim()
            );

            // Llenar objeto
            Disco d = new Disco(
                    vista.idDiscoSeleccionado,
                    titulo,
                    genero,
                    precioRenta,
                    existencia
            );

            // Actualizar
            dao.actualizar(d);

            // Limpiar datos
            limpiarDatos();

            // Reiniciar selección
            vista.idDiscoSeleccionado = -1;

            // Mensaje de confirmación
            JOptionPane.showMessageDialog(
                    vista,
                    "Disco actualizado: " + titulo
            );

            // Actualizar tabla
            consultarDatos();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Error al actualizar: " + ex.getMessage()
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Precio de renta y existencia deben ser números."
            );
        }
    }

    public void eliminarDatos() {

        try {

            // Verificar que haya un disco seleccionado
            if (vista.idDiscoSeleccionado == -1) {

                JOptionPane.showMessageDialog(
                        vista,
                        "Selecciona un disco para eliminar."
                );

                return;
            }

            // Confirmar eliminación
            int confirmacion = JOptionPane.showConfirmDialog(
                    vista,
                    "¿Estás seguro de eliminar este disco?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {

                // Eliminar
                dao.eliminar(vista.idDiscoSeleccionado);

                // Reiniciar selección
                vista.idDiscoSeleccionado = -1;

                // Limpiar campos
                limpiarDatos();

                // Mensaje
                JOptionPane.showMessageDialog(
                        vista,
                        "Disco eliminado."
                );

                // Actualizar tabla
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

        vista.txtTitulo.setText("");
        vista.txtGenero.setText("");
        vista.txtPrecioRenta.setText("");
        vista.txtExistencia.setText("");
    }
        
    public void regresarMenu(){
            VistaMenu menu = new VistaMenu();
    menu.setVisible(true);
    vista.dispose();
    }
}
    
    

