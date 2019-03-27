/*
 * Un avió es defineix pel seu codi, fabricant, model, capacitat i un vector amb 
 * les seves classes.
 */
package model;

import java.util.ArrayList;
import principal.Component;
import principal.GestioVolsExcepcio;

/**
 *
 * @author root
 */
public class Avio implements Component {

    private String codi;
    private String fabricant;
    private String model;
    private int capacitat;
    private ArrayList<Classe> classes;

    /*
     CONSTRUCTOR
     */
    public Avio(String codi, String fabricant, String model, int capacitat) {
        this.codi = codi;
        this.fabricant = fabricant;
        this.model = model;
        this.capacitat = capacitat;
        classes = new ArrayList();
    }

    /*
    Mètodes accessors
     */
    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getFabricant() {
        return fabricant;
    }

    public void setFabricant(String fabricant) {
        this.fabricant = fabricant;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacitat() {
        return capacitat;
    }

    public void setCapacitat(int capacitat) {
        this.capacitat = capacitat;
    }

    public ArrayList<Classe> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Classe> classes) {
        this.classes = classes;
    }

    public static Avio nouAvio() throws GestioVolsExcepcio {
        String codi, fabricant, model;
        int capacitat;

        System.out.println("\nCodi de l'avió:");
        codi = DADES.next();

        if (GestioVolsExcepcio.comprovarCodiAvio(codi)) {
            throw new GestioVolsExcepcio("2");
        }

        DADES.nextLine(); //Neteja de buffer
        System.out.println("\nFabricant de l'avió:");
        fabricant = DADES.nextLine();
        System.out.println("\nModel de l'avió:");
        model = DADES.nextLine();
        System.out.println("\nCapacitat de l'avió:");
        capacitat = DADES.nextInt();

        return new Avio(codi, fabricant, model, capacitat);
    }

    public void modificarComponent() throws GestioVolsExcepcio {

        System.out.println("\nEl codi de l'avió és: " + codi);
        codi = String.valueOf(demanarDades("\nQuin és el nou Codi de l'avió?", 2));

        if (GestioVolsExcepcio.comprovarCodiAvio(codi)) {
            throw new GestioVolsExcepcio("2");
        }

        demanarDades("", 4); //Netejar buffer
        System.out.println("\nEl fabricant de l'avió és: " + fabricant);
        fabricant = String.valueOf(demanarDades("\nQuin és el nou fabricant de l'avió?", 4));
        System.out.println("\nEl model de l'avió és: " + model);
        model = String.valueOf(demanarDades("\nQuin és el nou model de l'avió?", 4));
        System.out.println("\nLa capacitat de l'avió és: " + capacitat);
        capacitat = (int) demanarDades("\nQuina és la nova capacitat de l'avió?", 1);

    }

    public void mostrarComponent() {
        System.out.println("\nLes dades de l'avió amb codi " + codi + " són:");
        System.out.println("\nFabricant: " + fabricant);
        System.out.println("\nModel: " + model);
        System.out.println("\nCapacitat: " + capacitat);

    }

    /*
     Paràmetres: cap
     Accions:
     - Afegeix una nova classe al vector de classes de l'avió actual si aquesta encara 
     no s'ha afegit. S'ha de comprovar si s'ha afegit fent servir el mètode pertinent 
     d'aquesta classe, i actualitzar la posició del vector de classes.
     - Abans d'afegir la classe, també heu de comprovar que la seva capacitat sumada
     a la capacitat de les altres classes de l'avió, no superi la capacitat total
     de l'avió.
     - Si l'ingredient ja s'havia afegit o bé la seva capacitat sumada a les capacitats 
     de les altres classes supera la capacitat total de l'avió, no s'afegirà de nou i 
     li mostrarem a l'usuari el missatge "\nLa classe no s'ha pogut afegir".
     Retorn: cap
     */
    public void afegirClasse(Classe classe) {

        int capacitatClasses = 0;

        if (classe == null) {
            classe = Classe.novaClasse();
        }

        if (seleccionarClasse(classe.getNom()) == -1) { //La classe no existeix

            for (int i = 0; i < classes.size(); i++) {
                capacitatClasses += classes.get(i).getCapacitat();
            }

            if (capacitatClasses + classe.getCapacitat() <= capacitat) {
                classes.add(classe);
            }

        } else if (seleccionarClasse(classe.getNom()) != -1 || capacitatClasses + classe.getCapacitat() > capacitat) {
            System.out.println("\nLa classe no s'ha pogut afegir");
        }

    }

    public int seleccionarClasse(String nom) {

        boolean trobat = false;
        int pos = -1;

        for (int i = 0; i < classes.size() && !trobat; i++) {
            if (classes.get(i).getNom().equals(nom)) {
                pos = i;
                trobat = true;
            }
        }

        return pos;
    }

}
