package system;

public class Slot {

	private boolean disponibility;
	
	//date
	
	private Vehicle vehicle;
	
	private VehicleType slotType;
	
	public Slot(VehicleType slotType) {
		
		this.slotType = slotType;
		disponibility = true;
		
	}
	
	public boolean getDisponibility() {
		return disponibility;
	}
	
	
	public boolean addVehicle(Vehicle newVehicle) {
		
		boolean output = false;
		
		if(newVehicle.getType().equals(slotType)) {
			vehicle = newVehicle;
			disponibility = false;
			output = true;
		}
		
		return output;
	}
	
	public void removeVehicle() {
		if (vehicle!= null) vehicle = null;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}

}
