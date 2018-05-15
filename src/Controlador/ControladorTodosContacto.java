/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author silver
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
import Dao.DaoContacto;
import Vista.VistaMenu;
import Vo.Contacto;
import java.util.List;

public class ControladorTodosContacto extends Thread implements ActionListener{
    
       private  VistaTodosContacto vistaT =new VistaTodosContacto();

    

    public ControladorTodosContacto(VistaTodosContacto vistaT) {
        
        this.vistaT= vistaT;
        this.vistaT.SALIR.addActionListener(this);
        this.vistaT.setVisible(true);
        tablacontactos(this.vistaT.JTableContactos);
}
    
    
     public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()== vistaT.SALIR){
            VistaMenu vistaM = new VistaMenu();
            ControladorPrincipal controladorP = new ControladorPrincipal(vistaM);
            vistaT.dispose();
        }
    }
    
    public void tablacontactos(JTable tablacontacto){
      
        DaoContacto contactodao=new DaoContacto();
        
        List<Contacto> N=contactodao.mostrarTabla();
        DefaultTableModel tabla1 = new DefaultTableModel();
        tablacontacto.setModel(tabla1);
        
        tabla1.addColumn("ID");
        tabla1.addColumn("NOMBRE");
        tabla1.addColumn("TELEFONO");
    
        Object [] columna = new Object[3];
        
        int cantidad = contactodao.mostrarTabla().size();
        
        for(int i=0; i<cantidad;i++){
        
            columna[0] = contactodao.mostrarTabla().get(i).getId();
            columna[1] = contactodao.mostrarTabla().get(i).getNombre();
            columna[2] = contactodao.mostrarTabla().get(i).getTelefono();
                    
            tabla1.addRow(columna);
        }
        
    }

    

    
    
}
