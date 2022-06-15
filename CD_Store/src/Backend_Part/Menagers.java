package Backend_Part;

import java.io.Serializable;
import java.util.Date;
//Done

public class Menagers extends Users implements Serializable {

	String name;
	String surname;
	String phoneNr;
	Dates_Statistics bday;
	String email;
	double salary;
	String idnumber;

	double menagers_statistics; // salary for a period

	public Menagers() {
		bday = new Dates_Statistics();
		menagers_statistics = 0;
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

	public void setMenagers_statistics(int yearf, int monthf, int yeart, int montht) {
		int years_between = yeart - yearf;
		int months_between = montht - monthf;
		int nrofMonths = (years_between * 12) + months_between;

		menagers_statistics = salary * nrofMonths;
	}

	public double getMenagers_statistics() {
		return menagers_statistics;
	}

}
