package system;

import java.util.ArrayList;
import java.util.List;

public class Floor {
	
	private int carsSlotNumb, motorcyclesSlotNumb, miniTrucksSlotNumb;
	
	private List<Slot> slots = new ArrayList<Slot>();

	public Floor(int carsSlotNumb,int motorcyclesSlotNumb,int miniTrucksSlotNumb) {
		this.carsSlotNumb = carsSlotNumb;
		this.motorcyclesSlotNumb = motorcyclesSlotNumb;
		this.miniTrucksSlotNumb = miniTrucksSlotNumb;
		
		for(int i = (carsSlotNumb + motorcyclesSlotNumb + miniTrucksSlotNumb); i > 0; i--){
			slots.add(new Slot());
		}
	}
	
	
	public boolean addVehicle(Vehicle newVehicle) {
		
		boolean output = false;
		
		switch(newVehicle.getType()) {
		case CAR:
			for(int i = 0;i < carsSlotNumb;i++) {
				if(slots.get(i).addVehicle(newVehicle)) {
					output = true;
					break;
				}
			}
			break;
		case MINITRUCK:
			for(int i = carsSlotNumb;i < (carsSlotNumb + miniTrucksSlotNumb);i++) {
				if(slots.get(i).addVehicle(newVehicle)) {
					output = true;
					break;
				}
			}
			break;
		case MOTORCYCLE:
			for(int i = (carsSlotNumb + miniTrucksSlotNumb);i < (carsSlotNumb + miniTrucksSlotNumb+motorcyclesSlotNumb);i++) {
				if(slots.get(i).addVehicle(newVehicle)) {
					output = true;
					break;
				}
			}
			break;
		}
		
		return output;
		
	}
	
	public void removeVehicle(Vehicle vehicle) {
		
		for(Slot slot : slots) {
			if(slot.getVehicle().getPlate().equals(vehicle.getPlate())) {
				slot.removeVehicle();
				break;
			}
		}
		
	}
	
	public List<Vehicle> getSlots(){
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		for(Slot slot : slots) {
			vehicles.add(slot.getVehicle());
		}
		return vehicles;
	}
	
	

}
