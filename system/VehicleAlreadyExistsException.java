package system;

@SuppressWarnings("serial")
public class VehicleAlreadyExistsException extends Exception {

	public String getMessage() {
		return "Placa já cadastrada no sistema!";
	}

}
