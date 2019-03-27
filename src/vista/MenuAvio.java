package vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author root
 */
public class MenuAvio {
   private JFrame frame;

    private JButton[] menuButtons = new JButton[4];

    private final int AMPLADA = 800;
    private final int ALCADA = 600;

    /*  
    CONSTRUCTOR
    Paràmetres:cap
    Accions:
    Heu d'inicialitzar els atributs d'aquesta classe fent el següent (no afegiu cap listener a cap control):
            
     - Heu d'inicialitzar l'objecte JFrame amb títol "Menú Avió" i layout Grid d'una columna
     - Heu de crear els botons del formulari. Cada botó serà un element de l'array menuBotons amb les següents etiquetes:
                        "0. Sortir"
                        "1. Alta"
                        "2. Modificar"
                        "3. Llistar avions"
      - Heu d'afegir-ho tot a l'atribut frame
      - Heu de fer visible el frame amb l'amplada i alçada que proposen els atributs amplada i alcada
      - Heu de fer que la finestra es tanqui quan l'usuari ho fa amb el control "X" de la finestra
        
    */
    public MenuAvio() {
        frame = new JFrame("Menú Avió");
        frame.setLayout(new GridLayout(0, 1));
        menuButtons[0] = new JButton("0. Sortir");
        menuButtons[1] = new JButton("1. Alta");
        menuButtons[2] = new JButton("2. Modificar");
        menuButtons[3] = new JButton("3. Llistar avions");
        for(int i = 0; i <= menuButtons.length; i++){
            frame.add(menuButtons[i]);
        }
        frame.setSize(AMPLADA, ALCADA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
