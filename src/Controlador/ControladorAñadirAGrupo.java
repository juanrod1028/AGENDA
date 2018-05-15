package Controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Dao.DaoAgregarContacto;
import Dao.DaoContacto;
import Dao.DaoGrupo;
import Vo.Contacto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;
import Utilidades.Conexion;
import Vista.VistaAgregarContacto;
import Vista.VistaContacto;
import Vista.VistaMenu; 
import Vo.AgregarContacto;
import Vo.Grupo;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author blue
 */
public class ControladorAñadirAGrupo implements ActionListener {

    private static VistaAgregarContacto vistaAG;
    private static DaoAgregarContacto agrgarDAO;

    public ControladorAñadirAGrupo(VistaAgregarContacto vistaAG) {
        
        this.vistaAG = vistaAG;
        this.vistaAG.BuscarGrupoAsignar.addActionListener(this);
        this.vistaAG.AsignarContactoG.addActionListener(this);
        this.vistaAG.EliminarContactoG.addActionListener(this);
        this.vistaAG.SALIR.addActionListener(this);
        this.vistaAG.setVisible(true);
        tablacontactos(this.vistaAG.jTableContactos);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaAG.BuscarGrupoAsignar) {
            tablacontacto1();
        }
        if (e.getSource() == vistaAG.EliminarContactoG) {
            DefaultTableModel modelo1 = (DefaultTableModel) vistaAG.jTableGrupos.getModel();
            int fila = vistaAG.jTableGrupos.getSelectedRow();
            String id = String.valueOf(modelo1.getValueAt(fila, 0));
            System.out.println(id);
            agrgarDAO = new DaoAgregarContacto();
            agrgarDAO.eliminarContacto(id);
           
            tablacontacto1();

        }

        if (e.getSource() == vistaAG.AsignarContactoG) {
            DefaultTableModel modelo1 = (DefaultTableModel) vistaAG.jTableContactos.getModel();
            int fila = vistaAG.jTableContactos.getSelectedRow();
            AgregarContacto agregarCont = new AgregarContacto();
            agregarCont.setID(String.valueOf(modelo1.getValueAt(fila, 0)));
            agregarCont.setIDG(vistaAG.jTextGrupo.getText());

            agrgarDAO = new DaoAgregarContacto();
            agrgarDAO.ingresarContacto(agregarCont);
            tablacontacto1();

        }

        if (e.getSource() == vistaAG.SALIR) {
            VistaMenu vistaP = new VistaMenu();
            ControladorPrincipal controladorG = new ControladorPrincipal(vistaP);
            vistaP.dispose();
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

    public void tablagrupo1(JTable tablacontacto, List<Contacto> listas){
      
        
        
       
        DefaultTableModel tabla2 = new DefaultTableModel();
        tablacontacto.setModel(tabla2);
        
        tabla2.addColumn("ID ");
        tabla2.addColumn("NOMBRE CONTACTO");
        
    
        Object [] columna = new Object[2];
        
        int cantidad = listas.size();
        
        for(int i=0; i<cantidad;i++){
        
            columna[0] = listas.get(i).getId();
            columna[1] = listas.get(i).getNombre();
            
                    
            tabla2.addRow(columna);
        }
    }
    public void tablacontacto1() {
        List<Contacto> listas = new ArrayList<>();
        agrgarDAO = new DaoAgregarContacto();
        DaoGrupo grupoDao = new DaoGrupo();
        if (grupoDao.consultarGrupo(vistaAG.jTextGrupo.getText()).getIdGrupo()!= null) {

            listas = agrgarDAO.mostrarTabla(vistaAG.jTextGrupo.getText());
            tablagrupo1(vistaAG.jTableGrupos, listas);
            if (listas.size() == 0) {
                JOptionPane.showMessageDialog(null, "Este grupo no tiene contactos");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Este grupo no existe");
            tablagrupo1(vistaAG.jTableGrupos, listas);
        }

}    
}
