package controlador;

import db.ConexionOracle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import modelo.ProductoModelo;

public class ProductoControlador {

    //Variables Gobales
    private Connection connection;

    //Metodos par realizar el CRUD
    public ProductoControlador() {
        this.connection = ConexionOracle.getConnection();
    }

    public String CrearProducto(ProductoModelo producto) {
        //Insert Into Diego
        String sql = "INSERT INTO S_PRODUCTO "
                + "(NOMBRE_PRODUCTO, PRECIO,STOCK, ID_CATEGORIA,ID_USUARIO_CREADOR, ESTADO) "
                + "VALUES(?, ?, ?, ?, ?, ?) ";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, producto.getNombreProducto());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getStock());
            ps.setInt(4, producto.getIdCategoria());
            ps.setInt(5, 1);
            ps.setString(6, "1");
            ps.execute();
            connection.commit();
            return "Poducto creado correctamente";
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
            }
            return "Error al crear Producto: " + e.getMessage();
        }

    }

    public List<ProductoModelo> ListarProducto() {
        List<ProductoModelo> lista = new ArrayList<>();
        String sql = "SELECT * FROM S_PRODUCTO ";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ProductoModelo pm = new ProductoModelo();
                pm.setIdProducto(rs.getInt("id_producto"));
                pm.setNombreProducto(rs.getString("nombre_producto"));
                pm.setPrecio(rs.getDouble("precio"));
                pm.setStock(rs.getInt("stock"));
                pm.setIdCategoria(rs.getInt("id_categoria"));
                pm.setIdUsuarioCreador(rs.getInt("id_usuario_creador"));
                pm.setEstado(rs.getString("estado"));
                lista.add(pm);
            }

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
            }
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }

    public String EditarProducto(ProductoModelo pm) {
        // Update
        String sql = "UPDATE S_PRODUCTO SET "
                + "NOMBRE_PRODUCTO = ?, PRECIO = ?, STOCK = ?, ID_CATEGORIA = ?, "
                + "ID_USUARIO_CREADOR = ?, ESTADO = ?"
                + "WHERE ID_PRODUCTO = ? ";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, pm.getNombreProducto());
            ps.setDouble(2, pm.getPrecio());
            ps.setInt(3, pm.getStock());
            ps.setInt(4, pm.getIdCategoria());
            ps.setInt(5, 1);
            ps.setString(6, "1");
            ps.setInt(7, pm.getIdProducto());
            ps.execute();
            connection.commit();
            return "Poducto editado correctamente";
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
            }
            return "Error al editar Producto: " + e.getMessage();
        }
    }

    public String EliminarProducto(ProductoModelo pm) {
        // Delete.
        String sql = "DELETE FROM S_PRODUCTO WHERE ID_PRODUCTO = ? ";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, pm.getIdProducto());
            ps.execute();
            connection.commit();
            return "Poducto Eliminado correctamente";
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
            }
            return "Error al eliminar Producto: " + e.getMessage();
        }
    }
}
