package fr.isika.cda15.projet1.annuaire;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PanelAjoutStagiaire extends BorderPane{
	
	private BorderPane panelPrincipal = this;
	private Text titre = new Text("Ajout de stagiaire");
	private Button btnAjouter = new Button("Ajouter");
	private Button btnAnnuler = new Button("Annuler");
	
	//Création de la zone éditable
    private TextField prenomTxt = new TextField();
//    prenomTxt.setPromptText("Prénom");
//    prenomTxt.setMaxWidth();
    
    private TextField nomTxt = new TextField();
//    nomTxt.setMaxWidth(nomCol.getPrefWidth());
//    nomTxt.setPromptText("Nom");
//    
    private TextField promoTxt = new TextField();
//    promoTxt.setMaxWidth(promoCol.getPrefWidth());
//    promoTxt.setPromptText("Promotion");
//    
    private TextField anneeEntreeTxt = new TextField();
//    anneeEntreeTxt.setMaxWidth(anneeEntreeCol.getPrefWidth());
//    anneeEntreeTxt.setPromptText("Admission");
//    
    private TextField departementTxt = new TextField();
//    departementTxt.setMaxWidth(departementCol.getPrefWidth());
//    departementTxt.setPromptText("Département");
	
    private HBox panelTitre = new HBox();
	private BorderPane borderCenter = new BorderPane();
	private GridPane gridPane = new GridPane();

//	Stagiaire stag1 = new Stagiaire("LACROIX", "Pascale", "91", "BOBI 5", "2008");
//	Stagiaire newStagiaire = new Stagiaire(nomTxt.getText(), prenomTxt.getText(), 
//			departementTxt.getText(), promoTxt.getText(), anneeEntreeTxt.getText());
//	data.add(newStagiaire);
//	hbox.getChildren().addAll(prenomTxt, nomTxt, promoTxt, anneeEntreeTxt, departementTxt, button, button1);
//    hbox.setSpacing(3);
	
	public PanelAjoutStagiaire(final Stage stage) throws Exception {

		btnAjouter.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
//					new GestionAnnuaire(stage);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
		panelTitre.getChildren().add(titre);
		panelTitre.setAlignment(Pos.CENTER);
		panelTitre.setMinHeight(50);

		gridPane.setAlignment(Pos.CENTER);
		borderCenter.setCenter(gridPane);
		borderCenter.setMinHeight(100);

		panelPrincipal.setTop(panelTitre);
		panelPrincipal.setCenter(borderCenter);
		
		Scene scene = new Scene(panelPrincipal, 800, 550);
		stage.setTitle("Annuaire des stagiaires");
		stage.setScene(scene);
		stage.show();
	}
}
