package system;

@SuppressWarnings("serial")
public class OutOfSpaceException extends Exception {

	public String getMessage() {
		return "Estacionamento cheio!";
	}

}
