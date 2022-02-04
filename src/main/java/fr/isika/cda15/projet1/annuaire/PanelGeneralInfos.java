package fr.isika.cda15.projet1.annuaire;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
//import java.awt.Desktop;

import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;  
import com.itextpdf.text.DocumentException;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PanelGeneralInfos extends BorderPane {
	
	private static final String PDF_PATH = "src/main/resources/FilePDF.pdf";
	
	private HBox hbUser = new HBox();
	private VBox vbUser = new VBox();
	private ImageView imgUser = new ImageView();
	private Label lblBienvenue = new Label("Bienvenue");
	private Label lblUserName = new Label();
	
	private VBox vbMiddle = new VBox();
	private Button rechBtn = new Button("Rechercher");
	private Button listBtn = new Button("Liste des stagiaires");
	private Button imprBtn = new Button("Impression");
	private Button infoBtn = new Button("Information");
	
	private VBox vbOut = new VBox();
	private Button helpBtn = new Button("Support");
	private Button decoBtn = new Button("Déconnexion");
	
	public PanelGeneralInfos(final Stage stage) {
		
		
		rechBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					//Direction vers le panale filtre
//					new PanelFiltre(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		listBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// Appel la fonction d'initialisation
				try {
					PanelGestionnaire.data.clear();
					PanelGestionnaire.data.addAll(PanelConnexion.initPanelGestionnaire());
//					new PanelGestionnaire(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
        decoBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	try {
            		new PanelConnexion(stage);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
            }
        });
        
        imprBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					Document doc = new Document(); 
					PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(PDF_PATH));  
//					System.out.println("PDF created.");
					
					TableView<Stagiaire> tableView = new TableView<>();
					tableView = PanelGestionnaire.getTable();
					
					List<List<String>> listColumns = new ArrayList<List<String>>();
					
//					System.out.println(list);
					
					for (int i = 0; i < tableView.getColumns().size(); i++) {
						List<String> temp = new ArrayList<String>();
						TableColumn<Stagiaire, String> column = (TableColumn<Stagiaire, String>)tableView.getColumns().get(i);
						for(int j = 0; j < tableView.getItems().size(); j++) { 
							temp.add(column.getCellData(j));
//							System.out.println(column.getCellData(j));
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
					
					
//					File pdfFile = new File(PDF_PATH);
//					if (Desktop.isDesktopSupported()) {
//							Desktop.getDesktop().open(pdfFile);
//					} else {
//							System.out.println("Awt Desktop is not supported!");
//					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
        	
        });
		
		vbUser.getChildren().addAll(lblBienvenue, lblUserName);
		hbUser.getChildren().addAll(imgUser, vbUser);
		
		vbMiddle.getChildren().addAll(rechBtn, listBtn, imprBtn, infoBtn);
		
		vbOut.getChildren().addAll(helpBtn, decoBtn);
		
		this.setPadding(new Insets(20,20,20,10));
		this.setTop(hbUser);
		this.setCenter(vbMiddle);
		this.setBottom(vbOut);
		Scene scene = new Scene(this);
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
	
}
