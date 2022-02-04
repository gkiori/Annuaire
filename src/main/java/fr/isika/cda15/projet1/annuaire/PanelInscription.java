package fr.isika.cda15.projet1.annuaire;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PanelInscription extends Application{

	static final String PATH_FILE_INSCRIPTION = "src/main/resources/fichierInscription"; // chemin vers le fichier texte dans lequel on écrit les informations de l'user
	
	
	private void addLine(TextField champsNom, TextField champsPrenom,TextField champsEmail,  TextField champsMdp, ToggleGroup profilUser ) { // méthode pour récupérer la valeur des champs d'information
		String line = champsNom.getText() + "," + champsPrenom.getText() + "," + champsEmail.getText() + "," + champsMdp.getText() + "," +  ((RadioButton)profilUser.getSelectedToggle()).getText() + "\n" ;  // ce qui va être ajouté est de type String le nom de cet "objet" est line
	
		File fichierInscription = new File (PATH_FILE_INSCRIPTION); // création d'un objet de type File qui s'appelle "fichierInscription" et qui prend en arguments (caractéristiques) le chemin vers le fichier
		try { // méthode try if 
			if (fichierInscription.exists()) { 
				System.out.println("fichier créer");
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
				System.out.println(infoInscription);
				String [] splitInfoInscription = infoInscription.split(",");
				for (String s : splitInfoInscription) {
					System.out.println(s);
					
				}
			}
			else {
				System.out.println("Fichier pas crée");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Override 
	public void start (Stage primaryStage) throws Exception {
		primaryStage.setTitle("Inscription");
		GridPane theGridPane = inscriptionGrid();
		addUIControls(theGridPane);
		Scene theScene = new Scene (theGridPane, 800,500);
		primaryStage.setScene(theScene);
		primaryStage.show();
	}

	private GridPane inscriptionGrid() {
		GridPane theGridPane = new GridPane ();
		theGridPane.setAlignment(Pos.CENTER);
		theGridPane.setPadding (new Insets (40,40,40,40));
		theGridPane.setHgap(10);
		theGridPane.setVgap(10);

		ColumnConstraints columnOne = new ColumnConstraints (100,100,Double.MAX_VALUE);
		columnOne.setHalignment(HPos.RIGHT);
		ColumnConstraints columnTwo = new ColumnConstraints (200,200,Double.MAX_VALUE);
		columnTwo.setHgrow(Priority.ALWAYS);

		theGridPane.getColumnConstraints().addAll(columnOne, columnTwo);

		return theGridPane;

	}

	private void addUIControls (GridPane theGridPane) {

		Label header = new Label("Créer un compte utilisateur");
		header.setFont(Font.font ("Tahoma", FontWeight.BOLD, 24));
		theGridPane.add (header, 0,0,2,1);
		GridPane.setHalignment(header, HPos.CENTER);
		GridPane.setMargin(header, new Insets(20,0,20,0));

		Label nom = new Label("Votre nom : ");
		theGridPane.add(nom, 0, 1);

		TextField champsNom = new TextField();
		champsNom.setPrefHeight(40);
		theGridPane.add(champsNom, 1, 1);

		Label prenom = new Label("Votre prenom : ");
		theGridPane.add(prenom, 0, 2);

		TextField champsPrenom = new TextField();
		champsPrenom.setPrefHeight(40);
		theGridPane.add(champsPrenom, 1, 2);

		Label email = new Label ("Votre e-mail : ");
		theGridPane.add(email, 0, 3);

		TextField champsEmail = new TextField();
		champsEmail.setPrefHeight(40);
		theGridPane.add(champsEmail, 1, 3);

		Label mdp = new Label ("Mot de passe : ");
		theGridPane.add(mdp, 0, 4);

		PasswordField champsMdp = new PasswordField ();	
		champsMdp.setPrefHeight(40);
		theGridPane.add(champsMdp, 1, 4);

		Label profil = new Label ("Votre profil : ");
		theGridPane.add(profil, 0, 5);
		RadioButton userEnseignant = new RadioButton ("Enseignant");
		RadioButton userStagiaire = new RadioButton ("Apprenant");
		ToggleGroup profilUser = new ToggleGroup (); 
		userEnseignant.setToggleGroup (profilUser);
		userStagiaire.setToggleGroup(profilUser);
		theGridPane.add(userStagiaire, 1, 5);
		theGridPane.add(userEnseignant, 1, 6);

		Button btn = new Button ("valider");
		btn.setPrefHeight(40);
		btn.setDefaultButton(true);
		btn.setPrefWidth(100);
		

		theGridPane.add(btn, 0, 7, 3, 2);
		GridPane.setHalignment(btn, HPos.CENTER);
		GridPane.setMargin(btn, new Insets (20,0,20,0));

		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				if (champsNom.getText().isEmpty()) {
					showAlert (Alert.AlertType.ERROR, theGridPane.getScene().getWindow(),"Erreur !", "veuillez entrer votre nom");
					return;
				}

				if (champsPrenom.getText().isEmpty()) {
					showAlert (Alert.AlertType.ERROR, theGridPane.getScene().getWindow(),"Erreur !", "Veuillez entrer votre prénom");
					return;
				}

				if(champsEmail.getText().isEmpty()) {
					showAlert (Alert.AlertType.ERROR, theGridPane.getScene().getWindow(),"Erreur !", "veuillez entrer votre e-mail");
					return;
				}

				if(champsMdp.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, theGridPane.getScene().getWindow(), "Erreur !", "veuillez entrer votre mot de passe");
					return;

				}
				showAlert(Alert.AlertType.CONFIRMATION, theGridPane.getScene().getWindow(), "Inscription réussie", "bienvenue " + champsNom.getText());
			
				addLine ( champsNom,  champsPrenom, champsEmail,   champsMdp, profilUser);
			}

			private void showAlert (Alert.AlertType alertType, Window owner, String title, String message) {
	
				Alert alert = new Alert (alertType);
				alert.setTitle(title);
				alert.setHeaderText(null);
				alert.setContentText(message);
				alert.initOwner(owner);
				alert.show();

			}

			

		});
		
		


		}


	public static void main(String[] args) {

		launch();
	}

}
