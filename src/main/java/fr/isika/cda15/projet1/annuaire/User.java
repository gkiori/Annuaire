package fr.isika.cda15.projet1.annuaire;

public class User extends Compte {

	private String idCompte;
	private String mdpCompte;
	private String profil;
	
	public User (String nom, String prenom, String idCompte, String mdpCompte, String profil) {
		super (nom, prenom);
		this.idCompte = idCompte;
		this.mdpCompte = mdpCompte;
		this.profil = profil;
	}

	public String getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(String idCompte) {
		this.idCompte = idCompte;
	}

	public String getMdpCompte() {
		return mdpCompte;
	}

	public void setMdpCompte(String mdpCompte) {
		this.mdpCompte = mdpCompte;
	}

	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
	}
	
	
}
