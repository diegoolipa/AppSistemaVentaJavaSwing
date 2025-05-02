/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConexionMysql {
    // Variables estáticas para conexión MySQL

    private static Connection conn = null;
    private static String login = "root";           // Usuario MySQL
    private static String clave = "";         // Contraseña MySQL
    private static String url = "jdbc:mysql://localhost:3306/venta";

    // Método para obtener conexión
    public static Connection getConnection() {
        try {
            // Cargar el driver JDBC para MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer conexión
            conn = DriverManager.getConnection(url, login, clave);

            // Manejo manual de transacciones
            conn.setAutoCommit(false);

            System.out.println("======================================================");
            System.out.println("✅ Diego Frank Lipa Choque");
            System.out.println("======================================================");

            if (conn != null) {
                System.out.println("Conexión a MySQL Exitosa");
            } else {
                System.out.println("Alto: Conexión Fallida");
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
        }
        return conn;
    }

    // Cerrar conexión
    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada correctamente");
            }
        } catch (Exception e) {
            System.out.println("Alto: error al desconectar: " + e.getMessage());
        }
    }

    // Main de prueba
    public static void main(String[] args) {
        ConexionMysql c = new ConexionMysql();
        c.getConnection();
    }
}
