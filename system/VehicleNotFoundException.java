package system;

@SuppressWarnings("serial")
public class VehicleNotFoundException extends Exception {

	public String getMessage() {
		return "Veículo não encontrado!";
	}
	
}
