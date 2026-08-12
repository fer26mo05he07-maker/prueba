/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_modelo;

/**
 * Hola soy Arath
 * @author fer26
 */
public class Disco {
    
    private int idDisco;
    private String titulo;
    private String genero;
    private double precioRenta;
    private int existencia;

    public Disco() {
    }

    public Disco(int idDisco, String titulo, String genero, double precioRenta, int existencia) {
        this.idDisco = idDisco;
        this.titulo = titulo;
        this.genero = genero;
        this.precioRenta = precioRenta;
        this.existencia = existencia;
    }

    public int getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getPrecioRenta() {
        return precioRenta;
    }

    public void setPrecioRenta(double precioRenta) {
        this.precioRenta = precioRenta;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public String mostrarDatos() {
        return idDisco + " - " + titulo + " - " + genero + " - $" 
                + precioRenta + " - Existencia: " + existencia;
    }

}
