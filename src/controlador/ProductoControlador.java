
package controlador;

import db.Conexion;
import java.sql.Connection;
import java.sql.SQLException;

public class ProductoControlador {
    //Variables Gobales
    private Connection connection;
    
    //Metodos par realizar el CRUD
    
    public ProductoControlador() throws SQLException{
        this.connection = Conexion.getConnection();
    }
    
    public void CrearProducto(){
        //Insert Into
        String sql = "INSERT INTO S_PRODUCTO " +
        "(ID_PRODUCTO, NOMBRE_PRODUCTO, PRECIO,STOCK, ID_CATEGORIA,ID_USUARIO_CREADOR, ESTADO) " +
        "VALUES(2, 'Platano', 1, 50, 1, 1, 1) ";
        
        
        
        
    }
    
    public void ListarProducto(){
        // Select 
    }
    
    public void ActualizarProducto(){
        // Update
    }
    
    public void EliminarProducto(){
        // Delete
    }
}
