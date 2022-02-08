package fr.isika.cda15.projet1.annuaire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	private final String PATH_TO_CSSFILE = "src/main/resources/PanelConnexion.css";
	private File fichierInscription = new File(PATH_FILE_INSCRIPTION );
	private static final String PATH_RESOURCES = "src/main/resources/";
	
	private String mdpCheck, IdCheck;
	private static User user;


	private Label labelIncrip = new Label("Inscription");
	private Label labelQuestion = new Label("Vous n'avez pas de compte ?");
	private Label labelUserName = new Label("Identifiant : ");
	private Label labelPassword = new Label("Mot de passe : ");
	private Button boutonLogin = new Button("Connexion");
	private Label labelError = new Label();

	private final TextField txtUserName = new TextField();
	private final PasswordField mdpChamp = new PasswordField();

	private VBox titreLabel = new VBox();
	private HBox questionLabel = new HBox();
	private BorderPane borderCenter = new BorderPane();
	private GridPane gridPane = new GridPane();

	private Label titre = new Label("Connexion");
	private BorderPane panelPrincipal = this;

	@SuppressWarnings("exports")
	public PanelConnexion(final Stage stage) throws Exception {
		
		/**
		 * Label permettant d'accéder au panel d'incription
		 */
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

		/**
		 * Bouton permettant d'accéder au panel gestion
		 * Il vérifie que les identifiant entrés sont correct
		 */
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
						 }else {
							 labelError.setText("Identifiant et/ou mot de passe eronné !");
						 }
					}				
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		});
		
		
		labelQuestion.setStyle("-fx-text-fill : #0E4DA4;"
				 			 + "-fx-font-weight : bold; "
				 			 + "-fx-font-size: 15px; "
				 			 + "-fx-font-family: Verdana;");
		labelIncrip.setStyle("-fx-text-fill :  	#880000;"
						   + "-fx-font-weight : bold; "
						   + "-fx-font-size: 15px; "
						   + "-fx-font-family: Verdana;");
		questionLabel.getChildren().addAll(labelQuestion, labelIncrip);
		questionLabel.setSpacing(12);
		
		
		titre.setStyle("-fx-text-fill : #94A3B8;"
					 + "-fx-font-weight : 900; "
					 + "-fx-font-size: 36px; "
					 + "-fx-font-family: Verdana;");
		
		titreLabel.getChildren().addAll(titre, questionLabel);
		titreLabel.setSpacing(15);

		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(15));
		gridPane.setHgap(7);
		gridPane.setVgap(7);
		
		
		borderCenter.setCenter(gridPane);
		borderCenter.setPrefHeight(1259);
		borderCenter.setPrefWidth(899);
		borderCenter.setStyle("-fx-background-color : #EFF6FF;"); //-fx-border-radius: 111px; -fx-background-radius :
		

		panelPrincipal.setLeft(borderCenter);
		//panelPrincipal.setStyle("-fx-background-color : red ");//-fx-background-image : url('src/main/resources/imageConnexion.jpg')"
		Image image1 = new Image (new FileInputStream(PATH_RESOURCES + "imageConnexion.jpg"));
		ImageView img1 = new ImageView();
		img1.setImage(image1);
		img1.setFitHeight(1024);
		img1.setFitWidth(700);
		panelPrincipal.setRight(img1);
		
		Image logo = new Image (new FileInputStream(PATH_RESOURCES + "logo.png"));
		ImageView logo1 = new ImageView();
		logo1.setImage(logo);
		logo1.setFitWidth(184);
		logo1.setFitHeight(96);
		borderCenter.setTop(logo1);

	
	
		
		labelIncrip.setTextFill(Color.RED);
		labelIncrip.setUnderline(true);

		mdpChamp.setSkin(new VisiblePasswordFieldSkin(mdpChamp));
		
//		panelPrincipal.setPadding(new Insets(10, 50, 50, 50));
		
		labelError.setTextFill(Color.RED);

		labelUserName.setFont(Font.font("Arial", 15));
//		txtUserName.setPrefWidth(453);
//		txtUserName.setPrefHeight(56);
		labelUserName.setStyle("-fx-text-fill : #0E4DA4;"
	 			 + "-fx-font-weight : normal; "
	 			 + "-fx-font-size: 12px; "
	 			 + "-fx-font-family: Verdana;");
//		labelPassword.setFont(Font.font("Arial", 15));
		labelPassword.setStyle("-fx-text-fill : #0E4DA4;"
	 			 + "-fx-font-weight : normal; "
	 			 + "-fx-font-size: 12px; "
	 			 + "-fx-font-family: Verdana;");
		mdpChamp.setPrefWidth(453);
//		mdpChamp.setPrefHeight(56);
		
		boutonLogin.setStyle("-fx-background-color: #0E4DA4;"
//				+ "        #000000,\n"
//				+ "        linear-gradient(#7ebcea, #2f4b8f),\n"
//				+ "        linear-gradient(#426ab7, #263e75),\n"
//				+ "        linear-gradient(#395cab, #223768);\n"
				+ "    -fx-background-insets: 0,1,2,3;\n"
				+ "    -fx-background-radius: 6, 5;\n"
				+ "    -fx-padding: 12 30 12 30;\n"
				+ "    -fx-text-fill: white;\n"
				+ "    -fx-font-weight : bold;"
				+ "    -fx-font-size: 15px;");
		boutonLogin.setPrefSize(145, 44);

		gridPane.add(titreLabel, 0, 0, 2, 1);
		
		gridPane.add(labelUserName, 0, 5);
		gridPane.add(txtUserName, 0, 6);
		gridPane.add(labelPassword, 0, 11);
		gridPane.add(mdpChamp, 0, 12);
		gridPane.add(labelError, 0, 15);
		gridPane.add(boutonLogin, 0, 19);	
	
		
//		gridPane.setMinWidth(100);
		
		Scene scene = new Scene(panelPrincipal, 1050, 1259);
		scene.setFill(Color.web("#EFF6FF"));
//		scene.getStylesheets().add(PATH_TO_CSSFILE); 
		stage.setTitle("Annuaire des stagiaires");
		stage.setScene(scene);
		stage.setWidth(1440);
		stage.setHeight(1024);
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