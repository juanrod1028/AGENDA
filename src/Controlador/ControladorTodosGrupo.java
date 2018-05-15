/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author NICOLAS
 */
import Vista.VistaTodosContacto;
import Vista.VistaContacto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Dao.DaoGrupo;
import Vista.VistaMenu;
import Vista.VistaTodosGrupo;
import Vo.Grupo;
import java.util.List;

public class ControladorTodosGrupo extends Thread implements ActionListener{
    
       private  VistaTodosGrupo vistaG =new VistaTodosGrupo();

    

    public ControladorTodosGrupo(VistaTodosGrupo vistaG) {
        
        this.vistaG= vistaG;
        this.vistaG.SALIR.addActionListener(this);
        this.vistaG.setVisible(true);
        tablagrupo(this.vistaG.jTableGrupo);
}
    
    
     public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()== vistaG.SALIR){
            VistaMenu vistaM = new VistaMenu();
            ControladorPrincipal controladorP = new ControladorPrincipal(vistaM);
            vistaG.dispose();
        }
    }
    
    public void tablagrupo(JTable tablacontacto){
      
        DaoGrupo grupodao=new DaoGrupo();
        
        List<Grupo> N=grupodao.mostrarTabla();
        DefaultTableModel tabla2 = new DefaultTableModel();
        tablacontacto.setModel(tabla2);
        
        tabla2.addColumn("ID GRUPO");
        tabla2.addColumn("NOMBRE GRUPO");
        
    
        Object [] columna = new Object[3];
        
        int cantidad = grupodao.mostrarTabla().size();
        
        for(int i=0; i<cantidad;i++){
        
            columna[0] = grupodao.mostrarTabla().get(i).getIdGrupo();
            columna[1] = grupodao.mostrarTabla().get(i).getNombreGrupo();
            
                    
            tabla2.addRow(columna);
        }
        
    }
    }