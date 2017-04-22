package com.AirportEntities;

public class PassengerQueue {

	
	private int first = 0;
	private int last = 0;
	private int maxSize = 20;
	private int NumberofPassengers = 0;
	public Passenger[] passengersQueue = new Passenger[maxSize];
	
	PassengerQueue() {

		for (int i = 0; i < passengersQueue.length; i++) {
			Passenger passenger = new Passenger();
			passenger.setFirstName("Empty");
			passenger.setSurname("Empty");
			passengersQueue[i]= passenger;
			
			System.out.println("Passenger queue is Initialized" + " " + passengersQueue[i].getFirstName() + " "
					+ passengersQueue[i].getSurname());
		}
		
//		for (Passenger passenger : passengersQueue) {
//
//			
//				passenger = new Passenger();
//				passenger.setFirstName("Empty");
//				passenger.setSurname("Empty");
//				add(passenger);
//			
//			
//			System.out.println("Passenger queue is Initialized" + " " + passenger.getFirstName() + " "
//					+ passenger.getSurname());
//		}

	}

	public void add(Passenger passenger) {

		if (isFull()) {
			System.err.println("Error: Queue is FUll!");
		} else {
			passengersQueue[last] = passenger;
			last++;
			NumberofPassengers++;
		}
	}

	public void remove() {
		if (isEmpty()) {
			System.err.println("Error: Queue is Empty!");
		} else {
			for (int i = 0; i < passengersQueue.length; i++) {

				if ((!passengersQueue[i].getFirstName().equalsIgnoreCase("Empty")
						|| !passengersQueue[i].getSurname().equalsIgnoreCase("Empty"))&&(i==first)) {

					passengersQueue[i].setFirstName("Empty");
					passengersQueue[i].setSurname("Empty");
					
					break;
				}
				
				if(!passengersQueue[i].getFirstName().equalsIgnoreCase("Empty")
						|| !passengersQueue[i].getSurname().equalsIgnoreCase("Empty")){
					first = i++;
				}
				else{
					first = 0 ;
				}

			}
//			passengersQueue[first] = null;
//			first--;
			NumberofPassengers--;
		}
	}

	public void display() {

		if (isEmpty()) {
			System.err.println("Error: Queue is Empty!");
		} else {
			for (Passenger passenger1 : passengersQueue) {
				System.out.println(passenger1.getFirstName() + " " + passenger1.getSurname());
			}
			
			
		}
		
//		for (int i = 0; i < passengersQueue.length; i++) {
//			System.out.println(passengersQueue[i].getFirstName() + " " + passengersQueue[i].getSurname());
//		}
		
	}

	public int getMaxSize(Passenger[] ArrayofPassenger) {

		return maxSize;
	}

	public boolean isEmpty() {

		return (first == last) && (NumberofPassengers == 0);

	}

	public boolean isFull() {

		return (NumberofPassengers == passengersQueue.length);
	}

}
