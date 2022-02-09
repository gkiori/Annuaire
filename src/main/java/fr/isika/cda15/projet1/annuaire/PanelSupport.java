package fr.isika.cda15.projet1.annuaire;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PanelSupport {
	
	private static final String PATH_RESOURCES = "src/main/resources/";
	private static Stage popUpSupp;
	
	public static void PanelSupport() {
		
		PanelSupport.popUpSupp = new Stage();
		popUpSupp.initModality(Modality.APPLICATION_MODAL);
		popUpSupp.setTitle("Support application");
		BorderPane principalBorder;
		
		try {
			principalBorder = suppBorder();
			addUIControls(principalBorder);
			Scene theScene = new Scene(principalBorder, 600, 200);
			popUpSupp.setScene(theScene);
			popUpSupp.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static BorderPane suppBorder() {
		BorderPane principalBorder = new BorderPane();
		principalBorder.setStyle("-fx-background-color : #EFF6FF");
		return principalBorder;
	}
	
	private static void addUIControls(BorderPane principalBorder) throws IOException {
		
		Image image = new Image(new FileInputStream(PATH_RESOURCES + "imageTeam.png"));
		ImageView img = new ImageView();
		img.setImage(image);
		img.setFitHeight(50);
		img.setFitWidth(200);
		
		Label textSupp = new Label();
		textSupp.setText("Pour toute demande de support ou besoin d’ouverture de droit \"enseignant\",\n"
				+ "contactez le service informatique ISIKA à l’adresse suivante : SI@isika.fr.\n\n"
				+ "Nous vous réponderons dans un délai de 3h.");
		
		VBox vbSupp = new VBox();
		vbSupp.getChildren().addAll(textSupp, img);
		vbSupp.setSpacing(20);
		vbSupp.setAlignment(Pos.CENTER);
		principalBorder.setCenter(vbSupp);
		
	}
	
}
