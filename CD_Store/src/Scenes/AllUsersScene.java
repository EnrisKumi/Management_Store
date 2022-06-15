package Scenes;

import Backend_Part.Cashiers;
import Backend_Part.DataBase;
import Backend_Part.Menagers;
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

public class AllUsersScene {
	Stage window;
	Label titleLabel;
	Label cashiersLabel;
	Label menagersLabel;
	Button cancelButton;
	VBox cashiersVBox;
	VBox menagersVBox;
	Scene scene;
	VBox layout;
	ObservableList<Cashiers> cashiers;
	TableView<Cashiers> cashiersTable;
	ObservableList<Menagers> menagers;
	TableView<Menagers> menagersTable;

	public void showScene() {
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);

		titleLabel = new Label("All Users");
		titleLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");

		setCashiersTableTable();
		setMenagersTable();

		cancelButton = new Button("Exit");
		cancelButton
				.setStyle("-fx-background-color: #bb2020; -fx-font: normal bold 12px 'arial'; -fx-text-fill: white;");
		cancelButton.setOnAction(actionEvent -> window.close());

		cashiersLabel = new Label("Cashiers");
		cashiersLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
		cashiersVBox = new VBox(20);
		cashiersVBox.getChildren().addAll(cashiersLabel, cashiersTable);

		menagersLabel = new Label("Menagers");
		menagersLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
		menagersVBox = new VBox(20);
		menagersVBox.getChildren().addAll(menagersLabel, menagersTable);

		Pane spacer = new Pane();
		HBox.setHgrow(spacer, Priority.ALWAYS);

		HBox titleHBox = new HBox();
		titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		titleHBox.setAlignment(Pos.CENTER);
		titleHBox.setPadding(new Insets(5, 5, 10, 5));
		titleHBox.getChildren().addAll(titleLabel, spacer, cancelButton);

		layout = new VBox(20);
		layout.setPadding(new Insets(20));
		layout.getChildren().addAll(titleHBox, cashiersVBox, menagersVBox);
		layout.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #303030, #101820FF);");

		scene = new Scene(layout, 1100, 720);
		window.setScene(scene);
		window.showAndWait();
	}

	void setCashiersTableTable() {
		TableColumn<Cashiers, Integer> idColumn = new TableColumn<>("ID");
		idColumn.setMinWidth(50);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

		TableColumn<Cashiers, String> usernameColumn = new TableColumn<>("Username");
		usernameColumn.setMinWidth(90);
		usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

		TableColumn<Cashiers, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(70);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Cashiers, String> surnameColumn = new TableColumn<>("Surname");
		surnameColumn.setMinWidth(70);
		surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

		TableColumn<Cashiers, String> phNrColumn = new TableColumn<>("Phone Number");
		phNrColumn.setMinWidth(100);
		phNrColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNr"));

		TableColumn<Cashiers, String> emailColumn = new TableColumn<>("Email");
		emailColumn.setMinWidth(150);
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

		TableColumn<Cashiers, Double> salaryColumn = new TableColumn<>("Salary");
		salaryColumn.setMinWidth(70);
		salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

		TableColumn<Cashiers, String> idCardNrColumn = new TableColumn<>("ID Card Number");
		idCardNrColumn.setMinWidth(100);
		idCardNrColumn.setCellValueFactory(new PropertyValueFactory<>("idnumber"));

		TableColumn<Cashiers, Double> moneyGenColumn = new TableColumn<>("Money Generated");
		moneyGenColumn.setMinWidth(100);
		moneyGenColumn.setCellValueFactory(new PropertyValueFactory<>("profit"));

		cashiers = FXCollections.observableArrayList();
		cashiers.addAll(DataBase.getCashiers());

		cashiersTable = new TableView<>();
		cashiersTable.setItems(cashiers);
		cashiersTable.getColumns().addAll(idColumn, usernameColumn, nameColumn, surnameColumn, phNrColumn, emailColumn,
				salaryColumn, idCardNrColumn, moneyGenColumn);
	}

	void setMenagersTable() {
		TableColumn<Menagers, Integer> idColumn = new TableColumn<>("ID");
		idColumn.setMinWidth(50);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

		TableColumn<Menagers, String> usernameColumn = new TableColumn<>("Username");
		usernameColumn.setMinWidth(90);
		usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

		TableColumn<Menagers, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(70);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Menagers, String> surnameColumn = new TableColumn<>("Surname");
		surnameColumn.setMinWidth(70);
		surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

		TableColumn<Menagers, String> phNrColumn = new TableColumn<>("Phone Number");
		phNrColumn.setMinWidth(100);
		phNrColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNr"));

		TableColumn<Menagers, String> emailColumn = new TableColumn<>("Email");
		emailColumn.setMinWidth(150);
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

		TableColumn<Menagers, Double> salaryColumn = new TableColumn<>("Salary");
		salaryColumn.setMinWidth(70);
		salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

		TableColumn<Menagers, String> idCardNrColumn = new TableColumn<>("ID Card Number");
		idCardNrColumn.setMinWidth(100);
		idCardNrColumn.setCellValueFactory(new PropertyValueFactory<>("idnumber"));

		menagers = FXCollections.observableArrayList();
		menagers.addAll(DataBase.getMenagers());

		menagersTable = new TableView<>();
		menagersTable.setItems(menagers);
		menagersTable.getColumns().addAll(idColumn, usernameColumn, nameColumn, surnameColumn, phNrColumn, emailColumn,
				salaryColumn, idCardNrColumn);
	}
}
