package Backend_Part;

import java.io.Serializable;

public class Dates_Statistics implements Serializable{
	private int day;
	private int month;
	private int year;
	private String Datee;
	
	public Dates_Statistics(int day,int month,int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public Dates_Statistics() {
		day = 0;
		month = 0;
		year = 0;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public String getDate() {
		Datee = "" + day + "/" + month + "/" + year;
		return Datee;
	}
	
}
