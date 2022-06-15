package Scenes;

import Backend_Part.CD_bought;
import Backend_Part.CDs;
import Backend_Part.DataBase;
import Backend_Part.InputCheck;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class IncreaseQuantityScene {
	Stage window;
	Scene scene;
	Label title;
	Button addButton;
	TextField quantityTextField;
	Label selItemErrorLabel;
	Label notIntErrorLabel;
	VBox tableVBox;
	Button closeButton;
	HBox quantityHBox;
	VBox layout;

	ObservableList<CDs> productsList;
	TableView<CDs> productTable;

	public void showScene() {
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);

		title = new Label("Add Cd Quantity");
		title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");

		productsList = FXCollections.observableArrayList();
		productsList.addAll(DataBase.getCDs());
		setTable();

		selItemErrorLabel = new Label("");
		selItemErrorLabel.setStyle("-fx-text-fill: #ff5050;");

		tableVBox = new VBox(5);
		tableVBox.getChildren().addAll(productTable, selItemErrorLabel);

		quantityTextField = new TextField();
		quantityTextField.setPromptText("Quantity");
		quantityTextField
				.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");

		notIntErrorLabel = new Label("");
		notIntErrorLabel.setStyle("-fx-text-fill: #ff5050;");

		addButton = new Button("Add");
		addButton.setPrefWidth(76);
		addButton.setStyle(
				"-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");
		addButton.setOnAction(actionEvent -> addProductQuantity());

		quantityHBox = new HBox(10);
		quantityHBox.setMaxWidth(Double.MAX_VALUE);
		quantityHBox.setAlignment(Pos.CENTER_RIGHT);
		quantityHBox.getChildren().addAll(notIntErrorLabel, quantityTextField, addButton);

		closeButton = new Button("Close");
		closeButton.setPrefWidth(76);
		closeButton
				.setStyle("-fx-background-color: #bb2020; -fx-font: normal bold 12px 'arial'; -fx-text-fill: white;");
		closeButton.setOnAction(actionEvent -> window.close());

		Pane spacer = new Pane();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		HBox titleHBox = new HBox();
		titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		titleHBox.setPadding(new Insets(0, 0, 10, 0));
		titleHBox.setAlignment(Pos.CENTER);
		titleHBox.getChildren().addAll(title, spacer, closeButton);

		layout = new VBox(20);
		layout.setPadding(new Insets(20));
		layout.getChildren().addAll(titleHBox, tableVBox, quantityHBox);
		layout.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #303030, #101820FF);");

		scene = new Scene(layout, 376, 568);
		window.setScene(scene);
		window.showAndWait();
	}

	void addProductQuantity() {
		String name = "";
		int quantity = 0;
		String input = quantityTextField.getText();
		boolean isFieldCorrect = true;

		if (!productTable.getSelectionModel().isEmpty()) {
			name = productTable.getSelectionModel().getSelectedItem().getName();
			selItemErrorLabel.setText("");
		} else {
			selItemErrorLabel.setText("Select a product!");
			isFieldCorrect = false;
		}

		if (!input.isEmpty() && InputCheck.isInt(input)) {
			quantity = Integer.parseInt(input);
			notIntErrorLabel.setText("");
		} else {
			notIntErrorLabel.setText("Enter a number!");
			isFieldCorrect = false;
		}

		if (isFieldCorrect) {
			for (CDs addedProduct : DataBase.getCDs())
				if (name.equals(addedProduct.getName())) {
					
					
					DataBase.getCD_bought().add(new CD_bought());
					int addedBoughtProdIndex = DataBase.getCD_bought().size() - 1;
					CD_bought addedBoughtProd = DataBase.getCD_bought().get(addedBoughtProdIndex);

					addedBoughtProd.setName(addedProduct.getName());
					addedBoughtProd.setId(addedProduct.getID());
					addedBoughtProd.setGenre(addedProduct.getGenre());
					addedBoughtProd.setSupplierId(addedProduct.getSupplierID());
					addedBoughtProd.setPrice(addedProduct.getPrice());
					addedBoughtProd.setBarcode(addedProduct.getBarcode());
					addedBoughtProd.setQuantity(quantity);
					addedBoughtProd.setReleaseDate(addedProduct.getReleaseDate());
					addedProduct.setQuantity(addedProduct.getQuantity() + quantity);
					break;
				}

			setTable();
			tableVBox.getChildren().set(0, productTable);
		}

		quantityTextField.clear();
	}

	void setTable() {
		TableColumn<CDs, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<CDs, Integer> quantityColumn = new TableColumn<>("Quantity");
		quantityColumn.setMinWidth(70);
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

		productTable = new TableView<>();
		productTable.setItems(productsList);
		productTable.getColumns().addAll(nameColumn, quantityColumn);
	}
}
