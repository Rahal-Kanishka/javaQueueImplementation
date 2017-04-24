package com.AirportEntities;

import java.io.File;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Airport {
	static Scanner scan = new Scanner(System.in);
	static PassengerQueue queue1;
	static Passenger[] passengers;
	static int nextpassengerposition=0;
	static int maximumlength=0;
	static int maximumwaitingtime=0;
	static int minimumwaitingtime=50;
	static int totoalwaitingtime=0;

	public static void main(String[] args) {
		queue1 = new PassengerQueue();
		passengers = new Passenger[30];
		displayMenueOptions();

		String letter = scan.nextLine();

		while (!letter.equalsIgnoreCase("X")) {

			switch (letter) {

			case "A": {
				System.out.println("Entered A\n");
				addPassenger();
				break;
			}
			case "V": {
				System.out.println("Entered V\n");
				displayPassengerQueue();
				break;
			}
			case "D": {
				System.out.println("Entered D\n");
				deletePassenger();
				break;
			}
			case "S": {
				System.out.println("Entered S\n");
				saveToFile();
				break;
			}
			case "L": {
				System.out.println("Entered L\n");
				loadFromFile();
				break;
			}
			case "R": {
				System.out.println("Entered R\n");
				readDatFile();
				
				while(nextpassengerposition<=20){
					joinQueue();
					setProcessingDelay();
					queue1.remove(); //removing the next passenger	
				}
				generateReport();
				
				

				break;
			}
			default: {
				System.out.println("\nPleae Enter a correct Option!");
			}

			}
			displayMenueOptions();
			letter = scan.nextLine();

		}
		// X code goes here
		System.out.println("Programme Simulation has been finished!");

	}
	private static void readDatFile(){
		try {
			Scanner scanFile = new Scanner(new File("passengers.dat"));
			String scanedFirstname;
			String scanedSurname;
		int i = 0;
		
			while(scanFile.hasNextLine()){
				String readline = scanFile.nextLine();
//				System.out.println(readline);
				Passenger pass = new Passenger();
				scanedFirstname = readline.split(" ")[0];
				scanedSurname = readline.split(" ")[1];
				pass.setFirstName(scanedFirstname);
				pass.setSurname(scanedSurname);
				passengers[i] = pass;
			i++;
			}
			
			
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}
		
	}
	
	private static void generateReport(){
		String report;
		
		report = "==============Report==============\r"
				+"maximum Waiting Time is: "+maximumwaitingtime+"\r"
				+"minimum Waiting Time is: "+minimumwaitingtime+"\r"
				+"maximum Queue length is: "+maximumlength+"\r";
		
		
		File file = new File("report.dat");
		
			try {
				PrintWriter pw = new PrintWriter(file);
				pw.write(report);
				System.out.println(report);
				pw.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	private static int D6(){
		
		int value;
		Random rn = new Random();
		value = rn.nextInt(6) +1;
		return value;
	}
	
	private static int nD6(int n){
		int sum=0;
		for (int i = 0; i < n; i++) {
			sum += D6();
		}
		return sum;
		
	}
	
	private static void joinQueue(){
		
		
		int joiningnumber;
		
		//random number of passengers who will join the queue @ Airport
		joiningnumber = D6();
		//Adding the passengers to the queue
		
		for (int i = nextpassengerposition; i <joiningnumber; i++) {
			//adding passengers to the queue untill its full
			queue1.add(passengers[i]);
			passengers[i]= null;
		}
		nextpassengerposition=+ joiningnumber;
		System.out.println(joiningnumber+" passengers Added to Queue");
		if (maximumlength<PassengerQueue.NumberofPassengers) {
			maximumlength=PassengerQueue.NumberofPassengers;
		}
		
	}
	
	private static void setProcessingDelay(){
		int delay;
		for (int i = 0; i < PassengerQueue.passengersQueue.length; i++) {
			delay = nD6(3);
			PassengerQueue.passengersQueue[i].setSecondsInQueue(delay);
			if (maximumwaitingtime<delay) {
				maximumwaitingtime=delay;
			}
			if (minimumwaitingtime>delay) {
				minimumwaitingtime=delay;
			}
			totoalwaitingtime+=delay;
		}
		
		
	}
	
	
	private static void displayMenueOptions() {

		System.out.println("--------------------Main Menue--------------------");
		System.out.println("\t Insert \"A\":  Add a passenger");
		System.out.println("\t Insert \"V\":  View passenger Queue");
		System.out.println("\t Insert \"D\":  Delete a passenger");
		System.out.println("\t Insert \"S\":  Save to a Text file");
		System.out.println("\t Insert \"L\":  Load from a text file");
		System.out.println("\t Insert \"R\":  Run the Simulation");
		System.out.println("\t Insert \"X\":  Exit the Simulation\n");

	}

	private static void addPassenger() {

		System.out.println("Please enter the First Name: ");
		String firstName = scan.nextLine();
		System.out.println("Please enter the  SurName: ");
		String surname = scan.nextLine();

		Passenger passenger = new Passenger();
		passenger.setFirstName(firstName);
		passenger.setSurname(surname);

		queue1.add(passenger);

	}

	private static void displayPassengerQueue() {
		queue1.display();

	}

	private static void deletePassenger() {
		queue1.remove();

	}

	private static void saveToFile() {

		File file = new File("Output.txt");
		try {
			PrintWriter pw = new PrintWriter(file);

			for (Passenger passenger : queue1.passengersQueue) {
				pw.write(passenger.getFirstName() + " " + passenger.getSurname() + "\r");
			}

			pw.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}

		if (file.exists()) {
			System.out.println("File exists");
		} else {
			System.out.println("file not created!");
		}
		

	}

	private static void loadFromFile() {
		System.out.println("Reading from the file\n");
		
		Scanner scanFile = null;
		PassengerQueue.NumberofPassengers = 0;
		int i=0;
		boolean firstresult=true;
		try {
			scanFile = new Scanner(new File("Output.txt"));
			String line;
			String scanedFirstname;
			String scanedSurname;
			
			while (scanFile.hasNextLine()) {
				
				line = scanFile.nextLine();
				System.out.println(line);
				scanedFirstname=line.split(" ")[0];
				scanedSurname = line.split(" ")[1];
				Passenger addpassenger = new Passenger();
				//checking whether the scanned lines are not empty fields of first and surnames
				if (!scanedFirstname.equalsIgnoreCase("Empty")&&
						!scanedSurname.equalsIgnoreCase("Empty")) {
					
					if (firstresult) {
						PassengerQueue.first = i;
						firstresult = false;
					}
					addpassenger.setFirstName(scanedFirstname);
					addpassenger.setSurname(scanedSurname);
					PassengerQueue.passengersQueue[i] = addpassenger;
					PassengerQueue.NumberofPassengers++;
					PassengerQueue.last = i;
//					queue1.add(addpassenger);	
				}else{
					
					addpassenger.setFirstName(scanedFirstname);
					addpassenger.setSurname(scanedSurname);
					PassengerQueue.passengersQueue[i] = addpassenger;
				}
				i++;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			scanFile.close();
		}
		
		System.out.println("Passengers: "+PassengerQueue.NumberofPassengers);
		

	}
}
