package fr.isika.cda15.projet1.annuaire;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PanelGeneralInfos extends BorderPane {
	
	private HBox hbUser = new HBox();
	private VBox vbUser = new VBox();
	private ImageView imgUser = new ImageView();
	private Label lblBienvenue = new Label("Bienvenue");
	private Label lblUserName = new Label();
	
	private VBox vbMiddle = new VBox();
	private Button rechBtn = new Button("Rechercher");
	private Button listBtn = new Button("Liste des stagiaires");
	private Button imprBtn = new Button("Impression");
	private Button infoBtn = new Button("Information");
	
	private VBox vbOut = new VBox();
	private Button helpBtn = new Button("Support");
	private Button decoBtn = new Button("Déconnexion");
	
	public PanelGeneralInfos(final Stage stage) {
		
		
		rechBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					//Direction vers le panale filtre
//					new PanelFiltre(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		listBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// Appel la fonction d'initialisation
				try {
					PanelGestionnaire.setData(PanelConnexion.initPanelGestionnaire());
					new PanelGestionnaire(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
        decoBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	try {
            		new PanelConnexion(stage);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
            }
        });
		
		vbUser.getChildren().addAll(lblBienvenue, lblUserName);
		hbUser.getChildren().addAll(imgUser, vbUser);
		
		vbMiddle.getChildren().addAll(rechBtn, listBtn, imprBtn, infoBtn);
		
		vbOut.getChildren().addAll(helpBtn, decoBtn);
		
		this.setPadding(new Insets(20,20,20,10));
		this.setTop(hbUser);
		this.setCenter(vbMiddle);
		this.setBottom(vbOut);
		Scene scene = new Scene(this);
		stage.setScene(scene);
		
	}
	
}
