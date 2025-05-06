
package controlador;

import db.ConexionMysql;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.TipoDocumentoModelo;

public class TipoDocumentoControlador {
    
    private Connection connection;
    
    //crear un contructor
    public TipoDocumentoControlador(){
        this.connection = ConexionMysql.getConnection();
    }
    
    //Crear Tipo Documentos
    public String crearTipoDocumento(TipoDocumentoModelo tdm){
        String sql = "INSERT INTO TIPO_DOCUMENTO(NOMBRE, DESCRIPCION, ESTADO) "
                + " VALUE(?,?,?) ";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, tdm.getNombre());
            ps.setString(2, tdm.getDescripcion());
            ps.setInt(3, tdm.getEstado());
            ps.execute();
            connection.commit();
            return "Tipo Documento creado correctamente";
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
            }
            return "Error al crear Tipo Documento " + e.getMessage() ;
        }
    }
    
//    Listar Tipo Documento
    public List<TipoDocumentoModelo> ListarTipoDocumento(){
        List<TipoDocumentoModelo> lista = new ArrayList<>();
        String sql = "SELECT * FROM TIPO_DOCUMENTO ";
        try(Statement stmt = connection.createStatement(); ResultSet rs =stmt.executeQuery(sql)) {
            while(rs.next()){
                TipoDocumentoModelo tdm = new TipoDocumentoModelo();
                tdm.setIdTipoDocumento(rs.getInt("id_tipo_documento"));
                tdm.setNombre(rs.getString("nombre"));
                tdm.setDescripcion(rs.getString("descripcion"));
                tdm.setEstado(rs.getInt("estado"));
                lista.add(tdm);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar Tipo Documento " + e.getMessage());
        }
        return lista;
    }
    //Eliminar Tipo Documento
    public String eliminarTipoDocumento(int idTipoDocumento){
        String sql = "DELETE FROM TIPO_DOCUMENTO WHERE ID_TIPO_DOCUMENTO = ? ";
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idTipoDocumento);
            ps.execute();
            connection.commit();
            return "Tipo Documento eliminado correctamente";
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
            }
            return "Error al eliminar Tipo Documento " + e.getMessage() ;
        }
    }
    
    //Editar Tipo Documento
    public String editarTipoDocumento(TipoDocumentoModelo tdm){
        String sql = "UPDATE TIPO_DOCUMENTO SET NOMBRE=?, DESCRIPCION=?, ESTADO=? "
                + "WHERE ID_TIPO_DOCUMENTO=? ";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, tdm.getNombre());
            ps.setString(2, tdm.getDescripcion());
            ps.setInt(3, tdm.getEstado());
            ps.setInt(4, tdm.getIdTipoDocumento());
            ps.execute();
            connection.commit();
            return "Tipo Documento editado correctamente";
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
            }
            return "Error al editar Tipo Documento " + e.getMessage() ;
        }     
    }
}
