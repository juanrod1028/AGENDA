
package Main;

import Vista.VistaMenu;
import Controlador.ControladorPrincipal;

public class Main {
    public static void main(String[] args) {
        VistaMenu VP = new VistaMenu();
       ControladorPrincipal controladorMenu = new ControladorPrincipal(VP);
    }
    
}
