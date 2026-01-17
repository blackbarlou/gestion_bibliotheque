/*
 * Inf1002 Hiver 2024, groupe 2, équipe 13. Devoir 5
 *
 * Enum des langues possibles pour les objets Dictionnaire
 * @author Abiola Fashola Nurudeen
 * 
 */
package inf1002.gr2.equipe13.biblio;

public enum Langue {
	FRANCAIS("Français"),
	ANGLAIS("Anglais"),
	ESPAGNOL("Espagnol"),
	LATIN("Latin"),
	ALLEMAND("Allemand");
	
private String nomUsuel;
	
	private Langue(String nomUsuel) {
		this.nomUsuel = nomUsuel;
	}
	
	@Override
	public String toString() {
		return nomUsuel;
	}
	
	public static int afficherListeNum() {
		int index=0;
		for(Langue curLang: Langue.values()) {
			System.out.printf("%d) %s\n", ++index, curLang);
		}
		return index;
	}
}
