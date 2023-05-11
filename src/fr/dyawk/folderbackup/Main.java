package fr.dyawk.folderbackup;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		Utils.clearConsole();
		
		if(Storage.c.exists() == false) FilesManager.createConfigFile();
		else FilesManager.loadFiles();
		
		FoldersManager.Create();
		
		Utils.clearConsole();
		System.out.println("Starting backup program...");
		System.out.println("");
		System.out.println(" >> Directory: '" + Storage.var.get(Storage.configLines[0]) + "'");
		System.out.println(" >> Name: '" + Storage.var.get(Storage.configLines[1]) + "'");
		System.out.println("");
		
		Thread.sleep((long) 1000f);
		
		Copy();
	}
	
	public static void Copy() throws IOException {
		System.out.println(" >> Copy to the target directory.... \n");
		Utils.copyFiles(Storage.folderPath, Storage.backupFolderPath);
	    System.out.println("\n >> Copy of files done.");
	}
}