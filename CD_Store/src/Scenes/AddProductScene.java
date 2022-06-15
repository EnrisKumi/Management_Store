package Scenes;

import Backend_Part.CD_bought;
import Backend_Part.CDs;
import Backend_Part.DataBase;
import Backend_Part.Dates_Statistics;
import Backend_Part.InputCheck;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddProductScene {
	Stage window;
	Scene scene;
	VBox layout;
	Label title;
	Button exitButton;

	TextField nameTxtField;
	Label fillNameError;
	TextField singerField;
	Label singerError;
	ChoiceBox<String> genreChBox;
	Label fillGenreError;
	TextField supplierIDTxtField;
	Label fillSupplIDError;
	TextField priceTxtField;
	Label fillPriceError;
	TextField barcodeTxtField;
	Label fillBarcodeError;
	TextField quantityTxtField;
	Label fillQuantityError;

	Label yearLabel;
	ChoiceBox<String> yearChoiceBox;
	Label monthLabel;
	ChoiceBox<String> monthChoiceBox;
	Label dayLabel;
	ChoiceBox<String> dayChoiceBox;
	Label expiryDateErrLabel;

	VBox yearVBox;
	VBox monthVBok;
	VBox dayVBox;

	VBox nameHBox;
	VBox categoryHBox;
	VBox supplierIDHBox;
	VBox priceHBox;
	VBox barcodeHBox;
	VBox quantityHBox;
	HBox expiryDateHBox;
	VBox singerHBox;

	Button addButton;

	public void showScene() {
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);

		setFields();

		monthChoiceBox.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue,
						String newValue) -> addDaysInDayChBox());
		yearChoiceBox.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue,
						String newValue) -> addDaysInDayChBox());

		addButton = new Button("Add CD");
		addButton.setStyle(
				"-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");
		addButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		addButton.setOnAction(actionEvent -> addButtonPressed());

		VBox addButtonHBox = new VBox();
		addButtonHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		addButtonHBox.setPadding(new Insets(0, 150, 0, 150));
		addButtonHBox.setAlignment(Pos.CENTER);
		addButtonHBox.getChildren().add(addButton);

		exitButton = new Button("Cancel");
		exitButton.setStyle("-fx-background-color: #bb2020; -fx-font: normal bold 12px 'arial'; -fx-text-fill: white;");
		exitButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		exitButton.setOnAction(actionEvent -> window.close());

		Pane spacer = new Pane();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		HBox titleHBox = new HBox();
		titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		titleHBox.setPadding(new Insets(0, 0, 15, 0));
		titleHBox.setAlignment(Pos.CENTER);
		titleHBox.getChildren().addAll(title, spacer, exitButton);

		layout = new VBox(16);
		layout.setPadding(new Insets(20));
		layout.getChildren().addAll(titleHBox, nameHBox, singerHBox, categoryHBox, supplierIDHBox, priceHBox,
				barcodeHBox, quantityHBox, expiryDateHBox, addButtonHBox);
		layout.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #303030, #101820FF);");

		scene = new Scene(layout, 480, 720);

		window.setScene(scene);
		window.showAndWait();
	}

	void addButtonPressed() {
		boolean isFieldCorrect = true;

		String name = "";
		String singer = "";
		String category = "";
		int supplierID = 0;
		double price = 0.0;
		String barcode = "";
		int quantity = 0;
		int rack = 0;
		int floor = 0;
		String wareHouse = "";
		int day = 0;
		int month = 0;
		int year = 0;

		if (nameTxtField.getText().isEmpty()) {
			fillNameError.setText("Enter CD name!");
			isFieldCorrect = false;
		} else {
			// Check if this product is already in the product list on the database
			if (isOnList(nameTxtField.getText())) {
				fillNameError.setText("This CD already exists in the database!");
				isFieldCorrect = false;
			} else {
				name = nameTxtField.getText();
				fillNameError.setText("");
			}
		}

		if (singerField.getText().isEmpty()) {
			singerError.setText("Enter a singer name!");
			isFieldCorrect = false;
		} else {
			if (isOnList(singerField.getText())) {
				singerError.setText("This singer already exists!");
				isFieldCorrect = false;
			} else {
				singer = singerField.getText();
				singerError.setText("");
			}

		}

		if (genreChBox.getValue().equals("----------")) {
			fillGenreError.setText("Select CD genre!");
			isFieldCorrect = false;
		} else {
			category = genreChBox.getValue();
			fillGenreError.setText("");
		}

		if (supplierIDTxtField.getText().isEmpty() || !InputCheck.isInt(supplierIDTxtField.getText())) {
			fillSupplIDError.setText("Enter supplier ID!");
			isFieldCorrect = false;
		} else {
			supplierID = Integer.parseInt(supplierIDTxtField.getText());
			fillSupplIDError.setText("");
		}

		if (priceTxtField.getText().isEmpty() || !InputCheck.isDouble(priceTxtField.getText())) {
			fillPriceError.setText("Enter CD price!");
			isFieldCorrect = false;
		} else {
			price = Double.parseDouble(priceTxtField.getText());
			fillPriceError.setText("");
		}

		if (barcodeTxtField.getText().isEmpty()) {
			fillBarcodeError.setText("Enter CD barcode!");
			isFieldCorrect = false;
		} else {
			barcode = barcodeTxtField.getText();
			fillBarcodeError.setText("");
		}

		if (quantityTxtField.getText().isEmpty() || !InputCheck.isInt(quantityTxtField.getText())) {
			fillQuantityError.setText("Enter CD quantity!");
			isFieldCorrect = false;
		} else {
			quantity = Integer.parseInt(quantityTxtField.getText());
			fillQuantityError.setText("");
		}

		if (!yearChoiceBox.getValue().equals("----------") && !monthChoiceBox.getValue().equals("----------")
				&& !dayChoiceBox.getValue().equals("----------")) {
			year = Integer.parseInt(yearChoiceBox.getValue());
			month = Integer.parseInt(monthChoiceBox.getValue());
			day = Integer.parseInt(dayChoiceBox.getValue());
			expiryDateErrLabel.setText("");
		} else {
			isFieldCorrect = false;
			expiryDateErrLabel.setText("Enter release date!");
		}

		if (isFieldCorrect) {
			DataBase.getCDs().add(new CDs());
			int addedProdIndex = DataBase.getCDs().size() - 1;
			CDs addedProduct = DataBase.getCDs().get(addedProdIndex);

			addedProduct.setName(name);
			addedProduct.setGenre(category);
			addedProduct.setSupplierID(supplierID);
			addedProduct.setPrice(price);
			addedProduct.setBarcode(barcode);
			addedProduct.setQuantity(quantity);
			addedProduct.setReleaseDate(new Dates_Statistics(day, month, year));

			// Add product to products bought list on database
			DataBase.getCD_bought().add(new CD_bought());
			int addedBoughtProdIndex = DataBase.getCD_bought().size() - 1;
			CD_bought addedBoughtProd = DataBase.getCD_bought().get(addedBoughtProdIndex);

			addedBoughtProd.setName(addedProduct.getName());
			addedBoughtProd.setId(addedProduct.getID());
			addedBoughtProd.setGenre(addedProduct.getGenre());
			addedBoughtProd.setSupplierId(addedProduct.getSupplierID());
			addedBoughtProd.setPrice(addedProduct.getPrice());
			addedBoughtProd.setBarcode(addedProduct.getBarcode());
			addedBoughtProd.setQuantity(addedProduct.getQuantity());
			addedBoughtProd.setReleaseDate(addedProduct.getReleaseDate());

			window.close();
		}
	}

	void setFields() {
		title = new Label("Add a new Product");
		title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");

		nameTxtField = new TextField();
		nameTxtField.setPromptText("Name");
		nameTxtField.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
		fillNameError = new Label();
		fillNameError.setStyle("-fx-text-fill: #ff5050;");
		nameHBox = new VBox();
		nameHBox.getChildren().addAll(nameTxtField, fillNameError);

		singerField = new TextField();
		singerField.setPromptText("Singer");
		singerField.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
		singerError = new Label();
		singerError.setStyle("-fx-text-fill: #ff5050;");
		singerHBox = new VBox();
		singerHBox.getChildren().addAll(singerField, singerError);

		genreChBox = new ChoiceBox<>();
		genreChBox.getItems().add("----------");
		genreChBox.getItems().addAll(DataBase.getCategories());
		genreChBox.setValue("----------");
		genreChBox.setMaxWidth(Double.MAX_VALUE);
		fillGenreError = new Label();
		fillGenreError.setStyle("-fx-text-fill: #ff5050;");
		categoryHBox = new VBox();
		categoryHBox.getChildren().addAll(genreChBox, fillGenreError);

		supplierIDTxtField = new TextField();
		supplierIDTxtField.setPromptText("Supplier ID");
		supplierIDTxtField
				.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
		fillSupplIDError = new Label();
		fillSupplIDError.setStyle("-fx-text-fill: #ff5050;");
		supplierIDHBox = new VBox();
		supplierIDHBox.getChildren().addAll(supplierIDTxtField, fillSupplIDError);

		priceTxtField = new TextField();
		priceTxtField.setPromptText("Price");
		priceTxtField.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
		fillPriceError = new Label();
		fillPriceError.setStyle("-fx-text-fill: #ff5050;");
		priceHBox = new VBox();
		priceHBox.getChildren().addAll(priceTxtField, fillPriceError);

		barcodeTxtField = new TextField();
		barcodeTxtField.setPromptText("Barcode");
		barcodeTxtField
				.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
		fillBarcodeError = new Label();
		fillBarcodeError.setStyle("-fx-text-fill: #ff5050;");
		barcodeHBox = new VBox();
		barcodeHBox.getChildren().addAll(barcodeTxtField, fillBarcodeError);

		quantityTxtField = new TextField();
		quantityTxtField.setPromptText("Quantity");
		quantityTxtField
				.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");
		fillQuantityError = new Label();
		fillQuantityError.setStyle("-fx-text-fill: #ff5050;");
		quantityHBox = new VBox();
		quantityHBox.getChildren().addAll(quantityTxtField, fillQuantityError);

		yearLabel = new Label("Year");
		yearChoiceBox = new ChoiceBox<>();
		yearChoiceBox.getItems().add("----------");
		addYearsInYearChBox();
		yearChoiceBox.setValue("----------");
		monthLabel = new Label("Month");
		monthChoiceBox = new ChoiceBox<>();
		monthChoiceBox.getItems().add("----------");
		addMonthsInMonthChBox();
		monthChoiceBox.setValue("----------");
		dayLabel = new Label("Day");
		dayChoiceBox = new ChoiceBox<>();
		dayChoiceBox.getItems().add("----------");
		dayChoiceBox.setValue("----------");
		expiryDateErrLabel = new Label("");
		expiryDateErrLabel.setStyle("-fx-text-fill: #ff5050;");

		yearVBox = new VBox(5);
		yearVBox.getChildren().addAll(yearChoiceBox, yearLabel);
		monthVBok = new VBox(5);
		monthVBok.getChildren().addAll(monthChoiceBox, monthLabel);
		dayVBox = new VBox(5);
		dayVBox.getChildren().addAll(dayChoiceBox, dayLabel);
		expiryDateHBox = new HBox(10);
		expiryDateHBox.getChildren().addAll(yearVBox, monthVBok, dayVBox, expiryDateErrLabel);

	}

	private boolean isOnList(String text) {
		String name = text.toLowerCase();

		for (CDs c : DataBase.getCDs()) {
			String prodInListName = c.getName().toLowerCase();

			if (name.equals(prodInListName))
				return true;
		}

		return false;
	}

	void addDaysInDayChBox() {
		dayChoiceBox.getItems().clear();
		dayChoiceBox.getItems().add("----------");
		dayChoiceBox.setValue("----------");

		int month = 0;
		int year = 0;
		int max = 0;

		if (!monthChoiceBox.getValue().equals("----------"))
			month = Integer.parseInt(monthChoiceBox.getValue());
		if (!yearChoiceBox.getValue().equals("----------"))
			year = Integer.parseInt(yearChoiceBox.getValue());

		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
			max = 31;
		if (month == 4 || month == 6 || month == 9 || month == 11)
			max = 30;

		if (month == 2) {
			max = 28;

			for (int i = 2020; i <= 2030; i += 4) {
				if (year == i) {
					max = 29;
					break;
				}

				if (i > year)
					break;
			}
		}

		if (year == 0)
			max = 0;

		for (int i = 1; i <= max; i++)
			dayChoiceBox.getItems().add(Integer.toString(i));
	}

	void addMonthsInMonthChBox() {
		for (int i = 1; i <= 12; i++)
			monthChoiceBox.getItems().add(Integer.toString(i));
	}

	void addYearsInYearChBox() {
		for (int i = 2023; i >= 1990; i--)
			yearChoiceBox.getItems().add(Integer.toString(i));
	}
}
