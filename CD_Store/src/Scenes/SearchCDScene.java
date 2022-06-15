package Scenes;

import Backend_Part.CDs;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SearchCDScene {
	CDs product;
	Stage window;
	Scene scene;
	VBox layout;
	Label productLabel;
	Label quantityLabel;
	Label spinnerLabel;
	Label errorLabel;
	Spinner<Integer> quantitySpinner;
	Button addButton;
	Button cancelButton;
	int quantity;

	public int showScene(CDs product) {
		this.product = product;

		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);

		setLayout();
		quantity = 0;

		addButton.setOnAction(actionEvent -> addButtonClicked());
		cancelButton.setOnAction(actionEvent -> window.close());

		layout.setPadding(new Insets(20, 20, 10, 20));
		scene = new Scene(layout, 280, 240);
		window.setScene(scene);
		window.showAndWait();

		return quantity;
	}

	void addButtonClicked() {
		if (quantitySpinner.getValue() < 1)
			errorLabel.setText("Select a number!");
		else {
			quantity = quantitySpinner.getValue();
			window.close();
		}
	}

	void setLayout() {
		layout = new VBox(20);
		layout.setStyle("-fx-background-color: #303030;");

		productLabel = new Label("");
		productLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		productLabel.setAlignment(Pos.CENTER);
		productLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");

		quantityLabel = new Label("");
		quantityLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		quantityLabel.setAlignment(Pos.CENTER);
		quantityLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");

		spinnerLabel = new Label("Select quantity:");

		errorLabel = new Label("");
		errorLabel.setStyle("-fx-text-fill: #ff5050;");
		errorLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		errorLabel.setAlignment(Pos.CENTER_RIGHT);

		addButton = new Button("Add to cart");
		addButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		addButton.setStyle(
				"-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");

		cancelButton = new Button("Cancel");
		cancelButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		cancelButton
				.setStyle("-fx-background-color: #bb2020; -fx-font: normal bold 12px 'arial'; -fx-text-fill: white;");

		if (product.getName().equals("")) {
			productLabel.setText("No results");
			VBox vBox = new VBox(10);
			vBox.setPadding(new Insets(70));
			vBox.getChildren().addAll(productLabel, cancelButton);
			layout.getChildren().addAll(vBox);
		} else {
			productLabel.setText(product.getName());

			if (product.getQuantity() > 0) {
				quantityLabel.setText("Available: " + product.getQuantity());
				quantitySpinner = new Spinner<>(0, product.getQuantity(), 0);

				Pane spacer = new Pane();
				HBox.setHgrow(spacer, Priority.ALWAYS);
				Pane spacer1 = new Pane();
				HBox.setHgrow(spacer1, Priority.ALWAYS);
				Pane spacer2 = new Pane();
				HBox.setHgrow(spacer2, Priority.ALWAYS);

				HBox productHBox = new HBox(10); // productLabel, quantityLabel
				productHBox.getChildren().addAll(spacer, productLabel, quantityLabel);

				VBox spinnerVBox = new VBox(10); // spinnerLabel, quantityLabel, errorLabel
				spinnerVBox.getChildren().addAll(spinnerLabel, quantitySpinner, errorLabel);
				HBox spinnerHBox = new HBox();
				spinnerHBox.getChildren().addAll(spacer1, spinnerVBox);

				VBox buttonsVBox = new VBox(15); // addButton, cancelButton
				buttonsVBox.getChildren().addAll(addButton, cancelButton);
				HBox buttonsHBox = new HBox();
				buttonsHBox.getChildren().addAll(spacer2, buttonsVBox);

				layout.getChildren().addAll(productHBox, spinnerHBox, buttonsHBox);
			} else {
				quantityLabel.setText("This product is out of stock.");
				VBox vBox = new VBox(20);
				vBox.setPadding(new Insets(50));
				vBox.getChildren().addAll(productLabel, quantityLabel, cancelButton);
				layout.getChildren().addAll(vBox);
			}
		}
	}
}
