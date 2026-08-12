/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_modelo;

/**
 *
 * @author Arath
 */
public class Renta {

    private int idRenta;
    private int idCliente;
    private int idDisco;
    private String fecha;
    private int cantidad;

    public Renta() {
    }

    public Renta(int idRenta, int idCliente, int idDisco, String fecha, int cantidad) {
        this.idRenta = idRenta;
        this.idCliente = idCliente;
        this.idDisco = idDisco;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public int getIdRenta() {
        return idRenta;
    }

    public void setIdRenta(int idRenta) {
        this.idRenta = idRenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String mostrarDatos() {
        return idRenta + " - Cliente: " + idCliente 
                + " - Disco: " + idDisco 
                + " - Fecha: " + fecha 
                + " - Cantidad: " + cantidad;
    }
}
