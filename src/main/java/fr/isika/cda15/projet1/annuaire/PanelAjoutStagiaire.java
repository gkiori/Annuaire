package fr.isika.cda15.projet1.annuaire;

import fr.isika.cda15.projet1.annuaire.ArbreStagiaire;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PanelAjoutStagiaire extends BorderPane{
	
	private BorderPane panelPrincipal = this;
	private Text titre = new Text("Ajout de stagiaire");
	private Button btnAjouter = new Button("OK");
	private Button btnAnnuler = new Button("Annuler");
	
	//Création de la zone éditable
    private TextField prenomTxt = new TextField();
    private TextField nomTxt = new TextField();
    private TextField promoTxt = new TextField();  
    private TextField anneeEntreeTxt = new TextField();  
    private TextField departementTxt = new TextField();

	
    private HBox panelTitre = new HBox();
	private BorderPane borderCenter = new BorderPane();
	private GridPane gridPane = new GridPane();
	
	private HBox hbBtn = new HBox();

//	Stagiaire stag1 = new Stagiaire("LACROIX", "Pascale", "91", "BOBI 5", "2008");
//	Stagiaire newStagiaire = new Stagiaire(nomTxt.getText(), prenomTxt.getText(), 
//			departementTxt.getText(), promoTxt.getText(), anneeEntreeTxt.getText());
//	data.add(newStagiaire);
//	hbox.getChildren().addAll(prenomTxt, nomTxt, promoTxt, anneeEntreeTxt, departementTxt, button, button1);
//    
	
	public PanelAjoutStagiaire(final Stage stage, Stagiaire stagiaire) throws Exception {
		
		if (stagiaire.getNom() == null && stagiaire.getPrenom() == null  ) {
			
		    prenomTxt.setPromptText("Prénom");
		    nomTxt.setPromptText("Nom");
		    promoTxt.setPromptText("Promotion");
		    anneeEntreeTxt.setPromptText("Admission");
		    departementTxt.setPromptText("Département");
			
	//	    prenomTxt.setMaxWidth();
	//	    nomTxt.setMaxWidth(nomCol.getPrefWidth());
	//	    promoTxt.setMaxWidth(promoCol.getPrefWidth());
	//	    anneeEntreeTxt.setMaxWidth(anneeEntreeCol.getPrefWidth());
	//	    departementTxt.setMaxWidth(departementCol.getPrefWidth());
			
//			btnAnnuler.setOnAction(new EventHandler<ActionEvent>() {
//	
//				@Override
//				public void handle(ActionEvent event) {
//					try {
//						new PanelGestionnaire(stage);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}	
//				}			
//			});
			
			/**
			 * Bouton permettant de rajouter les infos du nouveau stagiaire
			 * Récupération des getText()
			 * Instantiation d'un nouveau stagiaire : new Stagiaire
			 * 
			 */
			btnAjouter.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					try {
						Stagiaire newStagiaire = new Stagiaire(nomTxt.getText(), prenomTxt.getText(), 
						departementTxt.getText(), promoTxt.getText(), anneeEntreeTxt.getText());
						ArbreStagiaire.ajouter(newStagiaire);
						new PanelGestionnaire(stage);
					} catch (Exception e) {
						e.printStackTrace();
					}	
				}
			});
		} else {
			
			prenomTxt.setText(stagiaire.getPrenom());
		    nomTxt.setText(stagiaire.getNom());
		    promoTxt.setText(stagiaire.getPromo());
		    anneeEntreeTxt.setText(stagiaire.getAnneeEntree());
		    departementTxt.setText(stagiaire.getDepartement());
			
	//	    prenomTxt.setMaxWidth();
	//	    nomTxt.setMaxWidth(nomCol.getPrefWidth());
	//	    promoTxt.setMaxWidth(promoCol.getPrefWidth());
	//	    anneeEntreeTxt.setMaxWidth(anneeEntreeCol.getPrefWidth());
	//	    departementTxt.setMaxWidth(departementCol.getPrefWidth());
			
//			btnAnnuler.setOnAction(new EventHandler<ActionEvent>() {
//	
//				@Override
//				public void handle(ActionEvent event) {
//					try {
//						new PanelGestionnaire(stage);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}	
//				}			
//			});
//			
			/**
			 * Bouton permettant de rajouter les infos du nouveau stagiaire
			 * Récupération des getText()
			 * Instantiation d'un nouveau stagiaire : new Stagiaire
			 * 
			 */
			btnAjouter.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					try {
						Stagiaire newStagiaire = new Stagiaire(nomTxt.getText(), prenomTxt.getText(), 
						departementTxt.getText(), promoTxt.getText(), anneeEntreeTxt.getText());
						ArbreStagiaire.modifier(stagiaire, newStagiaire);
						new PanelGestionnaire(stage);
					} catch (Exception e) {
						e.printStackTrace();
					}	
				}
			});
		}
		
		btnAnnuler.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					new PanelGestionnaire(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}			
		});
		
		hbBtn.getChildren().addAll(btnAjouter, btnAnnuler);
		hbBtn.setSpacing(3);
		gridPane.add(nomTxt, 0, 0);
		gridPane.add(prenomTxt, 0, 1);
		gridPane.add(promoTxt, 0, 2);
		gridPane.add(departementTxt, 0, 4);
		gridPane.add(anneeEntreeTxt, 0, 5);
		
		panelTitre.getChildren().add(titre);
		panelTitre.setAlignment(Pos.CENTER);
		panelTitre.setMinHeight(50);

		gridPane.setAlignment(Pos.CENTER);
		borderCenter.setCenter(gridPane);
		borderCenter.setMinHeight(100);

		panelPrincipal.setTop(panelTitre);
		panelPrincipal.setCenter(borderCenter);
		panelPrincipal.setBottom(hbBtn);
		
		Scene scene = new Scene(panelPrincipal, 800, 550);
		stage.setTitle("Annuaire des stagiaires");
		stage.setScene(scene);
		stage.show();
	}
}
