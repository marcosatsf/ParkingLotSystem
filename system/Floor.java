package system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Floor implements Serializable{
	
	private int carsSlotNumb, motorcyclesSlotNumb, miniTrucksSlotNumb;
	
	private List<Slot> slots;

	public Floor(int carsSlotNumb,int motorcyclesSlotNumb,int miniTrucksSlotNumb) {
		this.carsSlotNumb = carsSlotNumb;
		this.motorcyclesSlotNumb = motorcyclesSlotNumb;
		this.miniTrucksSlotNumb = miniTrucksSlotNumb;
		
		slots = new ArrayList<Slot>();
		
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
		case DEFAULT:
			//Do nothing
			break;
		default:
			//Do nothing
			break;
		}
		
		return output;
		
	}
	
	public void removeVehicle(String plate) {
		
		for(Slot slot : slots) {
			if(slot.getVehicle() != null)
				if(slot.getVehicle().getPlate().equals(plate)) {
					slot.removeVehicle();
					break;
				}
		}
		
	}
	
	public boolean checkVehicleExistence(String plate) {
		
		for(Slot slot : slots) {
			if(slot.getVehicle()!= null)
				if(slot.getVehicle().getPlate().equals(plate))
					return true;
		}
		
		return false;
		
	}
	
	public List<Slot> getSlots(){
		return slots;
	}
	
	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}
	
	public VehicleType getVehicleType(String plate) {
		for(Slot slot : slots) {
			if(slot.getVehicle()!= null)
				if(slot.getVehicle().getPlate().equals(plate))
					return slot.getVehicleType();
		}
		
		return VehicleType.DEFAULT;
	}
	
	

}
