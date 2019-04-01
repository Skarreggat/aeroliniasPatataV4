package vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author root
 */
public class MenuCompanyia {

    private JFrame frame;

    private JButton[] menuButtons = new JButton[7];

    private final int AMPLADA = 800;
    private final int ALCADA = 600;

    /*  
    CONSTRUCTOR
    Paràmetres:cap
    Accions:
    Heu d'inicialitzar els atributs d'aquesta classe fent el següent (no afegiu cap listener a cap control):
            
     - Heu d'inicialitzar l'objecte JFrame amb títol "Menú Companyia" i layout Grid d'una columna
     - Heu de crear els botons del formulari. Cada botó serà un element de l'array menuBotons amb les següents etiquetes:
                        "0. Sortir"
                        "1. Alta"
                        "2. Seleccionar"
                        "3. Modificar"
                        "4. Llistar companyies"
                        "5. Carregar companyia"
                        "6. Desar companyia"
      - Heu d'afegir-ho tot a l'atribut frame
      - Heu de fer visible el frame amb l'amplada i alçada que proposen els atributs amplada i alcada
      - Heu de fer que la finestra es tanqui quan l'usuari ho fa amb el control "X" de la finestra
        
     */
    public MenuCompanyia() {
        /*Construim la finestra on mostrarem la taula d'alumnes*/
        frame = new JFrame("Menú Companyia");
        frame.setLayout(new GridLayout(0, 1)); //Format de taula d'una columna
        menuButtons[0] = new JButton("0. Sortir");
        menuButtons[1] = new JButton("1. Alta");
        menuButtons[2] = new JButton("2. Seleccionar");
        menuButtons[3] = new JButton("3. Modificar");
        menuButtons[4] = new JButton("4. Llistar companyies");
        menuButtons[5] = new JButton("5. Carregar companyia");
        menuButtons[6] = new JButton("6. Desar companyia");
        for (int i = 0; i < menuButtons.length; i++) {
            frame.add(menuButtons[i]);
        }
        frame.setResizable(false);
        //Li assignem mides a la finestra. Aquestes seran les mides inicials.
        frame.setSize(AMPLADA, ALCADA);

        //Tanquem la finestra i sortim de l'aplicació al seleccionar x 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JButton[] getMenuButtons() {
        return menuButtons;
    }

    public void setMenuButtons(JButton[] menuButtons) {
        this.menuButtons = menuButtons;
    }
}
