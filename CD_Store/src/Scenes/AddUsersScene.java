package Scenes;

import Backend_Part.Cashiers;
import Backend_Part.DataBase;
import Backend_Part.DateChoiceBox;
import Backend_Part.Dates_Statistics;
import Backend_Part.InputCheck;
import Backend_Part.Menagers;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddUsersScene {
	Label title;
	Button cancel;
	VBox layout;
	static Stage AddUsersStage;
	TextField usernameField;
	Label usernameError;
	PasswordField passField;
	Label passError;
	PasswordField repeatPass;
	Label repeatPassError;
	TextField nameField;
	Label nameError;
	ChoiceBox<String> levelChoiceBox;
	Label levelError;
	TextField phoneNr;
	Label phoneNrError;
	TextField surnameField;
	Label surnameError;

	Label yearLabel;
	ChoiceBox<String> yearChoice;
	Label monthLabel;
	ChoiceBox<String> monthChoice;
	Label dayLabel;
	ChoiceBox<String> dayChoice;
	Label bdayError;
	VBox yearVBox;
	VBox monthVBok;
	VBox dayVBox;

	TextField emailField;
	Label emailError;
	TextField salaryField;
	Label salaryError;
	TextField idField;
	Label idError;

	VBox usernameHBox;
	VBox passwordHBox;
	VBox repeatPassHBox;
	VBox nameHBox;
	VBox surnameHBox;
	VBox roleHBox;
	VBox phoneNumberHBox;
	HBox birthdayHBox;
	VBox emailHBox;
	VBox salaryHBox;
	VBox idCardNrHBox;

	Button addButton;

	public void showScene() {
		AddUsersStage = new Stage();
		title = new Label("Add User");
		title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
		setFields();

		monthChoice.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends String> observable, String oldValue, String newValue) -> DateChoiceBox
						.addDaysInDayChBox(dayChoice, monthChoice, yearChoice, 1950, 2010));
		yearChoice.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends String> observable, String oldValue, String newValue) -> DateChoiceBox
						.addDaysInDayChBox(dayChoice, monthChoice, yearChoice, 1950, 2010));

		addButton = new Button("Add");
		addButton.setStyle(
				"-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");
		addButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		addButton.setOnAction(actionEvent -> addUser());

		cancel = new Button("Cancel");
		cancel.setStyle("-fx-background-color: #bb2020; -fx-font: normal bold 12px 'arial'; -fx-text-fill: white;");
		cancel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		cancel.setOnAction(actionEvent -> AddUsersStage.close());

		VBox addButtonHBox = new VBox();
		addButtonHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		addButtonHBox.setPadding(new Insets(0, 150, 0, 150));
		addButtonHBox.setAlignment(Pos.CENTER);
		addButtonHBox.getChildren().add(addButton);

		Pane spacer = new Pane();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		HBox titleHBox = new HBox();
		titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		titleHBox.setPadding(new Insets(0, 0, 15, 0));
		titleHBox.setAlignment(Pos.CENTER);
		titleHBox.getChildren().addAll(title, spacer, cancel);

		layout = new VBox(16);
		layout.setPadding(new Insets(20));
		layout.getChildren().addAll(titleHBox, usernameHBox, passwordHBox, repeatPassHBox, nameHBox, surnameHBox,
				roleHBox, phoneNumberHBox, birthdayHBox, emailHBox, salaryHBox, idCardNrHBox, addButtonHBox);
		layout.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #303030, #101820FF);");

		Scene sc = new Scene(layout, 480, 760);

		AddUsersStage.setScene(sc);
		AddUsersStage.showAndWait();
	}

	void addUser() {
		String username = "";
		String surname = "";
		String password = "";
		String name = "";
		String level = "";
		String phoneNumber = "";
		int year = 0;
		int month = 0;
		int day = 0;
		String email = "";
		double salary = 0.0;
		String idCardNr = "";

		boolean areFieldsCorrect = true;

		// check username input
		if (!usernameField.getText().isEmpty() && InputCheck.isUsernameValid(usernameField.getText())) {
			if (InputCheck.doesUsernameExists(usernameField.getText())) {
				areFieldsCorrect = false;
				usernameError.setText("This username already exists.");
			} else {
				username = usernameField.getText();
				usernameError.setText("");
			}
		} else {
			areFieldsCorrect = false;
			usernameError.setText("The only values that are allowed are: letters, digits and - _ .");
		}

		// check surname input
		if (!surnameField.getText().isEmpty() && InputCheck.isSurnameValid(surnameField.getText())) {
			surname = surnameField.getText();
			surnameError.setText("");
		} else {
			areFieldsCorrect = false;
			surnameError.setText("Enter surname!");
		}

		// check password input
		if (!passField.getText().isEmpty() && passField.getText().length() >= 8) {
			password = passField.getText();
			passError.setText("");
		} else {
			areFieldsCorrect = false;
			passError.setText("Enter a password!");
		}

		// check repeat password input
		if (!repeatPass.getText().equals(password)) {
			areFieldsCorrect = false;
			repeatPassError.setText("Password doesn't match.");
		} else
			repeatPassError.setText("");

		// check name input
		if (!nameField.getText().isEmpty() && InputCheck.isNameValid(nameField.getText())) {
			name = nameField.getText();
			nameError.setText("");
		} else {
			areFieldsCorrect = false;
			nameError.setText("Enter name!");
		}

		// check role input
		if (!levelChoiceBox.getValue().equals("----------")) {
			level = levelChoiceBox.getValue();
			levelError.setText("");
		} else {
			areFieldsCorrect = false;
			levelError.setText("Select a role!");
		}

		// check phoneNumberHBox input
		if (!phoneNr.getText().isEmpty() && InputCheck.isPhoneNrValid(phoneNr.getText())) {
			phoneNumber = phoneNr.getText();
			phoneNrError.setText("");
		} else {
			areFieldsCorrect = false;
			phoneNrError.setText("Enter 10 digits only!");
		}

		// check date
		if (!yearChoice.getValue().equals("----------") && !monthChoice.getValue().equals("----------")
				&& !dayChoice.getValue().equals("----------")) {
			year = Integer.parseInt(yearChoice.getValue());
			month = Integer.parseInt(monthChoice.getValue());
			day = Integer.parseInt(dayChoice.getValue());
			bdayError.setText("");
		} else {
			areFieldsCorrect = false;
			bdayError.setText("Select Date of Birth!");
		}

		// check email input
		if (!emailField.getText().isEmpty()) {
			email = emailField.getText();
			emailError.setText("");
		} else {
			areFieldsCorrect = false;
			emailError.setText("Enter email!");
		}

		// check salary input
		if (!salaryField.getText().isEmpty() && InputCheck.isDouble(salaryField.getText())) {
			salary = Double.parseDouble(salaryField.getText());
			salaryError.setText("");
		} else {
			areFieldsCorrect = false;
			salaryError.setText("Enter salary!");
		}

		// check idCardNr input
		if (!idField.getText().isEmpty() && idField.getText().length() == 10) {
			idCardNr = idField.getText();
			idError.setText("");
		} else {
			areFieldsCorrect = false;
			idError.setText("Enter ID Card Number!");
		}

		if (areFieldsCorrect) {
			if (level.equals("Cashier")) {
				DataBase.getCashiers().add(new Cashiers());
				int addedCashierIndex = DataBase.getCashiers().size() - 1;
				Cashiers addedUser = DataBase.getCashiers().get(addedCashierIndex);

				addedUser.setUsername(username);
				addedUser.setPassword(password);
				addedUser.setName(name);
				addedUser.setSurname(surname);
				addedUser.setAccsess_level(level);
				addedUser.setPhoneNr(phoneNumber);
				addedUser.setEmail(email);
				addedUser.setSalary(salary);
				addedUser.setIdnumber(idCardNr);
				addedUser.setBday(new Dates_Statistics(day, month, year));
			}

			if (level.equals("Menager")) {
				DataBase.getMenagers().add(new Menagers());
				int addedCashierIndex = DataBase.getMenagers().size() - 1;
				Menagers addedUser = DataBase.getMenagers().get(addedCashierIndex);

				addedUser.setUsername(username);
				addedUser.setPassword(password);
				addedUser.setName(name);
				addedUser.setSurname(surname);
				addedUser.setAccsess_level(level);
				addedUser.setPhoneNr(phoneNumber);
				addedUser.setEmail(email);
				addedUser.setSalary(salary);
				addedUser.setIdnumber(idCardNr);
				;
				addedUser.setBday(new Dates_Statistics(day, month, year));
			}

			AddUsersStage.close();
		}
	}

	void setFields() {
		usernameField = new TextField();
		usernameField.setPromptText("Username");
		usernameField.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
		usernameError = new Label("");
		usernameError.setStyle("-fx-text-fill: #ff5050;");
		usernameHBox = new VBox();
		usernameHBox.getChildren().addAll(usernameField, usernameError);

		passField = new PasswordField();
		passField.setPromptText("Password");
		passField.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
		passError = new Label("");
		passError.setStyle("-fx-text-fill: #ff5050;");
		passwordHBox = new VBox();
		passwordHBox.getChildren().addAll(passField, passError);

		repeatPass = new PasswordField();
		repeatPass.setPromptText("Confirm password");
		repeatPass.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
		repeatPassError = new Label("");
		repeatPassError.setStyle("-fx-text-fill: #ff5050;");
		repeatPassHBox = new VBox();
		repeatPassHBox.getChildren().addAll(repeatPass, repeatPassError);

		nameField = new TextField();
		nameField.setPromptText("Name");
		nameField.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
		nameError = new Label("");
		nameError.setStyle("-fx-text-fill: #ff5050;");
		nameHBox = new VBox();
		nameHBox.getChildren().addAll(nameField, nameError);

		surnameField = new TextField();
		surnameField.setPromptText("Surname");
		surnameField.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
		surnameError = new Label("");
		surnameError.setStyle("-fx-text-fill: #ff5050;");
		surnameHBox = new VBox();
		surnameHBox.getChildren().addAll(surnameField, surnameError);

		levelChoiceBox = new ChoiceBox<>();
		levelChoiceBox.getItems().addAll("----------", "Cashier", "Menager");
		levelChoiceBox.setValue("----------");
		levelChoiceBox.setMaxWidth(Double.MAX_VALUE);
		levelError = new Label("");
		levelError.setStyle("-fx-text-fill: #ff5050;");
		roleHBox = new VBox();
		roleHBox.getChildren().addAll(levelChoiceBox, levelError);

		phoneNr = new TextField();
		phoneNr.setPromptText("Phone number");
		phoneNr.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
		phoneNrError = new Label("");
		phoneNrError.setStyle("-fx-text-fill: #ff5050;");
		phoneNumberHBox = new VBox();
		phoneNumberHBox.getChildren().addAll(phoneNr, phoneNrError);

		// ----------------------------------------------------------------------
		yearLabel = new Label("Year");
		yearLabel.setMaxWidth(Double.MAX_VALUE);
		yearLabel.setAlignment(Pos.CENTER);
		yearLabel.setStyle("-fx-text-fill: #eeeeee;");

		yearChoice = new ChoiceBox<>();
		yearChoice.getItems().add("----------");
		DateChoiceBox.addYearsInYearChBox(yearChoice, 1950, 2010);
		yearChoice.setValue("----------");
		yearChoice.setMaxWidth(Double.MAX_VALUE);

		monthLabel = new Label("Month");
		monthLabel.setStyle("-fx-text-fill: #eeeeee;");
		monthLabel.setMaxWidth(Double.MAX_VALUE);
		monthLabel.setAlignment(Pos.CENTER);

		monthChoice = new ChoiceBox<>();
		monthChoice.getItems().add("----------");
		DateChoiceBox.addMonthsInMonthChBox(monthChoice);
		monthChoice.setValue("----------");
		monthChoice.setMaxWidth(Double.MAX_VALUE);

		dayLabel = new Label("Day");
		dayLabel.setStyle("-fx-text-fill: #eeeeee;");
		dayLabel.setMaxWidth(Double.MAX_VALUE);
		dayLabel.setAlignment(Pos.CENTER);

		dayChoice = new ChoiceBox<>();
		dayChoice.getItems().add("----------");
		dayChoice.setValue("----------");
		dayChoice.setMaxWidth(Double.MAX_VALUE);

		bdayError = new Label("");
		bdayError.setStyle("-fx-text-fill: #ff5050;");

		yearVBox = new VBox(5);
		yearVBox.getChildren().addAll(yearChoice, yearLabel);
		monthVBok = new VBox(5);
		monthVBok.getChildren().addAll(monthChoice, monthLabel);
		dayVBox = new VBox(5);
		dayVBox.getChildren().addAll(dayChoice, dayLabel);

		birthdayHBox = new HBox(5);
		birthdayHBox.setMaxWidth(Double.MAX_VALUE);
		birthdayHBox.getChildren().addAll(yearVBox, monthVBok, dayVBox, bdayError);
		// ------------------------------------------------------------------------------

		emailField = new TextField();
		emailField.setPromptText("E-mail");
		emailField.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
		emailError = new Label("");
		emailError.setStyle("-fx-text-fill: #ff5050;");
		emailHBox = new VBox();
		emailHBox.getChildren().addAll(emailField, emailError);

		salaryField = new TextField();
		salaryField.setPromptText("Salary");
		salaryField.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
		salaryError = new Label("");
		salaryError.setStyle("-fx-text-fill: #ff5050;");
		salaryHBox = new VBox();
		salaryHBox.getChildren().addAll(salaryField, salaryError);

		idField = new TextField();
		idField.setPromptText("ID card number");
		idField.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
		idError = new Label("");
		idError.setStyle("-fx-text-fill: #ff5050;");
		idCardNrHBox = new VBox();
		idCardNrHBox.getChildren().addAll(idField, idError);
	}
}
