package fr.isika.cda15.projet1.annuaire;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PanelFiltre extends BorderPane{
	
	Label titre = new Label("Filtres");
	TextField zoneRecherche = new TextField();
	TextField zoneRechercheNom = new TextField();
	TextField zoneRecherchePrenom = new TextField();
	Button reinitialisationFiltre = new Button("Réinitialiser les filtres");
	
	CheckComboBox<String> menuPromo = new CheckComboBox<String>();
	CheckComboBox<String> menuDepartement = new CheckComboBox<String>();
	CheckComboBox<String> menuAnneeEntree = new CheckComboBox<String>();
	
	TreeSet<String> vuePromo = new TreeSet<String>();
	TreeSet<String> vueDepartement = new TreeSet<String>();
	TreeSet<String> vueAnneeEntree = new TreeSet<String>();
	
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
		
		ChangeListener<String> changementZoneEcriture = new ChangeListener<String>(){
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				changementRecherche();
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
		
		VBox orgVbox = new VBox();
		orgVbox.getChildren().addAll(zoneRecherche, menuPromo, menuDepartement, menuAnneeEntree, zoneRechercheNom, zoneRecherchePrenom);
		orgVbox.setPadding(new Insets(20, 10, 20, 10));
		orgVbox.setSpacing(10);
		this.setTop(titre);
		this.setCenter(orgVbox);
		this.setBottom(reinitialisationFiltre);
		Scene scene = new Scene(this);
		stage.setScene(scene);
	}
	
	//________________________________________________________________________
	public void changementRecherche() {
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
		List<Stagiaire> resultatRecherche = Recherche.chercherMultiCle(listeRecherche);
		PanelGestionnaire.data.clear();
		PanelGestionnaire.data.addAll(FXCollections.observableArrayList(resultatRecherche));
	}
	//________________________________________________________________________
}
