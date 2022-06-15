package Backend_Part;

public class CD_inCart {
	String name;
	int productID;
	double price;
	int quantity;
	double totalPrice;

	public CD_inCart(String name, int productID, double price, int quantity) {
		this.name = name;
		this.productID = productID;
		this.price = price;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public int getProductID() {
		return productID;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		totalPrice = quantity * price;
		return totalPrice;
	}
}
