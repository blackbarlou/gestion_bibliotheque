/*
 * Inf1002 Hiver 2024, groupe 2, équipe 13. Devoir 5
 * Classe représentant un livre de type Dictionnaire.
 *
 * @author Pierre-Julien Houde
 * @author Hassan Hader
 * @author Abiola Fashola Nurudeen
 * 
 */
package inf1002.gr2.equipe13.biblio;

public class Dictionnaire extends Document {
	private Langue langue;

	public Dictionnaire(String titre, String noEnregistrement, int nbCopie, Langue langue) {
		super(titre, noEnregistrement, nbCopie);
		this.langue = langue;
	}

	@Override
	public String getDescription() {
		return "Ce document est de type dictionnaire, un dictionnaire est un document contenant des définitions de mots.";
	}

	@Override
	public String toString() {
		return "Dictionnaire [" + super.toString() + ", langue=" + langue + "]";
	}

}
