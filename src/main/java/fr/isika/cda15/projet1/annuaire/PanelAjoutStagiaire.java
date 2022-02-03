package fr.isika.cda15.projet1.annuaire;

import fr.isika.cda15.projet1.annuaire.ArbreStagiaire;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PanelAjoutStagiaire extends BorderPane{
	
	private static Stage popUpStagiaire;
	
	private BorderPane panelPrincipal = this;
//	private Text titre = new Text("Ajout de stagiaire");
//	private Button btnAjouter = new Button("OK");
//	private Button btnAnnuler = new Button("Annuler");
//	
//	//Création de la zone éditable
//    private TextField prenomTxt = new TextField();
//    private TextField nomTxt = new TextField();
//    private TextField promoTxt = new TextField();  
//    private TextField anneeEntreeTxt = new TextField();  
//    private TextField departementTxt = new TextField();
//
//	
//    private HBox panelTitre = new HBox();
//	private BorderPane borderCenter = new BorderPane();
//	private GridPane gridPane = new GridPane();
//	
//	private HBox hbBtn = new HBox();  
	
	public static void PanelAjoutStagiaire(Stagiaire stagiaire) throws Exception {
		PanelAjoutStagiaire.popUpStagiaire = new Stage();
		popUpStagiaire.initModality(Modality.APPLICATION_MODAL);
		popUpStagiaire.setTitle("Édition stagiaire");
		BorderPane principalBorder = inscriptionBorder();
		addUIControls(principalBorder, stagiaire);
		Scene theScene = new Scene(principalBorder, 800, 500);
		popUpStagiaire.setScene(theScene);
		popUpStagiaire.showAndWait();
	}
	
	private static BorderPane inscriptionBorder() {
		BorderPane principalBorder = new BorderPane();
		
		return principalBorder;
	}
	
	private static void addUIControls(BorderPane principalBorder, Stagiaire stagiaire) {
		
		Text titre = new Text("Ajout de stagiaire");
		Button btnAjouter = new Button("OK");
		Button btnAnnuler = new Button("Annuler");
		
		//Création de la zone éditable
	    TextField prenomTxt = new TextField();
	    TextField nomTxt = new TextField();
	    TextField promoTxt = new TextField();  
	    TextField anneeEntreeTxt = new TextField();  
	    TextField departementTxt = new TextField();

		
	    HBox panelTitre = new HBox();
		BorderPane borderCenter = new BorderPane();
		GridPane gridPane = new GridPane();
		
		HBox hbBtn = new HBox();
		
		if (stagiaire.getNom() == null && stagiaire.getPrenom() == null  ) {
					
				    prenomTxt.setPromptText("Prénom");
				    nomTxt.setPromptText("Nom");
				    promoTxt.setPromptText("Promotion");
				    anneeEntreeTxt.setPromptText("Admission");
				    departementTxt.setPromptText("Département");
					
					btnAjouter.setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent arg0) {
							try {
								
								if (nomTxt.getText().isEmpty() || prenomTxt.getText().isEmpty() || departementTxt.getText().isEmpty() || promoTxt.getText().isEmpty() || anneeEntreeTxt.getText().isEmpty()) {
									showAlert(Alert.AlertType.ERROR, principalBorder.getScene().getWindow(), "Erreur", "Veuillez remplir tous les champs pour continuer");
									return;
								}
								Stagiaire newStagiaire = new Stagiaire(nomTxt.getText(), prenomTxt.getText(), 
								departementTxt.getText(), promoTxt.getText(), anneeEntreeTxt.getText());
								ArbreStagiaire.ajouter(newStagiaire);
								popUpStagiaire.close();
//								new PanelGestionnaire(stage);
							} catch (Exception e) {
								e.printStackTrace();
							}	
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
				} else {
					
					prenomTxt.setText(stagiaire.getPrenom());
				    nomTxt.setText(stagiaire.getNom());
				    promoTxt.setText(stagiaire.getPromo());
				    anneeEntreeTxt.setText(stagiaire.getAnneeEntree());
				    departementTxt.setText(stagiaire.getDepartement());
					
					/**
					 * Bouton permettant de rajouter les infos du nouveau stagiaire
					 * Récupération des getText()
					 * Instantiation d'un nouveau stagiaire : new Stagiaire
					 * 
					 */
					btnAjouter.setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent arg0) {
								Stagiaire newStagiaire = new Stagiaire(nomTxt.getText(), prenomTxt.getText(), 
																	departementTxt.getText(), promoTxt.getText(), anneeEntreeTxt.getText());
								if (stagiaire.compareTo(newStagiaire) == 0) {
									showAlert(Alert.AlertType.ERROR, principalBorder.getScene().getWindow(), "Erreur", "Veuillez entrer le nom");
									return;
								}
								ArbreStagiaire.modifier(stagiaire, newStagiaire);
								popUpStagiaire.close();
//								new PanelGestionnaire(stage);
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
				
				btnAnnuler.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						try {
							showAlert(Alert.AlertType.WARNING, principalBorder.getScene().getWindow(), "Attention", "Aucune modification ne sera enregisitrée");
							popUpStagiaire.close();
//							new PanelGestionnaire(stage);
						} catch (Exception e) {
							e.printStackTrace();
						}	
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
		
				principalBorder.setTop(panelTitre);
				principalBorder.setCenter(borderCenter);
				principalBorder.setBottom(hbBtn);
	}
	
	
//	*****************************************************************************************
//	public PanelAjoutStagiaire(final Stage stage, Stagiaire stagiaire) throws Exception {
//		
//		if (stagiaire.getNom() == null && stagiaire.getPrenom() == null  ) {
//			
//		    prenomTxt.setPromptText("Prénom");
//		    nomTxt.setPromptText("Nom");
//		    promoTxt.setPromptText("Promotion");
//		    anneeEntreeTxt.setPromptText("Admission");
//		    departementTxt.setPromptText("Département");
//			
//			btnAjouter.setOnAction(new EventHandler<ActionEvent>() {
//				
//				@Override
//				public void handle(ActionEvent arg0) {
//					try {
//						Stagiaire newStagiaire = new Stagiaire(nomTxt.getText(), prenomTxt.getText(), 
//						departementTxt.getText(), promoTxt.getText(), anneeEntreeTxt.getText());
//						ArbreStagiaire.ajouter(newStagiaire);
//						new PanelGestionnaire(stage);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}	
//				}
//			});
//		} else {
//			
//			prenomTxt.setText(stagiaire.getPrenom());
//		    nomTxt.setText(stagiaire.getNom());
//		    promoTxt.setText(stagiaire.getPromo());
//		    anneeEntreeTxt.setText(stagiaire.getAnneeEntree());
//		    departementTxt.setText(stagiaire.getDepartement());
//			
//			/**
//			 * Bouton permettant de rajouter les infos du nouveau stagiaire
//			 * Récupération des getText()
//			 * Instantiation d'un nouveau stagiaire : new Stagiaire
//			 * 
//			 */
//			btnAjouter.setOnAction(new EventHandler<ActionEvent>() {
//				
//				@Override
//				public void handle(ActionEvent arg0) {
//					try {
//						Stagiaire newStagiaire = new Stagiaire(nomTxt.getText(), prenomTxt.getText(), 
//						departementTxt.getText(), promoTxt.getText(), anneeEntreeTxt.getText());
//						ArbreStagiaire.modifier(stagiaire, newStagiaire);
//						new PanelGestionnaire(stage);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}	
//				}
//			});
//		}
//		
//		btnAnnuler.setOnAction(new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent event) {
//				try {
//					new PanelGestionnaire(stage);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}	
//			}			
//		});
//		
//		hbBtn.getChildren().addAll(btnAjouter, btnAnnuler);
//		hbBtn.setSpacing(3);
//		gridPane.add(nomTxt, 0, 0);
//		gridPane.add(prenomTxt, 0, 1);
//		gridPane.add(promoTxt, 0, 2);
//		gridPane.add(departementTxt, 0, 4);
//		gridPane.add(anneeEntreeTxt, 0, 5);
//		
//		panelTitre.getChildren().add(titre);
//		panelTitre.setAlignment(Pos.CENTER);
//		panelTitre.setMinHeight(50);
//
//		gridPane.setAlignment(Pos.CENTER);
//		borderCenter.setCenter(gridPane);
//		borderCenter.setMinHeight(100);
//
//		panelPrincipal.setTop(panelTitre);
//		panelPrincipal.setCenter(borderCenter);
//		panelPrincipal.setBottom(hbBtn);
//		
//		Scene scene = new Scene(panelPrincipal, 800, 550);
//		stage.setTitle("Annuaire des stagiaires");
//		stage.setScene(scene);
//		stage.show();
//	}
}
