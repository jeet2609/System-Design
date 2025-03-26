package ParkingLot;

import ParkingLot.VehicleType.Vehicle;
import ParkingLot.VehicleType.VehicleType;
import java.util.ArrayList;
import java.util.List;

public class ParkingLevel {
	private final int floor;
	private final List<ParkingSpot> parkingSpots;

	public ParkingLevel(int floor, int numberOfSpots) {
		this.floor = floor;
		this.parkingSpots = new ArrayList<>(numberOfSpots);

		// Assign spots in ration of 50:40:10 for bikes, cars and trucks
		int numberOfBikes = (int) (numberOfSpots * 0.50);
		int numberOfCars = (int) (numberOfSpots * 0.40);

		for (int i = 1; i <= numberOfSpots; i++) {
			String spotNumber = String.valueOf(floor) + '-' + String.valueOf(i);
			if (i <= numberOfBikes) {
				parkingSpots.add(new ParkingSpot(spotNumber, VehicleType.BIKE));
			} else if (i > numberOfBikes && i <= numberOfBikes + numberOfCars) {
				parkingSpots.add(new ParkingSpot(spotNumber, VehicleType.CAR));
			} else {
				parkingSpots.add(new ParkingSpot(spotNumber, VehicleType.TRUCK));
			}
		}
	}

	public synchronized boolean parkVehicle(Vehicle vehicle) {
		for (ParkingSpot spot : parkingSpots) {
			if (spot.isAvailable() && spot.getVehicleType().equals(vehicle.getVehicleType())) {
				spot.parkVehicle(vehicle);
				return true;
			}
		}

		return false;
	}

	public synchronized boolean unparkVehicle(Vehicle vehicle) {
		for (ParkingSpot spot : parkingSpots) {
			if (!spot.isAvailable() && spot.getParkedVehicle().equals(vehicle)) {
				spot.unparkVehicle();
				return true;
			}
		}

		return false;
	}

	public void displayAvailability() {
		System.err.println("\nLevel " + floor + " Availability");
		for (ParkingSpot spot : parkingSpots) {
			if (spot.isAvailable()) {
				System.err.println("Spot " + spot.getSpotNumber() + ": " + "Available For " + spot.getVehicleType());
			} else {
				System.err.println("Spot " + spot.getSpotNumber() + ": " + "Occuped By " + spot.getVehicleType() + " "
						+ spot.getParkedVehicle().getLicensePlate());
			}
		}
	}
}
