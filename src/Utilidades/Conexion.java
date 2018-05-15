/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion{
    private static Connection cn = null;
    private static Driver driver = new org.apache.derby.jdbc.ClientDriver();
    private static String URLBD = "jdbc:derby://localhost:1527/agenda";
    private static String usuario = "juan";
    private static String contrasena = "juan";
        
    public static Connection getConexion() throws SQLException {
        DriverManager.registerDriver(driver);
        cn = DriverManager.getConnection(URLBD, usuario, contrasena);
        return cn;
    }
    
    public static void desconectar(){
      cn = null;
   }
}
class TestConexionBD {
    private static Connection con;
   
    public static void main(String[] args) throws SQLException{
        con = Conexion.getConexion();
        if(con != null){
            JOptionPane.showMessageDialog(null, "Conexión Realizada Correctamente");
        }
    }
}