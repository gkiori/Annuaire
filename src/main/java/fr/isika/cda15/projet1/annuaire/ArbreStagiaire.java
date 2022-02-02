package fr.isika.cda15.projet1.annuaire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ArbreStagiaire {
	
	/**
	 * Définition des attributs
	 * A voir l'intérêt
	 */
	static final int NOM = 25;
	static final int PRENOM = 20;
	static final int DEPARTEMENT = 3;
	static final int PROMO = 15;
	static final int ANNEE_ENTREE = 4;
	static final String PATH_FILE_BIN = "src/main/resources/stagiaires.bin";
	static final String PATH_FILE_DON = "src/main/resources/STAGIAIRES.DON";
	private static Noeud racine;
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
	public static Noeud getRacine() {
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
	
	public void initArbre() {
		File monFichierBin = new File(PATH_FILE_BIN);
		File monFichierDon = new File(PATH_FILE_DON);
		if(monFichierBin.exists()) {
			try {
				raf = new RandomAccessFile(PATH_FILE_BIN, "rw");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			initArbreFichierBin();
		}
		else if(monFichierDon.exists()) {
			try {
				raf = new RandomAccessFile(PATH_FILE_BIN, "rw");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			initLectureDon(monFichierDon);
		}
	}

//	************************** Initialisation de l'arbre à partir d'un fichier bin ************************** //
	
	private void initLectureDon(File monFichier) {
		String nom = "", prenom = "", departement = "", nomPromo = "", anneeEntree = "";
		try {
			FileReader fr = new FileReader(monFichier);
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				nom = br.readLine();
				prenom = br.readLine();
				departement = br.readLine();
				nomPromo = br.readLine();
				anneeEntree = br.readLine();
				br.readLine();
				this.ajouter(new Stagiaire(nom, prenom, departement, nomPromo, anneeEntree));
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initArbreFichierBin() {
		this.setRacine(new Noeud(lectureStagiaire(0), 0)); 
		this.initArbreFichierBin(racine);
	}
	
	private Noeud initArbreFichierBin(Noeud courant) {
		if (lectureIndexFilsG(courant.getIndex()) != -1) {
			courant.setGauche(this.initArbreFichierBin(new Noeud(lectureStagiaire(lectureIndexFilsG(courant.getIndex())), lectureIndexFilsG(courant.getIndex()))));	
		}
		if (lectureIndexFilsD(courant.getIndex()) != -1) {
			courant.setDroit(this.initArbreFichierBin(new Noeud(lectureStagiaire(lectureIndexFilsD(courant.getIndex())), lectureIndexFilsD(courant.getIndex()))));
		}
		return courant;	
	}
	
//	************************** Méthodes pour la lecture et l'écriture dans le fichier ************************** //
	
	private static Noeud ecritureNoeudFichier(Stagiaire x, int indexPere) {
		int index = 0;
		try {
			if(indexPere!=-1) {
				if (x.compareTo(lectureStagiaire(indexPere)) < 0) {
					raf.seek((indexPere * 142) + 134);
				} else {
					raf.seek((indexPere * 142) + 138);
				}
				index = (int)raf.length() / 142;
				raf.writeInt(index);
				raf.seek(raf.length());
			}else raf.seek(0); // si indexPere = -1, la racine change
			raf.writeChars(x.getNom());
			for (int i = x.getNom().length(); i < 25; i++) {
				raf.writeChars("*");
			}
			raf.writeChars(x.getPrenom());
			for (int i = x.getPrenom().length(); i < 20; i++) {
				raf.writeChars("*");
			}
			raf.writeChars(x.getDepartement());
			for (int i = x.getDepartement().length(); i < 3; i++) {
				raf.writeChars("*");
			}
			raf.writeChars(x.getPromo());
			for (int i = x.getPromo().length(); i < 15; i++) {
				raf.writeChars("*");
			}
			raf.writeChars(x.getAnneeEntree());
			for (int i = x.getAnneeEntree().length(); i < 4; i++) {
				raf.writeChars("*");
			}
			raf.writeInt(-1);
			raf.writeInt(-1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Noeud(x, index);
	}
	
	private static Stagiaire lectureStagiaire(int index) {
		Stagiaire stagiaireTemporaire = new Stagiaire("", "", "", "", "");
		String motTemporaire = "";
		try {
			raf.seek(index*142);
			for (int i = 0; i < 25; i++) {
				motTemporaire += raf.readChar();
			}
			stagiaireTemporaire.setNom(motTemporaire.replace("*", ""));
			motTemporaire = "";
			for (int i = 0; i < 20; i++) {
				motTemporaire += raf.readChar();
			}
			stagiaireTemporaire.setPrenom(motTemporaire.replace("*", ""));
			motTemporaire = "";
			for (int i = 0; i < 3; i++) {
				motTemporaire += raf.readChar();
			}
			stagiaireTemporaire.setDepartement(motTemporaire.replace("*", ""));
			motTemporaire = "";
			for (int i = 0; i < 15; i++) {
				motTemporaire += raf.readChar();
			}
			stagiaireTemporaire.setPromo(motTemporaire.replace("*", ""));
			motTemporaire = "";
			for (int i = 0; i < 4; i++) {
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
	
	private static int lectureIndexFilsG(int index) {
		int retour = 0;
		try {
			raf.seek((index*142)+134);
			retour = raf.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retour;
	}
	
	private static int lectureIndexFilsD(int index) {
		int retour = 0;
		try {
			raf.seek((index*142)+138);
			retour = raf.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retour;
	}
	
	private static void ecrireIndexFilsG(int index, int nouveauIndex) {
		try {
			raf.seek((index*142)+134);
			raf.writeInt(nouveauIndex);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void ecrireIndexFilsD(int index, int nouveauIndex) {
		try {
			raf.seek((index*142)+138);
			raf.writeInt(nouveauIndex);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void nouvelleRacine(Stagiaire x, int filsG, int filsD) {
		ecritureNoeudFichier(x, -1);
		try {
			raf.seek(134);
			raf.writeInt(filsG);
			raf.seek(138);
			raf.writeInt(filsD);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	************************** Méthodes pour ajout de Noeud ************************** //	
	
	public static void ajouter(Stagiaire x) {
		if (ArbreStagiaire.racine == null) {
			ArbreStagiaire.racine = ecritureNoeudFichier(x, -1); 
		}
		ajouterNoeud(x, racine, -1);
	}
	
	private static Noeud ajouterNoeud(Stagiaire x, Noeud courant, int indexPere) {
		if(courant == null) {
			return ecritureNoeudFichier(x, indexPere);
		}
		if (x.compareTo(courant.getStagiaire()) < 0) {
			courant.setGauche(ajouterNoeud(x, courant.getGauche(), courant.getIndex()));
		}
		if (x.compareTo(courant.getStagiaire()) > 0) {
			courant.setDroit(ajouterNoeud(x, courant.getDroit(), courant.getIndex()));
		}
		return courant;	
	}
	
//	************************** Méthodes pour suppression de Noeud ************************** //	
	
	public static void supprimer(Stagiaire x) {
		Noeud racineAvant = new Noeud(ArbreStagiaire.racine.getStagiaire(), 0);
		ArbreStagiaire.racine = supprimerNoeud(x, ArbreStagiaire.racine);
		if(racineAvant != ArbreStagiaire.racine && ArbreStagiaire.racine != null) {
			nouvelleRacine(ArbreStagiaire.racine.getStagiaire(), lectureIndexFilsG(ArbreStagiaire.racine.getIndex()), lectureIndexFilsD(ArbreStagiaire.racine.getIndex()));
			ArbreStagiaire.racine.setIndex(0);
		}
	}
	
	private static Noeud supprimerNoeud(Stagiaire x, Noeud courant) {
        if (courant == null)
            return courant;
        if (courant.getStagiaire().compareTo(x) == 0) {
            return supprimerRacine(courant);
        }else if (courant.getStagiaire().compareTo(x) > 0) {
            courant.setGauche(supprimerNoeud(x, courant.getGauche()));
            if(courant.getGauche() == null) ecrireIndexFilsG(courant.getIndex(), -1);
            else ecrireIndexFilsG(courant.getIndex(), courant.getGauche().getIndex());
        }else {
        	courant.setDroit(supprimerNoeud(x, courant.getDroit()));
        	if(courant.getDroit() == null) ecrireIndexFilsD(courant.getIndex(), -1);
        	else ecrireIndexFilsD(courant.getIndex(), courant.getDroit().getIndex());
        }
        return courant;
    }
	
	private static Noeud supprimerRacine(Noeud courant) {
		if (courant.getGauche() == null && courant.getDroit() == null) {
			return null;
		}else if (courant.getGauche() == null) {
			ecrireIndexFilsD(courant.getIndex(), courant.getDroit().getIndex());
            return courant.getDroit();
        }else if (courant.getDroit() == null) {
        	ecrireIndexFilsG(courant.getIndex(), courant.getGauche().getIndex());
            return courant.getGauche();
        }else {
        	Noeud dernierDescendant = dernierDescendant(courant.getGauche());
        	
        	courant.setStagiaire(dernierDescendant.getStagiaire());
        	courant.setIndex(dernierDescendant.getIndex());
        	
        	if(courant.getIndex() != courant.getDroit().getIndex()) ecrireIndexFilsD(courant.getIndex(), courant.getDroit().getIndex());
        	else ecrireIndexFilsD(courant.getIndex(), -1);
        	if(courant.getIndex() != courant.getGauche().getIndex()) ecrireIndexFilsG(courant.getIndex(), courant.getGauche().getIndex());
        	else ecrireIndexFilsG(courant.getIndex(), -1);
        	
        	courant.setGauche(supprimerNoeud(dernierDescendant.getStagiaire(), courant.getGauche()));
        }
        return courant;
    }

    private static Noeud dernierDescendant(Noeud courant) {
        if (courant.getDroit() == null)
            return courant;
        return dernierDescendant(courant.getDroit());
    }

//	************************** Méthodes pour modification de Noeud ************************** //
    
    public void modifierNom(Stagiaire stagiaire, String nouveauNom) {
    	Stagiaire ancienStagiaire = stagiaire;
    	stagiaire.setNom(nouveauNom);
    	modifier(ancienStagiaire, stagiaire); 	
    }
    
    public void modifierPrenom(Stagiaire stagiaire, String nouveauPrenom) {
    	Stagiaire ancienStagiaire = stagiaire;
    	stagiaire.setPrenom(nouveauPrenom);
    	modifier(ancienStagiaire, stagiaire); 	
    }
    
    public void modifierDepartement(Stagiaire stagiaire, String nouveauDepartement) {
    	Stagiaire ancienStagiaire = stagiaire;
    	stagiaire.setDepartement(nouveauDepartement);
    	modifier(ancienStagiaire, stagiaire); 	
    }
    
    public void modifierPromo(Stagiaire stagiaire, String nouvellePromo) {
    	Stagiaire ancienStagiaire = stagiaire;
    	stagiaire.setPromo(nouvellePromo);
    	modifier(ancienStagiaire, stagiaire); 	
    }
    
    public void modifierAnneeEntree(Stagiaire stagiaire, String nouvelleAnneeEntree) {
    	Stagiaire ancienStagiaire = stagiaire;
    	stagiaire.setAnneeEntree(nouvelleAnneeEntree);
    	modifier(ancienStagiaire, stagiaire); 	
    }
    
    public static void modifier(Stagiaire ancienStagiaire, Stagiaire nouveauStagiaire) {
    	ArbreStagiaire.supprimer(ancienStagiaire);
    	ArbreStagiaire.ajouter(nouveauStagiaire);
    }
//	********************************************************************	
	@Override
	public String toString() {
		return racine + " ";
	}
}
