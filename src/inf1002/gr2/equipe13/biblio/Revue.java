/*
 * Inf1002 Hiver 2024, groupe 2, équipe 13. Devoir 5
 *
 * Classe représentant un document de type Revue.
 * @author Abiola Fashola Nurudeen
 * 
 */
package inf1002.gr2.equipe13.biblio;

public class Revue extends Document{
	private int moisPublication, anneePublication;
	
	public Revue(String titre, String noEnregistrement, int nbCopie, int moisPublication, int anneePublication) {
		super(titre, noEnregistrement,nbCopie);
	}

	public int getMoisPublication() {
		return moisPublication;
	}

	public void setMoisPublication(int moisPublication) {
		this.moisPublication = moisPublication;
	}

	public int getAnneePublication() {
		return anneePublication;
	}

	public void setAnneePublication(int anneePublication) {
		this.anneePublication = anneePublication;
	}
	
	@Override
	public String getDescription() {
		return "Ce document est de type revue, une revue est une publication périodique.";
	}

	@Override
	public String toString() {
		return "Revue ["+super.toString()+" moisPublication=" + moisPublication + ", anneePublication=" + anneePublication + "]";
	}
	
	
}
