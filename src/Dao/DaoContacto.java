package Dao;

import Utilidades.Conexion;
import Vo.Contacto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DaoContacto {

    private Connection dbConnection;
    private PreparedStatement preparedStmt;
    private ResultSet resultSet;

    public DaoContacto() {
        this.dbConnection = null;
        this.preparedStmt = null;
        this.resultSet = null;
    }

    public int registrarContacto(Contacto contacto) {
        int resultado = 0;
        try {
            dbConnection = Conexion.getConexion();
            String insertSQL
                    = "INSERT INTO AGENDA(ID,NOMBRE,TELEFONO)" + " VALUES( ?,  ?,  ?)";
            preparedStmt = dbConnection.prepareStatement(insertSQL);
            preparedStmt.setString(1, contacto.getId());
            preparedStmt.setString(2, contacto.getNombre());
            preparedStmt.setString(3, contacto.getTelefono());

            resultado = preparedStmt.executeUpdate();
            dbConnection.close();
            preparedStmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas con la Creación del Contacto Comuniquese con el Adminstrador");
        }
        return resultado;
    }

    public Contacto consultarContacto(String ID) {
        Contacto contactoRead = new Contacto();
        try {
            dbConnection = Conexion.getConexion();
            String selectSQL = "SELECT * FROM AGENDA WHERE ID = ?";

            preparedStmt = dbConnection.prepareStatement(selectSQL);
            preparedStmt.setString(1, ID);
            resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                contactoRead.setNombre(resultSet.getString("nombre"));
                contactoRead.setTelefono(resultSet.getString("telefono"));
            }
            dbConnection.close();
            preparedStmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas en la Consulta Comuniquese con el Administrador");

        }
        return contactoRead;
    }

    public int consultarModificar(Contacto contacto) {
        int resultado = 0;
        try {
            dbConnection = Conexion.getConexion();
            String updateSQL = "UPDATE AGENDA SET TELEFONO =?,NOMBRE =? " + " WHERE ID = ?";
            preparedStmt = dbConnection.prepareStatement(updateSQL);
            preparedStmt.setString(1, contacto.getTelefono());
            preparedStmt.setString(2, contacto.getNombre());
            preparedStmt.setString(3, contacto.getId());
            resultado = preparedStmt.executeUpdate();
            dbConnection.close();
            preparedStmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas con la Actualizacion de un Contacto Comuniquese con el Administrador");
        }
        return resultado;
    }

    public int eliminarContacto(int ID) {
        int resultado = 0;
        try {
            dbConnection = Conexion.getConexion();
            String deleteSQL = "DELETE FROM AGENDA WHERE id = ?";
            preparedStmt = dbConnection.prepareStatement(deleteSQL);
            preparedStmt.setInt(1, ID);
            resultado = preparedStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas con el Borrado de un Contacto Comuniquese con el Administrador");
        }
        return resultado;
    }

    public List<Contacto> mostrarTabla() {
        List<Contacto> listContacto = new ArrayList();
        try {
            dbConnection = Conexion.getConexion();

            String selectSQL = "SELECT * FROM AGENDA ORDER BY nombre";

            preparedStmt = dbConnection.prepareStatement(selectSQL);

            resultSet = preparedStmt.executeQuery();

            Contacto contactoAll;

            while (resultSet.next()) {
                contactoAll = new Contacto();
                
                contactoAll.setId(resultSet.getString("ID"));
                
                contactoAll.setNombre(resultSet.getString("NOMBRE"));

                contactoAll.setTelefono(resultSet.getString("TELEFONO"));

                

                listContacto.add(contactoAll);
            }
            dbConnection.close();

            preparedStmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas en la Consulta Comuniquese con el Administrador");
        }
        return listContacto;
    }

}
