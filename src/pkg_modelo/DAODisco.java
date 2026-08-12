/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_modelo;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author fer26
 */

public class DAODisco {

    private final String URL = "jdbc:mysql://localhost:3306/renta_discos";
    private final String USER = "root";
    private final String PASS = "root";

    // INSERTAR DISCO
    public void insertar(Disco d) throws SQLException {

        String sql = "INSERT INTO disco(titulo, genero, precioRenta, existencia) "
                + "VALUES (?,?,?,?)";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, d.getTitulo());
            stmt.setString(2, d.getGenero());
            stmt.setDouble(3, d.getPrecioRenta());
            stmt.setInt(4, d.getExistencia());

            stmt.executeUpdate();
        }
    }

    // CONSULTAR DISCOS
    public ArrayList<Disco> consultar() throws SQLException {

        ArrayList<Disco> lista = new ArrayList<>();

        String sql = "SELECT * FROM disco";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {

                Disco d = new Disco();

                d.setIdDisco(rs.getInt("idDisco"));
                d.setTitulo(rs.getString("titulo"));
                d.setGenero(rs.getString("genero"));
                d.setPrecioRenta(rs.getDouble("precioRenta"));
                d.setExistencia(rs.getInt("existencia"));

                lista.add(d);
            }
        }

        return lista;
    }

    // ELIMINAR DISCO
    public void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM disco WHERE idDisco=?";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    // ACTUALIZAR DISCO
    public void actualizar(Disco d) throws SQLException {

        String sql = "UPDATE disco SET titulo=?, genero=?, precioRenta=?, "
                + "existencia=? WHERE idDisco=?";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, d.getTitulo());
            stmt.setString(2, d.getGenero());
            stmt.setDouble(3, d.getPrecioRenta());
            stmt.setInt(4, d.getExistencia());
            stmt.setInt(5, d.getIdDisco());

            stmt.executeUpdate();
        }
    }
}