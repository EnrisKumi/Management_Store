package Backend_Part;

import java.io.Serializable;
import java.time.LocalDate;

public class Bill implements Serializable {
	int bill_nr=0;
	double price;
	LocalDate datePrinted;

	public Bill() {
		bill_nr = getnrofBills() + 1;
		price = 0.0;
		datePrinted = LocalDate.now();
	}

	public int getBillnr() {
		return bill_nr;
	}

	public void setBillnr(int billnr) {
		this.bill_nr = billnr;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getDatePrinted() {
		return datePrinted;
	}

	public void setDatePrinted(LocalDate datePrinted) {
		this.datePrinted = datePrinted;
	}

	int getnrofBills() {
		int nrofBills = 0;
		for (Cashiers c : DataBase.getCashiers())
			nrofBills += c.getnrBills();
		return nrofBills;
	}

}