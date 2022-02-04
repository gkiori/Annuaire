package fr.isika.cda15.projet1.annuaire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class PanelConnexion extends BorderPane{

	private final String PATH_FILE_INSCRIPTION  = "src/main/resources/fichierInscription";
	private File fichierInscription = new File(PATH_FILE_INSCRIPTION );

	private String mdpCheck, IdCheck;
	private static User user;


	private Label labelIncrip = new Label("Inscription");
	private Label labelQuestion = new Label("Vous n'avez pas de compte ?");
	private Label labelUserName = new Label("Identifiant : ");
	private Label labelPassword = new Label("Mot de passe : ");
	private Button boutonLogin = new Button("Connexion");

	private final TextField txtUserName = new TextField();
	private final PasswordField mdpChamp = new PasswordField();
	private final Label labelMessage = new Label();

	private VBox titreLabel = new VBox();
	private HBox questionLabel = new HBox();
	private BorderPane borderCenter = new BorderPane();
	private GridPane gridPane = new GridPane();

	private Text titre = new Text("Connexion");
	private BorderPane panelPrincipal = this;

	public PanelConnexion(final Stage stage) throws Exception {

		questionLabel.getChildren().addAll(labelQuestion, labelIncrip);
		questionLabel.setSpacing(10);
		titreLabel.getChildren().addAll(titre, questionLabel);

		gridPane.setAlignment(Pos.CENTER);
		borderCenter.setCenter(gridPane);
		borderCenter.setMinHeight(USE_PREF_SIZE);

		panelPrincipal.setCenter(borderCenter);

		titre.setFont(Font.font("Verdana", FontWeight.BOLD, 35)); //, FontPosture.ITALIC
		labelIncrip.setTextFill(Color.RED);
		labelIncrip.setUnderline(true);

		labelIncrip.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					try {
						PanelInscription01.PanelInscription01(null);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});

		boutonLogin.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					mdpCheck = mdpChamp.getText();
					IdCheck = txtUserName.getText();
					
					FileReader fr = new FileReader(fichierInscription);
					BufferedReader br = new BufferedReader(fr);
					List<String> infoInscription = new ArrayList<>();
				
					while (br.ready()) {
						infoInscription.add(br.readLine());
						
					}
					br.close();
					fr.close();
				//System.out.println(infoInscription);
				
//				for (String s : splitInfoInscription) {
//						System.out.println(s);					
//		}
					for (int i = 0; i<infoInscription.size(); i++) {
						
//						System.out.println(infoInscription.get(i));	
						
						String [] splitInfoInscription = infoInscription.get(i).split(",");
						
						String nom = splitInfoInscription[0];
						String prenom = splitInfoInscription[1];
						String email = splitInfoInscription[2];
						String mdp = splitInfoInscription[3];
						String profil = splitInfoInscription[4];
						
						 if (email.equals(IdCheck) && mdp.equals(mdpCheck)) {
							 user = new User (nom, prenom, email, mdp, profil);
							 PanelGestionnaire.setData(initPanelGestionnaire());
								new PanelGestionnaire(stage);
								System.out.println("login successful");
						 }						 
						
//						System.out.println(nom+" "+ prenom+ " "+email+" "+mdp+" "+ profil+ " ");	
//						System.out.println("***************************************************");
					}
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});


		panelPrincipal.setPadding(new Insets(10, 50, 50, 50));
		gridPane.setPadding(new Insets(7));
		gridPane.setHgap(7);
		gridPane.setVgap(7);

		labelUserName.setFont(Font.font("Arial", 15));
		labelPassword.setFont(Font.font("Arial", 15));

		//		gridPane.add(titreLabel, 0, 0);
		gridPane.add(titreLabel, 0, 0, 2, 1);
		gridPane.add(labelUserName, 0, 2);
		gridPane.add(txtUserName, 1, 2);
		gridPane.add(labelPassword, 0, 4);
		gridPane.add(mdpChamp, 1, 4);
		gridPane.add(boutonLogin, 1, 6);
		gridPane.add(labelMessage, 1, 8);
		boutonLogin.setFont(Font.font(null, 15));
		gridPane.setMinWidth(100);
		boutonLogin.setPrefSize(100, 15);

		Scene scene = new Scene(panelPrincipal, 800, 550);
		stage.setTitle("Annuaire des stagiaires");
		stage.setScene(scene);
		stage.setWidth(1200);
		stage.setHeight(720);
		stage.show();
	}
	
	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		PanelConnexion.user = user;
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