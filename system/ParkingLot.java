package system;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

	private static ParkingLot instance = null;
	private List<Floor> floors;
	private Bank caixa;
	
	private ParkingLot() {
		floors = new ArrayList<Floor>();
		floors.add(new Floor(60,20,20));//Floor T
		floors.add(new Floor(100,0,0));//Floor 1
		
		caixa = Bank.getInstance();
	}
	
	public void setMultipliers(float carMult, float motorcycleMult, float miniTruckMult){
		caixa.setMultipliers(carMult, motorcycleMult, miniTruckMult);
	}
	
	public float getMultCar()
	{
		return caixa.getMultCar();
	}
	
	public boolean addVehicle(VehicleType type, String entryData, String entryTime, String vehiclePlate) {
		
		boolean output = false;
		
		Vehicle newVehicle = new Vehicle(vehiclePlate, type);
		
		for(Floor floor : floors) {
			if(floor.addVehicle(newVehicle)) {
				//Veículo adicionado
				output = true;
				break;
			}else {
				//Veículo não adicionado
			}
		}
		
		return output;
	}
	
	public List<VehicleType> getSlotsVehicleType(int whichFloor){//retorna uma lista dos slots
		List<Slot> slots =  floors.get(whichFloor).getSlots();
		List<VehicleType> vTypes = new ArrayList<VehicleType>();
		for(Slot slot : slots){
			if(slot.getVehicle()!=null)
				vTypes.add(slot.getVehicle().getType());
			else vTypes.add(VehicleType.DEFAULT);
		}
		return vTypes;
	}
	
	public List<Boolean> getSlotsDisponibility(int whichFloor){
		List<Slot> slots =  floors.get(whichFloor).getSlots();
		List<Boolean> isFreeSlot = new ArrayList<Boolean>();
		for(Slot each : slots){
			isFreeSlot.add(each.getDisponibility());
		}
		return isFreeSlot;
		
	}
	
	public static ParkingLot getInstance(){
		if(instance == null) instance = new ParkingLot();
		return instance;
	}
}
