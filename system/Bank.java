package system;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class Bank {

	private static Bank instance = null;
	private float carMult, motorcycleMult, miniTruckMult;
	private Dictionary<LocalDateTime,List<Vehicle>> vehicleOutByDay;//carros pagos naquele dia
	//private Dictionary<String,LocalDateTime> x;
	//private Dictionary<String,LocalDateTime> y;
	private Dictionary<LocalDateTime,List<Float>> plateToValue;//valores pagos naquele dia
	
	
	private Bank(){
		vehicleOutByDay = new Hashtable<LocalDateTime,List<Vehicle>>();
		plateToValue = new Hashtable<LocalDateTime,List<Float>>();
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
	
	public void addVehicleOutByDay(LocalDateTime k, Vehicle v, float value){
		List<Vehicle> addNewVehicle = new ArrayList<Vehicle>();
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
