package fr.isika.cda15.projet1.annuaire;

public class Noeud {
	
	/**
	 * Les attributs de la class Noeud
	 */
	private Stagiaire stagiaire;
	private Noeud gauche;
	private Noeud droit;
	private int index;
	//private int indexGauche;
	//private int indexDroit;
	
	/**
	 * Constructeur Noeud vide
	 * @TODO voir l'utilité du constructeur 
	 */
	public Noeud() {
	}
	
	public Noeud(Stagiaire stagiaire) {
		super();
		this.stagiaire = stagiaire;
		this.gauche = null;
		this.droit = null;
	}
	
	/**
	 * Constructeur Noeud surchargé à un paramètre
	 * @param stagiaire
	 */
	public Noeud(Stagiaire stagiaire, int index) {
		super();
		this.stagiaire = stagiaire;
		this.gauche = null;
		this.droit = null;
		this.index = index;
		//this.indexGauche = -1;
		//this.indexDroit = -1;
	}
	
	/**
	 * Constructeur Noeud surchargé
	 * Définition du noeud avec les noeuds fils directement
	 * @param stagiaire
	 * @param gauche
	 * @param droit
	 */
	public Noeud(Stagiaire stagiaire, Noeud gauche, Noeud droit) {
		super();
		this.stagiaire = stagiaire;
		this.gauche = gauche;
		this.droit = droit;
	}
	
	/**
	 * Constructeur surchargé
	 * Définition du Noeud avec les index des fils
	 * @param stagiaire
	 * @param index
	 * @param indexGauche
	 * @param indexDroit
	 */
//	public Noeud(Stagiaire stagiaire, int index, int indexGauche, int indexDroit) {
//		super();
//		this.stagiaire = stagiaire;
//		this.index = index;
//		this.indexGauche = indexGauche;
//		this.indexDroit = indexDroit;
//	}

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

	/**
	 * Retourne l'index du noeud 
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * 
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Retourne l'index du noeud enfant gauche
	 * @return the indexGauche
	 */
//	public int getIndexGauche() {
//		return indexGauche;
//	}

	/**
	 * Modifie l'indexdu noeud enfant gauche
	 * @param indexGauche
	 */
//	public void setIndexGauche(int indexGauche) {
//		this.indexGauche = indexGauche;
//	}

	/**
	 * Retourne l'index du noeud enfant droit
	 * @return the indexDroit
	 */
//	public int getIndexDroit() {
//		return indexDroit;
//	}

	/**
	 * Modifie l'indexdu noeud enfant droit
	 * @param indexDroit
	 */
//	public void setIndexDroit(int indexDroit) {
//		this.indexDroit = indexDroit;
//	}

	@Override
	public String toString() {
		String retour = "id : " + this.index + " -- "+ stagiaire.toString() + "{";
		if (gauche != null) {
			retour += "gauche : " + gauche.toString() + "/";
		}
		if (droit != null) {
			retour += "droit : " + droit.toString();
		}
		retour += "}";
		return retour;
	}
}
