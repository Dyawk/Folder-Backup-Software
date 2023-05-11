package fr.dyawk.folderbackup;

import java.io.File;
import java.util.Dictionary;
import java.util.Hashtable;

public class Storage {

	public static String windowName = "Folder Backup Software";
	public static File c = new File("config.yml");

	public static boolean hasBeenLaunchedOnce = false;
	
	public static Dictionary<String, String> var = new Hashtable<String, String>();

	// configuration //
	
	public static String[] configLines = { "Path", "Name" };
	
	public static String programPath;
	public static String backupFolderPath;
	public static String folderPath;
	
	public static String folderName;
	public static int backupNumber;
	
}