package controlador;

import db.ConexionOracle;
import java.sql.Connection;
import java.sql.SQLException;
import modelo.CategoriaModelo;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaControlador {

    private Connection connection;

    public CategoriaControlador() {
        this.connection = ConexionOracle.getConnection();
    }

    // CREATE
    public String crearCategoria(CategoriaModelo categoria) {
        String sql = "INSERT INTO S_CATEGORIA (NOMBRE_CATEGORIA, DESCRIPCION, ESTADO) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, categoria.getNombreCategoria());
            ps.setString(2, categoria.getDescripcion());
            ps.setInt(3, categoria.getEstado());
            ps.execute();
            connection.commit();
            return "Categoría creada correctamente";
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
            }
            return "Error al crear categoría: " + e.getMessage();
        }
    }

    // READ
    public List<CategoriaModelo> listarCategorias() {
        List<CategoriaModelo> lista = new ArrayList<>();
        String sql = "SELECT * FROM S_CATEGORIA";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                CategoriaModelo c = new CategoriaModelo();
                c.setIdCategoria(rs.getInt("ID_CATEGORIA"));
                c.setNombreCategoria(rs.getString("NOMBRE_CATEGORIA"));
                c.setDescripcion(rs.getString("DESCRIPCION"));
                c.setEstado(rs.getInt("ESTADO"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar categorías: " + e.getMessage());
        }
        return lista;
    }

    // UPDATE
    public String actualizarCategoria(CategoriaModelo categoria) {
        String sql = "UPDATE S_CATEGORIA SET NOMBRE_CATEGORIA = ?, DESCRIPCION = ?, ESTADO = ? WHERE ID_CATEGORIA = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, categoria.getNombreCategoria());
            ps.setString(2, categoria.getDescripcion());
            ps.setInt(3, categoria.getEstado());
            ps.setInt(4, categoria.getIdCategoria());
            ps.execute();
            connection.commit();
            return "Categoría actualizada correctamente";
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
            }
            return "Error al actualizar categoría: " + e.getMessage();
        }
    }

    // DELETE (soft delete)
    public String eliminarCategoria(int idCategoria) {
        String sql = "DELETE S_CATEGORIA WHERE ID_CATEGORIA = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCategoria);
            ps.execute();
            connection.commit();
            return "Categoría eliminada correctamente ";
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
            }
            return "Error al eliminar categoría: " + e.getMessage();
        }
    }

    public void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexión: " + e.getMessage());
        }
    }

}
