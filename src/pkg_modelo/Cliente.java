/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_modelo;

/**
 *
 * @author Arath
 */
public class Cliente extends Persona {

    private int idCliente;

    public Cliente() {
        super();
    }

    public Cliente(int idCliente, String nombre, String telefono, String correo) {
        super(nombre, telefono, correo);
        this.idCliente = idCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String mostrarDatos() {
        return idCliente + " - " + getNombre() + " - " + getTelefono() + " - " + getCorreo();
    }
}
