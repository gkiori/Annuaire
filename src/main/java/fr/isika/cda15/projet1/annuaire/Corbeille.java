package fr.isika.cda15.projet1.annuaire;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Corbeille {
	static final String PATH_FILE_CORBEILLE = "src/main/resources/corbeilleStagiaires.bin";
	private static RandomAccessFile rafCorbeille;
	
	public static void initCorbeille() {
		try {
			rafCorbeille = new RandomAccessFile(PATH_FILE_CORBEILLE, "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void ajoutCorbeille(Stagiaire stagiaire) {
		boolean inscrit = false;
		try {
			if(rafCorbeille.length() > 0) {
				for(int j = 0; j*134 < rafCorbeille.length(); j++) {
					rafCorbeille.seek(j*134);
					if(rafCorbeille.readChar() == '%' && inscrit == false){
						ecrireDansFichierCorbeille(j, stagiaire);
						inscrit = true;
					}
				}
			}else {
				ecrireDansFichierCorbeille(0, stagiaire);
				inscrit = true;
			}
			if(inscrit == false) {
				ecrireDansFichierCorbeille((int)rafCorbeille.length()/134, stagiaire);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void ecrireDansFichierCorbeille(int j, Stagiaire stagiaire) {
		try {
			rafCorbeille.seek(j*134);
			rafCorbeille.writeChars(stagiaire.getNom());
			for (int i = stagiaire.getNom().length(); i < 25; i++) {
				rafCorbeille.writeChar('*');
			}
			rafCorbeille.writeChars(stagiaire.getPrenom());
			for (int i = stagiaire.getPrenom().length(); i < 20; i++) {
				rafCorbeille.writeChar('*');
			}
			rafCorbeille.writeChars(stagiaire.getDepartement());
			for (int i = stagiaire.getDepartement().length(); i < 3; i++) {
				rafCorbeille.writeChar('*');
			}
			rafCorbeille.writeChars(stagiaire.getPromo());
			for (int i = stagiaire.getPromo().length(); i < 15; i++) {
				rafCorbeille.writeChar('*');
			}
			rafCorbeille.writeChars(stagiaire.getAnneeEntree());
			for (int i = stagiaire.getAnneeEntree().length(); i < 4; i++) {
				rafCorbeille.writeChar('*');
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Stagiaire lectureDansFichierStagiaire(int index) {
		Stagiaire stagiaireTemporaire = new Stagiaire();
		String motTemporaire = "";
		try {
			rafCorbeille.seek(index*134);
			for (int i = 0; i < 25; i++) {
				motTemporaire += rafCorbeille.readChar();
			}
			stagiaireTemporaire.setNom(motTemporaire.replace("*", ""));
			motTemporaire = "";
			for (int i = 0; i < 20; i++) {
				motTemporaire += rafCorbeille.readChar();
			}
			stagiaireTemporaire.setPrenom(motTemporaire.replace("*", ""));
			motTemporaire = "";
			for (int i = 0; i < 3; i++) {
				motTemporaire += rafCorbeille.readChar();
			}
			stagiaireTemporaire.setDepartement(motTemporaire.replace("*", ""));
			motTemporaire = "";
			for (int i = 0; i < 15; i++) {
				motTemporaire += rafCorbeille.readChar();
			}
			stagiaireTemporaire.setPromo(motTemporaire.replace("*", ""));
			motTemporaire = "";
			for (int i = 0; i < 4; i++) {
				motTemporaire += rafCorbeille.readChar();
			}
			stagiaireTemporaire.setAnneeEntree(motTemporaire.replace("*", ""));
			motTemporaire = "";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stagiaireTemporaire;
	}
	
	public static void retirerCorbeille(Stagiaire stagiaire) {
		try {
			for(int i = 0; i*134 < rafCorbeille.length(); i++) {
				if(stagiaire.compareTo(lectureDansFichierStagiaire(i)) == 0) {
					rafCorbeille.seek(i*134);
					for(int j = 0; j < 67; j++) {
						rafCorbeille.writeChar('%');
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Stagiaire> parcoursStagiaire(){
 		List<Stagiaire> listStagiaire = new ArrayList<Stagiaire>();
 		try {
			for(int i = 0; i*134 < rafCorbeille.length(); i++) {
				rafCorbeille.seek(i*134);
				if(rafCorbeille.readChar() != '%'){
					rafCorbeille.seek(i*134);
					listStagiaire.add(lectureDansFichierStagiaire(i));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
 		return listStagiaire;
 	}
}
