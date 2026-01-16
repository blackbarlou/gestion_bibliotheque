/*
 * Inf1002 Hiver 2024, groupe 2, équipe 13. Devoir 5
 *
 * Classe représentant un livre de type Roman.
 *
 * @author Pierre-Julien Houde
 * @author Hassan Hader
 * @author Abiola Fashola Nurudeen
 * 
 */
package inf1002.gr2.equipe13.biblio;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Roman extends Livre {
	private String[] listePrix;

	public Roman(String titre, String auteur, int nbPage, String noEnregistrement, int nbCopie) {
		super(titre, auteur, nbPage, noEnregistrement, nbCopie);
		listePrix = null;
	}

	public Roman(String titre, String auteur, int nbPage, String noEnregistrement, int nbCopie, String[] listePrix) {
		super(titre, auteur, nbPage, noEnregistrement, nbCopie);
		this.listePrix = listePrix;
	}

	@Override
	public String getDescription() {
		return "Ce document est de type roman, un roman est un livre racontant une histoire.";
	}

	public String[] getListePrix() {
		return listePrix;
	}
	
	public String listePrixToString() {
		return listePrix!=null?Arrays.stream(listePrix).collect(Collectors.joining(", ")):"";
		
	}

	public void setListePrix(String[] listePrix) {
		this.listePrix = listePrix;
	}

	@Override
	public String toString() {
		String chainePrix = listePrixToString();
		return super.toString() + (chainePrix.length()>0?String.format(", prix gagné(s): %s", chainePrix):", Aucun prix gagné");
	}

}
