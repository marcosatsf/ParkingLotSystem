package system;

import java.util.ArrayList;

public class ParkingLot {

	private static ParkingLot instance = null;
	private ArrayList<Floor> parkingLot;
	private Bank caixa;
	
	private ParkingLot() {
		parkingLot = new ArrayList<Floor>();
		parkingLot.add(new Floor(60,20,20));//Floor T
		parkingLot.add(new Floor(100,0,0));//Floor 1
		
		caixa = Bank.getInstance();
	}
	
	public void setMultipliers(float carMult, float motorcycleMult, float miniTruckMult){
		caixa.setMultipliers(carMult, motorcycleMult, miniTruckMult);
	}
	
	public float getMultCar()
	{
		return caixa.getMultCar();
	}
	
	public ArrayList<VehicleType> getSlotsVehicleType(int whichFloor){//retorna uma lista dos slots 
		for(Floor f : parkingLot)
		{
			for(Vehicle each : f.getSlots()){
				
			}
		}
		return parkingLot.get(whichFloor).getSlots();
	}
	
	//public ArrayList<Boolean> getSlotsDisponibility(int whichFloor){
		
	//}
	
	public static ParkingLot getInstance(){
		if(instance == null) instance = new ParkingLot();
		return instance;
	}
}
