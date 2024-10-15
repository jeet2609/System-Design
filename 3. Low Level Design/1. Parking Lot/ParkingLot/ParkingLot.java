package ParkingLot;

import java.util.List;

import ParkingLot.VehicleType.Vehicle;

import java.util.ArrayList;

public class ParkingLot {
    private static ParkingLot parkingLotInstance;
    private final List<ParkingLevel> parkingLevels;

    private ParkingLot() {
        parkingLevels = new ArrayList<>();
    }

    public static synchronized ParkingLot getInstance() {
        if(parkingLotInstance == null) {
            parkingLotInstance = new ParkingLot();
        }
        return parkingLotInstance;
    }

    public void addLevel(ParkingLevel parkingLevel) {
        parkingLevels.add(parkingLevel);
    }

    public synchronized boolean parkVehicle(Vehicle vehicle) {
        for(ParkingLevel parkingLevel : parkingLevels) {
            if(parkingLevel.parkVehicle(vehicle)) {
                System.out.println("Vehicle Parked Successfully");
                return true;
            }
        }

        System.out.println("Could Not Park Vehicle");
        return true;
    }

    public synchronized boolean unparkVehicle(Vehicle vehicle) {
        for(ParkingLevel parkingLevel : parkingLevels) {
            if(parkingLevel.unparkVehicle(vehicle)) {
                return true;
            }
        }

        return false;
    }

    public void displayAvailability() {
        for(ParkingLevel parkingLevel : parkingLevels) {
            parkingLevel.displayAvailability();
        }
    }
}
