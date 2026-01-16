/*
 * Inf1002 Hiver 2024, groupe 2, équipe 13. Devoir 5
 *
 * Point d'entrée du programme, menus et gestion de l'inventaire
 * des documents.
 * @author Abiola Fashola Nurudeen
 * 
 */
package inf1002.gr2.equipe13.biblio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principale {
	private static SupportMenu sm;
	private static ArrayList<Document> documents;
	private static Scanner scan;

	public static void main(String[] args) {
		sm = new SupportMenu();
		documents = new ArrayList<>();
		scan = new Scanner(System.in);

		menuPrincipal(scan);
		scan.close();
		System.out.println("Au revoir!");
	}

	private static void menuPrincipal(Scanner scan) {
		int choix;
		boolean quitter = false;

		do {
			System.out.println(sm.genererEnteteMenu("Bibliothèque - Menu principal"));
			System.out.println("1 - Ajouter un document");
			System.out.println("2 - Supprimer un document");
			System.out.println("3 - Lister tous les documents");
			System.out.println("4 - Afficher les caractéristiques d'un document");
			System.out.println("5 - Afficher la liste des prix d'un roman");
			System.out.println("6 - Gérer le nombre de copies d'un document");
			System.out.println("7 - Modifier le titre d'un document");
			System.out.println("8 - Modifier l'auteur d'un livre");
			System.out.println("9 - Ajouter les documents prédéfinis");
			System.out.println("10 - Quitter l'application\n");

			choix = sm.saisirEntier(scan, "Saisissez le numéro correspondant à votre choix: ", 0, 10);
			System.out.println();

			switch (choix) {
			case 1:
				ajouterDocument();
				break;
			case 2:
				supprimerDocument();
				break;
			case 3:
				listerDocuments();
				break;
			case 4:
				afficherCaracteristiqueDoc();
				break;
			case 5:
				listerPrixRoman();
				break;
			case 6:
				modifierNbCopies();
				break;
			case 7:
				modifierTitreDoc();
				break;
			case 8:
				modifierAuteurLivre();
				break;
			case 9:
				ajouterDocumentsPredefinis();
				break;
			case 10:
				quitter = true;
				break;
			default:
				System.out.println("Choix invalide, vous n'auriez jamais dû vous rendre ici...");

			}
		} while (!quitter);
	}

	private static void ajouterDocument() {
		final int DICTIONNAIRE = 1;
		final int MANUEL = 2;
		final int REVUE = 3;
		final int ROMAN = 4;

		Document nouvDoc = null;
		boolean ajouter = true;
		System.out.println(sm.genererEnteteMenu("Ajout d'un document"));
		// Demander le type de document
		System.out.printf("%d) Dictionnaire\n", DICTIONNAIRE);
		System.out.printf("%d) Manuel\n", MANUEL);
		System.out.printf("%d) Revue\n", REVUE);
		System.out.printf("%d) Roman\n\n", ROMAN);

		int choixType = sm.saisirEntier(scan, "Saisissez le type de document à créer: ", 1, 4);

		// Saisir les informations communes à tous les types de document
		String titre = sm.saisirChaineNonVide(scan, "Saisissez le titre du document: ");
		String noEnr = sm.saisirNoEnregistrement(scan, "Saisissez le numéro d'enregistrement du document: ");
		int nbCopies = sm.saisirEntier(scan, "Saisissez le nombre de copie du document à ajouter à l'inventaire: ", 1,
				1000);

		if (choixType == MANUEL || choixType == ROMAN) {
			String auteur = sm.saisirChaineNonVide(scan, "Saisissez le nom de l'auteur du livre: ");
			int nbPages = sm.saisirEntier(scan, "Saisissez le nombre de page du livre: ", 1, 10000);
			if (choixType == MANUEL) {
				Domaine[] domaines = Domaine.values();
				System.out.println();
				int index=Domaine.afficherListeNum();
				int choixDomaine = sm.saisirEntier(scan, "\nSaisissez le nombre correspondant au domaine du manuel: ",1, index);
				Domaine domaine = domaines[choixDomaine - 1];
				nouvDoc = new Manuel(titre, auteur, nbPages, noEnr, nbCopies, domaine);
			} else { // C'est un roman
				String[] listePrix=null;
				System.out.println("Saisissez une liste des prix attribués au roman (séparés par des virgules), laissez la chaîne vide s'il n'y a aucun prix: ");
				String saisiePrix = scan.nextLine();
				if(saisiePrix.length()>0)
						listePrix = saisiePrix.split(",");
				nouvDoc = new Roman(titre, auteur, nbPages, noEnr, nbCopies, listePrix);
			}
		} else if (choixType == DICTIONNAIRE) {
			Langue[] langues = Langue.values();
			int index=Langue.afficherListeNum();
			int choixLangue = sm.saisirEntier(scan, "\nSaisissez le nombre correspondant à la langue du dictionnaire: ",1, index);
			Langue langue = langues[choixLangue - 1];
			
			nouvDoc = new Dictionnaire(titre, noEnr, nbCopies, langue);
		} else if (choixType == REVUE) {
			int moisPub = sm.saisirEntier(scan, "Saisissez le mois de publication de la revue: ", 1, 12);
			int anneePub = sm.saisirEntier(scan, "Saisissez l'année de publication de la revue: ", 1900, 2025);
			nouvDoc = new Revue(titre, noEnr, nbCopies, moisPub, anneePub);
		} else {
			// on ne devrait jamais arriver ici
			System.out.println("Sélection de type de document invalide.");
			ajouter = false;
		}
		if (ajouter)
			documents.add(nouvDoc);
	}

	private static void supprimerDocument() {
		if (!documents.isEmpty()) {
			System.out.println(sm.genererEnteteMenu("Suppression d'un document"));

			int indice = 1;
			for (Document doc : documents)
				System.out.printf("%d) %s\n", indice++, doc.toShortString());
			System.out.println();
			int choix = sm.saisirEntier(scan, "Saisissez le numéro correspondant au document à supprimer: ", 1, indice);
			Document doc = documents.get(choix - 1);
			System.out.printf("\nEntrez \"oui\" pour confirmer la suppression du document \"%s\": ",
					doc.toShortString());
			String confirmation = scan.nextLine();
			if (confirmation.equalsIgnoreCase("oui")) {
				documents.remove(choix - 1);
				System.out.println("\nLe document à été supprimé de l'inventaire.\n");
			} else
				System.out.println("\nVous avez annulé la suppression du document.\n");
		} else
			System.out.println("Il n'y a aucun documents dans l'inventaire, impossible de supprimer.\n");
	}

	private static void listerDocuments() {
		if (documents.size() == 0) {
			System.out.println("Il n'y a aucun documents dans l'inventaire.\n");
			return;
		}
		System.out.println(sm.genererEnteteMenu("Liste de tous les documents"));
//		System.out.println();
		for (Document doc : documents) {
			System.out.println(doc.toShortString());
		}
		System.out.println();
	}

	private static void afficherCaracteristiqueDoc() {
		if (!documents.isEmpty()) {
			System.out.println(sm.genererEnteteMenu("Affichage des caractéristique d'un document"));
			String noEnr = sm.saisirNoEnregistrement(scan,
					"Saisissez le numéro d'enregistrement du document souhaité: ");
			Document doc = null;
			for (Document curDoc : documents)
				if (curDoc.getNoEnregistrement().equalsIgnoreCase(noEnr))
					doc = curDoc;

			System.out.println("\n" + (doc != null ? doc.toString()
					: String.format(
							"L'inventaire ne contient aucun document correspondant au numéro d'enregistrement \"%s\"",
							noEnr)));
			System.out.println();
		} else
			System.out.println("Il n'y a aucun document dans l'inventaire.\n");
	}

	private static void listerPrixRoman() {
		List<Document> romans = documents.stream().filter(doc -> doc instanceof Roman).collect(Collectors.toList());
		if (!romans.isEmpty()) {
			int num = 1;
			System.out.println(sm.genererEnteteMenu("Affichage des prix gagnés pour un roman"));
			for (Document roman : romans) {
				System.out.printf("%d) %s\n", num++, ((Roman) roman).toShortString(true));
			}
			System.out.println();
			int choix = sm.saisirEntier(scan, "Saisissez le numéro correspondant au roman souhaité: ", 1,
					romans.size());
			Roman roman = (Roman) romans.get(choix - 1);
			String[] prix = roman.getListePrix();
			if (prix != null && prix.length > 0) {
				System.out.printf("\nListe des prix gagnés pour le roman \"%s\":\n", roman.getTitre());
				for (String unPrix : prix)
					System.out.println("\t- " + unPrix);
				System.out.println();
			} else
				System.out.printf("\nLe roman \"%s\" n'a reçu aucun prix.\n\n", roman.getTitre());
		} else
			System.out.println("\nIl n'y a aucun roman dans l'inventaire.\n");
	}

	private static void modifierNbCopies() {
		if (!documents.isEmpty()) {
			System.out.println(sm.genererEnteteMenu("Affichage des caractéristique d'un document"));
			String noEnr = sm.saisirNoEnregistrement(scan,
					"Saisissez le numéro d'enregistrement du document souhaité: ");
			Document doc = null;
			for (Document curDoc : documents)
				if (curDoc.getNoEnregistrement().equalsIgnoreCase(noEnr))
					doc = curDoc;
			if (doc != null) {
				int nbCopie = doc.getNbCopie();
				System.out.printf("Il y a %d copie%s de ce document dans l'inventaire.\n", nbCopie,
						nbCopie > 1 ? "s" : "");
				int nbCopieAjout = sm.saisirEntier(scan,
						"Saisissez le nombre de copie du document à ajouter (valeur positive) ou à retirer (valeur négative) de l'inventaire: ",
						0 - doc.getNbCopie(), doc.getNbCopie());
				doc.ajouterCopie(nbCopieAjout);
				nbCopie = doc.getNbCopie();
				System.out.printf("Il y a maintenant %d copie%s de ce document dans l'inventaire.\n", nbCopie,
						nbCopie > 1 ? "s" : "");
			} else
				System.out.printf(
						"L'inventaire ne contient aucun document correspondant au numéro d'enregistrement \"%s\"",
						noEnr);
		} else
			System.out.println("\nIl n'y a aucun document dans l'inventaire.\n");
	}

	private static void modifierTitreDoc() {
		if (!documents.isEmpty()) {
			System.out.println(sm.genererEnteteMenu("Modifier le titre d'un document"));
			int indice = 1;
			for (Document doc : documents)
				System.out.printf("%d) %s\n", indice++, doc.toShortString());
			int choix = sm.saisirEntier(scan, "Saisissez le numéro correspondant au document à modifier: ", 1, indice);
			Document doc = documents.get(choix - 1);
			System.out.printf(
					"\nEntrez le nouveau titre pour le document \"%s\" (laissez la chaîne vide pour annuler): ",
					doc.getTitre());
			String nouvTitre = scan.nextLine().trim();
			if (nouvTitre.length() > 0) {
				System.out.printf("\nEntrez \"oui\" pour confirmer la modification du titre pour \"%s\": ", nouvTitre);
				String confirmation = scan.nextLine();
				if (confirmation.equalsIgnoreCase("oui")) {
					doc.setTitre(nouvTitre);
					System.out.println("\nLe titre du document à été modifié avec succès.\n");
				} else
					System.out.println("\nVous avez annulé la modification du titre du document.\n");
			}
		} else
			System.out.println("\nIl n'y a aucun document dans l'inventaire.\n");
	}

	private static void modifierAuteurLivre() {
		List<Document> livres = documents.stream().filter(doc -> doc instanceof Livre).collect(Collectors.toList());
		if (!livres.isEmpty()) {
			int num = 1;
			System.out.println(sm.genererEnteteMenu("Modifier l'auteur d'un livre"));
			for (Document livre : livres) {
				System.out.printf("%d) %s\n", num++, ((Livre) livre).toShortString(true));
			}
			System.out.println();
			int choix = sm.saisirEntier(scan,
					"Saisissez le numéro correspondant au livre que vous souhaitez modifier: ", 1, livres.size());
			Livre livre = (Livre) livres.get(choix - 1);
			System.out.printf("\nEntrez le nouvel auteur pour le livre \"%s\" (laissez la chaîne vide pour annuler): ",
					livre.getTitre());
			String nouvAuteur = scan.nextLine().trim();
			if (nouvAuteur.length() > 0) {
				System.out.printf("\nEntrez \"oui\" pour confirmer la modification de l'auteur du livre \"%s\": ",
						nouvAuteur);
				String confirmation = scan.nextLine();
				if (confirmation.equalsIgnoreCase("oui")) {
					livre.setAuteur(nouvAuteur);
					System.out.println("\nL'auteur du livre à été modifié avec succès.\n");
				} else
					System.out.println("\nVous avez annulé la modification de l'auteur du livre.\n");
			}
		} else {
			System.out.println("\nIl n'y a aucun livre dans l'inventaire.\n");
		}
	}

	private static void ajouterDocumentsPredefinis() {
		try {
			// Ajout de trois romans
			documents.add(new Roman("Bob le chauve", "Robert le chevelu", 257, "97c12B456", 1));
			documents.add(new Roman("La forêt sombre", "Liu Cixin", 380, "fh39djb26", 1,
					new String[] { "Prix Goncourt", "Prix FNAC" }));
			documents.add(new Roman("Ainsi va la vie", "Arobas Dégobas", 185, "93744k2hf", 1));

			// Ajout de trois manuels
			documents.add(new Manuel("La nutrition expliquée aux informaticiens", "Sylvie Lafontaine", 180, "123456789",
					5, Domaine.NUTRITION));
			documents.add(new Manuel("La chiropratique pour les nuls", "Jean Poliquin PHD", 180, "1234FO789", 5,
					Domaine.NUTRITION));
			documents.add(new Manuel("Devenir médecin par soi-même", "Les sceptiques associés", 180, "822I56789", 5,
					Domaine.NUTRITION));

			// Ajout de trois revues
			documents.add(new Revue("Philosophie Magazine", "abcde123h", 6, 1, 2024));
			documents.add(new Revue("Le monde de l'automobile", "bndke840z", 5, 2, 2024));
			documents.add(new Revue("Elle Québec", "nifhw381e", 4, 4, 2024));

			// Ajout de trois dictionnaire
			documents.add(new Dictionnaire("Le Larousse 2024", "ab0de123h", 2, Langue.FRANCAIS));
			documents.add(new Dictionnaire("Das Wörterbuch", "ld802af33", 2, Langue.ALLEMAND));
			documents.add(new Dictionnaire("The webster 2022", "7b0de1q3h", 2, Langue.ANGLAIS));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage() + "\n");
		}
	}

}
