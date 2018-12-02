package system;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class ParkingLot implements Serializable{

	private static ParkingLot instance = null;
	private List<Floor> floors;
	private Bank bank;
	
	private Dictionary<String,LocalDateTime> vehicleEntryDay = new Hashtable<String, LocalDateTime>();
	private Dictionary<LocalDateTime,List<String>> vehicleOutByDay = new Hashtable<LocalDateTime, List<String>>();//carros pagos naquele dia
	
	private ParkingLot() {
		floors = new ArrayList<Floor>();
		floors.add(new Floor(60,20,20));//Floor T
		floors.add(new Floor(100,0,0));//Floor 1
		
		bank = new Bank();
	}
	
	public static ParkingLot getInstance(){
		if(instance == null) {
			if((instance = FileManager.loadFileData()) == null) {
				instance = new ParkingLot();
			}
		}
		return instance;
	}
	
	
	public void saveParkingLot() {
		FileManager.saveFileData(instance);
	}
	
	public void setMultipliers(float carMult, float motorcycleMult, float miniTruckMult){
		bank.setMultipliers(carMult, motorcycleMult, miniTruckMult);
	}
	
	public double getMultCar()
	{
		return bank.getMultCar();
	}
	
	public double getMultMotorcycle()
	{
		return bank.getMultMotorcycle();
	}
	
	public double getMultMiniTruck()
	{
		return bank.getMultMiniTruck();
	}
	
	public void addVehicle(VehicleType type, String entryDateString, String entryTimeString, String vehiclePlate) 
			throws VehicleAlreadyExistsException, OutOfSpaceException, DateTimeException 
	{
		
		boolean output = false;
		
		Vehicle newVehicle = new Vehicle(vehiclePlate, type);
		
		for(Floor floor : floors) {
			
			if(floor.checkVehicleExistence(vehiclePlate))
				throw new VehicleAlreadyExistsException();
			
			String[] datePieces = entryDateString.split("/");
			String[] timePieces = entryTimeString.split(":");
			int year, monthNumb,day, hour, minute;
			Month month;
			
			year = Integer.parseInt(datePieces[2]);
			monthNumb = Integer.parseInt(datePieces[1]);
			month = Month.of(monthNumb);
			day = Integer.parseInt(datePieces[0]);
			hour = Integer.parseInt(timePieces[0]);
			minute = Integer.parseInt(timePieces[1]);
			
			
			LocalDateTime entryDate;
			try {
				entryDate = LocalDateTime.of(year, month, day, hour, minute);
			}catch(DateTimeException dateException) {
				throw dateException;
			}
			
			if(floor.addVehicle(newVehicle)) {
				//Veículo adicionado
				output = true;
				
				addVehicleEntryDay(vehiclePlate, entryDate);
				
				break;
			}
		}
		
		if(!output) 
			throw new OutOfSpaceException();
		
	}
	
	public double removeVehicle(String vehiclePlate, String exitDateString, String exitTimeString) 
	throws BreakingTimeException, DateTimeException, VehicleNotFoundException
	{
		
		double value = 0;
		
		boolean found = false;
		
		for(Floor floor : floors) {
			if(floor.checkVehicleExistence(vehiclePlate)) {
				
				found = true;
				
				String[] datePieces = exitDateString.split("/");
				String[] timePieces = exitTimeString.split(":");
				
				int year = Integer.parseInt(datePieces[2]);
				int monthNumb = Integer.parseInt(datePieces[1]);
				Month month = Month.of(monthNumb);
				int day = Integer.parseInt(datePieces[0]);
				int hour = Integer.parseInt(timePieces[0]);
				int minute = Integer.parseInt(timePieces[1]);
				
				
				LocalDateTime exitDate;
				try {
					exitDate = LocalDateTime.of(year, month, day, hour, minute);
				}catch(DateTimeException dateException) {
					throw dateException;
				}
				
				LocalDateTime entryDate = getVehicleEntryDate(vehiclePlate);
				
				if(!entryDate.isBefore(exitDate)) {
					throw new BreakingTimeException();
				}
				
				VehicleType type = floor.getVehicleType(vehiclePlate);
				
				addVehicleOutByDay(exitDate, vehiclePlate);
		
				value = bank.calculatePrice(entryDate, exitDate, type);

				bank.addPlateToValue(exitDate, value);
				
				floor.removeVehicle(vehiclePlate);
				
			}
		}
		
		if(!found) throw new VehicleNotFoundException();
			
		
		return value;
	}
	
	@SuppressWarnings("javadoc")
	public ArrayList<Integer> getQuantityArray(LocalDateTime initialDate, LocalDateTime finalDate)
	{
		ArrayList<Integer> counter = new ArrayList<Integer>();
		
		while(initialDate.compareTo(finalDate) < 1){//compareTo returns 1 when x is greater than y chronologically
			List<String> dayList = vehicleOutByDay.get(initialDate);
			if(dayList != null)
				counter.add(dayList.size());
			else counter.add(0);
			initialDate = initialDate.plusDays(1);
		}
		return counter;
	}
	
	public int getQuantity(LocalDateTime initialDate, LocalDateTime finalDate)
	{
		int counter =0;
		ArrayList<Integer> aux = getQuantityArray(initialDate,finalDate);
		for(Integer x : aux) counter += x;
		return counter;
	}
	
	public LocalDateTime toLocalDateTime(String stringValue) throws DateTimeException{
		
		String[] datePieces = stringValue.split("/");
		
		int year, monthNumb,day;
		Month month;
		
		year = Integer.parseInt(datePieces[2]);
		monthNumb = Integer.parseInt(datePieces[1]);
		month = Month.of(monthNumb);
		day = Integer.parseInt(datePieces[0]);
		
		LocalDateTime date;
		try {
			date = LocalDateTime.of(year, month, day, 0, 0);
		}catch(DateTimeException dateException) {
			throw dateException;
		}
		
		return date;
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

	public void addVehicleEntryDay(String k, LocalDateTime v) {
		vehicleEntryDay.put(k, v);
	}
	
	public void addVehicleOutByDay(LocalDateTime k, String v){
		List<String> list = vehicleOutByDay.get(k);
		
		if(list != null) {
			list.add(v);
		}else {
			list = new ArrayList<String>();
			list.add(v);
		}
		
		vehicleOutByDay.put(k, list);
	}
	
	public LocalDateTime getVehicleEntryDate(String vehiclePlate) {
		return vehicleEntryDay.get(vehiclePlate);
	}
}
