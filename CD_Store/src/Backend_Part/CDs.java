package Backend_Part;

import java.io.Serializable;
import java.time.LocalDate;

public class CDs implements Serializable {
	String name;
	String genre;
	String singer;
	int supplierID;
	double price;
	String barcode;
	int quantity;
	int ID;
	Dates_Statistics releaseDate;
	LocalDate purchasedDate;
	
	static int idGenerator = 0;
	
	public CDs() {
		ID = ++idGenerator;
		releaseDate = new Dates_Statistics();
		purchasedDate = LocalDate.now();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public LocalDate getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(LocalDate purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

	public Dates_Statistics getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Dates_Statistics releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	
	
	
	
}
