package Backend_Part;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Cashiers extends Users implements Serializable {
	String name;
	String surname;
	String phoneNr;
	Dates_Statistics bday;
	String email;
	double salary;
	String idnumber;

	int nrOfBills;
	int cd_nr_sold;
	ArrayList<CD_sold> nrCDSold;
	ArrayList<Bill> bills;

	double profit;

	String birthdayString;
	int bills_statistics; // bills printed over a period
	int cd_sold_statistics; // cds sold over a period
	double profit_statistics; // profit over a period
	double salary_statistics; // salary over a period

	public Cashiers() {
		nrCDSold = new ArrayList<>();
		bills = new ArrayList<>();
		nrOfBills = 0;
		cd_nr_sold = 0;
		bday = new Dates_Statistics();
		bills_statistics = 0;
		cd_sold_statistics = 0;
		profit_statistics = 0;
		salary_statistics = 0;
	}

	public ArrayList<CD_sold> getNrCDSold() {
		return nrCDSold;
	}

	public void setNrCDSold(ArrayList<CD_sold> nrCDSold) {
		this.nrCDSold = nrCDSold;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNr() {
		return phoneNr;
	}

	public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
	}

	public Dates_Statistics getBday() {
		return bday;
	}

	public void setBday(Dates_Statistics bday) {
		this.bday = bday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public ArrayList<CD_sold> getSold_cd() {
		return nrCDSold;
	}

	public void setSold_cd(ArrayList<CD_sold> sold_cd) {
		this.nrCDSold = sold_cd;
	}

	public ArrayList<Bill> getBills() {
		return bills;
	}

	public void setBills(ArrayList<Bill> bills) {
		this.bills = bills;
	}

	public int getnrBills() {
		nrOfBills = bills.size();
		return nrOfBills;
	}

	public int getnrCd() {
		cd_nr_sold = nrCDSold.size();
		return cd_nr_sold;
	}

	public double getProfit() {
		profit = 0;
		for (Bill b : bills) {
			profit += b.getPrice();
		}
		return profit;
	}

	public String getBirthday() {
		birthdayString = bday.getDate();
		return birthdayString;
	}

	public int getBills_statistics() {
		return bills_statistics;
	}

	public int getCd_sold_statistics() {
		return cd_sold_statistics;
	}

	public double getProfit_statistics() {
		return profit_statistics;
	}

	public void bills_profit_statistics(int dayf, int monthf, int yearf, int dayt, int montht, int yeart) {
		ArrayList<Bill> bills_statistics_overPeriod = new ArrayList<>();
		LocalDate dateFrom = LocalDate.of(yearf, monthf, dayf);
		LocalDate dateTo = LocalDate.of(yeart, montht, dayt);

		for (Bill b : bills) {
			if (b.getDatePrinted().isAfter(dateFrom) && b.getDatePrinted().isBefore(dateTo))
				bills_statistics_overPeriod.add(b);
			else if (b.getDatePrinted().isEqual(dateFrom) || b.getDatePrinted().isEqual(dateTo))
				bills_statistics_overPeriod.add(b);
		}
		bills_statistics = bills_statistics_overPeriod.size();

		profit_statistics = 0.0;
		for (Bill b1 : bills_statistics_overPeriod) {
			profit_statistics += b1.getPrice();
		}

	}

	public void sold_cd_statistics(int dayf, int monthf, int yearf, int dayt, int montht, int yeart) {
		ArrayList<CD_sold> cd_sold_statistics_overPeriod = new ArrayList<>();
		LocalDate dateFrom = LocalDate.of(yearf, monthf, dayf);
		LocalDate dateTo = LocalDate.of(yeart, montht, dayt);

		for (CD_sold cds : nrCDSold) {
			if (cds.getSold_date().isAfter(dateFrom) && cds.getSold_date().isBefore(dateTo))
				cd_sold_statistics_overPeriod.add(cds);
			else if (cds.getSold_date().isEqual(dateFrom) || cds.getSold_date().isEqual(dateTo))
				cd_sold_statistics_overPeriod.add(cds);
		}

		cd_sold_statistics = 0;
		for (CD_sold cds1 : cd_sold_statistics_overPeriod) {
			cd_sold_statistics += cds1.getCd_quantity();
		}

	}

	public double getSalary_statistics() {
		return salary_statistics;
	}

	public void setSalaryStatistics(int monthf, int yearf, int montht, int yeart) {
		int years_between = yeart - yearf;
		int months_between = montht - monthf;
		int nrofMonthBetween = (years_between * 12) + months_between;

		salary_statistics = salary * nrofMonthBetween;
	}

}
