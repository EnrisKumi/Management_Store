package Scenes;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AdminScene extends MenagerScene {
	Button allUsersButton;
	Button addUserButton;
	Button modifyUserButton;
	Button deleteUserButton;
	Button incomesButton;
	Button investmentsButton;
	HBox economyHBox;

	public void showScene(Stage window) throws FileNotFoundException {
		Image image = new Image(new FileInputStream("Images/adminimage.png"));
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(38);
		imageView.setPreserveRatio(true);

		title = new Label("Administrator");
		title.setGraphic(imageView);

		setLayout(window);
		setAdminLayout();

		Pane spacer = new Pane();
		HBox.setHgrow(spacer, Priority.ALWAYS);

		layout = new VBox(35);
		layout.setPadding(new Insets(10, 30, 30, 30));
		layout.getChildren().addAll(titleHBox, allButtons, spacer);
		layout.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #303030, #101820FF);");

		scene = new Scene(layout, 480, 350);
		window.setScene(scene);

		lowQuantityProds = FXCollections.observableArrayList();
		checkProductQuantity();
	}

	void setAdminLayout() {
		allUsersButton = new Button("All Users");
		allUsersButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		allUsersButton.setPrefWidth(128);
		allUsersButton.setAlignment(Pos.CENTER);
		allUsersButton.setStyle(
				"-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");
		allUsersButton.setOnAction(actionEvent -> new AllUsersScene().showScene());

		addUserButton = new Button("Add User");
		addUserButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		addUserButton.setPrefWidth(128);
		addUserButton.setAlignment(Pos.CENTER);
		addUserButton.setStyle(
				"-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");
		addUserButton.setOnAction(actionEvent -> new AddUsersScene().showScene());

		modifyUserButton = new Button("Modify User");
		modifyUserButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		modifyUserButton.setPrefWidth(128);
		modifyUserButton.setAlignment(Pos.CENTER);
		modifyUserButton.setStyle(
				"-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");
		modifyUserButton.setOnAction(actionEvent -> new ModifyUserScene().showScene());

		deleteUserButton = new Button("Delete User");
		deleteUserButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		deleteUserButton.setPrefWidth(128);
		deleteUserButton.setAlignment(Pos.CENTER);
		deleteUserButton.setStyle(
				"-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");
		deleteUserButton.setOnAction(actionEvent -> new DeleteUserScene().showScene());

		Pane spacer = new Pane();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		titleHBox = new HBox();
		titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		titleHBox.setAlignment(Pos.CENTER);
		titleHBox.setPadding(new Insets(5, 0, 5, 0));
		titleHBox.getChildren().addAll(title, spacer, logoutButton);

		VBox usersVBox = new VBox(10);
		usersVBox.getChildren().addAll(allUsersButton, addUserButton, modifyUserButton, deleteUserButton);

		allButtons = new HBox(30);
		allButtons.getChildren().addAll(usersVBox, addVBox, statsVBox);
	}
}
