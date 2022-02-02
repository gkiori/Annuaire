package fr.isika.cda15.projet1.annuaire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.controlsfx.control.CheckComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableView;
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
	
	CheckComboBox<String> menuPromo = new CheckComboBox<String>();
	CheckComboBox<String> menuDepartement = new CheckComboBox<String>();
	CheckComboBox<String> menuAnneeEntree = new CheckComboBox<String>();
	
	TreeSet<String> vuePromo = new TreeSet<String>();
	TreeSet<String> vueDepartement = new TreeSet<String>();
	TreeSet<String> vueAnneeEntree = new TreeSet<String>();
	
	public PanelFiltre(final Stage stage, BorderPane Parent) throws Exception{
		ArbreStagiaire monArbre = new ArbreStagiaire();
		monArbre.initArbre();
		
		vuePromo.addAll(Recherche.getListePromo(monArbre));
		vueDepartement.addAll(Recherche.getListeDepartement(monArbre));
		vueAnneeEntree.addAll(Recherche.getListeAnneeEntree(monArbre));
		
		ObservableList<String> listePromo = FXCollections.observableArrayList();
		for(String promo : vuePromo) listePromo.add(promo);
		ObservableList<String> listeDepartement = FXCollections.observableArrayList();
		for(String departement : vueDepartement) listeDepartement.add(departement);
		ObservableList<String> listeAnneeEntree = FXCollections.observableArrayList();
		for(String anneeEntree : vueAnneeEntree) listeAnneeEntree.add(anneeEntree);
		
		menuPromo.getItems().addAll(listePromo);
		menuPromo.setTitle("Promotion");
		menuDepartement.getItems().addAll(listeDepartement);
		menuDepartement.setTitle("Département");
		menuAnneeEntree.getItems().addAll(listeAnneeEntree);
		menuAnneeEntree.setTitle("Année d'entrée");
		
		Map<String, String> listeRecherche = new HashMap<String, String>();
		affichageResultat.setOnAction(new EventHandler<ActionEvent>(){
			@Override
            public void handle(ActionEvent e) {
        		listeRecherche.put(menuPromo.getCheckModel().getCheckedItems().toString(), "promo");
        		listeRecherche.put(menuDepartement.getCheckModel().getCheckedItems().toString(), "departement");
        		listeRecherche.put(menuAnneeEntree.getCheckModel().getCheckedItems().toString(), "anneeEntree");
        		List<Stagiaire> resultatRecherche = Recherche.chercherMultiCle(listeRecherche, monArbre);
        		ObservableList<Stagiaire> vueResultatRecherche = FXCollections.observableArrayList(resultatRecherche);
        		//parent.setData = vueResultatRecherche;
            }
		});
		VBox orgVbox = new VBox();
		orgVbox.getChildren().addAll(zoneRecherche, menuPromo, menuDepartement, menuAnneeEntree, zoneRechercheNom, zoneRecherchePrenom);
		this.setTop(titre);
		this.setCenter(orgVbox);
		this.setBottom(affichageResultat);
		Scene scene = new Scene(this);
		stage.setScene(scene);
	}
}
