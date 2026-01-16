/*
 * Inf1002 Hiver 2024, groupe 2, équipe 13. Devoir 5
 *
 * Classe abstraite représentant les propriétés communes à tous les
 * documents de la bibliothèque.
 *
 * @author Pierre-Julien Houde
 * @author Hassan Hader
 * @author Abiola Fashola Nurudeen
 * 
 */
package inf1002.gr2.equipe13.biblio;

public abstract class Livre extends Document {
	protected String auteur;
	protected int nbPage;

	public Livre(String Titre, String auteur, int nbPage, String noEnr, int nbCopie) {
		super(Titre, noEnr, nbCopie);
		this.auteur = auteur;
		this.nbPage = nbPage;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public int getNbPage() {
		return nbPage;
	}

	public void setNbPage(int nbPage) {
		this.nbPage = nbPage;
	}

	@Override
	public String toString() {
		return super.toString() + String.format(", Auteur: %s, Pages: %d", auteur, nbPage);
	}

	@Override
	public String toShortString(boolean specifique) {
		if(specifique) {
			return String.format("Titre: %s, Auteur: %s", titre, auteur);
		} else
			return toShortString();
	}
}
