package com.AirportEntities;

public class PassengerQueue {

	public static int first = 0;
	public static int last = 0;
	public static int maxSize = 20;
	public static int NumberofPassengers = 0;
	public static Passenger[] passengersQueue = new Passenger[maxSize];

	PassengerQueue() {

		for (int i = 0; i < passengersQueue.length; i++) {
			Passenger passenger = new Passenger();
			passenger.setFirstName("Empty");
			passenger.setSurname("Empty");
			passengersQueue[i] = passenger;

//			System.out.println("Passenger queue is Initialized" + " " + passengersQueue[i].getFirstName() + " "
//					+ passengersQueue[i].getSurname());
		}
		System.out.println("Queue is initialized with Passengers: " + NumberofPassengers + " length: " + passengersQueue.length);

		
	}

	public void add(Passenger passenger) {

		if (isFull()) {

			System.err.println("Error: Queue is FUll with " + NumberofPassengers + " Passengers");

		} else if (last == passengersQueue.length) {
			first = first - 1;
			passengersQueue[first] = passenger;
			NumberofPassengers = NumberofPassengers + 1;
		} else {
			last = last + 1;
			passengersQueue[last] = passenger;
			NumberofPassengers = NumberofPassengers + 1;
		}

		System.out
				.println("Passenger " + passenger.getFirstName() + " " + passenger.getSurname() + " added on " + last);
	}

	public void remove() {
		if (isEmpty()) {
			System.err.println("Error: Queue is Empty!");
		} else {

			for (int i = 0; i < passengersQueue.length; i++) {
				// remove only the first item of the queue (i == first)
				if ((!passengersQueue[i].getFirstName().equalsIgnoreCase("Empty")
						|| !passengersQueue[i].getSurname().equalsIgnoreCase("Empty")) && (i == first)) {
					
					passengersQueue[i].setFirstName("Empty");
					passengersQueue[i].setSurname("Empty");
					System.out.println(passengersQueue[i].getFirstName() + " " + passengersQueue[i].getSurname()
							+ " removed!");
					NumberofPassengers = NumberofPassengers - 1;
					System.out.println("Passengers: " + NumberofPassengers);
					if (isEmpty()) {
						System.err.println("Error: Queue is Emptied!");
						first = 0;
						last = 0;
						break;
					} else {
						first++;
					}
					break;
				} else {
					//not removed
				}

			}

		}
	}

	public void display() {

		if (isEmpty()) {
			System.err.println("Error: Queue is Empty!");
		} else {
			for (Passenger passenger1 : passengersQueue) {
				System.out.println(passenger1.getFirstName() + " " + passenger1.getSurname());
			}
			System.out.println("Passengers: " + NumberofPassengers);
		}

	}

	public int getMaxSize(Passenger[] ArrayofPassenger) {

		return maxSize;
	}

	public boolean isEmpty() {

		return (NumberofPassengers == 0);

	}

	public boolean isFull() {

		return (NumberofPassengers == passengersQueue.length);
	}

}
