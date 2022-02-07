package fr.isika.cda15.projet1.annuaire;

import java.util.HashSet;
import java.util.TreeSet;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * JavaFX Application
 */
public class App extends Application {
	
	private BorderPane panelPrincipal = new BorderPane();

    @Override
    public void start(Stage stage) throws Exception {
    	
    	PanelGestionnaire.setData(initPanelGestionnaire());
    	PanelConnexion connexion = new PanelConnexion(stage);

		panelPrincipal.setCenter(connexion);

		Scene scene = new Scene(panelPrincipal, 1050, 1259);
		stage.setTitle("Gestion Annuaire");
		stage.setScene(scene);
		stage.setWidth(1440);
        stage.setHeight(1024);
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
    
    public static ObservableList<Stagiaire> initPanelGestionnaire() {
		//		ArbreStagiaire monArbre = new ArbreStagiaire();
		ArbreStagiaire.initArbre();

		HashSet<Stagiaire> maList = new HashSet<>();
    	maList = ArbreStagiaire.parcoursStagiaire();
    	TreeSet<Stagiaire> maListeTrie = new TreeSet<Stagiaire>(maList);
		ObservableList<Stagiaire> list = FXCollections.observableArrayList(maListeTrie);
	    return list;

	}

}