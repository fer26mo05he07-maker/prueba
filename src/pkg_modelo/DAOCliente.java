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
public class DAOCliente {

    private final String URL = "jdbc:mysql://localhost:3306/renta_discos";
    private final String USER = "root";
    private final String PASS = "root";

    // INSERTAR CLIENTE
    public void insertar(Cliente c) throws SQLException {

        String sql = "INSERT INTO cliente(nombre, telefono, correo) VALUES (?,?,?)";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getNombre());
            stmt.setString(2, c.getTelefono());
            stmt.setString(3, c.getCorreo());

            stmt.executeUpdate();
        }
    }

    // CONSULTAR CLIENTES
    public ArrayList<Cliente> consultar() throws SQLException {

        ArrayList<Cliente> lista = new ArrayList<>();

        String sql = "SELECT * FROM cliente";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {

                Cliente c = new Cliente();

                c.setIdCliente(rs.getInt("idCliente"));
                c.setNombre(rs.getString("nombre"));
                c.setTelefono(rs.getString("telefono"));
                c.setCorreo(rs.getString("correo"));

                lista.add(c);
            }
        }

        return lista;
    }

    // ELIMINAR CLIENTE
    public void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM cliente WHERE idCliente=?";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    // ACTUALIZAR CLIENTE
    public void actualizar(Cliente c) throws SQLException {

        String sql = "UPDATE cliente SET nombre=?, telefono=?, correo=? "
                + "WHERE idCliente=?";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getNombre());
            stmt.setString(2, c.getTelefono());
            stmt.setString(3, c.getCorreo());
            stmt.setInt(4, c.getIdCliente());

            stmt.executeUpdate();
        }
    }
}
