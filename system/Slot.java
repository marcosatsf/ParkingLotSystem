package system;

import java.io.Serializable;

public class Slot implements Serializable{

	private boolean disponibility;
	
	//date
	
	private Vehicle vehicle;
	
	public Slot() {
		
		disponibility = true;
		
	}
	
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	
	public boolean addVehicle(Vehicle newVehicle) {
		
		boolean output = false;
		
		if(disponibility) {
			vehicle = newVehicle;
			disponibility = false;
			output = true;
		}
		
		return output;
	}
	
	public void removeVehicle() {
		
		if (vehicle!= null) 
			vehicle = null;	
		
		disponibility = true;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public VehicleType getVehicleType() {
		return vehicle.getType();
	}
	
	public boolean getDisponibility() {
		return disponibility;
	}

}
