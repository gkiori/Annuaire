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
	private Label listBtn = new Label("Réinitialiser les filtres");
	private Label imprBtn = new Label("Impression");
	private Label infoBtn = new Label("Information");
	private Label corbeilleBtn = new Label("Corbeille");
	
	private VBox vbOut = new VBox();
	private Label helpBtn = new Label("Support");
	private Label decoBtn = new Label("Déconnexion");
	
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
		rechBtn.setGraphic(imageInterRech);
		rechBtn.setStyle("-fx-text-fill : #4E73F8;"
 			 		   + "-fx-font-weight : bold; "
 			 		   + "-fx-font-size: 14px; "
 			 		   + "-fx-font-family: Verdana;");
//		rechBtn.setPrefHeight(24);
//		rechBtn.setPrefWidth(154);
		rechBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				try {
					//Direction vers le panale filtre
//					new PanelFiltre(stage);
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
		
		listBtn.setGraphic(imageInter);
		listBtn.setStyle("-fx-text-fill : #757D8A;"
		 		   + "-fx-font-weight : bold; "
		 		   + "-fx-font-size: 14px; "
		 		   + "-fx-font-family: Verdana;");
		listBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				// Appel la fonction d'initialisation
				if (event.getButton() == MouseButton.PRIMARY) {
					try {
						PanelGestionnaire.data.clear();
						PanelGestionnaire.data.addAll(App.initPanelGestionnaire());
//						new PanelGestionnaire(stage);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}				
			}
		});
		
		Image imageDeco = new Image(new FileInputStream(PATH_RESOURCES + "imageDeco.png"));
		ImageView imageInterDeco = new ImageView(imageDeco);
		imageInterDeco.setFitWidth(70);
		imageInterDeco.setFitHeight(70);
		decoBtn.setGraphic(imageInterDeco);
		decoBtn.setStyle("-fx-text-fill : #757D8A;"
		 		   + "-fx-font-weight : bold; "
		 		   + "-fx-font-size: 14px; "
		 		   + "-fx-font-family: Verdana;");
        decoBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	if (e.getButton() == MouseButton.PRIMARY) {
            		try {
//            			if (!PanelGestionnaire.getTable().getItems().isEmpty()) {
//            				PanelGestionnaire.reInitTable();
//            				System.out.println("GeneralInfos : TableView est reinitialisé" + PanelGestionnaire.getTable().getItems());
//            			}
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
        imprBtn.setGraphic(imageInterImpress);
        imprBtn.setStyle("-fx-text-fill : #757D8A;"
		 		   + "-fx-font-weight : bold; "
		 		   + "-fx-font-size: 14px; "
		 		   + "-fx-font-family: Verdana;");
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
						
						if (file != null) {
							
							PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(file.getPath()));
//							System.out.println("PDF created.");
							
							TableView<Stagiaire> tableView = new TableView<>();
							tableView.setItems(PanelGestionnaire.getData()); ;
							
							List<List<String>> listColumns = new ArrayList<List<String>>();
							
//							System.out.println(list);
							
							for (int i = 0; i < tableView.getColumns().size(); i++) {
								List<String> temp = new ArrayList<String>();
								TableColumn<Stagiaire, String> column = (TableColumn<Stagiaire, String>)tableView.getColumns().get(i);
								for(int j = 0; j < tableView.getItems().size(); j++) { 
									temp.add(column.getCellData(j));
//									System.out.println(column.getCellData(j));
								}
								listColumns.add(temp);
							}
							
							PdfPTable table = new PdfPTable(5);
					        
					        // set the width of the table to 100% of page
					        table.setWidthPercentage(100);
					        
					        // set relative columns width
					        table.setWidths(new float[]{0.6f, 1.4f, 0.8f,0.8f,1.8f});

					        
					        // ----------------Table Header "Title"----------------
					        // font
					        Font font = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.WHITE);
					        // create header cell
					        PdfPCell cell = new PdfPCell(new Phrase("Résultats de recherche", font));
					        // set Column span "1 cell = 6 cells width"
					        cell.setColspan(6);
					        // set style
					        //Style.headerCellStyle(cell);
					        // add to table
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
        infoBtn.setGraphic(imageInterInfo);
        infoBtn.setStyle("-fx-text-fill : #757D8A;"
		 		   + "-fx-font-weight : bold; "
		 		   + "-fx-font-size: 14px; "
		 		   + "-fx-font-family: Verdana;");
        
        Image imageSupport = new Image(new FileInputStream(PATH_RESOURCES + "imageSupport.png")); 
    	ImageView imageInterSupport = new ImageView(imageSupport);
    	imageInterSupport.setFitWidth(70);
    	imageInterSupport.setFitHeight(70);
        helpBtn.setGraphic(imageInterSupport);
        helpBtn.setStyle("-fx-text-fill : #757D8A;"
		 		   + "-fx-font-weight : bold; "
		 		   + "-fx-font-size: 14px; "
		 		   + "-fx-font-family: Verdana;");
        
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
        corbeilleBtn.setGraphic(imageInterTrash);
        corbeilleBtn.setStyle("-fx-text-fill : #757D8A;"
		 		   + "-fx-font-weight : bold; "
		 		   + "-fx-font-size: 14px; "
		 		   + "-fx-font-family: Verdana;");
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

		vbMiddle.getChildren().addAll(rechBtn, listBtn, imprBtn, infoBtn, corbeilleBtn);
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
