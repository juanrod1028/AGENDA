/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Utilidades.Conexion;
import Vo.Contacto;
import Vo.Grupo;

/**
 *
 * @author blue
 */
public class DaoGrupo {

    private Connection dbConnection;
    private PreparedStatement preparedStmt;
    private ResultSet resultSet;

    public DaoGrupo() {
        this.dbConnection = null;
        this.preparedStmt = null;
        this.resultSet = null;
    }

    public int registrarGrupo(Grupo grupito) {
        int resultado = 0;
        try {
            dbConnection = Conexion.getConexion();
            String insertSQL
                    = "INSERT INTO grupo(IDG,NOMBREGRUPO)" + " VALUES(?,?)";
            preparedStmt = dbConnection.prepareStatement(insertSQL);
            preparedStmt.setString(1, grupito.getIdGrupo());
            preparedStmt.setString(2, grupito.getNombreGrupo());

            resultado = preparedStmt.executeUpdate();
            dbConnection.close();
            preparedStmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas con la Creación del Contacto Comuniquese con el Adminstrador");
        }
        return resultado;
    }

    public Grupo consultarGrupo(String ID) {
        Grupo grupoRead = new Grupo();
        try {
            dbConnection = Conexion.getConexion();
            String selectSQL = "SELECT * FROM grupo WHERE IDG = ?";
            preparedStmt = dbConnection.prepareStatement(selectSQL);
            preparedStmt.setString(1, ID);
            resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                grupoRead.setIdGrupo(resultSet.getString("IDG"));
                grupoRead.setNombreGrupo(resultSet.getString("NOMBREGRUPO"));
            }
            dbConnection.close();
            preparedStmt.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas en la Consulta Comuniquese con el Administrador");

        }
        return grupoRead;
    }

    public int consultarModificar(Grupo grupito) {
        int resultado = 0;
        try {
            dbConnection = Conexion.getConexion();
            String updateSQL = "UPDATE grupo SET NOMBREGRUPO =? " + " WHERE IDG = ?";
            preparedStmt = dbConnection.prepareStatement(updateSQL);
            preparedStmt.setString(2, grupito.getNombreGrupo());
            preparedStmt.setString(1, grupito.getIdGrupo());
            resultado = preparedStmt.executeUpdate();
            dbConnection.close();
            preparedStmt.close();
            
            if (resultado != 0) {
                JOptionPane.showMessageDialog(null, "Se ha Modificado el grupo exitosamente!");
            } else {
                JOptionPane.showMessageDialog(null, "El grupo no existe");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas con la Actualizacion de un Contacto Comuniquese con el Administrador");
        }
        return resultado;
    }

    public int eliminarGrupo(int ID) {
        int resultado = 0;
        try {
            dbConnection = Conexion.getConexion();
            String deleteSQL = "DELETE FROM grupo WHERE IDG = ?";
            preparedStmt = dbConnection.prepareStatement(deleteSQL);
            preparedStmt.setInt(1, ID);
            resultado = preparedStmt.executeUpdate();
            if (resultado != 0) {
                JOptionPane.showMessageDialog(null, "Se ha eliminado el grupo exitosamente!");
            } else {
                JOptionPane.showMessageDialog(null, "El grupo no existe");
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas con el Borrado de un Contacto Comuniquese con el Administrador");
        }
        return resultado;
    }

    public List<Grupo> mostrarTabla() {
        List<Grupo> listGrupo = new ArrayList();
        try {
            dbConnection = Conexion.getConexion();

            String selectSQL = "SELECT * FROM grupo ORDER BY NOMBREGRUPO";

            preparedStmt = dbConnection.prepareStatement(selectSQL);

            resultSet = preparedStmt.executeQuery();

            Grupo grupoAll;

            while (resultSet.next()) {
                grupoAll = new Grupo();
                grupoAll.setIdGrupo(resultSet.getString("IDG"));

                grupoAll.setNombreGrupo(resultSet.getString("NOMBREGRUPO"));

                

                listGrupo.add(grupoAll);
            }
            dbConnection.close();

            preparedStmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas en la Consulta Comuniquese con el Administrador");
        }
        return listGrupo;
    }

}
