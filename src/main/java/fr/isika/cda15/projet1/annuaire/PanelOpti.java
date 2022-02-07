package fr.isika.cda15.projet1.annuaire;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PanelOpti extends BorderPane{
	
	private static Stage popUpOpti;
	
	public static void PanelOpti() throws Exception{
		Button valider = new Button("Valider");
		Button boutonOpti = new Button("Optimiser");
		
		Label messageInfo = new Label();
		Label hauteurMinAvant = new Label("Hauteur minimum de l'arbre avant l'équilibrage : " + Integer.toString(ArbreStagiaire.hauteurMinimum()));
		Label hauteurMaxAvant = new Label("Hauteur maximum de l'arbre avant l'équilibrage : " + Integer.toString(ArbreStagiaire.hauteurMaximum()));
		Label hauteurMinApres = new Label();
		Label hauteurMaxApres = new Label();
		
		BorderPane maBP = new BorderPane();
		VBox hauteurAvant = new VBox();
		hauteurAvant.setAlignment(Pos.CENTER);
		VBox hauteurApres = new VBox();
		hauteurApres.setAlignment(Pos.CENTER);
		VBox contenuBas = new VBox();
		contenuBas.setAlignment(Pos.CENTER);
		
		boutonOpti.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event) {
				try {
					messageInfo.setText("L'équilibrage de l'arbre est en cours, veuillez patienter");
					Thread.sleep(500);
					ArbreStagiaire.construireArbreEquilibre();
					messageInfo.setText("L'équilibrage de l'arbre est terminé. De l'espace mémoire a également été récupéré");
					hauteurMinApres.setText("Hauteur minimum de l'arbre après l'équilibrage : " + Integer.toString(ArbreStagiaire.hauteurMinimum()));
					hauteurMaxApres.setText("Hauteur maximum de l'arbre après l'équilibrage : " + Integer.toString(ArbreStagiaire.hauteurMaximum()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		valider.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				popUpOpti.close();	
			}
		});
		
		hauteurAvant.getChildren().addAll(hauteurMinAvant, hauteurMaxAvant);
		hauteurApres.getChildren().addAll(hauteurMinApres, hauteurMaxApres);
		contenuBas.getChildren().addAll(messageInfo, hauteurApres, valider);
		contenuBas.setSpacing(10);
		
		maBP.setTop(hauteurAvant);
		maBP.setCenter(boutonOpti);
		maBP.setBottom(contenuBas);
		maBP.setPadding(new Insets(10,20,10,20));
	
		PanelOpti.popUpOpti = new Stage();
		popUpOpti.initModality(Modality.APPLICATION_MODAL);
		popUpOpti.setTitle("Optimisation");
		
		Scene theScene = new Scene (maBP, 500,200);
		popUpOpti.setScene(theScene);
		popUpOpti.showAndWait();
		
	}
}
