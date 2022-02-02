/**
 * 
 */
package fr.isika.cda15.projet1.annuaire;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class PanelGestionnaire extends BorderPane {
	TableView<Stagiaire> table = new TableView<Stagiaire>();
	
	public PanelGestionnaire (final Stage stage) throws Exception {
		try {
					
					ObservableList<Stagiaire> data = getStagiaireList();
					
					//Création de la table
					table.setEditable(true);
					
			        Label label = new Label("Liste des stagiaires");
			        label.setFont(new Font("Arial", 20));
			 
			        //Création des cinq colonnes
			        TableColumn<Stagiaire, String> prenomCol = 
			        		new TableColumn<Stagiaire, String>("Prénom");
			        prenomCol.setMinWidth(100);
			        //specifier un "cell factory" pour cette colonne.
			        prenomCol.setCellValueFactory(
			                new PropertyValueFactory<Stagiaire, String>("prenom"));
			        
			        TableColumn<Stagiaire, String> nomCol = new TableColumn<Stagiaire, String>("Nom");
			        nomCol.setMinWidth(100);
			        //spécifier une préférence de tri pour cette colonne
			        nomCol.setSortType(TableColumn.SortType.ASCENDING);
			      //specifier un "cell factory" pour cette colonne.
			        nomCol.setCellValueFactory(
			                new PropertyValueFactory<Stagiaire, String>("nom"));
			        
			        TableColumn<Stagiaire, String> promoCol = new TableColumn<Stagiaire, String>("Promotion");
			        promoCol.setMinWidth(100);
			      //specifier un "cell factory" pour cette colonne.
			        promoCol.setCellValueFactory(
			                new PropertyValueFactory<Stagiaire, String>("promo"));
			        
			        TableColumn<Stagiaire, String> anneeEntreeCol = new TableColumn<Stagiaire, String>("Admission");
			        anneeEntreeCol.setMinWidth(100);
			        anneeEntreeCol.setCellValueFactory(
			        		new PropertyValueFactory<Stagiaire, String>("anneeEntree"));
			        
			        TableColumn<Stagiaire, String> departementCol = new TableColumn<Stagiaire, String>("Département");
			        departementCol.setMinWidth(100);
			        departementCol.setCellValueFactory(
			        		new PropertyValueFactory<Stagiaire, String>("departement"));
			                
			        //On ajoute les trois colonnes à la table
			        table.getColumns().addAll(prenomCol, nomCol, promoCol, anneeEntreeCol, departementCol);
			        // TO DO remplir la table avec une liste observable
			        table.setItems(data);
			        
			        Button button = new Button("Ajouter");
			        button.setOnAction(new EventHandler<ActionEvent>() {
			            @Override
			            public void handle(ActionEvent e) {
			        		try {
								new PanelAjoutStagiaire(stage);
							} catch (Exception e3) {
								e3.printStackTrace();
							}
			            }
			        });
			        
			        Button button1 = new Button("Deconnexion");
			        button1.setOnAction(new EventHandler<ActionEvent>() {
			            @Override
			            public void handle(ActionEvent e) {
			            	try {
			            		new PanelConnexion(stage);
							} catch (Exception e2) {
								e2.printStackTrace();
							}
			            }
			        });
			        
			        BorderPane root = new BorderPane();
			        HBox hbox = new HBox();
			    	hbox.getChildren().addAll(button, button1);

			        //On place le label, la table et la zone de modification dans une VBox
			        VBox vbox = new VBox();
			        vbox.setSpacing(5);
			        vbox.setPadding(new Insets(10, 10, 10, 10));
			        vbox.getChildren().addAll(label, table, hbox);
			        PanelFiltre monPanelFiltre = new PanelFiltre(stage, table);
			        this.setCenter(vbox);
			        this.setRight(monPanelFiltre);
					Scene scene = new Scene(this,400,400);
//					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					stage.setTitle("Annuaire");
					stage.setWidth(500);
					stage.setHeight(700);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
		
	}
	
	private ObservableList<Stagiaire> getStagiaireList() {
		
		ArbreStagiaire monArbre = new ArbreStagiaire();
		monArbre.initArbre();
    	
    	List<Stagiaire> maList = new ArrayList<>();
    	maList = Recherche.parcoursStagiaire(monArbre);
		ObservableList<Stagiaire> list = FXCollections.observableArrayList(maList);
	    return list;
	  }
	
}
