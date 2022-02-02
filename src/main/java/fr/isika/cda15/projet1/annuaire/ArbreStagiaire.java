package fr.isika.cda15.projet1.annuaire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
//import java.util.List;

public class ArbreStagiaire {
	
	/**
	 * Définition des attributs
	 * A voir l'intérêt
	 */
	static final int NOM_LONG = 25;
	static final int PRENOM_LONG = 20;
	static final int DEPARTEMENT_LONG = 3;
	static final int PROMO_LONG = 15;
	static final int ANNEE_ENTREE_LONG = 4;
	static final int STAGIAIRE_LONG = 142;
	static final int FILS_GAUCHE_LONG = 134;
	static final int FILS_DROIT_LONG = 138;
	static final String PATH_FILE_BIN = "src/main/resources/stagiaires.bin";
	static final String PATH_FILE_DON = "src/main/resources/STAGIAIRES.DON";
	private Noeud racine;
	private static RandomAccessFile raf;

	/**
	 * Constructeur vide 
	 * @TODO A voir l'intérêt
	 */
	public ArbreStagiaire() {
	}

//	************************** Méthodes Getter & Setter  ************************** //
	/**
	 * Retourne le noeud racine
	 * @return
	 */
	public Noeud getRacine() {
		return racine;
	}

	/**
	 * Modifie le noeud racine
	 * @param racine
	 */
	public void setRacine(Noeud racine) {
		this.racine = racine;
	}

//************************* Manipulation fichiers binaires ************************** //
	/**
	 * Initialisation de l'arbre stagiaire à partir du fichier .BIN ou .DON
	 */
	public void initArbre() {
		File monFichierBin = new File(PATH_FILE_BIN);
		File monFichierDon = new File(PATH_FILE_DON);
		if(monFichierBin.exists()) {
			try {
				raf = new RandomAccessFile(PATH_FILE_BIN, "rw");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			initLectureBin();
		}
		else if(monFichierDon.exists()) {
			try {
				raf = new RandomAccessFile(PATH_FILE_BIN, "rw");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			initLectureDon(monFichierDon);
			initLectureBin();
		}
	}
	
	/**
	 * Méthode permettant de lire le fichier d'extention *.DON
	 * @param monFichier
	 */
	private void initLectureDon(File monFichier) {
		String nom = "", prenom = "", departement = "", nomPromo = "", anneeEntree = "";
		try {
			FileReader fr = new FileReader(monFichier);
			BufferedReader br = new BufferedReader(fr);
			int i = 0;
			while (br.ready()) {
				nom = br.readLine();
				prenom = br.readLine();
				departement = br.readLine();
				nomPromo = br.readLine();
				anneeEntree = br.readLine();
				br.readLine();
				this.ajouterNoeud(new Stagiaire(nom, prenom, departement, nomPromo, anneeEntree), i, true);
				i++;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode permettant de lire le fichier d'extenrion *.BIN
	 */
	private void initLectureBin() {
		try {
			int endOfFile = (int)raf.length();
			int i = 0;
			while (raf.getFilePointer() < endOfFile) {
				this.ajouterNoeud(lectureStagiaire(i), i, false);
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode permettant d'écrire les information d'un objet de type Noeud 
	 * dans un fichier
	 * @param noeudCourant
	 * @param indexPere
	 */
	private static void ecritureNoeudFichier(Noeud noeudCourant, int indexPere) {
		try {
			if(indexPere!=-1) {
				if (noeudCourant.getStagiaire().compareTo(lectureStagiaire(indexPere)) < 0) {
					raf.seek((indexPere * STAGIAIRE_LONG) + FILS_GAUCHE_LONG);
				} else {
					raf.seek((indexPere * STAGIAIRE_LONG) + FILS_DROIT_LONG);
				}
				raf.writeInt((int) raf.length() / STAGIAIRE_LONG);
			}
			raf.seek(raf.length());
			raf.writeChars(noeudCourant.getStagiaire().getNom());
			for (int i = noeudCourant.getStagiaire().getNom().length(); i < NOM_LONG; i++) {
				raf.writeChars("*");
			}
			raf.writeChars(noeudCourant.getStagiaire().getPrenom());
			for (int i = noeudCourant.getStagiaire().getPrenom().length(); i < PRENOM_LONG; i++) {
				raf.writeChars("*");
			}
			raf.writeChars(noeudCourant.getStagiaire().getDepartement());
			for (int i = noeudCourant.getStagiaire().getDepartement().length(); i < DEPARTEMENT_LONG; i++) {
				raf.writeChars("*");
			}
			raf.writeChars(noeudCourant.getStagiaire().getPromo());
			for (int i = noeudCourant.getStagiaire().getPromo().length(); i < PROMO_LONG; i++) {
				raf.writeChars("*");
			}
			raf.writeChars(noeudCourant.getStagiaire().getAnneeEntree());
			for (int i = noeudCourant.getStagiaire().getAnneeEntree().length(); i < ANNEE_ENTREE_LONG; i++) {
				raf.writeChars("*");
			}
			raf.writeInt(-1);
			raf.writeInt(-1);		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode permettant d'obtenir les informations d'un pbjet Stagiaire à partir de son index
	 * @param index
	 * @return un objet de type Stagiaire
	 */
	private static Stagiaire lectureStagiaire(int index) {
		Stagiaire stagiaireTemporaire = new Stagiaire("", "", "", "", "");
		String motTemporaire = "";
		try {
			raf.seek(index*STAGIAIRE_LONG);
			for (int i = 0; i < NOM_LONG; i++) {
				motTemporaire += raf.readChar();
			}
			stagiaireTemporaire.setNom(motTemporaire.replace("*", ""));
			motTemporaire = "";
			for (int i = 0; i < PRENOM_LONG; i++) {
				motTemporaire += raf.readChar();
			}
			stagiaireTemporaire.setPrenom(motTemporaire.replace("*", ""));
			motTemporaire = "";
			for (int i = 0; i < DEPARTEMENT_LONG; i++) {
				motTemporaire += raf.readChar();
			}
			stagiaireTemporaire.setDepartement(motTemporaire.replace("*", ""));
			motTemporaire = "";
			for (int i = 0; i < PROMO_LONG; i++) {
				motTemporaire += raf.readChar();
			}
			stagiaireTemporaire.setPromo(motTemporaire.replace("*", ""));
			motTemporaire = "";
			for (int i = 0; i < ANNEE_ENTREE_LONG; i++) {
				motTemporaire += raf.readChar();
			}
			stagiaireTemporaire.setAnneeEntree(motTemporaire.replace("*", ""));
			motTemporaire = "";
			raf.readInt();
			raf.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stagiaireTemporaire;
	}
	
//	************************** Méthodes pour ajout de Noeud ************************** //	
	/**
	 * Méthode permettant de rajouter un objet de type Noeud dans un arbre
	 * @param x
	 * @param index
	 * @param inscrireDansFichier
	 */
	public void ajouterNoeud(Stagiaire x, int index, boolean inscrireDansFichier) {
		if (this.racine == null) {
			Noeud nouveauNoeud = new Noeud(x, index);
			if(inscrireDansFichier) ecritureNoeudFichier(nouveauNoeud, -1);
			this.racine = nouveauNoeud; // 0, -1, -1
		}
		ajouterNoeud(x, racine, index, racine.getIndex(), inscrireDansFichier);
	}
	
	/**
	 * Méthode pertmettant de parcourir l'arbre afin de réaliser l'ajout du nouvel objet de type Noeud
	 * @param x
	 * @param courant
	 * @param index
	 * @param indexPere
	 * @param inscrireDansFichier
	 * @return un objet de type noeud
	 */
	private Noeud ajouterNoeud(Stagiaire x, Noeud courant, int index, int indexPere, boolean inscrireDansFichier) {
		if(courant == null) {
			Noeud nouveauNoeud = new Noeud(x, index);
			if(inscrireDansFichier) ecritureNoeudFichier(nouveauNoeud, indexPere);
			return nouveauNoeud;
		}
		if (x.compareTo(courant.getStagiaire()) < 0) {
			courant.setGauche(this.ajouterNoeud(x, courant.getGauche(), index, indexPere, inscrireDansFichier));
		}
		if (x.compareTo(courant.getStagiaire()) > 0) {
			courant.setDroit(ajouterNoeud(x, courant.getDroit(), index, indexPere, inscrireDansFichier));
		}
		return courant;	
	}

//	********************************************************************
	/**
	 * Retour console String
	 */
	@Override
	public String toString() {
		return racine + " ";
	}
	
}
