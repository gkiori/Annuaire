package fr.isika.cda15.projet1.annuaire;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.pdf.parser.Path;
import com.itextpdf.text.pdf.parser.clipper.Paths;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PanelInscription01 extends GridPane {
	
	private static Stage popUpInscription;
	// chemin vers le fichier texte dans lequel on écrit les informations de l'user
	static final String PATH_FILE_INSCRIPTION = "src/main/resources/fichierInscription"; 
	
	public static void PanelInscription01(User monUser) throws Exception {
		
		PanelInscription01.popUpInscription = new Stage();
		
		popUpInscription.initModality(Modality.APPLICATION_MODAL);
		
		popUpInscription.setTitle("Inscription");
		GridPane theGridPane = inscriptionGrid();
		addUIControls(theGridPane, monUser);
		Scene theScene = new Scene (theGridPane, 800,500);
		popUpInscription.setScene(theScene);
		popUpInscription.showAndWait();
	}
	
	private static GridPane inscriptionGrid() {
		GridPane theGridPane = new GridPane();
		theGridPane.setAlignment(Pos.CENTER);
		theGridPane.setPadding(new Insets(40,40,40,40));
		theGridPane.setHgap(10);
		theGridPane.setVgap(10);
		
		ColumnConstraints columnOne = new ColumnConstraints(100, 100, Double.MAX_VALUE);
		columnOne.setHalignment(HPos.RIGHT);
		ColumnConstraints columnTwo = new ColumnConstraints(200, 200, Double.MAX_VALUE);
		columnTwo.setHgrow(Priority.ALWAYS);
		theGridPane.getColumnConstraints().addAll(columnOne, columnTwo);
		return theGridPane;
	}
	
	private static void addUIControls(GridPane theGridPane, User monUser) {
		Label header = new Label("Créer un compte utilisateur");
		header.setFont(Font.font("Tahoma", FontWeight.BOLD, 24));
		theGridPane.add(header, 0, 0, 2, 1);
		GridPane.setHalignment(header, HPos.CENTER);
		GridPane.setMargin(header, new Insets(20, 0, 20, 0));
		
		Label nom = new Label("Votre nom : ");
		theGridPane.add(nom, 0, 1);
		
		TextField champsNom = new TextField();
		champsNom.setPrefHeight(40);
		theGridPane.add(champsNom, 1, 1);
		
		Label prenom = new Label("Votre prénom : ");
		theGridPane.add(prenom, 0, 2);
		
		TextField champsPrenom = new TextField();
		champsPrenom.setPrefHeight(40);
		theGridPane.add(champsPrenom, 1, 2);
		
		Label email = new Label("Votre email : ");
		theGridPane.add(email, 0, 3);
		
		TextField champsEmail = new TextField();
		champsEmail.setPrefHeight(40);
		theGridPane.add(champsEmail, 1, 3);
		
		Label mdp = new Label("Votre mot de passe : ");
		theGridPane.add(mdp, 0, 4);
		
		PasswordField champsMdp = new PasswordField();
		champsMdp.setPrefHeight(40);
		theGridPane.add(champsMdp, 1, 4);
		
		
		Label profil = new Label("Votre profil : ");
		theGridPane.add(profil, 0, 5);
		RadioButton userEnseignant = new RadioButton("Enseignant");
		RadioButton userStagiaire = new RadioButton("Apprenant");
		ToggleGroup profilUser = new ToggleGroup();
		userEnseignant.setToggleGroup(profilUser);
		userStagiaire.setToggleGroup(profilUser);
		theGridPane.add(userStagiaire, 1, 5);
		theGridPane.add(userEnseignant, 1, 6);
		
		Button editNom = new Button("Editer");
		theGridPane.add(editNom, 2, 1);
		editNom.setVisible(false);
		Button editPrenom = new Button("Editer");
		theGridPane.add(editPrenom, 2, 2);
		editPrenom.setVisible(false);
		Button editEmail = new Button("Editer");
		theGridPane.add(editEmail, 2, 3);
		editEmail.setVisible(false);
		Button editMdp = new Button("Editer");
		theGridPane.add(editMdp, 2, 4);
		editMdp.setVisible(false);
		Button editProfil = new Button("Editer");
		theGridPane.add(editProfil, 2, 6);
		editProfil.setVisible(false);
		
		if(monUser != null) {
			header.setText("Informations du compte");
			editNom.setVisible(true);
			editNom.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					champsNom.setDisable(false);
					champsNom.clear();
					champsNom.requestFocus();
				}
			});
			editPrenom.setVisible(true);
			editPrenom.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					champsPrenom.setDisable(false);
					champsPrenom.clear();
					champsPrenom.requestFocus();
				}
			});
			editEmail.setVisible(true);
			editEmail.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					champsEmail.setDisable(false);
					champsEmail.clear();
					champsEmail.requestFocus();
				}
			});
			editMdp.setVisible(true);
			editMdp.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					champsMdp.setDisable(false);
					champsMdp.clear();
					champsMdp.requestFocus();
				}
			});
			editProfil.setVisible(true);
			editProfil.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					userEnseignant.setDisable(false);
					userStagiaire.setDisable(false);
				}
			});
			champsNom.setText(monUser.getNom());
			champsNom.setDisable(true);
			champsNom.setOpacity(0.9);
			champsPrenom.setText(monUser.getPrenom());
			champsPrenom.setDisable(true);
			champsPrenom.setOpacity(0.9);
			champsEmail.setText(monUser.getIdCompte());
			champsEmail.setDisable(true);
			champsEmail.setOpacity(0.9);
			champsMdp.setText(monUser.getMdpCompte());
			champsMdp.setDisable(true);
			champsMdp.setOpacity(0.9);
			if(monUser.getProfil().equalsIgnoreCase("enseignant")) userEnseignant.setSelected(true);
			else userStagiaire.setSelected(true);
			userEnseignant.setDisable(true);
			userEnseignant.setOpacity(0.9);
			userStagiaire.setDisable(true);
			userStagiaire.setOpacity(0.9);
		}
		
		Button btn = new Button("Valider");
		btn.setPrefHeight(40);
		btn.setDefaultButton(true);
		btn.setPrefWidth(100);
		
		theGridPane.add(btn, 0, 7, 3, 2);
		GridPane.setHalignment(btn, HPos.CENTER);
		GridPane.setMargin(btn, new Insets(20, 0, 20, 0));
		
		if (monUser != null) {
			btn.setText("Valider");
			btn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					modifyLine(champsNom, champsPrenom, champsEmail, champsMdp, profilUser, monUser);
					PanelConnexion.setUser(new User(champsNom.getText(), champsPrenom.getText(), champsEmail.getText(), champsMdp.getText(), ((RadioButton)profilUser.getSelectedToggle()).getText()));
					PanelGeneralInfos.setUserName(champsPrenom.getText(), champsNom.getText());
					popUpInscription.close();
					
					
				}
			});
		} else {
			btn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					if (champsNom.getText().isEmpty()) {
						showAlert(Alert.AlertType.ERROR, theGridPane.getScene().getWindow(), "Erreur",
								"Veuillez entrer votre nom");
						return;
					}
					if (champsPrenom.getText().isEmpty()) {
						showAlert(Alert.AlertType.ERROR, theGridPane.getScene().getWindow(), "Erreur",
								"Veuillez entrer votre prénom");
						return;
					}
					if (champsEmail.getText().isEmpty()) {
						showAlert(Alert.AlertType.ERROR, theGridPane.getScene().getWindow(), "Erreur",
								"Veuillez entrer votre Email");
						return;
					}
					if (champsMdp.getText().isEmpty()) {
						showAlert(Alert.AlertType.ERROR, theGridPane.getScene().getWindow(), "Erreur",
								"Veuillez entrer votre mot de passe");
						return;
					}
					showAlert(AlertType.CONFIRMATION, theGridPane.getScene().getWindow(), "Inscription réussie",
							"Bienvenue " + champsPrenom.getText());
					addLine(champsNom, champsPrenom, champsEmail, champsMdp, profilUser);
					popUpInscription.close();
				}

				private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
					Alert alert = new Alert(alertType);
					alert.setTitle(title);
					alert.setHeaderText(null);
					alert.setContentText(message);
					alert.initOwner(owner);
					alert.show();
				}

			});
		}
	}
	
	private static void addLine(TextField champsNom, TextField champsPrenom,TextField champsEmail,  TextField champsMdp, ToggleGroup profilUser ) { // méthode pour récupérer la valeur des champs d'information
		// ce qui va être ajouté est de type String le nom de cet "objet" est line
		String line = champsNom.getText() 
				+ "," + champsPrenom.getText() 
				+ "," + champsEmail.getText() 
				+ "," + champsMdp.getText() 
				+ "," +  ((RadioButton)profilUser.getSelectedToggle()).getText() + "\n" ;
		
		try { // méthode try if 
			// création d'un objet de type File qui s'appelle "fichierInscription" 
			// et qui prend en arguments (caractéristiques) le chemin vers le fichier
			File fichierInscription = new File (PATH_FILE_INSCRIPTION); 
			if (fichierInscription.exists() || fichierInscription.createNewFile()) { 
				FileWriter fichierW = new FileWriter (PATH_FILE_INSCRIPTION, true);
				BufferedWriter buffered_Writer = new BufferedWriter (fichierW);
				buffered_Writer.write(line);
				buffered_Writer.close();
				
				FileReader fr = new FileReader(fichierInscription);
				BufferedReader br = new BufferedReader(fr);
				String infoInscription = "";
			
				while (br.ready()) {
					infoInscription += br.readLine();
					infoInscription += "\n";
				}
				br.close();
				fr.close();
//				System.out.println(infoInscription);
				String [] splitInfoInscription = infoInscription.split(",");
//				for (String s : splitInfoInscription) {
//					System.out.println(s);
//					
//				}
			}
			else {
				System.out.println("Fichier pas crée");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void modifyLine(TextField champsNom, TextField champsPrenom,TextField champsEmail,  TextField champsMdp, ToggleGroup profilUser, User monUser ) {
		
		try {
			File inputFile = new File(PATH_FILE_INSCRIPTION);
			File tempFile = new File("src/main/resources/fichierInscriptiontemp");
	        BufferedReader bufRead = new BufferedReader(new FileReader(inputFile));
	        BufferedWriter bufWrite = new BufferedWriter(new FileWriter(tempFile, true));
	        String line = champsNom.getText()
		    		+ "," + champsPrenom.getText()
		    		+ "," + champsEmail.getText()
		    		+ "," + champsMdp.getText()
		    		+ "," + ((RadioButton)profilUser.getSelectedToggle()).getText();
	        String line2 = monUser.getNom()
		    		+ "," + monUser.getPrenom()
		    		+ "," + monUser.getIdCompte()
		    		+ "," + monUser.getMdpCompte()
		    		+ "," + monUser.getProfil();
	        String temp;
            	
	        while ((temp = bufRead.readLine()) != null) {
	            String trimmedLine = temp.trim();
	            if(trimmedLine.equals(line2)) bufWrite.write(line + System.getProperty("line.separator"));
	            else bufWrite.write(temp + System.getProperty("line.separator"));
	        }
	        bufRead.close();
	        bufWrite.close();
	        inputFile.delete();
	        tempFile.renameTo(inputFile);
	    } catch (Exception e) {
	        System.out.println("Problem reading file.");
	    }
		
	}
}
