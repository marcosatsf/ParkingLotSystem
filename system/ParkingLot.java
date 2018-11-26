package system;

import java.util.ArrayList;

public class ParkingLot {

	private static ParkingLot instance = null;
	private ArrayList<Floor> parkingLot;
	
	private ParkingLot() {
		parkingLot.add(new Floor(60,20,20));//Floor T
		parkingLot.add(new Floor(100,0,0));//Floor 1
	}
	
	public static ParkingLot getInstance(){
		if(instance == null) instance = new ParkingLot();
		return instance;
	}

}
