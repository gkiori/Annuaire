package fr.isika.cda15.projet1.annuaire;


public class Stagiaire extends Compte implements Comparable<Stagiaire> {
	
	/**
	 * Les attributs de la class Stagiaire
	 */
	private String departement;
	private String promo;
	private String anneeEntree;
	
	/**
	 * Constructeur vide
	 */
	public Stagiaire () {
		
	}
	
	/**
	 * Constructeur surchargé
	 * Définition complète de l'objet Stagiaire
	 * @param nom
	 * @param prenom
	 * @param departement
	 * @param promo
	 * @param anneeEntree
	 */
	public Stagiaire (String nom, String prenom, String departement, String promo, String anneeEntree) {
		super(nom, prenom);
		this.departement = departement;
		this.promo = promo;
		this.anneeEntree = anneeEntree;
	}
	
	/**
	 * Retourne l'attribut departement
	 * @return departement
	 */
	public String getDepartement() {
		return departement;
	}
	
	/**
	 * Modifie l'attribut departement
	 * @param departement
	 */
	public void setDepartement(String departement) {
		this.departement = departement;
	}

	/**
	 * Retourne l'attribut promo
	 * @return promo
	 */
	public String getPromo() {
		return promo;
	}

	/**
	 * Modifie l'attribut promo
	 * @param promo
	 */
	public void setPromo(String promo) {
		this.promo = promo;
	}

	/**
	 * Retourne l'attribut anneeEntree
	 * @return anneeEntree
	 */
	public String getAnneeEntree() {
		return anneeEntree;
	}

	/**
	 * Modifie l'attribut anneeEntree
	 * @param anneeEntree
	 */
	public void setAnneeEntree(String anneeEntree) {
		this.anneeEntree = anneeEntree;
	}

	@Override
	public String toString() {
		return getNom()+ " "  + getPrenom() + " " + departement + " " + promo + " " + anneeEntree + "\n" ;
	}

	@Override
	public int compareTo(Stagiaire otherStagiaire) {
		int nb = this.getNom().compareTo(otherStagiaire.getNom());
		return nb;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stagiaire other = (Stagiaire) obj;
		if (super.nom == null) {
			if(other.getNom() != null) {
				return false;
			}	
		}else if (!nom.equals(other.nom)) {
			return false;
		}
		if (prenom == null) {
			if (other.prenom != null) {
				return false;
			}
		} else if (!prenom.equals(other.prenom)) {
			return false;
		}
		if (departement == null) {
			if (other.departement != null)
				return false;
		} else if (!departement.equals(other.departement))
			return false;
		if (promo == null) {
			if (other.promo != null)
				return false;
		} else if (!promo.equals(other.promo))
			return false;
		if (anneeEntree == null) {
			if (other.anneeEntree != null)
				return false;
		} else if (!anneeEntree.equals(other.anneeEntree))
			return false;
		return true;
	}

}
