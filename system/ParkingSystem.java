package system;

import javax.swing.UIManager;

import framesPackage.ParkingFrame;

public class ParkingSystem {

	public static void main(String[] args) {
		
		int look = 3;
		ChangeUILook(look);
		
		new ParkingFrame();

	}
	
	private static void ChangeUILook(int look) {
		UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
		try {
			UIManager.setLookAndFeel(looks[look].getClassName());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
