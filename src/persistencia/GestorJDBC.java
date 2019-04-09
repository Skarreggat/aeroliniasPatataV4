package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import model.Avio;
import model.Companyia;
import principal.Component;
import principal.GestioVolsExcepcio;

/**
 *
 * @author root
 */
public class GestorJDBC implements ProveedorPersistencia {
	private Companyia companyia;

	private Connection conn; // Connexió a la base de dades

	public Companyia getCompanyia() {
		return companyia;
	}

	public void setCompanyia(Companyia companyia) {
		this.companyia = companyia;
	}

	/*
	 * 
	 * Paràmetres: cap
	 *
	 * Acció: - Heu d'establir la connexio JDBC amb la base de dades GestorVols -
	 * Heu de fer el catch de les possibles excepcions SQL mostrant el missatge de
	 * l'excepció capturada mitjançant getMessage(). - Si es produeix una excepció a
	 * l'establir la connexió, assignareu el valor null a l'atribut conn.
	 *
	 * Retorn: cap
	 *
	 */
	public void estableixConnexio() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/GestorVols";
		String usuari = "root";
		String contrasenya = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, usuari, contrasenya);
			System.out.println("Ens hem connectat");
		} catch (ClassNotFoundException e1) {
			System.out.println("ERROR: no s'ha trobat el controlador de la BD: " + e1.getMessage());
		} catch (SQLException e2) {
			System.out.println("ERROR: SQL ha fallat: " + e2.getMessage());
		}
	}

	/*
	 * Heu de tancar la connexió i assignar-li el valor null a l'atribut conn, es
	 * produeixi o no una excepció.
	 *
	 */
	public void tancaConnexio() throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

	/*
	 * 
	 * Paràmetres: el nom del fitxer i la companyia a desar
	 *
	 * Acció: Heu de desar la companyia passada com a paràmetre en la base de dades:
	 * - S'ha de desar en la taula companyes (nomFitxer és el codi de la companyia)
	 * - Cada avió de la companyia, s'ha de desar com registre de la taula avions -
	 * Heu de tenir en compte que si la companyia ja existeix a la base de dades,
	 * aleshores heu de fer el següent: - Actualitzar la companyia ja existent -
	 * Eliminar totes els avions d'aquesta companyia de la taula avions i després
	 * insertar els avions de la companyia. - Si al fer qualsevol operació es
	 * produeix una excepció, llavors heu de llançar l'excepció GestioVolsExcepcio
	 * amb codi "GestorJDBC.desar"
	 *
	 * Retorn: cap
	 *
	 */
	@Override
	public void desarDades(String nomFitxer, Companyia companyia) throws GestioVolsExcepcio {
		try {
			// Delete avio
			Statement st = conn.createStatement();
			st.execute("delete from avions where codiCompanyia =" + nomFitxer);
			// Delete companyia
			st = conn.createStatement();
			st.execute("delete from companyies where codi =" + nomFitxer);
			// Insert companyia
			st = conn.createStatement();
			st.executeUpdate(
					"Insert into companyies(codi, nom) values ('" + nomFitxer + "', '" + companyia.getNom() + "')");
			// Insert avio
			for (Component a : companyia.getComponents()) {
				if (a instanceof Avio) {
					Avio avio = (Avio) a;
					st = conn.createStatement();
					st.executeUpdate("Insert into avions(codi, fabricant, model, capacitat, codiCompanyia) values ('"
							+ avio.getCodi() + "', '" + avio.getFabricant() + "', '" + avio.getModel() + "', '"
							+ String.valueOf(avio.getCapacitat()) + "', '" + nomFitxer + "')");
				}
			}

		} catch (SQLException e) {
			throw new GestioVolsExcepcio("GestorJDBC.desar");
		}
	}

	/*
	 * 
	 * Paràmetres: el nom del fitxer de la companyia
	 *
	 * Acció: Heu de carregar la companyia des de la base de dades (nomFitxer és el
	 * codi de la companyia). Per fer això, heu de: - Cercar el registre companyia
	 * de la taula companyies amb codi = nomFitxer. - Heu d'afegir els avions al
	 * vector de components de la companyia a partir de la taula avions. - Si al fer
	 * qualsevol operació es produeix una excepció, llavors heu de llançar
	 * l'excepció GestioVolsExcepcio amb codi "GestorJDBC.carrega" - Si el nomFitxer
	 * donat no existeix a la taula companyies (és a dir, el codi = nomFitxer no
	 * existeix), aleshores heu de llançar l'excepció GestioVolsExcepcio amb codi
	 * "GestorJDBC.noexist"
	 *
	 * Retorn: cap
	 *
	 */
	@Override
	public Companyia carregarDades(String nomFitxer) throws ParseException, GestioVolsExcepcio {
		try {
			Statement st = conn.createStatement();
			ResultSet resultat = st.executeQuery("select codi, nom from companyies where codi = " + nomFitxer);
			Companyia companyia = new Companyia(resultat.getInt(0), resultat.getString(1));
			resultat.close();

			st = conn.createStatement();
			resultat = st.executeQuery(
					"select codi, fabricant, model, capacitat, codiCompanyia from avions where codiCompanyia = "
							+ nomFitxer);
			Avio avio = null;
			while (resultat.next()) {
				avio = new Avio(resultat.getString(0), resultat.getString(1), resultat.getString(2),
						resultat.getInt(3));
				companyia.afegirAvio(avio);
			}
			resultat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}