package Backend_Part;

import java.io.Serializable;
import java.time.LocalDate;

public class CD_sold implements Serializable {

	String name;
	int cd_id;
	int bill_nr;
	int cd_quantity;
	double price;
	LocalDate sold_date;
	double total_price;

	private double cd_sold_statistics;
	private double cd_price_statistics;

	public CD_sold() {
		sold_date = LocalDate.now();
		cd_sold_statistics = 0;
		cd_price_statistics = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCd_id() {
		return cd_id;
	}

	public void setCd_id(int cd_id) {
		this.cd_id = cd_id;
	}

	public int getBill_nr() {
		return bill_nr;
	}

	public void setBill_nr(int bill_nr) {
		this.bill_nr = bill_nr;
	}

	public int getCd_quantity() {
		return cd_quantity;
	}

	public void setCd_quantity(int cd_quantity) {
		this.cd_quantity = cd_quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getSold_date() {
		return sold_date;
	}

	public void setSold_date(LocalDate sold_date) {
		this.sold_date = sold_date;
	}

	public double getTotal_price() {
		total_price = price * cd_quantity;
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public double getCd_sold_statistics() {
		return cd_sold_statistics;
	}

	public void setCd_sold_statistics(int yearf, int monthf, int dayf, int yeart, int montht, int dayt) {
		LocalDate datefrom = LocalDate.of(yearf, monthf, dayf);
		LocalDate dateto = LocalDate.of(yeart, montht, dayt);
		if (sold_date.isAfter(datefrom) && sold_date.isBefore(dateto))
			cd_sold_statistics = cd_quantity;
		else if (sold_date.isEqual(datefrom) || sold_date.isEqual(dateto))
			cd_sold_statistics = cd_quantity;
		else
			cd_sold_statistics = 0;

		cd_price_statistics = price * cd_sold_statistics;
	}

	public double getCd_price_statistics() {
		return cd_price_statistics;
	}

	public void setCd_price_statistics(double cd_price_statistics) {
		this.cd_price_statistics = cd_price_statistics;
	}

}
