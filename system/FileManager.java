package system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileManager {
	private static final String defaultPath = "DataBase.bin";

	public FileManager() {
	}
	
	public ParkingLot loadFileData() {
		ParkingLot parking = null;
		
		try {
			File file = new File(defaultPath);
			FileInputStream fi = new FileInputStream(file);
			ObjectInputStream input = new ObjectInputStream(fi);
			
			parking = (ParkingLot) input.readObject();
			input.close();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return parking;
		
	}
	
	/*
	 * ------------------------------------------------guarda
	File file = new File("teste.bin");
	
	
	List<Estudante> s1 = new ArrayList<Estudante>();
	s1.add(new Estudante("Teste1", 5.0));
	s1.add(new Estudante("Teste2", 6.0));
	s1.add(new Estudante("Teste3", 4.0));
	
	try {
		FileOutputStream fileOut = new FileOutputStream(file);
		ObjectOutputStream output = new ObjectOutputStream(fileOut);
		for(Estudante x : s1) output.writeObject(x);
		output.close();
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	--------------------------------------------------------carrega
	List<Estudante> s2 = new ArrayList<Estudante>();
		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream input = new ObjectInputStream(fileIn);
			
			try {
				while(true)
				{
					Estudante x = (Estudante)  input.readObject();
					s2.add(x);
				}
			}catch(EOFException e) {
			}
			input.close();
			for(Estudante x : s2) System.out.println("Nome: " + x.getName() + "\nNota: " + x.getGrade());
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	*/
	
	public void saveFileData(ParkingLot parking) {
		
		try {
			File file = new File(defaultPath);
			FileOutputStream fo = new FileOutputStream(file);
			ObjectOutputStream output = new ObjectOutputStream(fo);
			
			output.writeObject(parking);
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}

}
