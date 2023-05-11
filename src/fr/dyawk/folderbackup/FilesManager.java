package fr.dyawk.folderbackup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FilesManager {
	
	public static void createConfigFile() {
		if(Storage.c.exists()) {
			System.out.println(" >> 'config.yml' successfully loaded.");
		} else {
			try {
				Storage.c.createNewFile();
				System.out.println("\n >> 'config.yml' created in the current directory.");
				Utils.CONFIG_askDirectory();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// FR: Cette fonction lit le fichier de config au démarrage, et place les valeurs correspondantes dans Storage.var (à n'utiliser qu'une fois donc.)
	// EN: This function reads the config file at startup, and places the corresponding values in Storage.var (to be used only once.)
	public static void readConfigFile() {
		BufferedReader reader = null;
		try {
			FileReader fr = new FileReader(Storage.c);
			reader = new BufferedReader(fr); 
			String line;
			
			while((line = reader.readLine()) != null) {

				String[] splitter = line.split(": ");
				String lineFinal0 = splitter[0];
				String lineFinal1 = splitter[1];
				
				Storage.var.put(lineFinal0, lineFinal1);
			}
			Utils.setCurrentPaths();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}
	
	public static void writeData(File file, String data) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			fw.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void loadFiles() throws InterruptedException {
		createConfigFile();
		readConfigFile();
		System.out.println("");
		Thread.sleep(500);
	}
}