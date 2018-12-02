package system;

import java.io.Serializable;

public class Vehicle implements Serializable{

	private String plate;
	
	private VehicleType type;
	
	public Vehicle(String plate, VehicleType type) {
		this.plate = plate;
		this.type = type;
	}

	public String getPlate() {
		return plate;
	}

	public VehicleType getType() {
		return type;
	}	
	
}
