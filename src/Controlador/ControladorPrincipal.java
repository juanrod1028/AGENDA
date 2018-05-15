/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.VistaAgregarContacto;
import Vista.VistaContacto;
import Vista.VistaTodosContacto;
import Vista.VistaMenu;
import Vista.VistaGrupo;
import Vista.VistaTodosGrupo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPrincipal implements ActionListener {

    private static VistaMenu VP;

    public ControladorPrincipal(VistaMenu vista) {
        this.VP = vista;
        this.VP.jMenuItemMenu.addActionListener(this);
        this.VP.jMenuItemGrupo.addActionListener(this);
        this.VP.jMenuItem2.addActionListener(this);
        this.VP.jMenuItemAñadirAGrupo.addActionListener(this);
        this.VP.TodosContacto.addActionListener(this);
        this.VP.TodosGrupo.addActionListener(this);
        this.VP.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == VP.jMenuItemMenu) {
            VistaContacto base = new VistaContacto();
            ControladorContacto bD = new ControladorContacto(base);
            //  VP.dispose();

        }
        if (e.getSource() == VP.jMenuItemGrupo) {
            VistaGrupo grupo = new VistaGrupo();
            ControladorGrupo ct = new ControladorGrupo(grupo);
            // VP.dispose();
        }
        if (e.getActionCommand().equalsIgnoreCase("salir")) {
            System.exit(0);
        }
        if (e.getSource() == VP.jMenuItemAñadirAGrupo) {
            VistaAgregarContacto vistaA= new VistaAgregarContacto();
            ControladorAñadirAGrupo controladorAÑ= new ControladorAñadirAGrupo(vistaA);
            VP.dispose();
        }
        
        if (e.getSource() == VP.TodosContacto) {
           VistaTodosContacto vistaT =new VistaTodosContacto();
            ControladorTodosContacto controladorTodos = new ControladorTodosContacto(vistaT);
            VP.dispose();
        }
        
        if (e.getSource() == VP.TodosGrupo) {
           VistaTodosGrupo vistaG =new VistaTodosGrupo();
            ControladorTodosGrupo controladorTodos = new ControladorTodosGrupo(vistaG);
            VP.dispose();
        }

    }
}
