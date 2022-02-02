package fr.isika.cda15.projet1.annuaire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PanelConnexion extends BorderPane{
	
	private final String fichierLoginPath = "src/resources/loginFile.bin";
	private File fichierLogin = new File(fichierLoginPath);
	
	private String mdpCheck, nomCheck;
	
	private Label labelUserName = new Label("Identifiant : ");
	private Label labelPassword = new Label("Mot de passe : ");
	private Button boutonLogin = new Button("Connexion");

	private final TextField txtUserName = new TextField();
	private final PasswordField mdpChamp = new PasswordField();
	private final Label labelMessage = new Label();

	private HBox panelTitre = new HBox();
	private BorderPane borderCenter = new BorderPane();
	private GridPane gridPane = new GridPane();
	private Text titre = new Text("Gestion Annuaire");
	private BorderPane panelPrincipal = this;
	
	public PanelConnexion(final Stage stage) throws Exception {
		
		panelTitre.getChildren().add(titre);
		panelTitre.setAlignment(Pos.CENTER);
		panelTitre.setMinHeight(USE_PREF_SIZE);
		
		gridPane.setAlignment(Pos.CENTER);
		borderCenter.setCenter(gridPane);
		borderCenter.setMinHeight(USE_PREF_SIZE);
		
		panelPrincipal.setTop(panelTitre);
		panelPrincipal.setCenter(borderCenter);
		
		titre.setFont(Font.font("Verdana", 35));
		
		boutonLogin.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
					PanelGestionnaire.setData(initPanelGestionnaire());
					new PanelGestionnaire(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		if(fichierLogin.exists()) {
			// Le fichier contenant les identifiant et les mdp
			FileReader fr = new FileReader(fichierLogin);
			BufferedReader br = new BufferedReader(fr);
			br.close();
		}else {
			//Si le fichier n'existe pas. Creer le fichier
		}
		panelPrincipal.setPadding(new Insets(10, 50, 50, 50));
		gridPane.setPadding(new Insets(7));
		gridPane.setHgap(7);
		gridPane.setVgap(7);
		
		labelUserName.setFont(Font.font("Arial", 15));
		labelPassword.setFont(Font.font("Arial", 15));
		
		gridPane.add(labelUserName, 0, 0);
		gridPane.add(txtUserName, 1, 0);
		gridPane.add(labelPassword, 0, 1);
		gridPane.add(mdpChamp, 1, 1);
		gridPane.add(boutonLogin, 1, 3);
		gridPane.add(labelMessage, 1, 5);
		boutonLogin.setFont(Font.font(null, 15));
		gridPane.setMinWidth(100);
		boutonLogin.setPrefSize(100, 15);
		
		Scene scene = new Scene(panelPrincipal, 800, 550);
		stage.setTitle("Annuaire des stagiaires");
		stage.setScene(scene);
		stage.show();
	}
	
	private static ObservableList<Stagiaire> initPanelGestionnaire() {
		ArbreStagiaire monArbre = new ArbreStagiaire();
		monArbre.initArbre();
    	
    	List<Stagiaire> maList = new ArrayList<>();
    	maList = Recherche.parcoursStagiaire(monArbre);
		ObservableList<Stagiaire> list = FXCollections.observableArrayList(maList);
	    return list;
		
	}
}
