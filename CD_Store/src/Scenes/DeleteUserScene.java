package Scenes;

import Backend_Part.Cashiers;
import Backend_Part.DataBase;
import Backend_Part.Menagers;
import Backend_Part.Users;
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

public class DeleteUserScene {
	Stage window;
	Label titleLabel;
	Label errorLabel;
	Button deleteButton;
	Button cancelButton;
	Scene scene;
	VBox layout;
	ObservableList<Users> users;
	TableView<Users> usersTable;

	ObservableList<Cashiers> cashiers;
	TableView<Cashiers> cashiersTable;

	ObservableList<Menagers> menagers;
	TableView<Menagers> menagersTable;

	public void showScene() {
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);

		titleLabel = new Label("Select user");
		titleLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");

		setTable();

		errorLabel = new Label("");
		errorLabel.setStyle("-fx-text-fill: #ff5050;");

		Pane spacer = new Pane();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		deleteButton = new Button("Delete");
		deleteButton
				.setStyle("-fx-background-color: #bb2020; -fx-font: normal bold 12px 'arial'; -fx-text-fill: white;");
		deleteButton.setOnAction(actionEvent -> deleteUser());
		HBox deleteHBox = new HBox(20);
		deleteHBox.getChildren().addAll(spacer, errorLabel, deleteButton);

		cancelButton = new Button("Exit");
		cancelButton.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #303030, #101820FF);");
		cancelButton.setOnAction(actionEvent -> window.close());

		Pane spacer1 = new Pane();
		HBox.setHgrow(spacer1, Priority.ALWAYS);
		HBox titleHBox = new HBox();
		titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		titleHBox.setAlignment(Pos.CENTER);
		titleHBox.setPadding(new Insets(5, 0, 5, 0));
		titleHBox.getChildren().addAll(titleLabel, spacer1, cancelButton);

		layout = new VBox(20);
		layout.setPadding(new Insets(20));
		layout.getChildren().addAll(titleHBox, usersTable, deleteHBox);
		layout.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #303030, #101820FF);");

		scene = new Scene(layout, 345, 440);
		window.setScene(scene);
		window.showAndWait();
	}

	private void deleteUser() {
		String username = "";

		if (!usersTable.getSelectionModel().isEmpty()) {
			username = usersTable.getSelectionModel().getSelectedItem().getUsername();
			errorLabel.setText("");
		} else {
			errorLabel.setText("Select a user!");
			return;
		}

		for (int i = 0; i < DataBase.getCashiers().size(); i++) {
			Cashiers cashier = DataBase.getCashiers().get(i);

			if (username.equals(cashier.getUsername())) {
				DataBase.getCashiers().remove(i);
				setTable();
				layout.getChildren().set(1, usersTable);
				return;
			}
		}

		for (int i = 0; i < DataBase.getMenagers().size(); i++) {
			Menagers menager = DataBase.getMenagers().get(i);

			if (username.equals(menager.getUsername())) {
				DataBase.getMenagers().remove(i);
				setTable();
				layout.getChildren().set(1, usersTable);
				return;
			}
		}
	}

	void setTable() {
		TableColumn<Users, Integer> idColumn = new TableColumn<>("ID");
		idColumn.setMinWidth(100);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

		TableColumn<Users, String> usernameColumn = new TableColumn<>("Username");
		usernameColumn.setMinWidth(100);
		usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

		TableColumn<Users, String> roleColumn = new TableColumn<>("Role");
		roleColumn.setMinWidth(100);
		roleColumn.setCellValueFactory(new PropertyValueFactory<>("accsess_level"));

		users = FXCollections.observableArrayList();
		users.addAll(DataBase.getCashiers());
		users.addAll(DataBase.getMenagers());

		usersTable = new TableView<>();
		usersTable.setItems(users);
		usersTable.getColumns().addAll(idColumn, usernameColumn, roleColumn);
	}
}
