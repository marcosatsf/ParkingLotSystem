package system;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class Bank implements Serializable{

	private double carMult, motorcycleMult, miniTruckMult;
	
	private Dictionary<LocalDateTime,List<Double>> plateToValue;//valores pagos naquele dia
	
	public Bank(){
		this(10,5,15); //Multiplicadores Iniciais
	}
	
	private Bank(double carMult,double motorcycleMult, double miniTruckMult){
		plateToValue = new Hashtable<LocalDateTime,List<Double>>();
		this.carMult = carMult;
		this.motorcycleMult = motorcycleMult;
		this.miniTruckMult = miniTruckMult;
	}
	
	public void addPlateToValue(LocalDateTime exitDate, double value) {
		List<Double> list = plateToValue.get(exitDate);
		if(list != null) {
			list.add(value);
		}else {
			list = new ArrayList<Double>();
			list.add(value);
		}
		plateToValue.put(exitDate, list);
	}
	
	public void setMultipliers(double carMult, double motorcycleMult, double miniTruckMult){
		this.carMult = carMult;
		this.motorcycleMult = motorcycleMult;
		this.miniTruckMult = miniTruckMult;
	}
	
	public double getMultCar()
	{
		return carMult;
	}
	public double getMultMotorcycle()
	{
		return motorcycleMult;
	}
	public double getMultMiniTruck()
	{
		return miniTruckMult;
	}
	
	public double calculatePrice(LocalDateTime entryDate, LocalDateTime exitDate, VehicleType type) {
		
		double value = 1;
				
        long hoursDiff = ChronoUnit.HOURS.between(entryDate, exitDate);
        
        switch(type) {
		case CAR:
			value = carMult;
			break;
		case MINITRUCK:
			value = miniTruckMult;
			break;
		case MOTORCYCLE:
			value = motorcycleMult;
			break;
		case DEFAULT:
			break;
		default:
			break;
        }
        
        if(hoursDiff > 0) {
        	value += (value/2) * (hoursDiff);
        }
        
		
		return value;
	}
	
	public double getPriceByDay(LocalDateTime whichDay){
		double counter =0;
		for(Double actualValue : plateToValue.get(whichDay)){
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
	
	
}
