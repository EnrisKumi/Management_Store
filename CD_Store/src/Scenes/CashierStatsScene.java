package Scenes;

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

public class CashierStatsScene {
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

	TableView<Cashiers> cashierStatsTable;
	ObservableList<Cashiers> cashiers;

	public void showScene() {
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);

		title = new Label("All cashiers");
		title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");

		cashiers = FXCollections.observableArrayList();
		cashiers.addAll(DataBase.getCashiers());

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

		dateFromHBox.setMaxWidth(Double.MAX_VALUE);
		dateFromHBox.setAlignment(Pos.CENTER_RIGHT);
		dateToHBox.setMaxWidth(Double.MAX_VALUE);
		dateToHBox.setAlignment(Pos.CENTER_RIGHT);

		filterButton = new Button("Filter");
		filterButton.setPrefWidth(68);
		filterButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
		filterButton.setOnAction(actionEvent -> filter());

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
		layout.getChildren().addAll(titleHBox, dateFilterVBox, cashierStatsTable);
		layout.setStyle("-fx-background-color: #303030;");

		scene = new Scene(layout, 642, 700);
		window.setScene(scene);
		window.showAndWait();
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
			for (Cashiers c : cashiers) {
				c.bills_profit_statistics(dayFrom, monthFrom, yearFrom, dayTo, monthTo, yearTo);
				c.sold_cd_statistics(dayFrom, monthFrom, yearFrom, dayTo, monthTo, yearTo);
			}

			setTable();
			layout.getChildren().set(2, cashierStatsTable);
		}
	}

	void setTable() {
		TableColumn<Cashiers, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(150);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Cashiers, Integer> nrOfBillsColumn = new TableColumn<>("Bills");
		nrOfBillsColumn.setMinWidth(150);
		nrOfBillsColumn.setCellValueFactory(new PropertyValueFactory<>("bills_statistics"));

		TableColumn<Cashiers, Integer> prodSoldColumn = new TableColumn<>("Products Sold");
		prodSoldColumn.setMinWidth(150);
		prodSoldColumn.setCellValueFactory(new PropertyValueFactory<>("cd_sold_statistics"));

		TableColumn<Cashiers, Double> moneyGenColumn = new TableColumn<>("Money Generated");
		moneyGenColumn.setMinWidth(150);
		moneyGenColumn.setCellValueFactory(new PropertyValueFactory<>("profit"));

		cashierStatsTable = new TableView<>();
		cashierStatsTable.setItems(cashiers);
		cashierStatsTable.getColumns().addAll(nameColumn, nrOfBillsColumn, prodSoldColumn, moneyGenColumn);
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
}
