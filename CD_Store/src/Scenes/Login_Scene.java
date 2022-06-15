package Scenes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Backend_Part.Cashiers;
import Backend_Part.DataBase;
import Backend_Part.Menagers;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Login_Scene {

	static Stage loginn;
	static Label incorrect;

	public static void loginscene(Stage loginn) throws FileNotFoundException {
		Login_Scene.loginn = loginn;
		Image img = new Image(new FileInputStream("Images/logo.jpeg"));
		ImageView imageView = new ImageView(img);
		imageView.setFitWidth(540);
		imageView.setFitHeight(400);
		imageView.setPreserveRatio(true);

		Label usernameLabel = new Label("Username: ");
		usernameLabel.setMaxSize(128, 128);
		usernameLabel.setStyle("-fx-background-color: #505050; -fx-text-fill: white; -fx-prompt-text-fill: #000000;");

		TextField userField = new TextField();
		userField.setFont(Font.font("Times New Roman"));
		userField.setPromptText("Enter your username: ");
		userField.setMaxWidth(200);
		
		incorrect = new Label("");
		incorrect.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		incorrect.setAlignment(Pos.CENTER);
		incorrect.setStyle("-fx-text-fill: #ff5050;");

		Label passLabel = new Label("Password: ");
		passLabel.setMaxSize(128, 128);
		passLabel.setStyle("-fx-background-color: #505050; -fx-text-fill: white; -fx-prompt-text-fill: #000000;");

		PasswordField passField = new PasswordField();
		passField.setFont(Font.font("Times New Toman"));
		passField.setPromptText("Enter your password: ");
		passField.setMaxWidth(200);

		Button loginBt = new Button("Login");
		loginBt.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		loginBt.setAlignment(Pos.CENTER);
		loginBt.setStyle("-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");

		Button exitBt = new Button("Exit");
		exitBt.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		exitBt.setAlignment(Pos.CENTER);
		exitBt.setStyle("-fx-background-color: #bb2020; -fx-font: normal bold 12px 'arial'; -fx-text-fill: white; ");
		

		FlowPane usernameInputs = new FlowPane();
		usernameInputs.setPadding(new Insets(10, 50, 0, 50));
		usernameInputs.setAlignment(Pos.CENTER);
		usernameInputs.getChildren().addAll(usernameLabel, userField);

		FlowPane passwordInput = new FlowPane();
		passwordInput.setPadding(new Insets(10, 50, 10, 50));
		passwordInput.setAlignment(Pos.CENTER);
		passwordInput.getChildren().addAll(passLabel, passField);

		VBox inputVbox = new VBox();
		inputVbox.setPadding(new Insets(10, 10, 10, 10));
		inputVbox.getChildren().addAll(imageView, usernameInputs, passwordInput);

		HBox buttonVbox = new HBox(10);
		buttonVbox.setPadding(new Insets(0, 50, 0, 50));
		buttonVbox.setAlignment(Pos.CENTER);
		buttonVbox.getChildren().addAll(loginBt, exitBt);

		VBox layout = new VBox(10);
		layout.setPadding(new Insets(30, 40, 10, 40));
		layout.getChildren().addAll(inputVbox,incorrect, buttonVbox);

		loginBt.setOnAction(e -> {
			try {
				login(userField.getText(), passField.getText());
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
		});

		exitBt.setOnAction(e -> {
			DataBase.save();
			loginn.close();
		});
		
		layout.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #303030, #101820FF);");
		Scene sc = new Scene(layout, 635, 480);
		Login_Scene.loginn.setScene(sc);

	}

	static void login(String username, String password) throws FileNotFoundException {
		if (username.equals("Admin"))
			if (password.equals("Admin123")) {
				new AdminScene().showScene(loginn);
				return;
			}

		for (Cashiers c : DataBase.getCashiers())
			if (username.equals(c.getUsername()))
				if (password.equals(c.getPassword())) {
					new CashierScene().showScene(c, loginn);
				}
		
		for(Menagers m: DataBase.getMenagers())
			if(username.equals(m.getUsername()))
				if(password.equals(m.getPassword())) {
					new MenagerScene().showScene(m, loginn);
				}
		incorrect.setText("Incorrect name or password!");
	}

}
