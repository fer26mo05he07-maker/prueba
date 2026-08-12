/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_modelo;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Arath
 */
public class DAORenta {

    private final String URL = "jdbc:mysql://localhost:3306/renta_discos";
    private final String USER = "root";
    private final String PASS = "root";

    // INSERTAR RENTA
    public void insertar(Renta r) throws SQLException {

        String sql = "INSERT INTO renta(idCliente, idDisco, fecha, cantidad) "
                + "VALUES (?,?,?,?)";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, r.getIdCliente());
            stmt.setInt(2, r.getIdDisco());
            stmt.setString(3, r.getFecha());
            stmt.setInt(4, r.getCantidad());

            stmt.executeUpdate();
        }
    }

    // CONSULTAR RENTAS
    public ArrayList<Renta> consultar() throws SQLException {

        ArrayList<Renta> lista = new ArrayList<>();

        String sql = "SELECT * FROM renta";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {

                Renta r = new Renta();

                r.setIdRenta(rs.getInt("idRenta"));
                r.setIdCliente(rs.getInt("idCliente"));
                r.setIdDisco(rs.getInt("idDisco"));
                r.setFecha(rs.getString("fecha"));
                r.setCantidad(rs.getInt("cantidad"));

                lista.add(r);
            }
        }

        return lista;
    }

    // ELIMINAR RENTA
    public void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM renta WHERE idRenta=?";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    // ACTUALIZAR RENTA
    public void actualizar(Renta r) throws SQLException {

        String sql = "UPDATE renta SET idCliente=?, idDisco=?, "
                + "fecha=?, cantidad=? WHERE idRenta=?";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, r.getIdCliente());
            stmt.setInt(2, r.getIdDisco());
            stmt.setString(3, r.getFecha());
            stmt.setInt(4, r.getCantidad());
            stmt.setInt(5, r.getIdRenta());

            stmt.executeUpdate();
        }
    }
}
