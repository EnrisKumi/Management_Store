package Scenes;

import Backend_Part.DataBase;
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

public class ModifyUserScene {
	Stage window;
	Label titleLabel;
	Label errorLabel;
	Button editButton;
	Button cancelButton;
	Scene scene;
	VBox layout;
	ObservableList<Users> users;
	TableView<Users> usersTable;

	public void showScene() {
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);

		titleLabel = new Label("Users");
		titleLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");

		users = FXCollections.observableArrayList();
		users.addAll(DataBase.getCashiers());
		users.addAll(DataBase.getMenagers());
		setTable();

		errorLabel = new Label("");
		errorLabel.setStyle("-fx-text-fill: #ff5050;");

		editButton = new Button("Edit");
		editButton.setStyle(
				"-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");
		editButton.setPrefWidth(64);
		editButton.setOnAction(actionEvent -> editUser());

		Pane spacer2 = new Pane();
		HBox.setHgrow(spacer2, Priority.ALWAYS);
		HBox addHBox = new HBox(15);
		addHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		addHBox.setAlignment(Pos.CENTER);
		addHBox.getChildren().addAll(spacer2, errorLabel, editButton);

		cancelButton = new Button("Exit");
		cancelButton
				.setStyle("-fx-background-color: #bb2020; -fx-font: normal bold 12px 'arial'; -fx-text-fill: white;");
		cancelButton.setPrefWidth(64);
		cancelButton.setOnAction(actionEvent -> window.close());

		Pane spacer = new Pane();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		HBox titleHBox = new HBox();
		titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		titleHBox.setAlignment(Pos.CENTER);
		titleHBox.getChildren().addAll(titleLabel, spacer, cancelButton);

		layout = new VBox(20);
		layout.setPadding(new Insets(20));
		layout.getChildren().addAll(titleHBox, usersTable, addHBox);
		layout.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #303030, #101820FF);");

		scene = new Scene(layout, 380, 520);
		window.setScene(scene);
		window.showAndWait();
	}

	private void editUser() {
		if (!usersTable.getSelectionModel().isEmpty()) {
			errorLabel.setText("");
			Users user = usersTable.getSelectionModel().getSelectedItem();

			if (user.getAccsess_level().equals("Cashier"))
				new EditCashierScene().showScene(user);

			if (user.getAccsess_level().equals("Menager"))
				new EditMenagerScene().showScene(user);
		} else {
			errorLabel.setText("Select a user!");
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

		usersTable = new TableView<>();
		usersTable.setItems(users);
		usersTable.getColumns().addAll(idColumn, usernameColumn, roleColumn);
	}
}
