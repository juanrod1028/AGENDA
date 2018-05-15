/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Utilidades.Conexion;
import Vo.AgregarContacto;
import Vo.Contacto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel Alexander Gomez
 * @serial 1.001.182.203
 * @author Laura Katherine Morales Camelo
 * @serial 1.019.122.626
 */
public class DaoAgregarContacto {

    private Connection dbConnection;
    private PreparedStatement preparedStmt;
    private ResultSet resultSet;

    public DaoAgregarContacto() {
        this.dbConnection = null;
        this.preparedStmt = null;
        this.resultSet = null;
    }

    public List<Contacto> mostrarTabla(String id) {
        List<Contacto> listContacto = new ArrayList();
        try {
            dbConnection = Conexion.getConexion();

            String selectSQL = "SELECT * FROM GRUPOCONTACTO WHERE IDG=?";

            preparedStmt = dbConnection.prepareStatement(selectSQL);
            preparedStmt.setString(1, id);
            resultSet = preparedStmt.executeQuery();

            DaoContacto contactoDAO;

            while (resultSet.next()) {
               contactoDAO= new DaoContacto();
                
               listContacto.add(contactoDAO.consultarContacto(resultSet.getString("ID")));
            }
            dbConnection.close();

            preparedStmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas en la Consulta Comuniquese con el Administrador");
        }
        return listContacto;
    }
    
    public int ingresarContacto(AgregarContacto agregarcont){
         int resultado = 0;
        try {
            dbConnection = Conexion.getConexion();
            String insertSQL = "INSERT INTO GRUPOCONTACTO(IDG,ID)" + " VALUES( ?,  ?)";
            preparedStmt = dbConnection.prepareStatement(insertSQL);

            preparedStmt.setString(1, agregarcont.getIDG());
            preparedStmt.setString(2, agregarcont.getID());

            resultado = preparedStmt.executeUpdate();
            dbConnection.close();
            preparedStmt.close();
            JOptionPane.showMessageDialog(null, "El contacto se agrego exitosamente");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas con la Creación del Contacto Comuniquese con el Adminstrador");
        }
        return resultado;
    }
    
    public int eliminarContacto(String idContacto){
        int resultado = 0;
        try {
            dbConnection = Conexion.getConexion();
            String deleteSQL = "DELETE FROM GRUPOCONTACTO WHERE ID = ?";
            preparedStmt = dbConnection.prepareStatement(deleteSQL);
            preparedStmt.setString(1, idContacto);
            resultado = preparedStmt.executeUpdate();
            if (resultado != 0) {
                JOptionPane.showMessageDialog(null, "Se ha eliminado el contacto exitosamente!");
            } else {
                JOptionPane.showMessageDialog(null, "El contacto no existe");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas con el Borrado de un Contacto Comuniquese con el Administrador");
        }
        return resultado;
    }
}