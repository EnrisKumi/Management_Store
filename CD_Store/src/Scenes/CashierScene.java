package Scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import Backend_Part.Bill;
import Backend_Part.CD_inCart;
import Backend_Part.CD_sold;
import Backend_Part.CDs;
import Backend_Part.Cashiers;
import Backend_Part.DataBase;

public class CashierScene {
	Stage window;
	Cashiers cashier;
	CDs searchedProduct;
	Scene scene;
	VBox billVBox;
	VBox layout;
	Label nameLabel;

	TextField searchBar;
	Button searchButton;
	Button deleteButton;
	Button printBillButton;
	Button allProductsButton;
	Button logoutButton;
	ObservableList<CD_inCart> cart;
	TableView<CD_inCart> cartView;
	String billPath;

	public void showScene(Cashiers cashier, Stage window) throws FileNotFoundException {
		this.window = window;
		this.cashier = cashier;

		Image image = new Image(new FileInputStream("Images/cashier.png"));
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(38);
		imageView.setPreserveRatio(true);

		nameLabel = new Label(cashier.getName());
		nameLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		nameLabel.setAlignment(Pos.CENTER_LEFT);
		nameLabel.setPadding(new Insets(5));
		nameLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #eeeeee;");
		nameLabel.setGraphic(imageView);

		cart = FXCollections.observableArrayList();
		setTable();

		searchBar = new TextField();
		searchBar.setPromptText("Search Cd");
		searchBar.setStyle("-fx-background-color: #505050; -fx-text-fill: #eeeeee; -fx-prompt-text-fill: #888888;");

		searchButton = new Button("Search");
		searchButton.setStyle(
				"-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");
		searchButton.setOnAction(actionEvent -> {
			searchedProduct = findSearchedProduct(searchBar.getText());
			int quantity = new SearchCDScene().showScene(searchedProduct);
			addProductToCart(searchedProduct, quantity);
		});

		Pane pane1 = new Pane();
		HBox.setHgrow(pane1, Priority.ALWAYS);
		HBox searchHBox = new HBox();
		searchHBox.getChildren().addAll(pane1, searchButton);

		VBox searchVBox = new VBox(10);
		searchVBox.getChildren().addAll(searchBar, searchHBox);

		deleteButton = new Button("Remove");
		deleteButton.setDisable(true);
		deleteButton
				.setStyle("-fx-background-color: #bb2020; -fx-font: normal bold 12px 'arial'; -fx-text-fill: white;");
		deleteButton.setOnAction(actionEvent -> removeFromCart());

		printBillButton = new Button("Print bill");
		printBillButton.setDisable(true);
		printBillButton.setStyle(
				"-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");
		printBillButton.setOnAction(actionEvent -> printBill());

		logoutButton = new Button("Logout");
		logoutButton
				.setStyle("-fx-background-color: #bb2020; -fx-font: normal bold 12px 'arial'; -fx-text-fill: white;");
		logoutButton.setOnAction(actionEvent -> {
			onExit();
			window.setOnCloseRequest(e -> DataBase.save());
			try {
				Login_Scene.loginscene(window);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		});

		this.window.setOnCloseRequest(e -> {
			onExit();
			DataBase.save();
		});

		allProductsButton = new Button("All CDs");
		allProductsButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		allProductsButton.setPrefWidth(96);
		allProductsButton.setStyle(
				"-fx-background-color:#F2AA4CFF; -fx-font: normal bold 12px 'arial'; -fx-text-fill: #000000;");
		allProductsButton.setOnAction(actionEvent -> new AllProductsScene().showScene());

		Pane spacer = new Pane();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		HBox titleHBox = new HBox();
		titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		titleHBox.setAlignment(Pos.CENTER);
		titleHBox.setPadding(new Insets(5, 0, 5, 0));
		titleHBox.getChildren().addAll(nameLabel, spacer, logoutButton);

		Pane spacer2 = new Pane();
		HBox.setHgrow(spacer2, Priority.ALWAYS);

		HBox buttonsHBox = new HBox(15);
		buttonsHBox.getChildren().addAll(spacer2, deleteButton, printBillButton, allProductsButton);

		billVBox = new VBox(15);
		billVBox.getChildren().addAll(cartView, buttonsHBox);

		layout = new VBox(35);
		layout.setPadding(new Insets(10, 30, 10, 30));
		layout.getChildren().addAll(titleHBox, searchVBox, billVBox);
		layout.setStyle("-fx-background-color: #303030;");

		scene = new Scene(layout, 500, 680);
		this.window.setScene(scene);
	}

	void onExit() {
		for (CD_inCart pInCart : cart) {
			int quantity = pInCart.getQuantity();

			for (CDs p : DataBase.getCDs())
				if (pInCart.getName().equals(p.getName())) {
					p.setQuantity(p.getQuantity() + quantity);
					break;
				}
		}
		cart.clear();
	}

	CDs findSearchedProduct(String input) {
		CDs product = new CDs();
		product.setName("");
		product.setQuantity(0);
		product.setPrice(0.0);

		String productName = input.toLowerCase();

		for (CDs c : DataBase.getCDs()) {
			String pName = c.getName().toLowerCase();

			if (productName.equals(pName)) {
				product = c;
				break;
			}
		}

		return product;
	}

	void addProductToCart(CDs searchedProduct, int quantity) {
		if (quantity > 0) {
			deleteButton.setDisable(false);
			printBillButton.setDisable(false);

			boolean isProdInCart = false;

			for (CD_inCart productInCart : cart)
				if (searchedProduct.getName().equals(productInCart.getName())) {
					productInCart.setQuantity(productInCart.getQuantity() + quantity);
					isProdInCart = true;
					break;
				}

			if (!isProdInCart)
				cart.add(new CD_inCart(searchedProduct.getName(), searchedProduct.getID(), searchedProduct.getPrice(),
						quantity));

			// Remove product from static list
			searchedProduct.setQuantity(searchedProduct.getQuantity() - quantity);

			setTable();
			billVBox.getChildren().set(0, cartView);
		}

		searchBar.clear();
	}

	void removeFromCart() {
		ObservableList<CD_inCart> productsInCarts = cart;
		ObservableList<CD_inCart> productSelected = cartView.getSelectionModel().getSelectedItems();

		// Add product back to the static Product list by adding to its quantity
		for (CD_inCart pInCart : productSelected) {
			int quantity = pInCart.getQuantity();

			for (CDs p : DataBase.getCDs())
				if (pInCart.getName().equals(p.getName())) {
					p.setQuantity(p.getQuantity() + quantity);
					break;
				}
		}

		// Remove the selected item from the cart
		productsInCarts.removeAll(productSelected);

		// Disable the remove button and the print bill button if the cart is empty
		if (cart.size() < 1) {
			deleteButton.setDisable(true);
			printBillButton.setDisable(true);
		}
	}

	void printBill() {
		if (cart.size() > 0) {
			// Create new bill
			cashier.getBills().add(new Bill());
			int addedBillIndex = cashier.getBills().size() - 1;
			Bill bill = cashier.getBills().get(addedBillIndex);

			// Get the price of the products and set it to the added bill. Also add products
			// sold to cashier's soldProducts.
			double totalBillPrice = 0;

			for (CD_inCart productInCart : cart) {
				totalBillPrice += productInCart.getTotalPrice();
				boolean doesProdExist = false;

				for (CD_sold soldProduct : cashier.getSold_cd())
					if (productInCart.getName().equals(soldProduct.getName())) {
						doesProdExist = true;
						soldProduct.setCd_quantity(soldProduct.getCd_quantity() + productInCart.getQuantity());
					}

				if (!doesProdExist) {
					cashier.getSold_cd().add(new CD_sold());
					int addedProductIndex = cashier.getSold_cd().size() - 1;
					CD_sold sp = cashier.getSold_cd().get(addedProductIndex);

					sp.setName(productInCart.getName());
					sp.setCd_id(productInCart.getProductID());
					sp.setCd_quantity(productInCart.getQuantity());
					sp.setPrice(productInCart.getPrice());
					sp.setBill_nr(bill.getBillnr());
					// sp.setDate();
				}
			}

			bill.setPrice(totalBillPrice);

			// Put bill on txt document
			billPath = "Resources/Bills/bill" + bill.getBillnr() + ".txt";

			try {
				FileWriter fw = new FileWriter(billPath);
				BufferedWriter bw = new BufferedWriter(fw);

				for (CD_inCart pInCart : cart)
					bw.write(pInCart.getName() + " | Quantity: " + pInCart.getQuantity() + " | Unit Price: "
							+ pInCart.getPrice() + "$ | Total Price: " + pInCart.getTotalPrice() + "$\n");
				bw.write("Total price: " + bill.getPrice() + "$");

				bw.close();
				fw.close();
			} catch (Exception e) {
				System.out.println("Something went wrong while printing the bill");
			}

			// Clear the cart
			cart.clear();
			deleteButton.setDisable(true);
			printBillButton.setDisable(true);
			setTable();
		}
	}

	void setTable() {
		TableColumn<CD_inCart, String> nameColumn = new TableColumn<>("Product");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<CD_inCart, Integer> quantityColumn = new TableColumn<>("Quantity");
		quantityColumn.setMinWidth(70);
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

		TableColumn<CD_inCart, Double> priceColumn = new TableColumn<>("Unit Price");
		priceColumn.setMinWidth(70);
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

		TableColumn<CD_inCart, Double> totalColumn = new TableColumn<>("Total Price");
		totalColumn.setMinWidth(70);
		totalColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

		cartView = new TableView<>();
		cartView.setItems(cart);
		cartView.getColumns().addAll(nameColumn, quantityColumn, priceColumn, totalColumn);
		cartView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
}
