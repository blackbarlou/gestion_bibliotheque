/*
 * Inf1002 Hiver 2024, groupe 2, équipe 13. Devoir 5
 *
 * Classe de support aux menus, offre des méthodes pour faciliter
 * l'affichage des menus, les saisies et la validation de ces dernières
 *
 * @author Pierre-Julien Houde
 * @author Hassan Hader
 * @author Abiola Fashola Nurudeen
 * 
 */
package inf1002.gr2.equipe13.biblio;

import java.util.Scanner;

public class SupportMenu {
	/**
	 * Génère une chaîne de caractère avec le libellé, un saut de ligne et une
	 * séparation de la longueur de la chaîne passée en paramètre. Utile pour les
	 * entêtes de menu.
	 * 
	 * @param libelle Texte à afficher en entête
	 * @return Chaîne formatée avec le séparateur '='
	 */
	public String genererEnteteMenu(String libelle) {
		return genererEnteteMenu(libelle, "=");
	}

	/**
	 * Génère une chaîne de caractère avec le libellé, un saut de ligne et une
	 * séparation de la longueur de la chaîne passée en paramètre. Utile pour les
	 * entêtes de menu. Si le paramètre separateur est plus grand que 1, on ne prend
	 * que le premier charactère.
	 * 
	 * @param libelle    Texte à afficher en entête
	 * @param separateur Caractère à utiliser pour générer la séparation
	 * @return Chaîne formatée avec le séparateur passé en paramètre ('=' par
	 *         défaut)
	 */
	public String genererEnteteMenu(String libelle, String separateur) {
		String sep;
		if (separateur.length() == 0)
			sep = "";
		else if (separateur.length() != 1)
			sep = "\n" + separateur.substring(0, 1).repeat(libelle.length());
		else
			sep = "\n" + separateur.repeat(libelle.length());
		return libelle + sep + "\n";
	}

	/**
	 * Affiche une invite de saisie et retourne la valeur saisie après avoir validé
	 * qu'elle se trouve dans l'intervalle comprise entre valeurBasse et
	 * valeurHaute. La saisie utilise nextLine() et parseInt(), le Scanner sera donc
	 * vide après le retour.
	 * 
	 * @param scan        Le Scanner à utiliser pour saisir la valeur
	 * @param invite      Texte à afficher pour l'invite de la saisie
	 * @param valeurBasse Valeur de comparaison basse, la valeur saisie doit être
	 *                    supérieure ou égale
	 * @param valeurHaute Valeur de comparaison haute, la valeur saisie doit être
	 *                    inférieure ou égale
	 * @return La valeur saisie par le Scanner
	 */
	public int saisirEntier(Scanner scan, String invite, int valeurBasse, int valeurHaute) {
		int saisie = valeurBasse-1;
		do {
			try {
				System.out.print(invite);
				saisie = Integer.parseInt(scan.nextLine());
				if (saisie < valeurBasse || saisie > valeurHaute) {
					System.out.printf("La valeur doit être comprise entre %d et %d.\n", valeurBasse, valeurHaute);
					saisie = -1;
				}
			} catch (NumberFormatException e) {
				System.out.printf("Vous devez entrer un nombre entier compris entre %d et %d.\n", valeurBasse,
						valeurHaute);
			}
		} while (saisie < valeurBasse || saisie > valeurHaute);
		return saisie;
	}

	/**
	 * Affiche une invite de saisie et retourne la valeur saisie après avoir validé
	 * qu'elle se trouve dans l'intervalle comprise entre valeurBasse et
	 * valeurHaute. La saisie utilise nextLine() et parseFloat(), le Scanner sera
	 * donc vide après le retour.
	 * 
	 * @param scan        Le Scanner à utiliser pour saisir la valeur
	 * @param invite      Texte à afficher pour l'invite de la saisie
	 * @param valeurBasse Valeur de comparaison basse, la valeur saisie doit être
	 *                    supérieure ou égale
	 * @param valeurHaute Valeur de comparaison haute, la valeur saisie doit être
	 *                    inférieure ou égale
	 * @return La valeur saisie par le Scanner
	 */
	public float saisirFloat(Scanner scan, String invite, float valeurBasse, float valeurHaute) {
		float saisie = 0f;
		do {
			try {
				System.out.print(invite);
				saisie = Float.parseFloat(scan.nextLine());
				if (saisie < valeurBasse || saisie > valeurHaute) {
					System.out.printf("La valeur doit être comprise entre %f et %f.\n", valeurBasse, valeurHaute);
					saisie = 0f;
				}
			} catch (NumberFormatException e) {
				System.out.printf("Vous devez entrer un nombre à décimales compris entre %d et %d.\n", valeurBasse,
						valeurHaute);
			}
		} while (saisie < valeurBasse || saisie > valeurHaute);
		return saisie;
	}
	
	/**
	 * Affiche l'invite de saisie passée en paramètre et retourne le numéro d'enregistrement
	 * saisie s'il est valide. Utilise la vérification de la classe Document pour s'assurer
	 * que la validation suit toujours le format attendu.
	 * 
	 * @param scan        Le Scanner à utiliser pour saisir la valeur
	 * @param invite      Texte à afficher pour l'invite de la saisie
	 * @return Le numéro d'enregistrement d'un document correspondant au format valide
	 */
	public String saisirNoEnregistrement(Scanner scan, String invite) {
		String saisie="";
		boolean noEnrEstValide=false;
		do {
			System.out.print(invite);
			saisie = scan.nextLine();
			noEnrEstValide=Document.validerNoEnr(saisie);
			if(!noEnrEstValide)
				System.out.println("Le numéro d'enregistrement doit être composé de 9 caractères alpha-numériques\n");
		} while (!noEnrEstValide);
		return saisie;
	}

	/**
	 * Affiche l'invite de saisie passée en paramètre et retourne la chaîne saisie par
	 * l'utilisateur. On boucle jusqu'à ce que la chaîne saisie ne soit pas vide.
	 * 
	 * @param scan        Le Scanner à utiliser pour saisir la valeur
	 * @param invite      Texte à afficher pour l'invite de la saisie
	 * @return	La chaîne de caractères non vide saisie par l'utilisateur
	 */
	public String saisirChaineNonVide(Scanner scan, String invite) {
		String saisie="";
		do {
			System.out.print(invite);
			saisie = scan.nextLine().trim();
			if(saisie.length()==0)
				System.out.println("\nLa chaîne saisie ne peut être vide.\n");
		} while(saisie.length()==0);
		return saisie;
	}
}
