package fr.isika.cda15.projet1.annuaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Recherche {
	
	private static String attributeType01;
	private static String attributeType02;


	
// ******************* Métthode pour le choix du type de recherhche  *******************
	
	/**
	 * 
	 * @param attributeType
	 * @return
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
		else {
			return 0;
		}
	}

//	************************ Méthodes de recherche par département  ************************
	
	/**
	 * 
	 * @param cle
	 * @return List<Stagiaire> : listRechDep
	 */
	public static List<Stagiaire> chercherDepartement(String cle, MiniArbre arbre) {
		List<Stagiaire> listRechDep = new ArrayList<>();
		return chercherDepartement(cle, arbre.getRacine(),listRechDep);
	}
	
	/**
	 * 
	 * @param cle
	 * @param r
	 * @param listResult
	 * @return
	 */
	private static List<Stagiaire> chercherDepartement(String cle, Noeud r, List<Stagiaire> listResult) {
		if (r == null){
			return listResult;
		}
		chercherDepartement(cle, r.getGauche(), listResult);
		if (cle.compareTo(r.getStagiaire().getDepartement()) == 0){
			listResult.add(r.getStagiaire());
//			System.out.println(r.getStagiaire() + " ");
		}
		chercherDepartement(cle, r.getDroit(), listResult);
		return listResult;
	}
// ************************ Méthodes de recherche par nom ************************
	
	/**
	 * 
	 * @param cle
	 * @param arbre
	 * @return
	 */
	public static List<Stagiaire> chercherNom(String cle, MiniArbre arbre) {
		List<Stagiaire> listRechNom = new ArrayList<>();
		return chercherNom(cle, arbre.getRacine(), listRechNom);
	}
	
	/**
	 * 
	 * @param cle
	 * @param r
	 * @param listResult
	 * @return
	 */
	private static List<Stagiaire> chercherNom(String cle, Noeud r, List<Stagiaire> listResult) {
		if (r == null){
			return listResult;
		}
		chercherNom(cle, r.getGauche(), listResult);
		if (r.getStagiaire().getNom().toLowerCase().contains(cle.toLowerCase())){
			listResult.add(r.getStagiaire());
//			System.out.println(r.getStagiaire() + " ");
		}
//		System.out.println(r.getStagiaire().getDepartement() + " ");
		chercherNom(cle, r.getDroit(), listResult);
		return listResult;
	}
	
	// ************************ Méthodes de recherche par prenom ************************
	
		/**
		 * 
		 * @param cle
		 * @param arbre
		 * @return
		 */
		public static List<Stagiaire> chercherPrenom(String cle, MiniArbre arbre) {
			List<Stagiaire> listRechPrenom = new ArrayList<>();
			return chercherPrenom(cle, arbre.getRacine(), listRechPrenom);
		}
		
		/**
		 * 
		 * @param cle
		 * @param r
		 * @param listResult
		 * @return
		 */
		private static List<Stagiaire> chercherPrenom(String cle, Noeud r, List<Stagiaire> listResult) {
			if (r == null){
				return listResult;
			}
			chercherPrenom(cle, r.getGauche(), listResult);
			if (r.getStagiaire().getPrenom().toLowerCase().contains(cle.toLowerCase())){
				listResult.add(r.getStagiaire());
			}
			chercherPrenom(cle, r.getDroit(), listResult);
			return listResult;
		}
		
//************************ Méthodes de recherche par année d'entrée ************************
	/**
	 * 
	 * @param cle
	 * @param arbre
	 * @return
	 */
	public static List<Stagiaire> chercherAnneeEntree(String cle, MiniArbre arbre) {
		List<Stagiaire> listRechAnnee = new ArrayList<>();
		return chercherAnneeEntree(cle, arbre.getRacine(), listRechAnnee);
	}
	
	/**
	 * 
	 * @param cle
	 * @param r
	 * @param listResult
	 * @return
	 */
	private static List<Stagiaire> chercherAnneeEntree(String cle, Noeud r, List<Stagiaire> listResult) {
		if (r == null){
			return listResult;
		}
		chercherAnneeEntree(cle, r.getGauche(), listResult);
		if (cle.compareTo(r.getStagiaire().getAnneeEntree()) == 0){
			listResult.add(r.getStagiaire());
		}
		chercherAnneeEntree(cle, r.getDroit(), listResult);
		return listResult;
	}
//************************ Méthodes de recherche par promotion spécifique  ************************
	
	/**
	 * 
	 * @param cle
	 * @param arbre
	 * @return
	 */
	public static List<Stagiaire> chercherPromotion(String cle, MiniArbre arbre) {
		List<Stagiaire> listRechPromo = new ArrayList<>();
		return chercherPromotion(cle, arbre.getRacine(), listRechPromo);
	}
	
	/**
	 * 
	 * @param cle
	 * @param r
	 * @param listResult
	 * @return
	 */
	private static List<Stagiaire> chercherPromotion(String cle, Noeud r, List<Stagiaire> listResult) {
		if (r == null){
			return listResult;
		}
		chercherPromotion(cle, r.getGauche(), listResult);
		if (cle.compareTo(r.getStagiaire().getPromo()) == 0 ){
			listResult.add(r.getStagiaire());
		}
		chercherPromotion(cle, r.getDroit(), listResult);
		return listResult;
	}
	
//************************ Méthodes de recherche par promotion générale ************************
	
	/**
	 * 
	 * @param cle
	 * @param arbre
	 * @return
	 */
	public static List<Stagiaire> chercherPromotionFull(String cle, MiniArbre arbre) {
		List<Stagiaire> listRechPromo = new ArrayList<>();
		return chercherPromotionFull(cle, arbre.getRacine(), listRechPromo);
	}
	
	/**
	 * 
	 * @param cle
	 * @param r
	 * @param listResult
	 * @return
	 */
	private static List<Stagiaire> chercherPromotionFull(String cle, Noeud r, List<Stagiaire> listResult) {
		if (r == null){
			return listResult;
		}
		chercherPromotionFull(cle, r.getGauche(), listResult);
		if (cle.compareTo(r.getStagiaire().getPromo()) == -3 ){ 
			listResult.add(r.getStagiaire());
		}
		chercherPromotionFull(cle, r.getDroit(), listResult);
		return listResult;
	}
//************************ Méthodes de recherche parmot clé ************************
	
	/**
	 * 
	 * @param cle
	 * @param type
	 * @param arbre
	 * @return
	 */
	public static List<Stagiaire> chercherCle(String cle, String type, MiniArbre arbre) {
		attributeType01 = type;
		int key = keyValue(attributeType01);
		List<Stagiaire> listRech = new ArrayList<>();
		return chercherCle(cle, key, arbre, listRech);
	}
	
	/**
	 * 
	 * @param cle
	 * @param key
	 * @param arbre
	 * @param listResult
	 * @return
	 */
	private static List<Stagiaire> chercherCle(String cle, int key, MiniArbre arbre, List<Stagiaire> listResult) {
		switch (key) {
		case 1:
			listResult = chercherNom(cle, arbre);
			break;
		case 2:
			listResult = chercherPrenom(cle, arbre);
			break;
		case 3:
			listResult = chercherAnneeEntree(cle, arbre);
			break;
		case 4:
			listResult = chercherPromotion(cle, arbre);
			break;
		case 5:
			listResult = chercherDepartement(cle, arbre);
			break;
		default:
			listResult = new ArrayList<Stagiaire>();
			break;
		}
		return listResult;
	}
	
//************************ Méthodes de recherche avec multicritère (2 mots clé)  ************************
	
	public static List<Stagiaire> chercherMultiCle(Map<String, String> listeRecherche) {
		List<Stagiaire> resultatRecherche = new ArrayList<Stagiaire>();
		Recherche.MiniArbre miniArbre = new Recherche().new MiniArbre(ArbreStagiaire.parcoursStagiaire());
		for (Map.Entry<String, String> recherche : listeRecherche.entrySet()) {
			List<Stagiaire> resultatRechercheIntermediaire = new ArrayList<Stagiaire>();
			String cleNonSepare = recherche.getKey().replace("[", "").replace("]", "");
			String[] cles = cleNonSepare.split(", ");
			String type = recherche.getValue();
			int key = keyValue(type);
			switch (key) {
			case 1:
				for(String cle : cles) resultatRechercheIntermediaire.addAll(chercherNom(cle, miniArbre));
				break;
			case 2:
				for(String cle : cles) resultatRechercheIntermediaire.addAll(chercherPrenom(cle, miniArbre));
				break;
			case 3:
				for(String cle : cles) resultatRechercheIntermediaire.addAll(chercherAnneeEntree(cle, miniArbre));
				break;
			case 4:
				for(String cle : cles) resultatRechercheIntermediaire.addAll(chercherPromotion(cle, miniArbre));
				break;
			case 5:
				for(String cle : cles) resultatRechercheIntermediaire.addAll(chercherDepartement(cle, miniArbre));
				break;
			default:
				System.out.println("No Results founded");
				resultatRecherche = new ArrayList<Stagiaire>();
				break;
			}
			resultatRecherche = resultatRechercheIntermediaire;
			miniArbre = new Recherche().new MiniArbre(resultatRecherche);
		}
		return resultatRecherche;
	}
	
	public static TreeSet<String> getListePromo() {
		List<Stagiaire> stagiaires = ArbreStagiaire.parcoursStagiaire();
		TreeSet<String> listePromo = new TreeSet<String>();
		for(Stagiaire stagiaire : stagiaires){
			listePromo.add(stagiaire.getPromo());
		};
		return listePromo;
	}
	
	public static TreeSet<String> getListeDepartement() {
		List<Stagiaire> stagiaires = ArbreStagiaire.parcoursStagiaire();
		TreeSet<String> listeDepartement = new TreeSet<String>();
		for(Stagiaire stagiaire : stagiaires){
			listeDepartement.add(stagiaire.getDepartement());
		};
		return listeDepartement;
	}
	
	public static TreeSet<String> getListeAnneeEntree() {
		List<Stagiaire> stagiaires = ArbreStagiaire.parcoursStagiaire();
		TreeSet<String> listeAnneeEntree = new TreeSet<String>();
		for(Stagiaire stagiaire : stagiaires){
			listeAnneeEntree.add(stagiaire.getAnneeEntree());
		};
		return listeAnneeEntree;
	}
	
	public class MiniArbre {
		private Noeud racine;
		
		public MiniArbre(List<Stagiaire> maList) {
			ajouterNoeudAll(maList);
				
		}
		
		private void ajouterNoeudAll(List<Stagiaire> listStagiaire) {
			for (Stagiaire stagiaire : listStagiaire) {
				ajouterNoeud(stagiaire);
			}
		}
		
		private void ajouterNoeud(Stagiaire x) {
			if (this.racine == null) {
				this.racine = new Noeud(x);
			}
			ajouterNoeud(x, this.racine);
		}
		
		private Noeud ajouterNoeud(Stagiaire x, Noeud courant) {
			if(courant == null) {
				return new Noeud(x);
			}
			if (x.getNom().compareTo(courant.getStagiaire().getNom()) < 0) {
				courant.setGauche(ajouterNoeud(x, courant.getGauche()));
			}
			if (x.getNom().compareTo(courant.getStagiaire().getNom()) > 0) {
				courant.setDroit(ajouterNoeud(x, courant.getDroit()));		
			}	
			return courant;	
		}
		
		public Noeud getRacine() {
			return this.racine;
		}
	}
	public class Noeud {
		private Stagiaire stagiaire;
		private Noeud gauche;
		private Noeud droit;
		
		public Noeud(Stagiaire stagiaire) {
			this.stagiaire = stagiaire;
			this.gauche = null;
			this.droit = null;
		}
		
		/**
		 * Retourne le noeud Parent stagiaire
		 * @return the stagiaire
		 */
		public Stagiaire getStagiaire() {
			return stagiaire;
		}

		/**
		 * Retourne le noeud enfant gauche
		 * @return gauche
		 */
		public Noeud getGauche() {
			return gauche;
		}

		/**
		 * Retourne le noeud enfant droit
		 * @return
		 */
		public Noeud getDroit() {
			return droit;
		}

		/**
		 * Moodifie le noeud parent stagiaire 
		 * @param stagiaire
		 */
		public void setStagiaire(Stagiaire stagiaire) {
			this.stagiaire = stagiaire;
		}

		/**
		 * Modifie le noeud enfant gauche
		 * @param gauche
		 */
		public void setGauche(Noeud gauche) {
			this.gauche = gauche;
		}

		/**
		 * Modifie le noeud enfant droit
		 * @param droit
		 */
		public void setDroit(Noeud droit) {
			this.droit = droit;
		}
	}
}



