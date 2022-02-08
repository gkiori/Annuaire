package fr.isika.cda15.projet1.annuaire;

//import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
//import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;

import org.controlsfx.control.CheckComboBox;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class PanelFiltre extends BorderPane{
	
	private static Label titre = new Label("Filtres");
	private static TextField zoneRecherche = new TextField();
	private static TextField zoneRechercheNom = new TextField();
	private static TextField zoneRecherchePrenom = new TextField();
	private static Button reinitialisationFiltre = new Button("Réinitialiser les filtres");

	private static CheckComboBox<String> menuPromo = new CheckComboBox<String>();
	private static CheckComboBox<String> menuDepartement = new CheckComboBox<String>();
	private static CheckComboBox<String> menuAnneeEntree = new CheckComboBox<String>();
	
	private TreeSet<String> vuePromo = new TreeSet<String>();
	private TreeSet<String> vueDepartement = new TreeSet<String>();
	private TreeSet<String> vueAnneeEntree = new TreeSet<String>();

	private static boolean changementLance = false;
	
	public PanelFiltre(final Stage stage) throws Exception{
		
		zoneRecherche.setPromptText("Recherche");
		zoneRechercheNom.setPromptText("Nom");
		zoneRecherchePrenom.setPromptText("Prenom");
		
		vuePromo.addAll(Recherche.getListePromo());
		vueDepartement.addAll(Recherche.getListeDepartement());
		vueAnneeEntree.addAll(Recherche.getListeAnneeEntree());
		
		ObservableList<String> listePromo = FXCollections.observableArrayList();
		for(String promo : vuePromo) listePromo.add(promo);
		ObservableList<String> listeDepartement = FXCollections.observableArrayList();
		for(String departement : vueDepartement) listeDepartement.add(departement);
		ObservableList<String> listeAnneeEntree = FXCollections.observableArrayList();
		for(String anneeEntree : vueAnneeEntree) listeAnneeEntree.add(anneeEntree);
		
		menuPromo.getItems().addAll(vuePromo);
		menuPromo.setTitle("Promotion");
		menuDepartement.getItems().addAll(vueDepartement);
		menuDepartement.setTitle("Département");
		menuAnneeEntree.getItems().addAll(vueAnneeEntree);
		menuAnneeEntree.setTitle("Année d'entrée");
		
		zoneRecherche.setOnMouseClicked(clic -> { zoneRecherche.clear();});
		zoneRechercheNom.setOnMouseClicked(clic -> { zoneRechercheNom.clear();});
		zoneRecherchePrenom.setOnMouseClicked(clic -> { zoneRecherchePrenom.clear();});
		
//		zoneRecherche.setStyle("-fx-background-color: #6989FE; -fx-text-fill: #ffffff;-fx-prompt-text-fill: #BAC8FA;");
//		zoneRechercheNom.setStyle("-fx-background-color: #6989FE; -fx-text-fill: #ffffff;-fx-prompt-text-fill: #BAC8FA;");
//		zoneRecherchePrenom.setStyle("-fx-background-color: #6989FE; -fx-text-fill: #ffffff;-fx-prompt-text-fill: #BAC8FA;");
		
		reinitialisationFiltre.setStyle("-fx-background-color: #0E4DA4;"
				+ "    -fx-background-insets: 0,1,2,3;\n"
				+ "    -fx-background-radius: 6, 5;\n"
				+ "    -fx-padding: 12 30 12 30;\n"
				+ "    -fx-text-fill: white;\n"
				+ "    -fx-font-weight : bold;"
				+ "    -fx-font-size: 14px;");
	
		
		
		
		ChangeListener<String> changementZoneEcriture = new ChangeListener<String>(){
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				TimerTask tacheChangementRecherche = new TimerTask() {
					@Override
					public void run() {
						if(!changementLance) changementRecherche();
					}
				};
				Timer timerChangementRecherche = new Timer();
				timerChangementRecherche.schedule(tacheChangementRecherche, 300);
			}
			
		};
		zoneRecherche.textProperty().addListener(changementZoneEcriture);
		zoneRechercheNom.textProperty().addListener(changementZoneEcriture);
		zoneRecherchePrenom.textProperty().addListener(changementZoneEcriture);
		
		ListChangeListener<String> changementComboBox = new ListChangeListener<String>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends String> c) {
				changementRecherche();
			}
		};
		menuPromo.getCheckModel().getCheckedItems().addListener(changementComboBox);
		menuDepartement.getCheckModel().getCheckedItems().addListener(changementComboBox);
		menuAnneeEntree.getCheckModel().getCheckedItems().addListener(changementComboBox);
		
		reinitialisationFiltre.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event) {
				menuPromo.getCheckModel().clearChecks();
				menuDepartement.getCheckModel().clearChecks();
				menuAnneeEntree.getCheckModel().clearChecks();
				zoneRecherche.clear();
				zoneRechercheNom.clear();
				zoneRecherchePrenom.clear();
			}
		});
		
////		menuPromo.setStyle(
//		"-fx-body-color: #6989FE;  -fx-selection-bar: #B7C8E0; "
////				+ "-fx-selection-bar-non-focused: #B7C8E0;\r\n"
//////				+ "-fx-border-color:#B7C8E0;\r\n"
////				+ "-fx-accent: #F1FDF7;\r\n");
////		
////		menuDepartement.setStyle(
//		"-fx-body-color: #6989FE;  -fx-selection-bar: #B7C8E0; "
////				+ "-fx-selection-bar-non-focused: #B7C8E0;\r\n"
////				+ "-fx-accent: #F1FDF7;\r\n");
////		
////		menuAnneeEntree.setStyle(
//		"-fx-body-color: #6989FE;  -fx-selection-bar: #B7C8E0; "
////				+ "-fx-selection-bar-non-focused: #B7C8E0;\r\n"
////				+ "-fx-accent: #F1FDF7;\r\n");
//		
		
	
		
		titre.setPrefSize(282, 64);
		titre.setTextAlignment(TextAlignment.CENTER);
		titre.setStyle("    -fx-text-fill: #404D61;\n"
//				"-fx-background-color: #6989FE;"
//				+ "        linear-gradient(#7ebcea, #2f4b8f),\n"
//				+ "        linear-gradient(#426ab7, #263e75),\n"
//				+ "        linear-gradient(#395cab, #223768);\n"
//				+ "    -fx-background-insets: 0,1,2,3;\n"
//				+ "    -fx-background-radius: 6, 5;\n"
//				+ "    -fx-padding: 12 30 12 30;\n"
				+ "		-fx-font-weight: bold;\n"
				+ "    -fx-font-size: 18px;");
		
		
		VBox boxRecherche = new VBox();
		boxRecherche.getChildren().addAll(zoneRechercheNom, zoneRecherchePrenom, menuPromo, menuDepartement, menuAnneeEntree);
		boxRecherche.setSpacing(10);
		VBox orgVbox = new VBox();
		orgVbox.getChildren().addAll(zoneRecherche, boxRecherche, reinitialisationFiltre);
		orgVbox.setPadding(new Insets(20,20,20,10));
		orgVbox.setSpacing(50);
		this.setTop(titre);
		this.setCenter(orgVbox);
		this.setStyle("-fx-background-color: #FBFDFF");
		cacherRecherche();
		Scene scene = new Scene(this, 314, 1024);
		stage.setScene(scene);
	
	}
	
	public static void cacherRecherche() {
		titre.setVisible(false);
		zoneRecherche.setVisible(false);
		reinitialisationFiltre.setVisible(false);
		zoneRechercheNom.setVisible(false);
		zoneRecherchePrenom.setVisible(false);
		menuPromo.setVisible(false);
		menuDepartement.setVisible(false);
		menuAnneeEntree.setVisible(false);
	}
	
	public static void montrerRecherche() {
		zoneRecherche.setVisible(true);
		reinitialisationFiltre.setVisible(true);
		zoneRechercheNom.setVisible(true);
		zoneRecherchePrenom.setVisible(true);
		menuPromo.setVisible(true);
		menuDepartement.setVisible(true);
		menuAnneeEntree.setVisible(true);
	}
	
	public static void changementRecherche() {
		changementLance = true;
		Map<String, String> listeRecherche = new HashMap<String, String>();
		if(menuPromo.getCheckModel().getCheckedItems().toString() != "[]") 
			listeRecherche.put(menuPromo.getCheckModel().getCheckedItems().toString(), "promo");
		if(menuDepartement.getCheckModel().getCheckedItems().toString() != "[]") 
			listeRecherche.put(menuDepartement.getCheckModel().getCheckedItems().toString(), "departement");
		if(menuAnneeEntree.getCheckModel().getCheckedItems().toString() != "[]") 
			listeRecherche.put(menuAnneeEntree.getCheckModel().getCheckedItems().toString(), "anneeEntree");
		listeRecherche.put(zoneRechercheNom.getText(), "nom");
		listeRecherche.put(zoneRecherchePrenom.getText(), "prenom");
		listeRecherche.put(zoneRecherche.getText(), "recherche");
		HashSet<Stagiaire> resultatRecherche = Recherche.chercherMultiCle(listeRecherche);
		TreeSet<Stagiaire> resultatRechercheTrie = new TreeSet<Stagiaire>(resultatRecherche);
		PanelGestionnaire.data.clear();
		PanelGestionnaire.data.addAll(FXCollections.observableArrayList(resultatRechercheTrie));
		changementLance = false;
	}
}
