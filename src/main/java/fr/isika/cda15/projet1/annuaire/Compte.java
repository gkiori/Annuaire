package fr.isika.cda15.projet1.annuaire;

public abstract class Compte { // Public ? private ? 

	/**
	 * Attribut de la class Compte
	 */
	protected String nom;
	protected String prenom;
	public Compte () {};

	/**
	 * Constructeur surchargé
	 * Définition de l'objet Compte à partir des attributs : nom & prenom
	 * @param nom
	 * @param prenom
	 */
	public Compte (String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;

	}

	/**
	 * Retourne l'attribut nom
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Modifie l'attribut nom
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Retourne l'attribut prenom
	 * @return prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Modifie l'attribut prenom
	 * @param prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


}
