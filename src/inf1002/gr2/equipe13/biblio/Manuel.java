/*
 * Inf1002 Hiver 2024, groupe 2, équipe 13. Devoir 5
 *
 * Classe représentant un livre de type Manuel.
 * @author Abiola Fashola Nurudeen
 * 
 */
package inf1002.gr2.equipe13.biblio;

public class Manuel extends Livre {
	private Domaine domaine;

	public Manuel(String titre, String auteur, int nbPage, String noEnregistrement, int nbCopie, Domaine domaine) {
		super(titre, auteur, nbPage, noEnregistrement, nbCopie);
		this.domaine = domaine;
	}

	@Override
	public String getDescription() {
		return "Ce document est de type manuel, un manuel est un ouvrage pédagogique sur un sujet donné.";
	}

	public Domaine getDomaine() {
		return domaine;
	}

	public void setDomaine(Domaine domaine) {
		this.domaine = domaine;
	}

	@Override
	public String toString() {
		return super.toString() + ", Domaine:" + domaine;
	}
}
