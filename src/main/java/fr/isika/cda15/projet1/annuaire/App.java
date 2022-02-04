package fr.isika.cda15.projet1.annuaire;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX Application
 */
public class App extends Application {
	
	private BorderPane panelPrincipal = new BorderPane();

    @Override
    public void start(Stage stage) throws Exception {
    	
    	PanelConnexion connexion = new PanelConnexion(stage);

		panelPrincipal.setCenter(connexion);

		Scene scene = new Scene(panelPrincipal, 400, 350);
		stage.setTitle("Gestion Annuaire");
		stage.setScene(scene);
		stage.setWidth(1200);
        stage.setHeight(720);
		stage.show();
//    	
//        var javaVersion = SystemInfo.javaVersion();
//        var javafxVersion = SystemInfo.javafxVersion();
//
//        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
//        var scene = new Scene(new StackPane(label), 640, 480);
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}