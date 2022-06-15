package Scenes;

import Backend_Part.CD_sold;
import Backend_Part.Cashiers;
import Backend_Part.DataBase;
import Backend_Part.DateChoiceBox;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProductsSoldScene {
	Stage window;
	Scene scene;
	Label title;
	Button closeButton;
	VBox layout;

	Label dateFromLabel;
	Label yearLabel;
	ChoiceBox<String> yearChoiceBox;
	Label monthLabel;
	ChoiceBox<String> monthChoiceBox;
	Label dayLabel;
	ChoiceBox<String> dayChoiceBox;
	Label dateFromErrLabel;

	VBox yearVBox;
	VBox monthVBok;
	VBox dayVBox;
	HBox dateFromHBox;

	Label dateToLabel;
	Label yearLabel2;
	ChoiceBox<String> yearChoiceBox2;
	Label monthLabel2;
	ChoiceBox<String> monthChoiceBox2;
	Label dayLabel2;
	ChoiceBox<String> dayChoiceBox2;
	Label dateToErrLabel;

	VBox yearVBox2;
	VBox monthVBok2;
	VBox dayVBox2;
	HBox dateToHBox;

	Button filterButton;
	VBox dateFilterVBox;

	ObservableList<CD_sold> soldProductsList;
	TableView<CD_sold> soldProductTable;

	public void showScene() {
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);

		title = new Label("CDs Sold");
		title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");

		setSoldProdsList();

		instantiateDateFilters();
		DateChoiceBox.setDateChoiceBox(dateFromLabel, yearLabel, yearChoiceBox, monthLabel, monthChoiceBox, dayLabel,
				dayChoiceBox, dateFromErrLabel, yearVBox, monthVBok, dayVBox, dateFromHBox, 2010, 2022);
		DateChoiceBox.setDateChoiceBox(dateToLabel, yearLabel2, yearChoiceBox2, monthLabel2, monthChoiceBox2, dayLabel2,
				dayChoiceBox2, dateToErrLabel, yearVBox2, monthVBok2, dayVBox2, dateToHBox, 2010, 2022);

		monthChoiceBox.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends String> observable, String oldValue, String newValue) -> DateChoiceBox
						.addDaysInDayChBox(dayChoiceBox, monthChoiceBox, yearChoiceBox, 2010, 2022));
		yearChoiceBox.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends String> observable, String oldValue, String newValue) -> DateChoiceBox
						.addDaysInDayChBox(dayChoiceBox, monthChoiceBox, yearChoiceBox, 2010, 2022));

		monthChoiceBox2.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends String> observable, String oldValue, String newValue) -> DateChoiceBox
						.addDaysInDayChBox(dayChoiceBox2, monthChoiceBox2, yearChoiceBox2, 2010, 2022));
		yearChoiceBox2.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends String> observable, String oldValue, String newValue) -> DateChoiceBox
						.addDaysInDayChBox(dayChoiceBox2, monthChoiceBox2, yearChoiceBox2, 2010, 2022));

		filterButton = new Button("Filter");
		filterButton.setPrefWidth(68);
		filterButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
		filterButton.setOnAction(actionEvent -> filter());

		dateFromHBox.setMaxWidth(Double.MAX_VALUE);
		dateFromHBox.setAlignment(Pos.CENTER_RIGHT);
		dateToHBox.setMaxWidth(Double.MAX_VALUE);
		dateToHBox.setAlignment(Pos.CENTER_RIGHT);

		dateFilterVBox = new VBox(10);
		dateFilterVBox.setMaxWidth(Double.MAX_VALUE);
		dateFilterVBox.setAlignment(Pos.CENTER_RIGHT);
		dateFilterVBox.setPadding(new Insets(25, 0, 10, 0));
		dateFilterVBox.getChildren().addAll(dateFromHBox, dateToHBox, filterButton);

		setTable();

		closeButton = new Button("Close");
		closeButton.setPrefWidth(68);
		closeButton
				.setStyle("-fx-background-color: #bb2020; -fx-font: normal bold 12px 'arial'; -fx-text-fill: white;");
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
		layout.getChildren().addAll(titleHBox, dateFilterVBox, soldProductTable);
		layout.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #303030, #101820FF);");

		scene = new Scene(layout, 512, 700);
		window.setScene(scene);
		window.showAndWait();
	}

	void setSoldProdsList() {
		soldProductsList = FXCollections.observableArrayList();

		for (Cashiers cashier : DataBase.getCashiers())
			soldProductsList.addAll(cashier.getNrCDSold());
	}

	void filter() {
		boolean areFieldsCorrect = true;

		int yearFrom = 0;
		int monthFrom = 0;
		int dayFrom = 0;

		int yearTo = 0;
		int monthTo = 0;
		int dayTo = 0;

		if (!yearChoiceBox.getValue().equals("----------") && !monthChoiceBox.getValue().equals("----------")
				&& !dayChoiceBox.getValue().equals("----------")) {
			yearFrom = Integer.parseInt(yearChoiceBox.getValue());
			monthFrom = Integer.parseInt(monthChoiceBox.getValue());
			dayFrom = Integer.parseInt(dayChoiceBox.getValue());
			dateFromErrLabel.setText("");
		} else {
			areFieldsCorrect = false;
			dateFromErrLabel.setText("Select Date!");
		}

		if (!yearChoiceBox2.getValue().equals("----------") && !monthChoiceBox2.getValue().equals("----------")
				&& !dayChoiceBox2.getValue().equals("----------")) {
			yearTo = Integer.parseInt(yearChoiceBox2.getValue());
			monthTo = Integer.parseInt(monthChoiceBox2.getValue());
			dayTo = Integer.parseInt(dayChoiceBox2.getValue());
			dateToErrLabel.setText("");
		} else {
			areFieldsCorrect = false;
			dateToErrLabel.setText("Select Date!");
		}

		if (areFieldsCorrect) {
			for (CD_sold sp : soldProductsList)
				sp.setCd_sold_statistics(dayFrom, monthFrom, yearFrom, dayTo, monthTo, yearTo);

			setTable();
			layout.getChildren().set(2, soldProductTable);
		}
	}

	void instantiateDateFilters() {
		dateFromLabel = new Label("From");
		dateFromLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
		dateFromErrLabel = new Label("");

		yearLabel = new Label("Year");
		yearLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
		yearChoiceBox = new ChoiceBox<>();

		monthLabel = new Label("Month");
		monthLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
		monthChoiceBox = new ChoiceBox<>();

		dayLabel = new Label("Day");
		dayLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
		dayChoiceBox = new ChoiceBox<>();

		yearVBox = new VBox(5);
		monthVBok = new VBox(5);
		dayVBox = new VBox(5);

		dateFromHBox = new HBox(10);

		// ------------------------------------------------------

		dateToLabel = new Label("To");
		dateToLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
		dateToErrLabel = new Label("");

		yearLabel2 = new Label("Year");
		yearLabel2.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
		yearChoiceBox2 = new ChoiceBox<>();

		monthLabel2 = new Label("Month");
		monthLabel2.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
		monthChoiceBox2 = new ChoiceBox<>();

		dayLabel2 = new Label("Day");
		dayLabel2.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
		dayChoiceBox2 = new ChoiceBox<>();

		yearVBox2 = new VBox(5);
		monthVBok2 = new VBox(5);
		dayVBox2 = new VBox(5);

		dateToHBox = new HBox(10);
	}

	void setTable() {
		TableColumn<CD_sold, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(150);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<CD_sold, Integer> idColumn = new TableColumn<>("ID");
		idColumn.setMinWidth(50);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("cd_id"));

		TableColumn<CD_sold, Integer> billColumn = new TableColumn<>("Bill no");
		billColumn.setMinWidth(80);
		billColumn.setCellValueFactory(new PropertyValueFactory<>("bill_nr"));

		TableColumn<CD_sold, Integer> quantityColumn = new TableColumn<>("Quantity");
		quantityColumn.setMinWidth(50);
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("cd_sold_statistics"));

		TableColumn<CD_sold, Double> priceColumn = new TableColumn<>("Price");
		priceColumn.setMinWidth(70);
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("cd_price_statistics"));

		soldProductTable = new TableView<>();
		soldProductTable.setItems(soldProductsList);
		soldProductTable.getColumns().addAll(nameColumn, idColumn, billColumn, quantityColumn, priceColumn);
	}
}
