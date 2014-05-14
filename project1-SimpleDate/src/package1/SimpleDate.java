package package1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**********************************************************************
 * A class which can perform various methods to a date
 *
 * @author John Tunisi
 * @version 9/16/2013
 *********************************************************************/

public class SimpleDate {
	
	/**Day*/
	private int day;
	
	/**Month*/
	private int month;
	
	/**Year*/
	private int year;
	
	/**Minimum year possible*/
	private static int MIN_YEAR = 1753;
	
	/**Number of SimpleDates created*/
	private static int count;
	
	
	/******************************************************************
	 * This is a constructor that initializes the simple date variables
	 *****************************************************************/
	public SimpleDate(){
		day = 0;
		month = 0;
		year = 0;
		count++;
	}
	
	/******************************************************************
	 * This is a constructor to initialize the variables
	 * 
	 * @param day this is an integer of the day
	 * @param month this is an integer of the month
	 * @param year this is an integer of the year
	 *****************************************************************/
	public SimpleDate(int day, int month, int year){
		this.day = day;
		this.month = month;
		this.year = year;
		count++;
		
		if(day < 1 || month < 1 || year < MIN_YEAR){
			throw new IllegalArgumentException();
		}
	}

	/******************************************************************
	 * A constructor that uses the parameter of a SimpleDate
	 * 
	 * @param other Another SimpleDate to put into this SimpleDate
	 *****************************************************************/
	public SimpleDate (SimpleDate other){
		count++;
		day = other.day;
		month = other.month;
		year = other.year;
		
		if(day < 1 || month < 1 || year < MIN_YEAR){
			throw new IllegalArgumentException();
		}
		
	}
	
	/******************************************************************
	 * A constructor that uses a string with a date in it
	 * 
	 * @param startDate A string of the date to put into SimpleDate
	 *****************************************************************/
	public SimpleDate (String startDate){
		count++;
		String [] part;
		part = startDate.split("/");

		day = Integer.parseInt(part[1].trim());
		month = Integer.parseInt(part[0].trim());
		year = Integer.parseInt(part[2].trim());

		if(day < 1 || month < 1 || year < MIN_YEAR || part.length < 3){
			throw new IllegalArgumentException();
		}
	}
	
	/******************************************************************
	 * Gets the day of SimpleDate
	 * 
	 * @return day The day of SimpleDate
	 *****************************************************************/
	public int getDay() {
		return day;
	}

	/******************************************************************
	 * Sets the day of SimpleDate
	 *****************************************************************/
	public void setDay(int day) {
		this.day = day;
	}

	/******************************************************************
	 * Gets the month of SimpleDate
	 * 
	 * @return month the month of SimpleDate
	 *****************************************************************/
	public int getMonth() {
		return month;
	}

	/******************************************************************
	 * Sets the month of SimpleDate
	 *****************************************************************/
	public void setMonth(int month) {
		this.month = month;
	}

	/******************************************************************
	 * Gets the year of SimpleDate
	 * 
	 * @return year of SimpleDate
	 *****************************************************************/
	public int getYear() {
		return year;
	}

	/******************************************************************
	 * Sets the year of SimpleDate
	 *****************************************************************/
	public void setYear(int year) {
		this.year = year;
	}

	/******************************************************************
	 * Checks if this SimpleDate equals other SimpleDate
	 * 
	 * @return true if SimpleDate equals other SimpleDate
	 * @return false if SimpleDate does not equal other SimpleDate
	 *****************************************************************/
	public boolean equals(Object other){
		if(month == ((SimpleDate) other).getMonth() &&
		   day == ((SimpleDate) other).getDay() &&
		   year == ((SimpleDate) other).getYear()){
			return true;
		}
		return false;
	}
	
	/******************************************************************
	 * Checks if one SimpleDate is the same as another
	 * 
	 * @param s1 SimpleDate1
	 * @param s2 SimpleDate2
	 * @return true if one SimpleDate equals the other
	 * @return false if one SimpleDate does not equal the other
	 *****************************************************************/
	public static boolean equals(SimpleDate s1, SimpleDate s2){
		if(s1.getMonth() == s2.getMonth() &&
		   s1.getDay() == s2.getDay()     &&
		   s1.getYear() == (s2.getYear())){
			return true;
		}
		return false;
	}
	
	/******************************************************************
	 * Compares this SimpleDate to other SimpleDate
	 * 
	 * @param other another SimpleDate to compare to
	 * @return 1 if this is greater than other
	 * @return 0 if this equals other
	 * @return -1 if this is less than other
	 *****************************************************************/
	public int compareTo(SimpleDate other){
		if (this.year > other.year)
			return 1;
		if (this.year < other.year)
			return -1;

		if (this.month > other.month)
			return 1;
		if (this.month < other.month)
			return -1;

		if (this.day > other.day)
			return 1;
		if (this.day < other.day)
			return -1;

		return 0;
	}
	
	/******************************************************************
	 * Checks if the year is a leap year
	 * 
	 * @return true if it is a leap year
	 * @return false if it is not a leap year
	 *****************************************************************/
	public boolean isLeapYear(){
		if(year%100 == 0){
			if(year%400 == 0){
				return true;
			}
			return false;
		}
		if(year%4 == 0){
			return true;
		}
		return false;
	}
	
	/******************************************************************
	 * Checks if a certain year is a leap year
	 * 
	 * @param year a year to check if it is a leap year
	 * @return true if it is a leap year
	 * @return false if it is not a leap year
	 *****************************************************************/
	public static boolean isLeapYear(int year){
		if(year%100 == 0){
			if(year%400 == 0){
				return true;
			}
			return false;
		}
		if(year%4 == 0){
			return true;
		}
		return false;
	}
	
	/******************************************************************
	 * Gets the number of days so far in a year
	 * 
	 * @return sum the number of days before current SimpleDate
	 *****************************************************************/
	public int ordinalDate(){
		int sum = 0;
		
		//subtracts a month because it is not a full month
		int pmonth = month - 1;
		while(pmonth > 0){
			sum += daysInMonth(pmonth, year);
			pmonth --;
		}
		sum += day;
		return sum;
	}
	
	/******************************************************************
	 * Gets the number of days so far in a year for a given date
	 * 
	 * @param year the year of the date
	 * @param month the month of the date
	 * @param day the day of the date
	 * @return sum the number of days before current SimpleDate
	 *****************************************************************/
	public int ordinalDate(int month, int day, int year){
		int sum = 0;
		
		//subtracts a month because it is not a full month
		int pmonth = month - 1;
		while(pmonth > 0){
			sum += daysInMonth(pmonth, year);
			pmonth --;
		}
		sum += day;
		return sum;
	}
	
	/******************************************************************
	 * Goes forward a day from the current date
	 *****************************************************************/
	public void increment(){
		//if the month has 31 days in it
		if(month == 1 || month == 3 ||
		   month == 5 || month == 7 ||
		   month == 8 || month == 10 ||
		   month == 12){
			
			//if it is the last day of the month
			if(day == 31){
				//if it is the last day of the last month of the year
				if(month == 12){
					month = 1;
					day = 1;
					year++;
					return;
				}
				//if it is not the last month of the year
				day = 1;
				month++;
				return;
			}
			//if it is not the last day of the month
			day++;
			
		}
		//if the month is February
		else if(month == 2){
			//if it is a leap year
			if(isLeapYear()){
				//if it is the last day of the month in a leap year
				if(day == 29){
					day = 1;
					month++;
					return;
				}
			}
			//if it is the last day of the month and not a leap year
			if(day == 28){
				day = 1;
				month ++;
				return;
			}
			//if it is February and it is not the last day of the month
			day++;
		}
		else{
			//if it is the last day of the month
			if(day == 30){
				//if it is the last day of the last month
				if(month == 12){
					month = 1;
					day = 1;
					year++;
					return;
				}
				//if it is the last day of the month
				day = 1;
				month++;
				return;
			}
			//if it is not the last day of the month
			day++;
		}
	}

	/******************************************************************
	 * Goes back a day from the current date
	 *****************************************************************/
	public void decrement(){
		//if it is the first day of the month
		if(day == 1){
			//if the month is January
			if(month == 1){
				month = 12;
				year --;
				day = 31;
				return;
			}
			//if the month is March
			if(month == 3){
				//if it is a leap year
				if(isLeapYear()){
					day = 29;
					month --;
					return;
				}
				//if it is not a leap year
				day = 28;
				month --;
				return;
			}
			//if it is the first day of the month
			day = 30;
			month --;
			//if this month has 31 days
			if(month == 1 || month == 3 ||
			   month == 5 || month == 7 ||
			   month == 8 || month == 10 ||
			   month == 12){
				day++;
				return;
			}
			return;
		}
		//if the day is not the first of the month
		day --;
	}

	/******************************************************************
	 * Creates a string of the date
	 * Taken from blackboard example
	 * 
	 * @return sDay a string of the date
	 *****************************************************************/
	public String toString(){
		String sDay = "";
		String sMonth = "";

		if (month == 1)
			sMonth = "January";	
		if (month == 2)
			sMonth = "February";
		if (month == 3)
			sMonth = "March";
		if (month == 4)
			sMonth = "April";
		if (month == 5)
			sMonth = "May";
		if (month == 6)
			sMonth = "June";
		if (month == 7)
			sMonth = "July";
		if (month == 8)
			sMonth = "August";
		if (month == 9)
			sMonth = "September";
		if (month == 10)
			sMonth = "October";
		if (month == 11)
			sMonth = "November";
		if (month == 12)
			sMonth = "December";

		if (day < 10)
			sDay = "0" + day;
		else
			sDay = "" + day;
		
		return sDay + " " + sMonth + " " + year;
	}
	
	/******************************************************************
	 * Gets the number of simple dates
	 * 
	 * @return count the number of SimpleDates
	 *****************************************************************/
	public static int getNumberOfSimpleDates(){
		return count;
	}
	
	/******************************************************************
	 * Gets the date that is n days away from this date
	 * 
	 * @param n the number of days away the date is requested
	 * @return sd a SimpleDate of the date n days from now
	 *****************************************************************/
	public SimpleDate daysFromNow(int n){
		if(day < 1 || month < 1 || year < MIN_YEAR){
			throw new IllegalArgumentException();
		}
		
		SimpleDate sd = new SimpleDate();
		sd.setDay(day);
		sd.setMonth(month);
		sd.setYear(year);
		
		//if date requested is negative, give date in the past
		if(n < 0){
			while(n < 0){
				sd.decrement();
				n++;
			}
			return sd;
		}
		//if date requested is positive, give date in future
		while(n > 0){
			sd.increment();
			n--;
		}
		return sd;
	}
	
	/******************************************************************
	 * Returns the number of days in a given month
	 * 
	 * @param month the month requested
	 * @param year the year of the month requested
	 * @return 29 for february if it is a leap year
	 * @return 28 for february if it is not a leap year
	 * @return 31 if the month has 31 days
	 * @return 30 if the month has 30 days
	 *****************************************************************/
	private static int daysInMonth(int month, int year) {
		//if it is February and a leap year
		if (month == 2 && isLeapYear(year)) {
			return 29;
		}
		//if it is February and not a leap year
		else if(month == 2){
			return 28;
		}
		//if the month has 31 days
		if(month == 1 || month == 3 ||
		   month == 5 || month == 7 ||
		   month == 8 || month == 10 ||
	   	   month == 12){
			return 31;
		}
		//if the month has 30 days
		else{
			return 30;
		}
	}
	
	/******************************************************************
	 * Returns the number of days since a given date was from
	 * this date
	 * 
	 * @param other a SimpleDate to get the number of days since
	 * @return count the number of days since a given date
	 *****************************************************************/
	public int daysSince(SimpleDate other){
		if(day < 1 || month < 1 || year < MIN_YEAR){
			throw new IllegalArgumentException();
		}
		int thisDate = ordinalDate();
		int otherDate=ordinalDate(other.month, other.day, other.year);
		
		//if otherDate follows thisDate, return negative number of days 
		if(otherDate > thisDate){
			otherDate = otherDate - thisDate;
			return otherDate = otherDate * -1;
		}
		//if thisDate follows otherDate, return number of days since
		if(thisDate > otherDate){
			return thisDate = thisDate - otherDate;
		}
		return 0;
	}

	/******************************************************************
	 * Loads a file with the date in it
	 *****************************************************************/
	public void load(){	
		String m = "";
		try{
			Scanner fileReader = new Scanner(new File("test.txt"));
			m = fileReader.next();
			fileReader.close();
		}
		catch(FileNotFoundException error){
			System.out.println("File not found");
		}
		
		String [] part;
		part = m.split("/");

		day = Integer.parseInt(part[1].trim());
		month = Integer.parseInt(part[0].trim());
		year = Integer.parseInt(part[2].trim());
	}

	/******************************************************************
	 * Saves a file with the date in it
	 *****************************************************************/
	public void save(){
		PrintWriter out = null;
		try{
			out = new PrintWriter(new BufferedWriter(new FileWriter("test.txt")));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		String s = (month + "/" + day + "/" + year);
		out.println(s);
		out.close();
	}
}