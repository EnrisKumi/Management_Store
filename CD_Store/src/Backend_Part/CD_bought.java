package Backend_Part;

import java.io.Serializable;
import java.time.LocalDate;

public class CD_bought implements Serializable{
	
	String name;
	String singer;
	String genre;
	int supplierId;
	LocalDate purchasedDate;
	double price;
	String barcode;
	int quantity;
	int id;
	Dates_Statistics releaseDate;
	double totalPrice;
	
	int bought_statistics;
	double profit_statistics;
	
	public CD_bought() {
		purchasedDate = LocalDate.now();
		bought_statistics = 0;
		profit_statistics = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public LocalDate getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(LocalDate purchasedDate) {
		this.purchasedDate = purchasedDate;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Dates_Statistics getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Dates_Statistics releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public double getTotalPrice() {
		totalPrice = price * quantity;
		return totalPrice;
	}
	
	public void setBoughtOverAPeriod(int dayFrom, int monthFrom, int yearFrom, int dayTo, int monthTo, int yearTo)
    {
        LocalDate dateFrom = LocalDate.of(yearFrom, monthFrom, dayFrom);
        LocalDate dateTo = LocalDate.of(yearTo, monthTo, dayTo);

        if(purchasedDate.isAfter(dateFrom) && purchasedDate.isBefore(dateTo))
            bought_statistics = quantity;
        else if(purchasedDate.isEqual(dateFrom) || purchasedDate.equals(dateTo))
            bought_statistics = quantity;
        else
            bought_statistics = 0;

        profit_statistics = price * bought_statistics;
    }

	public int getBought_statistics() {
		return bought_statistics;
	}

	public void setBought_statistics(int bought_statistics) {
		this.bought_statistics = bought_statistics;
	}

	public double getProfit_statistics() {
		return profit_statistics;
	}

	public void setProfit_statistics(double profit_statistics) {
		this.profit_statistics = profit_statistics;
	}
	
	
	
	
}
