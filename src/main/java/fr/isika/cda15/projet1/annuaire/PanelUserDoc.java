package fr.isika.cda15.projet1.annuaire;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.Cursor;
//import javafx.scene.web.HTMLEditor;
//import javafx.scene.web.WebEngine;
//import javafx.scene.web.WebView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PanelUserDoc extends BorderPane {
	
	private static final String PATH_RESOURCES = "src/main/resources/";
	private static Stage popUpDoc;
	static double orgCliskSceneX;
	static double orgReleaseSceneX;
	static List<String> list = new ArrayList<String>();
    static int j = 0;
    static Button lbutton;
	static Button rButton;
    static ImageView imageView;
	
	static EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {

	    @Override
	    public void handle(MouseEvent t) {
	        orgCliskSceneX = t.getSceneX();
	    }
	};
	
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
//		Image image1 = new Image (new FileInputStream(PATH_RESOURCES + "Documentation.png"));
//		ImageView img1 = new ImageView();
//		img1.setImage(image1);
//		img1.setFitHeight(1024);
//		img1.setFitWidth(700);
//		principalBorder.setCenter(img1);
		return principalBorder;
	}
	
	private static void addUIControls (BorderPane principalBorber) throws IOException {
		
		try {
			
			for (int i = 0; i < 3; i++) {
				list.add(PATH_RESOURCES + "Documentation" + (i+1) + ".png");
			}
			
			System.out.println(list);

	        GridPane gridPane = new GridPane();
	        gridPane.setAlignment(Pos.CENTER);

	        lbutton = new Button("<");
	        rButton = new Button(">");

	        Image images[] = new Image[list.size()];
	        for (int i = 0; i < list.size(); i++) {
	        	System.out.println("fichier " + i + ": " + list.get(i));
	            images[i] = new Image(list.get(i));
	        }

	        imageView = new ImageView(images[j]);
	        imageView.setCursor(Cursor.CLOSED_HAND);

	        imageView.setOnMousePressed(circleOnMousePressedEventHandler);

	        imageView.setOnMouseReleased(e -> {
	            orgReleaseSceneX = e.getSceneX();
	            if (orgCliskSceneX > orgReleaseSceneX) {
	                lbutton.fire();
	            } else {
	                rButton.fire();
	            }
	        });

	        rButton.setOnAction(e -> {
	            j = j + 1;
	            if (j == list.size()) {
	                j = 0;
	            }
	            imageView.setImage(images[j]);

	        });
	        
	        lbutton.setOnAction(e -> {
	            j = j - 1;
	            if (j == 0 || j > list.size() + 1 || j == -1) {
	                j = list.size() - 1;
	            }
	            imageView.setImage(images[j]);

	        });

	        imageView.setFitHeight(1024);
	        imageView.setFitWidth(700);

	        HBox hBox = new HBox();
	        hBox.setSpacing(15);
	        hBox.setAlignment(Pos.CENTER);
	        hBox.getChildren().addAll(imageView);

	        gridPane.add(hBox, 1, 1);
	        principalBorber.setCenter(gridPane);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }	
	}	
}
	
