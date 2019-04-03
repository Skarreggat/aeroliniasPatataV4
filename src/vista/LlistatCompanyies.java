package vista;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author root
 */
public class LlistatCompanyies {

	private JFrame frame;
	private final int AMPLADA = 600;
	private final int ALCADA = 200;

	private JTable taulaCompanyies;

	private JButton bSortir;

	/*
	 * CONSTRUCTOR Paràmetres:cap Accions: Heu d'inicialitzar els atributs d'aquesta
	 * classe fent el següent (no afegiu cap listener a cap control):
	 * 
	 * - Heu d'inicialitzar l'objecte JFrame amb títol "Llistat de companyies" i
	 * layout Grid d'una columna - Heu d'inicialitzar l'objecte Jtable amb un nou
	 * objecte de TaulaCompanyia - Heu d'inicialitzar l'objecte JButton amb nom
	 * "Sortir". - Heu d'afegir-ho tot a l'atribut frame - Heu de fer visible el
	 * frame amb l'amplada i alçada que proposen els atributs alcada i amplada - Heu
	 * de fer que la finestra es tanqui quan l'usuari ho fa amb el control "X" de la
	 * finestra
	 * 
	 */
	public LlistatCompanyies() {
		frame = new JFrame("Llistat de companyies");
		frame.setLayout(new GridLayout(0, 1)); // Format de taula d'una columna
		TaulaCompanyia tcomp = new TaulaCompanyia();
		taulaCompanyies = new JTable(tcomp.getCompanyes(), tcomp.getNomColumnes());
		bSortir = new JButton("Sortir");
		JScrollPane scrollPanel = new JScrollPane(taulaCompanyies);
		frame.add(scrollPanel);
		frame.add(bSortir);
		frame.setResizable(false);
		// Li assignem mides a la finestra. Aquestes seran les mides inicials.
		frame.setSize(AMPLADA, ALCADA);
		// Tanquem la finestra i sortim de l'aplicació al seleccionar x
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTable getTaulaAvions() {
		return taulaCompanyies;
	}

	public void setTaulaCompanyies(JTable pTaulaCompanyies) {
		taulaCompanyies = pTaulaCompanyies;
	}

	public JButton getSortir() {
		return bSortir;
	}

	public void setSortir(JButton bSortir) {
		this.bSortir = bSortir;
	}
}
