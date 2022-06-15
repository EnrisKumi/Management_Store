package Scenes;

import Backend_Part.CD_bought;
import Backend_Part.Cashiers;
import Backend_Part.DataBase;
import Backend_Part.DateChoiceBox;
import Backend_Part.Menagers;
import javafx.beans.value.ObservableValue;
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

public class InvestmentsScene extends ProductsBoughtScene {
	Label productsLabel;
	VBox productsVBox;

	Label totalInvLabel;
	Label amountLabel;
	HBox investmentsHBox;

	Label usersLabel;
	HBox usersHBox;
	VBox usersVBox;

	Label usersSalaryLabel;
	Label usersAmountLabel;
	HBox usersSalaryHBox;

	ObservableList<Cashiers> cashiers;
	ObservableList<Menagers> menagers;
	TableView<Cashiers> cashierTable;
	TableView<Menagers> menagerTable;

	@Override
	public void showScene() {
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);

		title = new Label("Investments");
		title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");

		productsList = FXCollections.observableArrayList();
		productsList.addAll(DataBase.getCD_bought());

		cashiers = FXCollections.observableArrayList();
		cashiers.addAll(DataBase.getCashiers());
		menagers = FXCollections.observableArrayList();
		menagers.addAll(DataBase.getMenagers());

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

		productsLabel = new Label("Products");
		productsVBox = new VBox(5);
		productsVBox.getChildren().addAll(productsLabel, productTable);

		totalInvLabel = new Label("Product investments:");
		totalInvLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
		amountLabel = new Label("0.0");
		amountLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
		investmentsHBox = new HBox(5);
		investmentsHBox.getChildren().addAll(totalInvLabel, amountLabel);

		usersLabel = new Label("Users");
		setCashierTable();
		setEconomistTable();

		usersHBox = new HBox(30);
		usersHBox.getChildren().addAll(cashierTable, menagerTable);

		usersVBox = new VBox(5);
		usersVBox.getChildren().addAll(usersLabel, usersHBox);

		usersSalaryLabel = new Label("Total users salary:");
		usersSalaryLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
		usersAmountLabel = new Label("0.0");
		usersAmountLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
		usersSalaryHBox = new HBox(5);
		usersSalaryHBox.getChildren().addAll(usersSalaryLabel, usersAmountLabel);

		closeButton = new Button("Close");
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

		layout = new VBox(10);
		layout.setPadding(new Insets(20));
		layout.getChildren().addAll(titleHBox, dateFilterVBox, productsVBox, investmentsHBox, usersVBox,
				usersSalaryHBox);
		layout.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #303030, #101820FF);");

		scene = new Scene(layout, 680, 720);
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
			double productsTotal = 0;
			double cashiersTotal = 0;
			double menagerTotal = 0;

			for (CD_bought bp : productsList) {
				bp.setBoughtOverAPeriod(dayFrom, monthFrom, yearFrom, dayTo, monthTo, yearTo);
				productsTotal += bp.getProfit_statistics();
			}

			for (Cashiers c : cashiers) {
				c.setSalaryStatistics(monthFrom, yearFrom, monthTo, yearTo);
				cashiersTotal += c.getSalary_statistics();
			}

			for (Menagers m : menagers) {
				m.setMenagers_statistics(yearFrom, monthFrom, yearTo, monthTo);
				menagerTotal += m.getMenagers_statistics();
			}

			double totalUserSalary = cashiersTotal + menagerTotal;

			usersAmountLabel.setText(Double.toString(totalUserSalary));
			amountLabel.setText(Double.toString(productsTotal));

			setTable();
			setCashierTable();
			setEconomistTable();
			layout.getChildren().set(2, productTable);
			usersHBox.getChildren().set(0, cashierTable);
			usersHBox.getChildren().set(1, menagerTable);
		}
	}

	@Override
	void setTable() {
		TableColumn<CD_bought, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(100);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<CD_bought, String> singerColumn = new TableColumn<>("Singer");
		singerColumn.setMinWidth(100);
		singerColumn.setCellValueFactory(new PropertyValueFactory<>("singer"));

		TableColumn<CD_bought, Double> priceColumn = new TableColumn<>("Price");
		priceColumn.setMinWidth(80);
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

		TableColumn<CD_bought, Integer> quantityColumn = new TableColumn<>("Quantity");
		quantityColumn.setMinWidth(40);
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("bought_statistics"));

		TableColumn<CD_bought, Double> totalPriceColumn = new TableColumn<>("Total price");
		totalPriceColumn.setMinWidth(80);
		totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("profit_statistics"));

		productTable = new TableView<>();
		productTable.setItems(productsList);
		productTable.getColumns().addAll(nameColumn, singerColumn, priceColumn, quantityColumn, totalPriceColumn);
	}

	void setCashierTable() {
		TableColumn<Cashiers, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(100);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Cashiers, Double> salaryPerMonthColumn = new TableColumn<>("Salary/month");
		salaryPerMonthColumn.setMinWidth(100);
		salaryPerMonthColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

		TableColumn<Cashiers, Double> salaryColumn = new TableColumn<>("Total salary");
		salaryColumn.setMinWidth(100);
		salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary_statistics"));

		cashierTable = new TableView<>();
		cashierTable.setItems(cashiers);
		cashierTable.getColumns().addAll(nameColumn, salaryPerMonthColumn, salaryColumn);
	}

	void setEconomistTable() {
		TableColumn<Menagers, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(100);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Menagers, Double> salaryPerMonthColumn = new TableColumn<>("Salary/month");
		salaryPerMonthColumn.setMinWidth(100);
		salaryPerMonthColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

		TableColumn<Menagers, Double> salaryColumn = new TableColumn<>("Total salary");
		salaryColumn.setMinWidth(100);
		salaryColumn.setCellValueFactory(new PropertyValueFactory<>("menagers_statistics"));

		menagerTable = new TableView<>();
		menagerTable.setItems(menagers);
		menagerTable.getColumns().addAll(nameColumn, salaryPerMonthColumn, salaryColumn);
	}
}
