
/*
 * Lane Sharp
 * CS 1450-001
 * Assignment 4
 * Due: September 19, 2018
 * 
 * This program creates an array for a rail yard and sorts trains from a file provided.
 * The trains are then sorted by departing rail numbers and information for each train is 
 * displayed (Company, # of Cars, engine number, and destination). 
 * 
 */





import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class LsharpAssignment4 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File("sortingYardTrains.txt"));
		Train trains[];
		int n = scan.nextInt();
		trains = new Train[n];
		int engineNumber;
		String company;
		int numberRailCars;
		String destinationCity;
		int i = 0;
		
		while(scan.hasNext())
		{
			engineNumber = scan.nextInt();
			company = scan.next();
			numberRailCars = scan.nextInt();
			destinationCity = scan.next();
			
		}
		
		
		
	}
	
}

abstract class Train
{
	private int engineNumber;
	private String company;
	private int numberRailCars;
	private String destinationCity;	
	
	public Train(int engineNumber, String company, int numberRailCars, String destinationCity)
	{
		this.engineNumber = engineNumber;
		this.company = company;
		this.numberRailCars = numberRailCars;
		this.destinationCity = destinationCity;
	}
	
	public int getEngineNumber()
	{
		return engineNumber;
	}
	
	public String getCompany()
	{
		return company;
	}
	
	public int getNumberRailCars()
	{
		return numberRailCars;
	}
	
	public String getDestinationCity()
	{
		return destinationCity;
	}
}


