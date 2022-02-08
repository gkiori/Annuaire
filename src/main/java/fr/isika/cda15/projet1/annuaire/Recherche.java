package fr.isika.cda15.projet1.annuaire;

import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

public class Recherche {
	
// ******************* Méthode pour le choix du type de recherche  *******************

	/**
	 *  Méthode permettant de transformer le type du critère de la recherche (nom, prénom, département, année d'entrée ou promotion) en entier
	 *  
	 *  @param attributeType	String contenant le nom du critère recherché
	 *  @return 				entier correspondant au critère recherché
	 */
	private static int keyValue(String attributeType) {
		if(attributeType.equalsIgnoreCase("nom")) {
			return 1;
		}
		else if(attributeType.equalsIgnoreCase("prenom")) {
			return 2;
		}
		else if(attributeType.equalsIgnoreCase("anneeEntree")) {
			return 3;
		}
		else if(attributeType.equalsIgnoreCase("promo")) {
			return 4;
		}
		else if(attributeType.equalsIgnoreCase("departement")) {
			return 5;
		}
		else if(attributeType.equalsIgnoreCase("recherche")) {
			return 6;
		}
		else {
			return 0;
		}
	}
	
	/**
	 *  Méthode recherchant tout les stagiaires de la liste reçue en paramètre contenant la valeur du critère également reçu
	 *  
	 *  @param cle			String contenu l'élément recherché
	 *  @param type			entier représentant le type de l'élément recherché
	 *  @param setEntrant	HashSet contenant tout les stagiaires dont on vérifiera s'ils valident le critère
	 *  @return 			retourne un HashSet contenant tout les stagiaire correspondant au critère reçu en paramètre
	 */
	public static HashSet<Stagiaire> rechercher(String cle, int type, HashSet<Stagiaire> setEntrant) {
		HashSet<Stagiaire> setResultat = new HashSet<Stagiaire>();
		switch (type) {
			case 1:
				for(Stagiaire stagiaire : setEntrant)
					if(stagiaire.getNom().toLowerCase().contains(cle.toLowerCase())) 
						setResultat.add(stagiaire);
				break;
			case 2:
				for(Stagiaire stagiaire : setEntrant)
					if(stagiaire.getPrenom().toLowerCase().contains(cle.toLowerCase())) 
						setResultat.add(stagiaire);
				break;
			case 3:
				for(Stagiaire stagiaire : setEntrant)
					if(stagiaire.getAnneeEntree().toLowerCase().contains(cle.toLowerCase())) 
						setResultat.add(stagiaire);
				break;
			case 4:
				for(Stagiaire stagiaire : setEntrant)
					if(stagiaire.getPromo().toLowerCase().contains(cle.toLowerCase())) 
						setResultat.add(stagiaire);
				break;
			case 5:
				for(Stagiaire stagiaire : setEntrant)
					if(stagiaire.getDepartement().toLowerCase().contains(cle.toLowerCase())) 
						setResultat.add(stagiaire);
				break;
			case 6:
				for(Stagiaire stagiaire : setEntrant)
					if(stagiaire.getNom().toLowerCase().contains(cle.toLowerCase())
						|| stagiaire.getPrenom().toLowerCase().contains(cle.toLowerCase())
						|| stagiaire.getAnneeEntree().toLowerCase().contains(cle.toLowerCase())
						|| stagiaire.getPromo().toLowerCase().contains(cle.toLowerCase())
						|| stagiaire.getDepartement().toLowerCase().contains(cle.toLowerCase())) 
						setResultat.add(stagiaire);
				break;
			default:
				System.out.println("No Results founded");
				break;
		}
		return setResultat;
	}
	
//************************ Méthodes de recherche multicritère ************************
	
	/**
	 *  Méthode permettant de traiter une map de critère afin de préparer les appels nécessaires à la fonction rechercher
	 *  
	 *  @see 				Recherche#rechercher(String, int, HashSet)
	 *  @listeRecherche		Map contenant tout les critères de recherche selectionnés par l'utilisateur
	 *  @return 			retourne un HashSet contenant tout les stagiaires correspondant à tout les critères reçus en paramètre
	 */
	public static HashSet<Stagiaire> chercherMultiCle(Map<String, String> listeRecherche) {
		HashSet<Stagiaire> resultatRecherche = new HashSet<Stagiaire>();
		resultatRecherche = ArbreStagiaire.parcoursStagiaire();
		for (Map.Entry<String, String> recherche : listeRecherche.entrySet()) {
			HashSet<Stagiaire> resultatRechercheIntermediaire = new HashSet<Stagiaire>();
			String cleNonSepare = recherche.getKey().replace("[", "").replace("]", "");
			String[] cles = cleNonSepare.split(", ");
			String type = recherche.getValue();
			int key = keyValue(type);
			for(String cle : cles) {
				if(cle != "")
					resultatRechercheIntermediaire.addAll(rechercher(cle, key, resultatRecherche));
				else resultatRechercheIntermediaire = resultatRecherche;
			}
			resultatRecherche = resultatRechercheIntermediaire;
		}
		return resultatRecherche;
	}
	
	/**
	 *  @return 	Retourne un TreeSet contenant toutes les différentes promotions existant dans le fichier
	 */
	public static TreeSet<String> getListePromo() {
		HashSet<Stagiaire> stagiaires = ArbreStagiaire.parcoursStagiaire();
		TreeSet<String> listePromo = new TreeSet<String>();
		for(Stagiaire stagiaire : stagiaires){
			listePromo.add(stagiaire.getPromo());
		};
		return listePromo;
	}
	
	/**
	 *  @return 	Retourne un TreeSet contenant tout les différents départements existant dans le fichier
	 */
	public static TreeSet<String> getListeDepartement() {
		HashSet<Stagiaire> stagiaires = ArbreStagiaire.parcoursStagiaire();
		TreeSet<String> listeDepartement = new TreeSet<String>();
		for(Stagiaire stagiaire : stagiaires){
			listeDepartement.add(stagiaire.getDepartement());
		};
		return listeDepartement;
	}
	
	/**
	 *  @return 	Retourne un TreeSet contenant toutes les différentes années d'entrées existant dans le fichier
	 */
	public static TreeSet<String> getListeAnneeEntree() {
		HashSet<Stagiaire> stagiaires = ArbreStagiaire.parcoursStagiaire();
		TreeSet<String> listeAnneeEntree = new TreeSet<String>();
		for(Stagiaire stagiaire : stagiaires){
			listeAnneeEntree.add(stagiaire.getAnneeEntree());
		};
		return listeAnneeEntree;
	}
}



