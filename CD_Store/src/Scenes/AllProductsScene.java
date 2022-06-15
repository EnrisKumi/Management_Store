package Scenes;

import Backend_Part.CDs;
import Backend_Part.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AllProductsScene {

	Stage windowAP;
	Label titleLabel;
	Label cdLabel;
	Button cancelButton;
	VBox cdVBox;
	Scene scene;
	VBox layout;
	ObservableList<CDs> products;
	TableView<CDs> productTableView;

	public void showScene() {
		windowAP = new Stage();
		windowAP.initModality(Modality.APPLICATION_MODAL);

		titleLabel = new Label("All Available CDs");
		titleLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");

		setProductsTable();

		cancelButton = new Button("Exit");
		cancelButton
				.setStyle("-fx-background-color: #bb2020; -fx-font: normal bold 12px 'arial'; -fx-text-fill: white;");
		cancelButton.setOnAction(actionEvent -> {
			windowAP.close();
		});

		cdLabel = new Label("CDs");
		cdLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
		cdVBox = new VBox(10);
		cdVBox.getChildren().addAll(cdLabel, productTableView);

		Pane spacer = new Pane();
		HBox.setHgrow(spacer, Priority.ALWAYS);

		HBox titleHBox = new HBox();
		titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		titleHBox.setAlignment(Pos.CENTER);
		titleHBox.setPadding(new Insets(5, 5, 10, 5));
		titleHBox.getChildren().addAll(titleLabel, spacer, cancelButton);

		layout = new VBox(20);
		layout.setPadding(new Insets(5, 5, 10, 5));
		layout.getChildren().addAll(titleHBox, cdVBox);
		layout.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #303030, #101820FF);");

		scene = new Scene(layout, 660, 500);
		windowAP.setScene(scene);
		windowAP.showAndWait();

	}

	void setProductsTable() {
		TableColumn<CDs, String> idColumn = new TableColumn<>("ID");
		idColumn.setMinWidth(100);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

		TableColumn<CDs, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(100);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<CDs, String> genreColumn = new TableColumn<>("Genre");
		genreColumn.setMinWidth(100);
		genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

		TableColumn<CDs, String> priceColumn = new TableColumn<>("Price");
		priceColumn.setMinWidth(100);
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

		TableColumn<CDs, String> quantityColumn = new TableColumn<>("Quantity");
		quantityColumn.setMinWidth(100);
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

		TableColumn<CDs, String> barcodeColumn = new TableColumn<>("Barcode");
		barcodeColumn.setMinWidth(150);
		barcodeColumn.setCellValueFactory(new PropertyValueFactory<>("barcode"));

		products = FXCollections.observableArrayList();
		products.addAll(DataBase.getCDs());

		productTableView = new TableView<CDs>();
		productTableView.setItems(products);
		productTableView.getColumns().addAll(idColumn, nameColumn, genreColumn, priceColumn, quantityColumn,
				barcodeColumn);
	}
}
