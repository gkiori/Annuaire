package fr.isika.cda15.projet1.annuaire;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
//import javafx.scene.web.HTMLEditor;
//import javafx.scene.web.WebEngine;
//import javafx.scene.web.WebView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PanelUserDoc extends BorderPane {
	
	private static final String PATH_RESOURCES = "src/main/resources/";
	private static Stage popUpDoc;
	private static int pageToShow = 0;
	
	public static void PanelUserDoc() {
		
		PanelUserDoc.popUpDoc = new Stage();
		popUpDoc.initModality(Modality.APPLICATION_MODAL);
		popUpDoc.setTitle("Documentation utilisateur");
		BorderPane principalBorder;
		try {
			principalBorder = docBorder();
			addUIControls(principalBorder);
			Scene theScene = new Scene(principalBorder, 700, 1024);
			popUpDoc.setScene(theScene);
			popUpDoc.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static BorderPane docBorder() {
		
		BorderPane principalBorder = new BorderPane();
//		VBox vbDoc = new VBox();
//		vbDoc.setOnScroll(null);
//		vbDoc.getChildren().add(img1);
//		principalBorder.setCenter(vbDoc);
		return principalBorder;
	}
	
	private static void addUIControls (BorderPane principalBorber) throws IOException  {
		
		Image image1 = new Image (new FileInputStream(PATH_RESOURCES + "Documentation1.png"));
		Image image2 = new Image (new FileInputStream(PATH_RESOURCES + "Documentation2.png"));
		Image image3 = new Image (new FileInputStream(PATH_RESOURCES + "Documentation3.png"));
		ImageView img1 = new ImageView();
		img1.setImage(image1);
		img1.setFitHeight(1024);
		img1.setFitWidth(700);
		
		ImageView img2 = new ImageView();
		img2.setImage(image2);
		img2.setFitHeight(1024);
		img2.setFitWidth(700);
		
		ImageView img3 = new ImageView();
		img3.setImage(image3);
		img3.setFitHeight(1024);
		img3.setFitWidth(700);
		
		List<ImageView> listImage = new ArrayList<ImageView>();
		listImage.add(img1);
		listImage.add(img2);
		listImage.add(img3);
		
		Button btnOK = new Button("Suivant");
		Button btnBack = new Button("Précédent");
		
		btnOK.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				switch (pageToShow) {
				case 0:
					btnBack.setDisable(true);
					pageToShow++;
					principalBorber.setCenter(listImage.get(pageToShow));
//					btnBack.setDisable(true);
//					pageToShow++;
					System.out.println("print pagetoShow : " + pageToShow);
					break;
				case 1:
					btnBack.setDisable(false);
					pageToShow++;
					principalBorber.setCenter(listImage.get(pageToShow));
//					btnBack.setDisable(false);
//					pageToShow++;
					System.out.println("print pagetoShow : " + pageToShow);
					break;
				case 2:
					btnOK.setDisable(true);
					btnBack.setDisable(false);
					principalBorber.setCenter(listImage.get(pageToShow));
					System.out.println("print pagetoShow : " + pageToShow);
					break;
				}
//				popUpDoc.close();	
			}
		});
		
		btnBack.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				switch (pageToShow) {
				case 0:
//					
					
					btnOK.setDisable(false);
					principalBorber.setCenter(listImage.get(pageToShow));
					System.out.println("print pagetoShow : " + pageToShow);
					break;
				case 1:
					btnBack.setDisable(true);
					btnOK.setDisable(false);
					pageToShow--;
					principalBorber.setCenter(listImage.get(pageToShow));
					System.out.println("print pagetoShow : " + pageToShow);
					break;
				case 2:
//					
					btnOK.setDisable(false);
					pageToShow--;
					principalBorber.setCenter(listImage.get(pageToShow));
					System.out.println("print pagetoShow : " + pageToShow);
					break;
				}
//				popUpDoc.close();	
			}
		});
		
		GridPane gridPane = new GridPane();
//		gridPane.getChildren().addAll(btnBack,btnOK);
		gridPane.add(btnOK, 1, 0);
		gridPane.add(btnBack, 0, 0);
		gridPane.setAlignment(Pos.CENTER);
		principalBorber.setCenter(listImage.get(pageToShow));
		principalBorber.setTop(gridPane);
	}
	
}
