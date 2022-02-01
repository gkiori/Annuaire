package fr.isika.cda15.projet1.annuaire;

public abstract class Compte { // Public ? private ? 

	/**
	 * Attribut de la class Compte
	 */
	protected String nom;
	protected String prenom;
	private String idCompte;
	private String mdpCompte;
	
	
	public Compte () {
		
	}

	/**
	 * Constructeur surchargé
	 * Défnition de l'objet Compte à partir de tous ses attributs
	 * @param nom
	 * @param prenom
	 * @param idCompte
	 * @param mdpCompte
	 */
	public Compte (String nom, String prenom, String idCompte, String mdpCompte) {
		this.nom = nom;
		this.prenom = prenom;
		this.idCompte = idCompte;
		this.mdpCompte = mdpCompte;

	}
	
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

	/**
	 * Retourne l'attribut IdCompte
	 * @return
	 */
	public String getIdCompte() {
		return idCompte;
	}

	/**
	 * Modifie l'attribut IdCompte
	 * @param idCompte
	 */
	public void setIdCompte(String idCompte) {
		this.idCompte = idCompte;
	}

	/**
	 * Retourne l'attribut MdpCompte
	 * @return
	 */
	public String getMdpCompte() {
		return mdpCompte;
	}

	/**
	 * Modifie l'attribut MdpCompte
	 * @param mdpCompte
	 */
	public void setMdpCompte(String mdpCompte) {
		this.mdpCompte = mdpCompte;
	}
}
