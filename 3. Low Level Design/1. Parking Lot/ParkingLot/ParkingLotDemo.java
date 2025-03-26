package ParkingLot;

import ParkingLot.VehicleType.Bike;
import ParkingLot.VehicleType.Car;
import ParkingLot.VehicleType.Truck;
import ParkingLot.VehicleType.Vehicle;

public class ParkingLotDemo {
	public static void main(String[] args) {
		ParkingLot parkingLot = ParkingLot.getInstance();

		parkingLot.addLevel(new ParkingLevel(1, 10));
		parkingLot.addLevel(new ParkingLevel(2, 20));

		Vehicle bike1 = new Bike("A-1");
		Vehicle bike2 = new Bike("X-2");
		Vehicle car1 = new Car("ABC-123");
		Vehicle car2 = new Car("XYZ-456");
		Vehicle truck1 = new Truck("ABCD-1234");
		Vehicle truck2 = new Truck("WXYZ-5678");

		parkingLot.parkVehicle(bike1);
		parkingLot.parkVehicle(bike2);
		parkingLot.parkVehicle(car1);
		parkingLot.parkVehicle(car2);
		parkingLot.parkVehicle(truck1);
		parkingLot.parkVehicle(truck2);

		parkingLot.displayAvailability();

		parkingLot.unparkVehicle(bike2);
		parkingLot.unparkVehicle(car1);

		parkingLot.displayAvailability();
	}
}

/*
 * HashMap<String, ParkingLot> map;
 * 
 * 
 */