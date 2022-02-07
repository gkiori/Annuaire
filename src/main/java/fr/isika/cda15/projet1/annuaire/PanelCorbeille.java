package fr.isika.cda15.projet1.annuaire;

import java.util.Optional;
import java.util.Set;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PanelCorbeille extends BorderPane {

	public static void PanelCorbeille() throws Exception {
		Stage popUpCorbeille;
		TableView<Stagiaire> table = new TableView<Stagiaire>();
		popUpCorbeille = new Stage();
		popUpCorbeille.initModality(Modality.APPLICATION_MODAL);
		popUpCorbeille.setTitle("Corbeille");

		ObservableList<Stagiaire> contenuCorbeille = FXCollections.observableArrayList();

		ContextMenu contextMenu = new ContextMenu();
		MenuItem itemRecup = new MenuItem("Récupérer");

		table.setEditable(true);

		Label labelRecup = new Label("Vous avez réintegré votre stagiaire");
		labelRecup.setVisible(false);
		Label labelCorbeille = new Label("Corbeille");
		labelCorbeille.setFont(Font.font("Verdana", FontWeight.BOLD, 40));

		TableColumn<Stagiaire, String> prenomCol = new TableColumn<Stagiaire, String>("Prénom");
		prenomCol.setMinWidth(100);
		prenomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));

		TableColumn<Stagiaire, String> nomCol = new TableColumn<Stagiaire, String>("Nom");
		nomCol.setMinWidth(100);
		nomCol.setSortType(TableColumn.SortType.ASCENDING);
		nomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));

		TableColumn<Stagiaire, String> promoCol = new TableColumn<Stagiaire, String>("Promotion");
		promoCol.setMinWidth(100);
		promoCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("promo"));

		TableColumn<Stagiaire, String> anneeEntreeCol = new TableColumn<Stagiaire, String>("Admission");
		anneeEntreeCol.setMinWidth(100);
		anneeEntreeCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("anneeEntree"));

		TableColumn<Stagiaire, String> departementCol = new TableColumn<Stagiaire, String>("Département");
		departementCol.setMinWidth(150);
		departementCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("departement"));

		table.getColumns().addAll(prenomCol, nomCol, promoCol, anneeEntreeCol, departementCol);

		contenuCorbeille.addAll(Corbeille.parcoursStagiaire());
		table.setItems(contenuCorbeille);

		Button boutonFermer = new Button("Fermer");
		boutonFermer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					popUpCorbeille.close();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
		});

		Button boutonVider = new Button("Vider la corbeille");
		boutonVider.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Corbeille.viderCorbeille();
				contenuCorbeille.clear();
				contenuCorbeille.addAll(contenuCorbeille);
			}
		});
		
		table.setOnMouseClicked(evt -> {
			if (evt.getButton() == MouseButton.SECONDARY) {
				Set<Node> rows = table.lookupAll(".table-row-cell");
				Optional<Cell> n = rows.stream().map(r -> (Cell) r).filter(Cell::isSelected).findFirst();

				if (n.isPresent()) {
					Optional<Node> node = n.get().getChildrenUnmodifiable().stream()
							.filter(c -> c instanceof TableCell && ((TableCell) c).getTableColumn() == departementCol)
							.findFirst();

					if (node.isPresent()) {
						Node cell = node.get();
						Bounds b = cell.getLayoutBounds();
						contextMenu.show(cell, Side.BOTTOM, b.getWidth() / 2, b.getHeight() / -2);
					}
				}
			}
		});

		itemRecup.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stagiaire stagiaireRecup = table.getSelectionModel().getSelectedItem();
				Corbeille.retirerCorbeille(stagiaireRecup);
				ArbreStagiaire.ajouter(stagiaireRecup);
				contenuCorbeille.clear();
				contenuCorbeille.addAll(Corbeille.parcoursStagiaire());
				PanelGestionnaire.data.add(stagiaireRecup);
				PanelFiltre.changementRecherche();
				labelRecup.setVisible(true);
			}
		});
		
		contextMenu.getItems().addAll(itemRecup);
		BorderPane monBP = new BorderPane();
		VBox vboxBottom = new VBox();
		HBox hboxCorbeille = new HBox();
		hboxCorbeille.getChildren().addAll(boutonFermer, boutonVider);
		hboxCorbeille.setPadding(new Insets(20,20,20,20));
		hboxCorbeille.setSpacing(50);
		hboxCorbeille.setAlignment(Pos.CENTER);
		vboxBottom.getChildren().addAll(labelRecup, hboxCorbeille);
		vboxBottom.setPadding(new Insets(20,20,20,20));
		vboxBottom.setAlignment(Pos.CENTER);
		monBP.setTop(labelCorbeille);
		monBP.setCenter(table);
		monBP.setBottom(vboxBottom);

		Scene maScene = new Scene(monBP, 800, 500);
		popUpCorbeille.setScene(maScene);
		popUpCorbeille.showAndWait();
	}
}
