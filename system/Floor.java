package system;

import java.util.ArrayList;
import java.util.List;

public class Floor {
	
	private int carsSlotNumb, motorcyclesSlotNumb, miniTrucksSlotNumb;
	
	private List<Slot> slots = new ArrayList<Slot>();

	public Floor(int carsSlotNumb,int motorcyclesSlotNumb,int miniTrucksSlotNumb) {
		this.carsSlotNumb = carsSlotNumb;
		this.motorcyclesSlotNumb = motorcyclesSlotNumb;
		this.miniTrucksSlotNumb = miniTrucksSlotNumb;
		
		for(int i = carsSlotNumb; i > 0; i++ ){
			slots.add(new Slot(VehicleType.CAR));
		}
		
		for(int i = motorcyclesSlotNumb; i > 0; i++ ){
			slots.add(new Slot(VehicleType.MOTORCYCLE));
		}
		
		for(int i = miniTrucksSlotNumb; i > 0; i++ ){
			slots.add(new Slot(VehicleType.MINITRUCK));
		}
	}

}
