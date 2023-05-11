package fr.dyawk.folderbackup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Utils {

	public static String getSplittedLine(String str, int i) {
		String[] _string = str.split(": ");
		return _string[i];
	}
	
	public static void setCurrentPaths() {
		Storage.programPath = System.getProperty("user.dir");
		Storage.folderPath = Storage.var.get(Storage.configLines[0]);
		Storage.folderName = Storage.var.get(Storage.configLines[1]);
	}
	
	public static void clearConsole(){
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
	    } catch (IOException | InterruptedException ex) {}
	}
	
	public static void CONFIG_askDirectory() {
		int i = 0;
		Scanner scan = new Scanner(System.in);
		String[] paths = new String[3];
		String[] msgs = { 
			"  > Enter the directory to copy below (" + Storage.configLines[0] + "):",
			"  > Enter the name of your choice which will be the name of the created directory (" + Storage.configLines[1] + ")"
		};
		
		
		System.out.println("\n CONFIGURATION:");
		while(i < 2) {
			System.out.println(msgs[i]);
			String r = scan.nextLine();
			paths[i] = r;
			i++;
		}
		
		FilesManager.writeData(Storage.c, 
				Storage.configLines[0] + ": " + paths[0] 
				+ "\n" +
				Storage.configLines[1] + ": " + paths[1]);	
		FilesManager.readConfigFile();
		
		System.out.println("Start backup program ? (y/n)");
		String str_s = scan.next();
				
		if(str_s.equals("N") || str_s.equals("n")) System.exit(0);  
		
		scan.close();
	}
	
	public static void copyFiles(String sourceDirPath, String targetDirPath) throws IOException {
	    File sourceDir = new File(sourceDirPath);
	    File targetDir = new File(targetDirPath);
	    if(!targetDir.exists()) {
	        targetDir.mkdirs();
	    }
	    for(File sourceFile : sourceDir.listFiles()){
	    	System.out.println("Copy of '" + sourceFile + "' to directory '" + sourceDir + "'");
	        File targetFile = new File(targetDir.getAbsolutePath() + File.separator + sourceFile.getName());
	        if(sourceFile.isDirectory()){
	            copyFiles(sourceFile.getAbsolutePath(), targetFile.getAbsolutePath());
	        } else {
	            Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	        }
	    }
	}
}