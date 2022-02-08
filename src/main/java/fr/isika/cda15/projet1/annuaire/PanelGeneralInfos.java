package fr.isika.cda15.projet1.annuaire;

import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;  
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class PanelGeneralInfos extends BorderPane {
	
//	private static final String PDF_PATH = "src/main/resources/FilePDF.pdf";
	private static final String PATH_RESOURCES = "src/main/resources/";
	
	private HBox hbUser = new HBox();
	private VBox vbUser = new VBox();
//	private ImageView imgUser; //"src/main/resources/profil.png"
	private Label lblBienvenue = new Label("Bienvenue");
	private static Label lblUserName = new Label(PanelConnexion.getUser().getPrenom() + " " + PanelConnexion.getUser().getNom());
	
	private VBox vbMiddle = new VBox();
	private Label rechBtn = new Label("Rechercher");
	private Label optiBtn = new Label("Optimiser");
	private Label imprBtn = new Label("Impression");
	private Label infoBtn = new Label("Information");
	private Label corbeilleBtn = new Label("Corbeille");
	
	private VBox vbOut = new VBox();
	private Label helpBtn = new Label("Support");
	private Label decoBtn = new Label("Déconnexion");
	private static Boolean activeRech = false;
	
	@SuppressWarnings("exports")
	public PanelGeneralInfos(final Stage stage) throws FileNotFoundException{
		
		Image image = new Image(new FileInputStream(PATH_RESOURCES + "profil.png"));		
		ImageView imgUser = new ImageView(image);
		imgUser.setFitHeight(70); 
		imgUser.setFitWidth(70);
		
		lblUserName.setTextFill(Color.BLUE);
		lblUserName.setUnderline(true);		
		lblUserName.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					try {
						PanelInscription01.PanelInscription01(PanelConnexion.getUser());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		Image imageRech = new Image(new FileInputStream(PATH_RESOURCES + "imageRech.png"));
		ImageView imageInterRech = new ImageView(imageRech);
		imageInterRech.setFitWidth(70);
		imageInterRech.setFitHeight(70);
		Image imageRechIN = new Image (new FileInputStream(PATH_RESOURCES + "imageRechIn.png"));
		ImageView imageInterRechIN = new ImageView(imageRechIN);
		imageInterRechIN.setFitWidth(70);
		imageInterRechIN.setFitHeight(70);
		rechBtn.setGraphic(imageInterRech);
		rechBtn.setStyle("-fx-text-fill : #757D8A;"
 			 		   + "-fx-font-weight : bold; "
 			 		   + "-fx-font-size: 14px; "
 			 		   + "-fx-font-family: Verdana;");
		rechBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				rechBtn.setGraphic(imageInterRechIN);
				rechBtn.setStyle("-fx-text-fill : #0E4DA4;" //4E73F8 color mouse
	 			 		   + "-fx-font-weight : bold; "
	 			 		   + "-fx-font-size: 14px; "
	 			 		   + "-fx-font-family: Verdana;");
			}
		});
		rechBtn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				rechBtn.setGraphic(imageInterRech);
				rechBtn.setStyle("-fx-text-fill : #757D8A;" //4E73F8 color mouse
	 			 		   + "-fx-font-weight : bold; "
	 			 		   + "-fx-font-size: 14px; "
	 			 		   + "-fx-font-family: Verdana;");
			}
		});
		
		rechBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				try {
					if (event.getButton() == MouseButton.PRIMARY && activeRech == false) {
						PanelFiltre.montrerRecherche();
						activeRech = true;
					}else {
						PanelFiltre.cacherRecherche();
						activeRech = false;
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Image imageList = new Image(new FileInputStream(PATH_RESOURCES + "imageList.png"));
		ImageView imageInter = new ImageView(imageList);
		imageInter.setFitWidth(70);
		imageInter.setFitHeight(70);
		Image imageListIN = new Image(new FileInputStream(PATH_RESOURCES + "imageListIn.png"));
		ImageView imageInterIN = new ImageView(imageListIN);
		imageInterIN.setFitWidth(70);
		imageInterIN.setFitHeight(70);	
		optiBtn.setGraphic(imageInter);
		optiBtn.setStyle("-fx-text-fill : #757D8A;"
		 		   + "-fx-font-weight : bold; "
		 		   + "-fx-font-size: 14px; "
		 		   + "-fx-font-family: Verdana;");
		optiBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				optiBtn.setGraphic(imageInterIN);
				optiBtn.setStyle("-fx-text-fill : #4E73F8;" //4E73F8 color mouse
	 			 		   + "-fx-font-weight : bold; "
	 			 		   + "-fx-font-size: 14px; "
	 			 		   + "-fx-font-family: Verdana;");
			}
		});
		optiBtn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				optiBtn.setGraphic(imageInter);
				optiBtn.setStyle("-fx-text-fill : #757D8A;" //4E73F8 color mouse
	 			 		   + "-fx-font-weight : bold; "
	 			 		   + "-fx-font-size: 14px; "
	 			 		   + "-fx-font-family: Verdana;");
			}
		});
		
		if(PanelConnexion.getUser().getProfil().equalsIgnoreCase("Apprenant")) {
			optiBtn.setDisable(true);
		}
		optiBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				try {
					PanelOpti.PanelOpti();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		Image imageDeco = new Image(new FileInputStream(PATH_RESOURCES + "imageDeco.png"));
		ImageView imageInterDeco = new ImageView(imageDeco);
		imageInterDeco.setFitWidth(70);
		imageInterDeco.setFitHeight(70);
		Image imageDecoIN = new Image(new FileInputStream(PATH_RESOURCES + "imageDecoIn.png"));
		ImageView imageInterDecoIN = new ImageView(imageDecoIN);
		imageInterDecoIN.setFitWidth(70);
		imageInterDecoIN.setFitHeight(70);
		decoBtn.setGraphic(imageInterDeco);
		decoBtn.setStyle("-fx-text-fill : #757D8A;"
		 		   + "-fx-font-weight : bold; "
		 		   + "-fx-font-size: 14px; "
		 		   + "-fx-font-family: Verdana;");
		decoBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				decoBtn.setGraphic(imageInterDecoIN);
				decoBtn.setStyle("-fx-text-fill : #4E73F8;" //4E73F8 color mouse
	 			 		   + "-fx-font-weight : bold; "
	 			 		   + "-fx-font-size: 14px; "
	 			 		   + "-fx-font-family: Verdana;");
				
			}
		});
		decoBtn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				decoBtn.setGraphic(imageInterDeco);
				decoBtn.setStyle("-fx-text-fill : #757D8A;" //4E73F8 color mouse
	 			 		   + "-fx-font-weight : bold; "
	 			 		   + "-fx-font-size: 14px; "
	 			 		   + "-fx-font-family: Verdana;");
				
			}
		});
        decoBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	if (e.getButton() == MouseButton.PRIMARY) {
            		try {
                		new PanelConnexion(stage);
    				} catch (Exception e2) {
    					e2.printStackTrace();
    				}
            	}
            }
        });
        
        Image imageImpress = new Image(new FileInputStream(PATH_RESOURCES + "imageImpress.png"));
    	ImageView imageInterImpress = new ImageView(imageImpress);
    	imageInterImpress.setFitWidth(70);
    	imageInterImpress.setFitHeight(70);
    	Image imageImpressIN = new Image(new FileInputStream(PATH_RESOURCES + "imageImpressIn.png"));
    	ImageView imageInterImpressIN = new ImageView(imageImpressIN);
    	imageInterImpressIN.setFitWidth(70);
    	imageInterImpressIN.setFitHeight(70);
        imprBtn.setGraphic(imageInterImpress);
        imprBtn.setStyle("-fx-text-fill : #757D8A;"
		 		   + "-fx-font-weight : bold; "
		 		   + "-fx-font-size: 14px; "
		 		   + "-fx-font-family: Verdana;");
        imprBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				imprBtn.setGraphic(imageInterImpressIN);
				 imprBtn.setStyle("-fx-text-fill : #4E73F8;" //4E73F8 color mouse
	 			 		   + "-fx-font-weight : bold; "
	 			 		   + "-fx-font-size: 14px; "
	 			 		   + "-fx-font-family: Verdana;");
				
			}
		});
        imprBtn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				imprBtn.setGraphic(imageInterImpress);
				 imprBtn.setStyle("-fx-text-fill : #757D8A;" //4E73F8 color mouse
	 			 		   + "-fx-font-weight : bold; "
	 			 		   + "-fx-font-size: 14px; "
	 			 		   + "-fx-font-family: Verdana;");
				
			}
		});
        imprBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					try {
						
						Document doc = new Document();
						
						FileChooser fileChooser = new FileChooser();
						fileChooser.setTitle("Enregister");
						fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Fichiers PDF (*.pdf)", "*.pdf"));
						File file = fileChooser.showSaveDialog(stage);
						
						if (file == null) {
							System.out.println("L'objet de type File n'existe pas.\nImpossible de créer un fichier pdf.");
						}else {
							
							PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(file.getPath()));
							
//							System.out.println("Recuperation des resultats :\n");
							TableView<Stagiaire> tableView = new TableView<>();
							TableColumn<Stagiaire, String> prenomCol = 
					        		new TableColumn<Stagiaire, String>("Prénom");
					        prenomCol.setMinWidth(tableView.getPrefWidth()/5);
					        prenomCol.setCellValueFactory(
					                new PropertyValueFactory<Stagiaire, String>("prenom"));
					        
					        TableColumn<Stagiaire, String> nomCol = new TableColumn<Stagiaire, String>("Nom");
					        nomCol.setMinWidth(tableView.getPrefWidth()/5);
					        nomCol.setSortType(TableColumn.SortType.ASCENDING);
					        nomCol.setCellValueFactory(
					                new PropertyValueFactory<Stagiaire, String>("nom"));
					        
					        TableColumn<Stagiaire, String> promoCol = new TableColumn<Stagiaire, String>("Promotion");
					        promoCol.setMinWidth(tableView.getPrefWidth()/5);
					        promoCol.setCellValueFactory(
					                new PropertyValueFactory<Stagiaire, String>("promo"));
					        
					        TableColumn<Stagiaire, String> anneeEntreeCol = new TableColumn<Stagiaire, String>("Admission");
					        anneeEntreeCol.setMinWidth(tableView.getPrefWidth()/5-55);
					        anneeEntreeCol.setCellValueFactory(
					        		new PropertyValueFactory<Stagiaire, String>("anneeEntree"));
					        
					        TableColumn<Stagiaire, String> departementCol = new TableColumn<Stagiaire, String>("Département");
					        departementCol.setMinWidth(tableView.getPrefWidth()/5);
					        departementCol.setCellValueFactory(
					        		new PropertyValueFactory<Stagiaire, String>("departement"));
					                
					        tableView.getColumns().addAll(nomCol, prenomCol, promoCol, anneeEntreeCol, departementCol);
				
							tableView.setItems(PanelGestionnaire.getData());					
//							System.out.println("Résultats :\n" + tableView.getItems());
							
//							System.out.println("Division en colonne :\n");
							List<List<String>> listColumns = new ArrayList<List<String>>();
//							System.out.println("----- Instanciation d'une list contenat une list ");
//							System.out.println("----- Nombre de columns : " + tableView.getColumns().size());
							for (int i = 0; i < tableView.getColumns().size(); i++) {
								List<String> temp = new ArrayList<String>();
								TableColumn<Stagiaire, String> column = (TableColumn<Stagiaire, String>)tableView.getColumns().get(i);
								for(int j = 0; j < tableView.getItems().size(); j++) { 
									temp.add(column.getCellData(j));
//									System.out.println("column : " + i + "; row : " + j);
								}
								listColumns.add(temp);			
							}
//							System.out.println("Resultats :\n" + listColumns.toString());
							
//							System.out.println(listColumns.toString());
							
							PdfPTable table = new PdfPTable(5);
					        
					        table.setWidthPercentage(100);
					        table.setWidths(new float[]{0.6f, 1.4f, 0.8f,0.8f,1.8f});

					        
					        // ----------------Table Header "Title"----------------
					        Font font = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.WHITE);
					        PdfPCell cell = new PdfPCell(new Phrase("Résultats de recherche", font));
					        cell.setColspan(6);
					        table.addCell(cell);
		                   
		                  //-----------------Table Cells Label/Value------------------
		                    //title 
					        table.addCell(createLabelCell("Nom"));
		                    table.addCell(createLabelCell("Prénom"));
		                    table.addCell(createLabelCell("Promotion"));
		                    table.addCell(createLabelCell("Abmission"));
		                    table.addCell(createLabelCell("Département"));
		                    
		                    for (int i = 0; i < tableView.getItems().size(); i++) {
		                    	table.addCell(createValueCell(listColumns.get(0).get(i)));
		                        table.addCell(createValueCell(listColumns.get(1).get(i)));
		                        table.addCell(createValueCell(listColumns.get(2).get(i)));
		                        table.addCell(createValueCell(listColumns.get(3).get(i)));
		                        table.addCell(createValueCell(listColumns.get(4).get(i)));
		                    }
							
							doc.open(); 
							doc.add(table);
							doc.close();
							writer.close();
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}				
				}
				
			}
        	
        });
        
        Image imageInfo = new Image(new FileInputStream(PATH_RESOURCES + "imageInfo.png"));
    	ImageView imageInterInfo = new ImageView(imageInfo);
    	imageInterInfo.setFitWidth(70);
    	imageInterInfo.setFitHeight(70);
    	Image imageInfoIN = new Image(new FileInputStream(PATH_RESOURCES + "imageInfoIn.png"));
    	ImageView imageInterInfoIN = new ImageView(imageInfoIN);
    	imageInterInfoIN.setFitWidth(70);
    	imageInterInfoIN.setFitHeight(70);
        infoBtn.setGraphic(imageInterInfo);
        infoBtn.setStyle("-fx-text-fill : #757D8A;"
		 		   + "-fx-font-weight : bold; "
		 		   + "-fx-font-size: 14px; "
		 		   + "-fx-font-family: Verdana;");
        infoBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				infoBtn.setGraphic(imageInterInfoIN);
				infoBtn.setStyle("-fx-text-fill : #4E73F8;" //4E73F8 color mouse
	 			 		   + "-fx-font-weight : bold; "
	 			 		   + "-fx-font-size: 14px; "
	 			 		   + "-fx-font-family: Verdana;");
				
			}
		});
        infoBtn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				infoBtn.setGraphic(imageInterInfo);
				infoBtn.setStyle("-fx-text-fill : #757D8A;" //4E73F8 color mouse
	 			 		   + "-fx-font-weight : bold; "
	 			 		   + "-fx-font-size: 14px; "
	 			 		   + "-fx-font-family: Verdana;");
				
			}
		});
        infoBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					PanelUserDoc.PanelUserDoc();
				}
			}
        	
		});
        
        Image imageSupport = new Image(new FileInputStream(PATH_RESOURCES + "imageSupport.png")); 
    	ImageView imageInterSupport = new ImageView(imageSupport);
    	imageInterSupport.setFitWidth(70);
    	imageInterSupport.setFitHeight(70);
    	Image imageSupportIN = new Image(new FileInputStream(PATH_RESOURCES + "imageSupportIn.png")); 
    	ImageView imageInterSupportIN = new ImageView(imageSupportIN);
    	imageInterSupportIN.setFitWidth(70);
    	imageInterSupportIN.setFitHeight(70);
        helpBtn.setGraphic(imageInterSupport);
        helpBtn.setStyle("-fx-text-fill : #757D8A;"
		 		   + "-fx-font-weight : bold; "
		 		   + "-fx-font-size: 14px; "
		 		   + "-fx-font-family: Verdana;");
        helpBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				helpBtn.setGraphic(imageInterSupportIN);
				helpBtn.setStyle("-fx-text-fill : #4E73F8;" //4E73F8 color mouse
	 			 		   + "-fx-font-weight : bold; "
	 			 		   + "-fx-font-size: 14px; "
	 			 		   + "-fx-font-family: Verdana;");
				
			}
		});
        helpBtn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				helpBtn.setGraphic(imageInterSupport);
				helpBtn.setStyle("-fx-text-fill : #757D8A;" //4E73F8 color mouse
	 			 		   + "-fx-font-weight : bold; "
	 			 		   + "-fx-font-size: 14px; "
	 			 		   + "-fx-font-family: Verdana;");
				
			}
		});
        helpBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
//					new PanelInformation();
				}
			}
        	
		});
        
        lblBienvenue.setStyle("-fx-text-fill : #757D8A;"
		 		   + "-fx-font-weight : normal; "
		 		   + "-fx-font-size: 18px; "
		 		   + "-fx-font-family: Verdana;");
        
        lblUserName.setStyle("-fx-text-fill : #5A6474;"
		 		   + "-fx-font-weight : bold; "
		 		   + "-fx-font-size: 20px; "
		 		   + "-fx-font-family: Verdana;");
        
        Image imageTrash = new Image(new FileInputStream(PATH_RESOURCES + "imageTrash.png"));
    	ImageView imageInterTrash = new ImageView(imageTrash);
    	imageInterTrash.setFitWidth(70);
    	imageInterTrash.setFitHeight(70);
    	Image imageTrashIN = new Image(new FileInputStream(PATH_RESOURCES + "imageTrashIn.png"));
    	ImageView imageInterTrashIN = new ImageView(imageTrashIN);
    	imageInterTrashIN.setFitWidth(70);
    	imageInterTrashIN.setFitHeight(70);
        corbeilleBtn.setGraphic(imageInterTrash);
        corbeilleBtn.setStyle("-fx-text-fill : #757D8A;"
		 		   + "-fx-font-weight : bold; "
		 		   + "-fx-font-size: 14px; "
		 		   + "-fx-font-family: Verdana;");
        corbeilleBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				corbeilleBtn.setGraphic(imageInterTrashIN);
				corbeilleBtn.setStyle("-fx-text-fill : #4E73F8;" //4E73F8 color mouse
	 			 		   + "-fx-font-weight : bold; "
	 			 		   + "-fx-font-size: 14px; "
	 			 		   + "-fx-font-family: Verdana;");
				
			}
		});
        corbeilleBtn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				corbeilleBtn.setGraphic(imageInterTrash);
				corbeilleBtn.setStyle("-fx-text-fill : #757D8A;" //4E73F8 color mouse
	 			 		   + "-fx-font-weight : bold; "
	 			 		   + "-fx-font-size: 14px; "
	 			 		   + "-fx-font-family: Verdana;");
				
			}
		});
        corbeilleBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	if (e.getButton() == MouseButton.PRIMARY) {
	            	try {
	            		PanelCorbeille.PanelCorbeille();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
            	}
            }
        });
        
		vbUser.getChildren().addAll(lblBienvenue, lblUserName);
		hbUser.getChildren().addAll(imgUser, vbUser);

		vbMiddle.getChildren().addAll(rechBtn, imprBtn, infoBtn, corbeilleBtn, optiBtn);
		vbMiddle.setSpacing(40);
		// ajouter ici alignement 

		vbMiddle.setPadding(new Insets(20,20,20,10));
		vbOut.getChildren().addAll(helpBtn, decoBtn);
		vbOut.setSpacing(32);
		vbOut.setPadding(new Insets(20));
		
		this.setPadding(new Insets(20,20,20,10));
		this.setStyle("-fx-background-color : #EFF6FF");
		this.setTop(hbUser);
		this.setCenter(vbMiddle);
		this.setBottom(vbOut);
		this.setMargin(hbUser, new Insets(0, 0, 91, 0)); // Insets(top, right, bottom, left)
		Scene scene = new Scene(this, 314,1024);
		stage.setScene(scene);
		
	}
	
    // create cells
    private static PdfPCell createLabelCell(String text){
    	// font
    	Font font = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.DARK_GRAY);
    	
    	// create cell
    	PdfPCell cell = new PdfPCell(new Phrase(text,font));

    	// set style
//    	Style.labelCellStyle(cell);
        return cell;
    }
    
    // create cells
    private static PdfPCell createValueCell(String text){
    	// font
    	Font font = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
    	
    	// create cell
    	PdfPCell cell = new PdfPCell(new Phrase(text,font));
    	
    	// set style
//    	Style.valueCellStyle(cell);
        return cell;
    }
	
    public static void setUserName(String nom, String prenom) {
    	lblUserName.setText(prenom + " " + nom);
    }
}
