package fr.isika.cda15.projet1.annuaire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

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
	private static int racine = -1;
	private static RandomAccessFile raf;

//************************* Manipulation fichiers binaires ************************** //
	
	/**
	 *  Méthode d'initialisation :
	 *  - si un fichier binaire existe, l'ouvre et initialise le RandomAccessFile
	 *  - s'il n'existe aucun fichier binaire, en crée un et initialise le RandomAccessFile, puis lance l'initialisation à partir d'un fichier .DON
	 */
	public static void initArbre() {
		File monFichierBin = new File(PATH_FILE_BIN);
		File monFichierDon = new File(PATH_FILE_DON);
		if(!monFichierBin.exists()) {
			try {
				raf = new RandomAccessFile(PATH_FILE_BIN, "rw");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			initLectureDon(monFichierDon);
		}else {
			try {
				raf = new RandomAccessFile(PATH_FILE_BIN, "rw");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			racine = 0;
		}
		Corbeille.initCorbeille();
	}

//	************************** Initialisation de l'arbre à partir d'un fichier bin ************************** //
	
	/**
	 *	Méthode permettant la lecture d'un fichier .DON avec la structure demandée
	 *	Crée une liste contenant tout les stagiaires contenus dans le fichier
	 *	appelle la méthode construireArbreEquilibre
	 *
	 *	@see ArbreStagiaire#construireArbreEquilibre()
	 */
	private static void initLectureDon(File monFichier) {
		String nom = "", prenom = "", departement = "", nomPromo = "", anneeEntree = "";
		List<Stagiaire> listeStagiaire = new ArrayList<Stagiaire>();
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
				listeStagiaire.add(new Stagiaire(nom, prenom, departement, nomPromo, anneeEntree));
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(listeStagiaire);
		construireArbreEquilibre(listeStagiaire);
	}
	
//	************************** Méthodes pour la lecture et l'écriture dans le fichier ************************** //
	
	/**
	 *  Méthode permettant d'écrire un lien père fils entre deux noeuds dans le fichier lors de l'ajout d'un nouveau stagiaire et d'appeler la méthode écraser
	 *  
	 *  @see ArbreStagiaire#ecraser(int, Stagiaire)
	 *  @param x	le nouveau stagiaire
	 *  @param indexPere	l'index correspondant à la position du père dans le fichier
	 */
	private static void ecritureNoeudFichier(Stagiaire x, int indexPere) {
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
			}else index = 0; // si indexPere = -1, la racine change
			ecraser(index, x);
			raf.writeInt(-1);
			raf.writeInt(-1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode permettant d'écrire un stagiaire au bon index dans le fichier
	 * 
	 * @param index		index correspondant à la position ou le stagiaire doit être écrit
	 * @param x 		le stagiaire à écrire
	 */
	private static void ecraser(int index, Stagiaire x) {
		try {
			raf.seek(index*142);
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  Méthode permettant de lire un stagiaire à un certain index dans le fichier
	 *  
	 *  @param index	la position du stagiaire à lire
	 *  @return 		retourne le stagiaire lu
	 */
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
	
	/**
	 * 	Méthode permettant de lire du fils gauche d'un stagiaire positionné à un index précis
	 * 
	 * 	@param index	l'index du stagiaire dont on lira le fils gauche 
	 *  @return			l'index du fils gauche du stagiaire
	 */
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
	
	/**
	 * 	Méthode permettant de lire du fils droit d'un stagiaire positionné à un index précis
	 * 
	 * 	@param index	l'index du stagiaire dont on lira le fils droit 
	 *  @return			l'index du fils droit du stagiaire
	 */
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
	
	/**
	 * 	Méthode permettant de changer le fils gauche d'un stagiaire positionné à un index précis
	 * 
	 * 	@param index	l'index du stagiaire dont on changera le fils gauche
	 */
	private static void ecrireIndexFilsG(int index, int nouveauIndex) {
		try {
			raf.seek((index*142)+134);
			raf.writeInt(nouveauIndex);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	Méthode permettant de changer le fils droit d'un stagiaire positionné à un index précis
	 * 
	 * 	@param index	l'index du stagiaire dont on changera le fils droit
	 */
	private static void ecrireIndexFilsD(int index, int nouveauIndex) {
		try {
			raf.seek((index*142)+138);
			raf.writeInt(nouveauIndex);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	Méthode permettant de changer la racine du fichier
	 * 
	 * 	@param x		la nouvelle racine
	 * 	@param filsG	le fils gauche de cette nouvelle racine
	 * 	@param filsD	le fils droit de cette nouvelle racine
	 */
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
	
	/**
	 * 	Point d'entrée de la méthode récursive d'ajout d'un nouveau stagiaire dans le fichier
	 * 
	 * 	@param x 	Le stagiaire à ajouter
	 */
	public static void ajouter(Stagiaire x) {
		ajouterNoeud(x, racine, -1);
	}
	
	/**
	 * 	Méthode récursive d'ajout d'un nouveau stagiaire dans le fichier
	 * 
	 * 	@param x 			Le stagiaire à ajouter
	 * 	@param courant		Le noeud sur lequel on est situé actuellement
	 * 	@param indexPere	Le noeud pere du noeud courant
	 */
	private static void ajouterNoeud(Stagiaire x, int courant, int indexPere) {
		if(courant == -1) {
			ecritureNoeudFichier(x, indexPere);
			racine = 0;
		}else if (x.compareTo(lectureStagiaire(courant)) < 0) {
			ajouterNoeud(x, lectureIndexFilsG(courant), courant);
		}else if (x.compareTo(lectureStagiaire(courant)) > 0) {
			ajouterNoeud(x, lectureIndexFilsD(courant), courant);
		}
	}
	
//	************************** Méthodes pour suppression de Noeud ************************** //	
	
	/**
	 * 	Point d'entrée de la méthode récursive de suppression d'un nouveau stagiaire dans le fichier
	 * 
	 * 	@param x 	Le stagiaire à supprimer
	 */
	public static void supprimer(Stagiaire x) {
		int racineAvant = racine;
		racine = supprimerNoeud(x, racine);
		if(racineAvant != racine && racine != -1) {
			nouvelleRacine(lectureStagiaire(racine), lectureIndexFilsG(racine), lectureIndexFilsD(racine));
			racine = 0;
		}
		Corbeille.ajoutCorbeille(x);
	}
	
	/**
	 * 	Méthode récursive de recherche du stagiaire à supprimer
	 * 
	 * 	@param x 		Le stagiaire à supprimer
	 * 	@param courant	Le noeud sur lequel on est situé actuellement
	 */
	private static int supprimerNoeud(Stagiaire x, int courant) {
        if (courant == -1)
            return 0;
        if (lectureStagiaire(courant).compareTo(x) == 0) {
            return supprimerRacine(courant);
        }else if (lectureStagiaire(courant).compareTo(x) > 0) {
        	int valeurRetour = supprimerNoeud(x, lectureIndexFilsG(courant));
        	if(valeurRetour == -1) ecrireIndexFilsG(courant, -1);
            else ecrireIndexFilsG(courant, valeurRetour);
        }else {
        	int valeurRetour = supprimerNoeud(x, lectureIndexFilsD(courant));
        	if(valeurRetour == -1) ecrireIndexFilsD(courant, -1);
            else ecrireIndexFilsD(courant, valeurRetour);
        }
        return courant;
    }

	/**
	 * 	Méthode récursive de suppression d'un nouveau stagiaire dans le fichier qui gère les cas suivant :
	 * 	- Le noeud à supprimer est une feuille
	 * 	- Le noeud à supprimer n'a qu'un seul fils
	 * 	- Le noeud à supprimer a deux fils
	 * 
	 * 	@param courant	Le noeud sur lequel on est situé actuellement qui contient le stagiaire à supprimer
	 */
	private static int supprimerRacine(int courant) {
		if (lectureIndexFilsG(courant) == -1 && lectureIndexFilsD(courant) == -1) {
			return -1;
		}else if (lectureIndexFilsG(courant) == -1) {
            return lectureIndexFilsD(courant);
        }else if (lectureIndexFilsD(courant) == -1) {
            return lectureIndexFilsG(courant);
        }else {
        	int dernierDescendant = dernierDescendant(lectureIndexFilsG(courant));
        	
        	ecraser(courant, lectureStagiaire(dernierDescendant));
        	
        	ecrireIndexFilsG(courant, supprimerNoeud(lectureStagiaire(courant), lectureIndexFilsG(courant)));
        	return courant;
        }
    }

	/**
	 * 	Méthode cherchant le dernier descendant le plus à droit du noeud courant
	 * 
	 * 	@param courant	Fils gauche du noeud père à supprimer
	 */
    private static int dernierDescendant(int courant) {
        if (lectureIndexFilsD(courant) == -1)
            return courant;
        return dernierDescendant(lectureIndexFilsD(courant));
    }

//	************************** Méthodes pour modification de Noeud ************************** //
    
    /**
	 * 	Méthode modifiant le nom d'un stagiaire
	 * 
	 * 	@param stagiaire	stagiaire dont le nom va être modifié
	 * 	@param nouveauNom	nouveau nom du stagiaire
	 */
    public static void modifierNom(Stagiaire stagiaire, String nouveauNom) {
    	Stagiaire ancienStagiaire = stagiaire;
    	stagiaire.setNom(nouveauNom);
    	modifier(ancienStagiaire, stagiaire); 	
    }
    
    /**
	 * 	Méthode modifiant le prénom d'un stagiaire
	 * 
	 * 	@param stagiaire		stagiaire dont le prénom va être modifié
	 * 	@param nouveauPrenom	nouveau prénom du stagiaire
	 */
    public static void modifierPrenom(Stagiaire stagiaire, String nouveauPrenom) {
    	Stagiaire ancienStagiaire = stagiaire;
    	stagiaire.setPrenom(nouveauPrenom);
    	modifier(ancienStagiaire, stagiaire); 	
    }
    
    /**
	 * 	Méthode modifiant le département d'un stagiaire
	 * 
	 * 	@param stagiaire			stagiaire dont le département va être modifié
	 * 	@param nouveauDepartement	nouveau département du stagiaire
	 */
    public static void modifierDepartement(Stagiaire stagiaire, String nouveauDepartement) {
    	Stagiaire ancienStagiaire = stagiaire;
    	stagiaire.setDepartement(nouveauDepartement);
    	modifier(ancienStagiaire, stagiaire); 	
    }
    
    /**
	 * 	Méthode modifiant la promotion d'un stagiaire
	 * 
	 * 	@param stagiaire		stagiaire dont la promotion va être modifié
	 * 	@param nouvellePromo	nouvelle promotion du stagiaire
	 */
    public static void modifierPromo(Stagiaire stagiaire, String nouvellePromo) {
    	Stagiaire ancienStagiaire = stagiaire;
    	stagiaire.setPromo(nouvellePromo);
    	modifier(ancienStagiaire, stagiaire); 	
    }
    
    /**
	 * 	Méthode modifiant l'année d'entrée d'un stagiaire
	 * 
	 * 	@param stagiaire			stagiaire dont l'année d'entrée va être modifié
	 * 	@param nouvelleAnneeEntree	nouvelle année d'entrée du stagiaire
	 */
    public static void modifierAnneeEntree(Stagiaire stagiaire, String nouvelleAnneeEntree) {
    	Stagiaire ancienStagiaire = stagiaire;
    	stagiaire.setAnneeEntree(nouvelleAnneeEntree);
    	modifier(ancienStagiaire, stagiaire); 	
    }
    
    /**
	 * 	Méthode supprimant un stagiaire et en ajoutant un nouveau
	 * 
	 * 	@param ancienStagiaire	stagiaire à supprimer
	 * 	@param nouveauStagiaire stagiaire à ajouter
	 */
    public static void modifier(Stagiaire ancienStagiaire, Stagiaire nouveauStagiaire) {
    	supprimer(ancienStagiaire);
    	ajouter(nouveauStagiaire);
    }
    
 // ******************* Création de la liste complète des stagiaire  *******************
	
 	/**
 	 * Méthode d'entrée de la méthode récursive parcourant l'arbre pour retourner un HashSet contenant tout les stagiaires
 	 * 
 	 * @return retourne un set contenant tout les stagiaires contenu dans le fichier
 	 */
 	public static HashSet<Stagiaire> parcoursStagiaire(){
 		HashSet<Stagiaire> listStagiaire = new HashSet<Stagiaire>();
 		return parcoursStagiaire(racine, listStagiaire);
 	}
 	
 	/**
 	 * Méthode récursive parcourant l'arbre pour retourner un HashSet contenant tout les stagiaires
 	 * 
 	 * @param listInfixe	Set dans lequel on ajoutera tout les stagiaires contenu dans le fichier 
 	 * @return 				retourne un set contenant tout les stagiaires contenu dans le fichier
 	 */
 	private static HashSet<Stagiaire> parcoursStagiaire(int r, HashSet<Stagiaire> listInfixe){
 		if(r == -1) return new HashSet<Stagiaire>(listInfixe);
 		
 		parcoursStagiaire(lectureIndexFilsG(r), listInfixe);
 		listInfixe.add(lectureStagiaire(r));
 		parcoursStagiaire(lectureIndexFilsD(r), listInfixe);
 		
 		
 		return new HashSet<Stagiaire>(listInfixe);
 	}
 
 	 // ******************* Méthode d'équilibrage d'arbre  *******************
 	
 	/**
 	 * Méthode récursive ajoutant les stagiaires d'une liste reçue en paramètre de manière à constituer un arbre équilibré 
 	 * en ajoutant à chaque appel récursif la médiane du tableau qu'on reçoit en paramètre 
 	 * et en appelant ensuite cette méthode sur la partie gauche puis droite du tableau
 	 * 
 	 * @param arbre 	Liste des stagiaires à ajouter
 	 * @param debut		index du début de la partie du tableau sur laquelle on va travailler durant l'appel récursif
 	 * @param fin		index de la fin de la partie du tableau sur laquelle on va travailler durant l'appel récursif
 	 */
    private static void construireArbreEquilibreRec(List<Stagiaire> arbre, int debut, int fin){
        if (debut > fin) {
            return;
        }
 
        int milieu = (debut + fin) / 2;
 
        ajouter(arbre.get(milieu));
        
        construireArbreEquilibreRec(arbre, debut, milieu - 1);
        construireArbreEquilibreRec(arbre, milieu + 1, fin);
    }
    
    /**
 	 * Point d'entrée de la méthode récursive ajoutant les stagiaires pour constituer un arbre équilibré.
 	 * Elle écrase le fichier précédent afin d'en créer un nouveau contenant l'arbre équilibré
 	 */
    public static void construireArbreEquilibre(){
    	List<Stagiaire> listeStagiaire = new ArrayList<Stagiaire>();
        listeStagiaire.addAll(parcoursStagiaire());
        Collections.sort(listeStagiaire);
        try {
        	FileWriter fw = new FileWriter(new File(PATH_FILE_BIN));
        	fw.flush();
        	raf.setLength(0);
			racine = -1;
		} catch (IOException e) {
		}
        construireArbreEquilibreRec(listeStagiaire, 0, listeStagiaire.size() - 1);
    }
    
    /**
 	 * Point d'entrée de la méthode récursive ajoutant les stagiaires pour constituer un arbre équilibré.
 	 * Elle prend en paramètre un liste de stagiaire et n'est appelée que lors de l'initialisation à partir d'un fichier .DON
 	 */
    private static void construireArbreEquilibre(List<Stagiaire> listeStagiaire){
        construireArbreEquilibreRec(listeStagiaire, 0, listeStagiaire.size() - 1);
    }

 // ******************* Méthode de calcul de hauteur d'arbre  *******************
    
    /**
 	 * Point d'entrée de la méthode récursive calculant la hauteur minimum d'un arbre
 	 */
    public static int hauteurMinimum(){
    	
        return hauteurMinimum(racine);
    }
 
    /**
 	 * Méthode récursive calculant la hauteur minimum de arbre situé dans le fichier
 	 * 
 	 * @param courant	le noeud sur lequel on est situé durant cet appel récursif
 	 */
    public static int hauteurMinimum(int courant){
    	
        if (lectureIndexFilsG(courant) == -1 && lectureIndexFilsD(courant) == -1)
            return 1;
 
        if (lectureIndexFilsG(courant) == -1)
            return hauteurMinimum(lectureIndexFilsD(courant)) + 1;
 
        if (lectureIndexFilsD(courant) == -1)
            return hauteurMinimum(lectureIndexFilsG(courant)) + 1;
 
        return Math.min(hauteurMinimum(lectureIndexFilsG(courant)),hauteurMinimum(lectureIndexFilsD(courant))) + 1;
    }
    
    /**
 	 * Point d'entrée de la méthode récursive calculant la hauteur maximum d'un arbre
 	 */
    public static int hauteurMaximum(){
    	
        return hauteurMaximum(racine);
    }
 
    /**
 	 * Méthode récursive calculant la hauteur maximum de l'arbre situé dans le fichier
 	 * 
 	 * @param courant	le noeud sur lequel on est situé durant cet appel récursif
 	 */
    public static int hauteurMaximum(int courant){
 
        if (lectureIndexFilsG(courant) == -1 && lectureIndexFilsD(courant) == -1)
            return 1;
 
        if (lectureIndexFilsG(courant) == -1)
            return hauteurMaximum(lectureIndexFilsD(courant)) + 1;
 
        if (lectureIndexFilsD(courant) == -1)
            return hauteurMaximum(lectureIndexFilsG(courant)) + 1;
 
        return Math.max( hauteurMaximum(lectureIndexFilsG(courant)), hauteurMaximum(lectureIndexFilsD(courant))) + 1;
    }
}
