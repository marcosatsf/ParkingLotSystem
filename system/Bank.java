package system;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class Bank {

	private static Bank instance = null;
	private float carMult, motorcycleMult, miniTruckMult;
	private Dictionary<LocalDateTime,List<String>> vehicleOutByDay;//carros pagos naquele dia
	private Dictionary<LocalDateTime,List<String>> vehicleEntryByDay;//carros pagos naquele dia
	//private Dictionary<String,LocalDateTime> x;
	private Dictionary<String,LocalDateTime> vehicleEntryDay;
	private Dictionary<LocalDateTime,List<Float>> plateToValue;//valores pagos naquele dia
	
	
	private Bank(){
		this(10,5,15);
	}
	
	private Bank(float carMult,float motorcycleMult, float miniTruckMult){
		vehicleOutByDay = new Hashtable<LocalDateTime,List<String>>();
		vehicleEntryByDay = new Hashtable<LocalDateTime,List<String>>();
		vehicleEntryDay = new Hashtable<String,LocalDateTime>();
		plateToValue = new Hashtable<LocalDateTime,List<Float>>();
		this.carMult = carMult;
		this.motorcycleMult = motorcycleMult;
		this.miniTruckMult = miniTruckMult;
	}

	public static Bank getInstance(){
		if(instance == null) instance = new Bank();
		return instance;
	}
	
	public void setMultipliers(float carMult, float motorcycleMult, float miniTruckMult){
		this.carMult = carMult;
		this.motorcycleMult = motorcycleMult;
		this.miniTruckMult = miniTruckMult;
	}
	
	public float getMultCar()
	{
		return carMult;
	}
	public float getMultMotorcycle()
	{
		return motorcycleMult;
	}
	public float getMultMiniTruck()
	{
		return miniTruckMult;
	}
	
	public void addVehicleOutByDay(LocalDateTime k, String v, float value){
		List<String> addNewVehicle = new ArrayList<String>();
		List<Float> addNewValue = new ArrayList<Float>();
		if(vehicleOutByDay.isEmpty()){
			addNewVehicle.add(v);
			vehicleOutByDay.put(k, addNewVehicle);
			addNewValue.add(value);
			plateToValue.put(k, addNewValue);
		}
		else{
			if(vehicleOutByDay.get(k) != null){
				addNewVehicle = vehicleOutByDay.get(k);
				addNewVehicle.add(v);
				addNewValue = plateToValue.get(k);
				addNewValue.add(value);
				vehicleOutByDay.remove(k);
				plateToValue.remove(k);
				vehicleOutByDay.put(k, addNewVehicle);
				plateToValue.put(k, addNewValue);
			}
			else{
				addNewVehicle.add(v);
				addNewValue.add(value);
				vehicleOutByDay.put(k, addNewVehicle);
				plateToValue.put(k, addNewValue);
			}
		}
	}
	
	public void addVehicleEntryByDay(LocalDateTime k, String v, float value){
		List<String> addNewVehicle = new ArrayList<String>();
		List<Float> addNewValue = new ArrayList<Float>();
		if(vehicleOutByDay.isEmpty()){
			addNewVehicle.add(v);
			vehicleOutByDay.put(k, addNewVehicle);
			addNewValue.add(value);
			plateToValue.put(k, addNewValue);
		}
		else{
			if(vehicleOutByDay.get(k) != null){
				addNewVehicle = vehicleOutByDay.get(k);
				addNewVehicle.add(v);
				addNewValue = plateToValue.get(k);
				addNewValue.add(value);
				vehicleOutByDay.remove(k);
				plateToValue.remove(k);
				vehicleOutByDay.put(k, addNewVehicle);
				plateToValue.put(k, addNewValue);
			}
			else{
				addNewVehicle.add(v);
				addNewValue.add(value);
				vehicleOutByDay.put(k, addNewVehicle);
				plateToValue.put(k, addNewValue);
			}
		}
	}
	
	public void addVehicleEntryDay(String k, LocalDateTime v) {
		vehicleEntryDay.put(k, v);
	}
	
	public float getPriceByDay(LocalDateTime whichDay){
		float counter =0;
		for(Float actualValue : plateToValue.get(whichDay)){
			counter+=actualValue;
		}
		return counter;
	}
	
	public float getPrice(LocalDateTime initialDate, LocalDateTime finalDate)
	{
		float counter =0;
		while(initialDate.getDayOfMonth() != finalDate.getDayOfMonth()){
			counter += getPriceByDay(initialDate);
			initialDate.plusDays(1);
		}
		return counter;
	}
	
	public int getQuantity(LocalDateTime initialDate, LocalDateTime finalDate)
	{
		int counter =0;
		while(initialDate.getDayOfMonth() != finalDate.getDayOfMonth()){
			counter += vehicleOutByDay.get(initialDate).size();
			initialDate.plusDays(1);
		}
		return counter;
	}
}
