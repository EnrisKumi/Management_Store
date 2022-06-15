package Scenes;

import Backend_Part.DataBase;
import Backend_Part.Suppliers;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SuppliersListScene {
	Stage window;
	Scene scene;
	Label title;
	Label errorLabel;
	Button viewButton;
	Button closeButton;
	VBox layout;

	ListView<String> supplierList;

	public void showScene() {
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);

		title = new Label("Suppliers");
		title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");

		setSupplierListView();

		errorLabel = new Label("");
		errorLabel.setStyle("-fx-text-fill: #ff5050;");

		viewButton = new Button("View Products");
		viewButton.setStyle(
				"-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");
		viewButton.setPrefWidth(92);
		viewButton.setOnAction(actionEvent -> viewButtonPressed());

		Pane spacer2 = new Pane();
		HBox.setHgrow(spacer2, Priority.ALWAYS);
		HBox viewHBox = new HBox(15);
		viewHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		viewHBox.setAlignment(Pos.CENTER);
		viewHBox.setPadding(new Insets(5, 0, 5, 0));
		viewHBox.getChildren().addAll(spacer2, errorLabel, viewButton);

		closeButton = new Button("Close");
		closeButton.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #303030, #101820FF);");
		closeButton.setPrefWidth(76);
		closeButton.setOnAction(actionEvent -> window.close());

		Pane spacer = new Pane();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		HBox titleHBox = new HBox();
		titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		titleHBox.setAlignment(Pos.CENTER);
		titleHBox.setPadding(new Insets(5, 0, 5, 0));
		titleHBox.getChildren().addAll(title, spacer, closeButton);

		layout = new VBox(20);
		layout.setPadding(new Insets(20));
		layout.getChildren().addAll(titleHBox, supplierList, viewHBox);
		layout.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #303030, #101820FF);");

		scene = new Scene(layout, 360, 552);
		window.setScene(scene);
		window.showAndWait();
	}

	void setSupplierListView() {
		supplierList = new ListView<>();
		for (Suppliers s : DataBase.getSuppliers())
			supplierList.getItems().add(s.getName());
	}

	void viewButtonPressed() {
		String name = "";

		if (!supplierList.getSelectionModel().isEmpty()) {
			errorLabel.setText("");
			name = supplierList.getSelectionModel().getSelectedItem();
			new SupplierCDScene().showScene(name);
		} else
			errorLabel.setText("Select a supplier!");
	}
}
