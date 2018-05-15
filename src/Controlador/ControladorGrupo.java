
package Controlador;
import Vista.VistaGrupo;
import Dao.DaoGrupo;
import Vista.VistaMenu;
import Vo.Grupo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControladorGrupo implements ActionListener {

    private VistaGrupo vista;
    private DaoGrupo modeloContacto;
    private Grupo grupo;

    public ControladorGrupo(VistaGrupo vista){
        this.vista=vista;
        this.vista.CrearGrupo.addActionListener(this);
        this.vista.MODIFICARGRUPO.addActionListener(this);
        this.vista.SALIR.addActionListener(this);
        this.vista.EliminarGrupo.addActionListener(this);
        this.modeloContacto = new DaoGrupo();
        this.vista.setVisible(true);
        tablagrupo(this.vista.JTableGrupos);
    }
    
      public void vaciar() {
        vista.jTextIdGrupo.setText("");
        vista.jTextNombreGrupo.setText("");
            }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.CrearGrupo) {
            this.grupo = new Grupo();
            grupo.setIdGrupo((String.valueOf(vista.jTextIdGrupo.getText())));
            grupo.setNombreGrupo(String.valueOf(vista.jTextNombreGrupo.getText()));     
            JOptionPane.showMessageDialog(null, "Se ha creado un grupo");
            modeloContacto.registrarGrupo(this.grupo);
            tablagrupo(vista.JTableGrupos);
            vaciar();
        }
       
        if (e.getSource() == vista.EliminarGrupo) {
            if (modeloContacto.eliminarGrupo(Integer.valueOf(this.vista.jTextIdGrupo.getText())) == 0) {
                JOptionPane.showMessageDialog(vista, "No se ha eliminado");
            } else {
                JOptionPane.showMessageDialog(vista, "Se ha eliminado");
            }
            tablagrupo(vista.JTableGrupos);
            vaciar();
        } 
        if (e.getSource() == vista.SALIR) {
            VistaMenu vistaM = new VistaMenu();
            ControladorPrincipal controladorP = new ControladorPrincipal(vistaM);
            vista.dispose();
        }
        if (e.getSource() == vista.MODIFICARGRUPO) {

            grupo = new Grupo();
            modeloContacto = new DaoGrupo();
            grupo.setIdGrupo(vista.jTextIdGrupo.getText());
            grupo.setNombreGrupo(vista.jTextNombreGrupo.getText());
            modeloContacto.consultarModificar(grupo);
            tablagrupo(vista.JTableGrupos);
            vaciar();
        }
        
    }
    public void tablagrupo(JTable tabla1){
        
        DaoGrupo grupoDAO = new DaoGrupo();
    
        DefaultTableModel tabla = new DefaultTableModel();
        tabla1.setModel(tabla);
        
        tabla.addColumn("ID GRUPO");
        tabla.addColumn("NOMBRE GRUPO");
    
        Object [] columna = new Object[4];
        
        int numeroderegistro = grupoDAO.mostrarTabla().size();
        
        for(int i=0; i<numeroderegistro;i++){
        
            columna[0] = grupoDAO.mostrarTabla().get(i).getIdGrupo();
            columna[1] = grupoDAO.mostrarTabla().get(i).getNombreGrupo();
                    
            tabla.addRow(columna);
        }
    }
}
