/*
 * Inf1002 Hiver 2024, groupe 2, équipe 13. Devoir 5
 *
 * Enum pour lister les domaines possibles des objets Manuel
 *
 * @author Pierre-Julien Houde
 * @author Hassan Hader
 * @author Abiola Fashola Nurudeen
 * 
 */
package inf1002.gr2.equipe13.biblio;

public enum Domaine {
	BIOLOGIE("Biologie"),
	NUTRITION("Nutrition"),
	ERGONOMIE("Ergonomie"),
	MEDECINE("Médecine"),
	CHIROPRATIQUE("Chiropratique");
	
private String nomUsuel;
	
	private Domaine(String nomUsuel) {
		this.nomUsuel = nomUsuel;
	}
	
	@Override
	public String toString() {
		return nomUsuel;
	}
	
	public static int afficherListeNum() {
		int index=0;
		for(Domaine domaine: Domaine.values()) {
			System.out.printf("%d) %s\n", ++index, domaine);
		}
		return index;
	}
}
