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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
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

					for (int i = 0; i<infoInscription.size(); i++) {	
						
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
//								System.out.println("login successful");
						 }						 
					}				
				} catch (Exception e) {
					
					e.printStackTrace();
				}

			}
		});

		mdpChamp.setSkin(new VisiblePasswordFieldSkin(mdpChamp));
		
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
	
	class VisiblePasswordFieldSkin extends TextFieldSkin {

		private final Button actionButton = new Button("View");
		private final SVGPath actionIcon = new SVGPath();

		private boolean mask = true;

		public VisiblePasswordFieldSkin(PasswordField textField) {

		    super(textField);

		    actionButton.setId("actionButton");
		    actionButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		    actionButton.setPrefSize(30,30);
		    actionButton.setFocusTraversable(false);
		    actionButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, new Insets(0))));

		    getChildren().add(actionButton);
		    actionButton.setCursor(Cursor.HAND);
		    actionButton.toFront();

		    actionIcon.setContent(Icons.VIEWER.getContent());
		    actionButton.setGraphic(actionIcon);

		    actionButton.setVisible(false);
		    
		    actionButton.setOnMouseClicked(event -> {

		        if(mask) {
		            actionIcon.setContent(Icons.VIEWER_OFF.getContent());
		            mask = false;
		        } else {
		            actionIcon.setContent(Icons.VIEWER.getContent());
		            mask = true;
		        }
		        textField.setText(textField.getText());

		        textField.end();

		    });

		    textField.textProperty().addListener((observable, oldValue, newValue) -> actionButton.setVisible(!newValue.isEmpty()));

		}

		@Override
		protected void layoutChildren(double x, double y, double w, double h) {
		    super.layoutChildren(x, y, w, h);

		    layoutInArea(actionButton, x, y, w, h,0, HPos.RIGHT, VPos.CENTER);
		}

		@Override
		protected String maskText(String txt) {
		    if (getSkinnable() instanceof PasswordField && mask) {
		        int n = txt.length();
		        StringBuilder passwordBuilder = new StringBuilder(n);
		        for (int i = 0; i < n; i++) {
		            passwordBuilder.append("*");
		        }
		        return passwordBuilder.toString();
		    } else {

		        return txt;
		    }
		}
		}

		enum Icons {

			VIEWER_OFF("M12 6c3.79 0 7.17 2.13 8.82 5.5-.59 1.22-1.42 2.27-2." +
			        "41 3.12l1.41 1.41c1.39-1.23 2.49-2.77 3.18-4.53C21.27 7.11 17 4 12 4c-1.27 " +
			        "0-2.49.2-3.64.57l1.65 1.65C10.66 6.09 11.32 6 12 6zm-1.07 1.14L13 9.21c.57.25 1.03.71 " +
			        "1.28 1.28l2.07 2.07c.08-.34.14-.7.14-1.07C16.5 9.01 14.48 7 12 7c-.37 0-.72.05-1.07." +
			        "14zM2.01 3.87l2.68 2.68C3.06 7.83 1.77 9.53 1 11.5 2.73 15.89 7 19 12 19c1.52 0 2.98-.29 " +
			        "4.32-.82l3.42 3.42 1.41-1.41L3.42 2.45 2.01 3.87zm7.5 7.5l2.61 2.61c-.04.01-.08.02-.12.02-1.38 " +
			        "0-2.5-1.12-2.5-2.5 0-.05.01-.08.01-.13zm-3.4-3.4l1.75 1.75c-.23.55-.36 1.15-.36 1.78 0 2.48 2.02 " +
			        "4.5 4.5 4.5.63 0 1.23-.13 1.77-.36l.98.98c-.88.24-1.8.38-2.75.38-3.79 0-7.17-2.13-8.82-5.5.7-1.43 1.72-2.61 2.93-3.53z"),
	
			VIEWER("M12 4.5C7 4.5 2.73 7.61 1 12c1.73 4.39 6 7.5 11 7.5s9.27-3.11 11-7.5c-1.73-4.39-6-7." +
			        "5-11-7.5zM12 17c-2.76 0-5-2.24-5-5s2.24-5 5-5 5 2.24 5 5-2.24 5-5 5zm0-8c-1.66 0-3 1.34-3 3s1.34 3 3 3 3-1.34 3-3-1.34-3-3-3z");
	
			private String content;
	
			Icons(String content) {
			    this.content = content;
			}
	
			public String getContent() {
			    return content;
			}
		}
}