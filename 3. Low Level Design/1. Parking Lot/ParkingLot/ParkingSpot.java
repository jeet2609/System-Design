package ParkingLot;

import ParkingLot.VehicleType.Vehicle;
import ParkingLot.VehicleType.VehicleType;

public class ParkingSpot {
	private final String spotNumber;
	private final VehicleType vehicleType;
	private Vehicle parkedVehicle;

	public ParkingSpot(String spotNumber, VehicleType vehicleType) {
		this.spotNumber = spotNumber;
		this.vehicleType = vehicleType;
	}

	public synchronized boolean isAvailable() {
		return parkedVehicle == null;
	}

	public synchronized void parkVehicle(Vehicle vehicle) {
		if (isAvailable() && vehicle.getVehicleType().equals(this.vehicleType)) {
			parkedVehicle = vehicle;
		} else {
			throw new IllegalArgumentException("Invalid vehicle type or spot already occupied.");
		}
	}

	public synchronized void unparkVehicle() {
		parkedVehicle = null;
	}

	public VehicleType getVehicleType() {
		return this.vehicleType;
	}

	public Vehicle getParkedVehicle() {
		return this.parkedVehicle;
	}

	public String getSpotNumber() {
		return this.spotNumber;
	}
}
