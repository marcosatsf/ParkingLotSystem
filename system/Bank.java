package system;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class Bank {

	private float carMult, motorcycleMult, miniTruckMult;
	
	private Dictionary<LocalDateTime,List<Float>> plateToValue;//valores pagos naquele dia
	
	public Bank(){
		this(10,5,15); //Multiplicadores Iniciais
	}
	
	private Bank(float carMult,float motorcycleMult, float miniTruckMult){
		plateToValue = new Hashtable<LocalDateTime,List<Float>>();
		this.carMult = carMult;
		this.motorcycleMult = motorcycleMult;
		this.miniTruckMult = miniTruckMult;
	}
	
	
	public void addPlateToValue(LocalDateTime exitDate, float value) {
		List<Float> list = plateToValue.get(exitDate);
		if(list != null) {
			list.add(value);
		}else {
			list = new ArrayList<Float>();
			list.add(value);
		}
		plateToValue.put(exitDate, list);
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
	
	public float calculatePrice(LocalDateTime entryDate, LocalDateTime exitDate, VehicleType type) {
		
		float value = 1;
				
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
	
	
}
