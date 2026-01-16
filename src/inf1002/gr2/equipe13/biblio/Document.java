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

import java.util.ArrayList;
import java.util.regex.Pattern;

public abstract class Document {
	private static ArrayList<String> listeEnr = new ArrayList<>();
	private static final Pattern ALPHANUM_PATTERN = Pattern.compile("^[A-Za-z0-9]{9}$");
	protected String noEnregistrement;
	protected String titre;
	protected int nbCopie;

	/**
	 * Constructeur Document, vérifie si le no d'enregistrement est valide et unique
	 * avant d'instancier l'objet
	 * 
	 * @throws IllegalArgumentException Si noEnregistrement n'est pas composé de 9
	 *                                  caractères alpha-numériques
	 * @throws IllegalArgumentException Si noEnregistrement à déjà été utilisé
	 * @param titre            Le titre du document
	 * @param noEnregistrement Chaîne de 9 caractères alpha-numériques
	 * @param nbCopie          Nombre de copies du documents
	 */
	public Document(String titre, String noEnregistrement, int nbCopie) {
		if (!ALPHANUM_PATTERN.matcher(noEnregistrement).matches())
			throw new IllegalArgumentException(
					"Le numéro d'enregistrement doit être composé de 9 caractères alpha-numériques");
		if (listeEnr.contains(noEnregistrement))
			throw new IllegalArgumentException("Le numéro d'enregistrement existe déjà pour un autre document.");
		this.titre = titre;
		this.noEnregistrement = noEnregistrement;
		this.nbCopie = nbCopie;
		listeEnr.add(noEnregistrement);
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getNbCopie() {
		return nbCopie;
	}

	public void setNbCopie(int nbCopie) {
		this.nbCopie = nbCopie;
	}

	public void ajouterCopie(int nbCopie) {
		this.nbCopie += nbCopie;
	}

	public void retirerCopie(int nbCopie) {
		if (nbCopie > this.nbCopie)
			this.nbCopie = 0;
		else {
			this.nbCopie -= nbCopie;
		}
	}

	public String getNoEnregistrement() {
		return noEnregistrement;
	}

	/*
	 * Retourner une description du type de document, en utilisant le nom
	 * de la classe (dynamique avec la notion d'héritage), affichera au 
	 * moins le type si la méthode n'est pas redéfinie par une classe
	 * enfant
	 */
	public String getDescription() {
		String typeDoc = this.getClass().getSimpleName().toLowerCase();
		return String.format("Ce document est de type \"%s\"", typeDoc);
	}

	public String toString() {
		return String.format("%s\nTitre: %s, No d'enregistrement: %s, Nombre de copies; %d", getDescription(), titre, noEnregistrement,
				nbCopie);
	}

	public String toShortString() {
		return String.format("Titre: %s, No d'enregistrement: %s", titre, noEnregistrement);
	}
	
	public String toShortString(boolean specifique) {
		return toShortString();
	}
	
	public static boolean validerNoEnr(String noEnr) {
		return ALPHANUM_PATTERN.matcher(noEnr).matches();
	}
}
