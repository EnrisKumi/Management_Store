package Scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import Backend_Part.CDs;
import Backend_Part.DataBase;
import Backend_Part.Menagers;

public class MenagerScene {
	Menagers menagers;
	Scene scene;
	Label title;
	Button logoutButton;
	Button allProductsButton;
	Button addProductButton;
	Button addQuantityButton;
	Button addCategoryButton;
	Button showCashierStatsButton;
	Button showProductsSoldButton;
	Button showProductsBoughtButton;
	Button showSuppliersButton;
	Button investmentsButton;
	Button incomesButton;
	VBox statsVBox;
	VBox addVBox;
	HBox titleHBox;
	HBox allButtons;

	VBox layout;
	ObservableList<CDs> lowQuantityProds;

	public void showScene(Menagers menagers, Stage window) throws FileNotFoundException {
		this.menagers = menagers;

		Image image = new Image(new FileInputStream("Images/menager.png"));
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(38);
		imageView.setPreserveRatio(true);

		title = new Label(menagers.getName());
		title.setGraphic(imageView);
		setLayout(window);

		layout = new VBox(35);
		layout.setPadding(new Insets(10, 30, 10, 30));
		layout.getChildren().addAll(titleHBox, allButtons);
		layout.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #303030, #101820FF);");

		scene = new Scene(layout, 360, 250);
		window.setScene(scene);

		lowQuantityProds = FXCollections.observableArrayList();
		checkProductQuantity();
	}

	void checkProductQuantity() {
		for (CDs cd : DataBase.getCDs())
			if (cd.getQuantity() < 5)
				lowQuantityProds.add(cd);

		if (lowQuantityProds.size() > 0)
			new LowQuantityAlertScene().showScene(lowQuantityProds);
	}

	void setLayout(Stage window) {
		title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");

		// Add Product button
		addProductButton = new Button("Add a new CD");
		addProductButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		addProductButton.setPrefWidth(128);
		addProductButton.setAlignment(Pos.CENTER);
		addProductButton.setStyle(
				"-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");
		addProductButton.setOnAction(actionEvent -> new AddProductScene().showScene());

		// Add quantity button
		addQuantityButton = new Button("Add Quantity");
		addQuantityButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		addQuantityButton.setPrefWidth(128);
		addQuantityButton.setAlignment(Pos.CENTER);
		addQuantityButton.setStyle(
				"-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");
		addQuantityButton.setOnAction(actionEvent -> new IncreaseQuantityScene().showScene());

		// Add Category button
		addCategoryButton = new Button("Add Genre");
		addCategoryButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		addCategoryButton.setPrefWidth(128);
		addCategoryButton.setAlignment(Pos.CENTER);
		addCategoryButton.setStyle(
				"-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");
		addCategoryButton.setOnAction(actionEvent -> new AddCategoryScene().showScene());

		// Show Cashier stats button
		showCashierStatsButton = new Button("Cashier stats");
		showCashierStatsButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		showCashierStatsButton.setPrefWidth(128);
		showCashierStatsButton.setAlignment(Pos.CENTER);
		showCashierStatsButton.setStyle(
				"-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");
		showCashierStatsButton.setOnAction(actionEvent -> new CashierStatsScene().showScene());

		// Show bought products button
		showProductsBoughtButton = new Button("Products bought");
		showProductsBoughtButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		showProductsBoughtButton.setPrefWidth(128);
		showProductsBoughtButton.setAlignment(Pos.CENTER);
		showProductsBoughtButton.setStyle(
				"-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");
		showProductsBoughtButton.setOnAction(actionEvent -> new ProductsBoughtScene().showScene());

		// Show Suppliers button
		showSuppliersButton = new Button("Suppliers");
		showSuppliersButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		showSuppliersButton.setPrefWidth(128);
		showSuppliersButton.setAlignment(Pos.CENTER);
		showSuppliersButton.setStyle(
				"-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");
		showSuppliersButton.setOnAction(actionEvent -> new SuppliersListScene().showScene());

		allProductsButton = new Button("All CDs");
		allProductsButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		allProductsButton.setPrefWidth(96);
		allProductsButton.setStyle(
				"-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");
		allProductsButton.setOnAction(actionEvent -> new AllProductsScene().showScene());

		investmentsButton = new Button("Investments");
		investmentsButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		investmentsButton.setPrefWidth(96);
		investmentsButton.setStyle(
				"-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");
		investmentsButton.setOnAction(actionEvent -> new InvestmentsScene().showScene());

		// Logout button
		logoutButton = new Button("Logout");
		logoutButton
				.setStyle("-fx-background-color: #bb2020; -fx-font: normal bold 12px 'arial'; -fx-text-fill: white;");
		logoutButton.setOnAction(actionEvent -> {
			try {
				Login_Scene.loginscene(window);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		});

		Pane spacer = new Pane();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		titleHBox = new HBox();
		titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		titleHBox.setAlignment(Pos.CENTER);
		titleHBox.setPadding(new Insets(5, 0, 5, 0));
		titleHBox.getChildren().addAll(title, spacer, logoutButton);

		statsVBox = new VBox(10);
		statsVBox.getChildren().addAll(showCashierStatsButton, showProductsBoughtButton, showSuppliersButton,
				investmentsButton);

		addVBox = new VBox(10);
		addVBox.getChildren().addAll(addProductButton, addQuantityButton, addCategoryButton, allProductsButton);

		Pane spacer2 = new Pane();
		HBox.setHgrow(spacer2, Priority.ALWAYS);
		allButtons = new HBox();
		allButtons.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		allButtons.setAlignment(Pos.CENTER);
		allButtons.getChildren().addAll(addVBox, spacer2, statsVBox);
	}
}
