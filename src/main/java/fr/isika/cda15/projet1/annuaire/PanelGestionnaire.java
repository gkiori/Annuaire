/**
 * 
 */
package fr.isika.cda15.projet1.annuaire;

import fr.isika.cda15.projet1.annuaire.ArbreStagiaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class PanelGestionnaire extends BorderPane {
	
//	private static TableView<Stagiaire> table = new TableView<Stagiaire>();
	protected static ObservableList<Stagiaire> data;
	
	public PanelGestionnaire (final Stage stage) throws Exception {
		try {
			
					TableView<Stagiaire> table = new TableView<Stagiaire>();
					
//					data = getStagiaireList();
					
					ContextMenu contextMenu = new ContextMenu();										
			        MenuItem itemModif = new MenuItem("Modifier");
			        MenuItem itemSupp = new MenuItem("Supprimer");
					
					//Création de la table
					table.setEditable(false);
					table.setStyle("-fx-background-color: #F8FBFF;\n"
								 + "-fx-background-insets: 0;\n"
								 + "-fx-padding: 10;");
					table.setPrefHeight(850);
					table.setPrefWidth(850);
					table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

					
					
			        Label titreLbl = new Label("Liste des stagiaires");
			        titreLbl.setFont(Font.font("Verdana", FontWeight.BOLD, 32));
			 
			        TableColumn<Stagiaire, String> prenomCol = 
			        		new TableColumn<Stagiaire, String>("Prénom");
			        prenomCol.setMinWidth(table.getPrefWidth()/5);
			        prenomCol.setCellValueFactory(
			                new PropertyValueFactory<Stagiaire, String>("prenom"));
			        
			        TableColumn<Stagiaire, String> nomCol = new TableColumn<Stagiaire, String>("Nom");
			        nomCol.setMinWidth(table.getPrefWidth()/5);
			        nomCol.setSortType(TableColumn.SortType.ASCENDING);
			        nomCol.setCellValueFactory(
			                new PropertyValueFactory<Stagiaire, String>("nom"));
			        
			        TableColumn<Stagiaire, String> promoCol = new TableColumn<Stagiaire, String>("Promotion");
			        promoCol.setMinWidth(table.getPrefWidth()/5);
			        promoCol.setCellValueFactory(
			                new PropertyValueFactory<Stagiaire, String>("promo"));
			        
			        TableColumn<Stagiaire, String> anneeEntreeCol = new TableColumn<Stagiaire, String>("Admission");
			        anneeEntreeCol.setMinWidth(table.getPrefWidth()/5-55);
			        anneeEntreeCol.setCellValueFactory(
			        		new PropertyValueFactory<Stagiaire, String>("anneeEntree"));
			        
			        TableColumn<Stagiaire, String> departementCol = new TableColumn<Stagiaire, String>("Département");
			        departementCol.setMinWidth(table.getPrefWidth()/5);
			        departementCol.setCellValueFactory(
			        		new PropertyValueFactory<Stagiaire, String>("departement"));
			                
			        //On ajoute les trois colonnes à la table
			        table.getColumns().addAll(nomCol, prenomCol, promoCol, anneeEntreeCol, departementCol);
			        // TO DO remplir la table avec une liste observable
			        table.setItems(data);
			        
			        Button button = new Button("Ajouter");
			      
			        String profilType = PanelConnexion.getUser().getProfil();
        			if (profilType.compareTo("Apprenant") == 0 ) {
						button.setDisable(true);
						itemModif.setDisable(true);
						itemSupp.setDisable(true);
						
					}
        			button.setStyle("-fx-background-color: \n"
        					+ "        #000000,\n"
        					+ "        linear-gradient(#7ebcea, #2f4b8f),\n"
        					+ "        linear-gradient(#426ab7, #263e75),\n"
        					+ "        linear-gradient(#395cab, #223768);\n"
        					+ "    -fx-background-insets: 0,1,2,3;\n"
        					+ "    -fx-background-radius: 6, 5;\n"
        					+ "    -fx-padding: 12 30 12 30;\n"
        					+ "    -fx-text-fill: white;\n"
        					+ "    -fx-font-size: 14px;");
			        button.setOnAction(new EventHandler<ActionEvent>() {
			            @Override
			            public void handle(ActionEvent e) {
			        		try {
			        			
			        			Stagiaire stagiaire = new Stagiaire();
			        			PanelAjoutStagiaire.PanelAjoutStagiaire(stagiaire);
							} catch (Exception e3) {
								e3.printStackTrace();
							}
			            }
			        });
			        
			        
			        
					table.setOnMouseClicked(evt -> {
					    if (evt.getButton() == MouseButton.SECONDARY) {
					        Set<Node> rows = table.lookupAll(".table-row-cell");
					        Optional<Cell> n = rows.stream().map(r -> (Cell) r).filter(Cell::isSelected).findFirst();
					
					        if (n.isPresent()) {
					            Optional<Node> node = n.get().getChildrenUnmodifiable().stream()
					                    .filter(c -> c instanceof TableCell && ((TableCell) c).getTableColumn() == prenomCol)
					                    .findFirst();
					
					            if (node.isPresent()) {
					                Node cell = node.get();
					                Bounds b = cell.getLayoutBounds();
					                contextMenu.show(cell, Side.BOTTOM, b.getWidth() / 2, b.getHeight() / -2);
					            }
					        }
					    }
					});
					
					Label contextMenuResultLbl = new Label();
					
					itemModif.setOnAction(new EventHandler<ActionEvent>() {
						 
			            @Override
			            public void handle(ActionEvent event) {
			            	//TODO Récuperation des infos de l'item selectionner
			            	//TODO Passage au panel modification
			            	Stagiaire stagiaireAModif = table.getSelectionModel().getSelectedItem();
			            	try {
			            		PanelAjoutStagiaire.PanelAjoutStagiaire(stagiaireAModif);
							} catch (Exception e) {
								e.printStackTrace();
							}
			            	contextMenuResultLbl.setText("Vous avez choisi de modifier la cellule" + table.getSelectionModel().getSelectedIndex());       
			                
			            }
			        });
			       
			        itemSupp.setOnAction(new EventHandler<ActionEvent>() {
			 
			            @Override
			            public void handle(ActionEvent event) {
			            	//TODO Recupération des infos de l'item slectionner
			            	//TODO appel de la fonction suppression 	
			            	Stagiaire stagiaireASupp = table.getSelectionModel().getSelectedItem();
			            	ArbreStagiaire.supprimer(stagiaireASupp);
			            	data.remove(stagiaireASupp);
			            	contextMenuResultLbl.setText("Vous avez choisi de supprimer la cellule " + stagiaireASupp);
			               
			            }
			        });
			        		        
			        contextMenu.getItems().addAll(itemModif, itemSupp);
			        
			        HBox hbTop = new HBox();
			        hbTop.getChildren().addAll(titreLbl, button);
			        hbTop.setPadding(new Insets(20, 10, 40, 10));
			        hbTop.setSpacing(450);
			        hbTop.setStyle("-fx-background-color : #F8FBFF;");
			        
			        BorderPane root = new BorderPane();
			        HBox hbox = new HBox();
			    	hbox.getChildren().addAll( contextMenuResultLbl);

			        //On place le label, la table et la zone de modification dans une VBox
			        VBox vbox = new VBox();
			        vbox.setSpacing(5);
			        vbox.setPadding(new Insets(10, 10, 10, 10));
			        vbox.getChildren().addAll(table, hbox);
			        vbox.setStyle("-fx-background-color : #F8FBFF;");
			        
			        PanelFiltre monPanelFiltre = new PanelFiltre(stage);
			        PanelGeneralInfos monPanelInfos = new PanelGeneralInfos(stage);
			        
			        BorderPane panelBottom = new BorderPane();
			        panelBottom.setCenter(vbox);
			        panelBottom.setRight(monPanelFiltre);
			        
			        BorderPane panelCentral = new BorderPane();
			        panelCentral.setTop(hbTop);
			        panelCentral.setCenter(panelBottom);
			        
			        this.setCenter(panelCentral);
//			        this.setRight(monPanelFiltre);
			        this.setLeft(monPanelInfos);
					Scene scene = new Scene(this,1050, 1259);
//					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					
					stage.setTitle("Annuaire");
					stage.setWidth(1440);
			        stage.setHeight(1024);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
		
	}
	
//	private ObservableList<Stagiaire> getStagiaireList() {
//		
//		ArbreStagiaire monArbre = new ArbreStagiaire();
//		monArbre.initArbre();
//    	
//    	List<Stagiaire> maList = new ArrayList<>();
//    	maList = Recherche.parcoursStagiaire(monArbre);
//		ObservableList<Stagiaire> list = FXCollections.observableArrayList(maList);
//	    return list;
//	  }

//	public static TableView<Stagiaire> getTable() {
//		return table;
//	}
//
//	public void setTable(TableView<Stagiaire> table) {
//		this.table = table;
//	}

	public static ObservableList<Stagiaire> getData() {
		return data;
	}

	public static void setData(ObservableList<Stagiaire> data) {
		PanelGestionnaire.data = data;
	}
	
//	public static void reInitTable() {
//		table.getItems().clear();
//	}
	
}
