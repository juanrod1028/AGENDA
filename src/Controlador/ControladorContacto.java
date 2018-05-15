package Controlador;

import Dao.DaoContacto;
import Vo.Contacto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;
import Utilidades.Conexion;
import Vista.VistaContacto;
import Vista.VistaMenu;

public class ControladorContacto implements ActionListener {

    private VistaContacto vista;
    private DaoContacto modeloContacto;
    private Contacto contact;

    public ControladorContacto(VistaContacto vista) {
        this.vista = vista;
        this.vista.jButtonRegistrar.addActionListener(this);
        this.vista.SALIR.addActionListener(this);
        this.vista.jButtonConsultar.addActionListener(this);
        this.vista.jButtonModificar.addActionListener(this);
        this.vista.jButtonEliminar.addActionListener(this);
        this.modeloContacto = new DaoContacto();
        this.vista.jButtonModificar.setEnabled(false);
        this.vista.jButtonEliminar.setEnabled(false);
        this.vista.setVisible(true);

    }

    public void vaciar() {
        vista.jTextID.setText(" ");
        vista.jTextNOMBRE.setText(" ");
        vista.jTextTELEFONO.setText(" ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jButtonRegistrar) {
            this.contact = new Contacto();

            contact.setId((String.valueOf(vista.jTextID.getText())));
            contact.setNombre(String.valueOf(vista.jTextNOMBRE.getText()));
            contact.setTelefono(String.valueOf(vista.jTextTELEFONO.getText()));
            JOptionPane.showMessageDialog(null, "Se ha creado un contacto");
            modeloContacto.registrarContacto(this.contact);
            
            vaciar();
        }
        if (e.getSource() == vista.jButtonConsultar) {
            Contacto contact = new Contacto();
            contact = this.modeloContacto.consultarContacto(vista.jTextID.getText());
            if (contact != null) {
                vista.jTextNOMBRE.setText(contact.getNombre());
                vista.jTextTELEFONO.setText(contact.getTelefono());
            } else {
                JOptionPane.showMessageDialog(vista, "No existe");
            }
            this.vista.jButtonModificar.setEnabled(true);
            this.vista.jButtonEliminar.setEnabled(true);
        }

        if (e.getSource() == vista.jButtonEliminar) {
            if (modeloContacto.eliminarContacto(Integer.valueOf(this.vista.jTextID.getText())) == 0) {
                JOptionPane.showMessageDialog(vista, "No se ha eliminado");

            } else {
                JOptionPane.showMessageDialog(vista, "Se ha eliminado");
            }
        }
        if (e.getSource() == vista.jButtonModificar) {
            Contacto contact = new Contacto();
            contact.setNombre(this.vista.jTextNOMBRE.getText());
            contact.setId((this.vista.jTextID.getText()));
            contact.setTelefono((this.vista.jTextTELEFONO.getText()));
            DaoContacto dao = new DaoContacto();
            dao.consultarModificar(contact);
              vaciar();

        }
        if (e.getSource() == vista.SALIR) {
            VistaMenu vistaM = new VistaMenu();
            ControladorPrincipal controladorP = new ControladorPrincipal(vistaM);
            vista.dispose();
        }
    }
}
