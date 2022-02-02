package fr.isika.cda15.projet1.annuaire;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.controlsfx.control.CheckComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PanelFiltre extends BorderPane{
	Label titre = new Label("Filtres");
	TextField zoneRecherche = new TextField("Recherche");
	TextField zoneRechercheNom = new TextField("Nom");
	TextField zoneRecherchePrenom = new TextField("Prénom");
	Button affichageResultat = new Button("Afficher les résultats");
	
	MenuButton menuPromo = new MenuButton("Promotion");
	MenuButton menuDepartement = new MenuButton("Département");
	MenuButton menuAnneeEntree = new MenuButton("Année d'entrée");
	
	Set<String> vuePromo = new HashSet<String>();
	Set<String> vueDepartement = new HashSet<String>();
	Set<String> vueAnneeEntree = new HashSet<String>();
	
	public PanelFiltre(final Stage stage) throws Exception{
		ArbreStagiaire monArbre = new ArbreStagiaire();
		
		vuePromo.addAll(Recherche.getListePromo(monArbre));
		vueDepartement.addAll(Recherche.getListeDepartement(monArbre));
		vueAnneeEntree.addAll(Recherche.getListeAnneeEntree(monArbre));
		
		ObservableList<CustomMenuItem> listePromo = FXCollections.observableArrayList();
		for(String promo : vuePromo) {
			CustomMenuItem nouvelleCheckBox = new CustomMenuItem(new CheckBox(promo));
			nouvelleCheckBox.setHideOnClick(false);
			listePromo.add(nouvelleCheckBox);
		}
		ObservableList<CustomMenuItem> listeDepartement = FXCollections.observableArrayList();
		for(String departement : vueDepartement) {
			CustomMenuItem nouvelleCheckBox = new CustomMenuItem(new CheckBox(departement));
			nouvelleCheckBox.setHideOnClick(false);
			listeDepartement.add(nouvelleCheckBox);
		}
		ObservableList<CustomMenuItem> listeAnneeEntree = FXCollections.observableArrayList();
		for(String anneeEntree : vueAnneeEntree) {
			CustomMenuItem nouvelleCheckBox = new CustomMenuItem(new CheckBox(anneeEntree));
			nouvelleCheckBox.setHideOnClick(false);
			listeAnneeEntree.add(nouvelleCheckBox);
		}
		menuPromo.getItems().addAll(listePromo);
		menuDepartement.getItems().addAll(listeDepartement);
		menuAnneeEntree.getItems().addAll(listeAnneeEntree);
		
		VBox orgVbox = new VBox();
		orgVbox.getChildren().addAll(zoneRecherche, menuPromo, menuDepartement, menuAnneeEntree, zoneRechercheNom, zoneRecherchePrenom);
		this.setTop(titre);
		this.setCenter(orgVbox);
		this.setBottom(affichageResultat);
		Scene scene = new Scene(this);
		stage.setScene(scene);
	}
}
