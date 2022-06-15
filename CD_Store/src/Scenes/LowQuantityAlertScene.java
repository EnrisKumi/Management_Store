package Scenes;

import Backend_Part.CDs;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LowQuantityAlertScene {
	Stage window;
	Scene scene;
	Label titleLabel;
	Button closeButton;
	ObservableList<CDs> products;
	TableView<CDs> productsTable;

	public void showScene(ObservableList<CDs> products) {
		this.products = products;

		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);

		titleLabel = new Label("The following CDs have a less than 5 quantity");
		titleLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
		setTable();

		closeButton = new Button("Close");
		closeButton
				.setStyle("-fx-background-color: #bb2020; -fx-font: normal bold 12px 'arial'; -fx-text-fill: white;");
		closeButton.setOnAction(actionEvent -> window.close());

		VBox layout = new VBox(20);
		layout.getChildren().addAll(titleLabel, productsTable, closeButton);
		layout.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #303030, #101820FF);");

		scene = new Scene(layout, 540, 480);
		window.setScene(scene);
		window.showAndWait();
	}

	void setTable() {
		TableColumn<CDs, String> nameColumn = new TableColumn<>("Product");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<CDs, Integer> quantityColumn = new TableColumn<>("Quantity");
		quantityColumn.setMinWidth(70);
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

		productsTable = new TableView<>();
		productsTable.setItems(products);
		productsTable.getColumns().addAll(nameColumn, quantityColumn);
	}
}
