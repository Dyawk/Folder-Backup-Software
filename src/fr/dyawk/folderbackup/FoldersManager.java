package fr.dyawk.folderbackup;

import java.io.File;
import java.io.IOException;

public class FoldersManager {
	
	static String mainSavesPath = "./Backups";
    static int actualSaves = 0;
    
    public static void Create() throws IOException {
    	CreateFolder(mainSavesPath);
    	CreateSaveFolder();
    	CreateBackupFolder();
    }
    
    // FR: Fonction principale de création de dossiers, permet de créer un dossier dans un répertoire indiqué.
    // EN: Main function for creating folders, allows you to create a folder in a specified directory.
    public static File CreateFolder(String path) throws IOException {
        File folder = new File(path);
        boolean success = folder.mkdir();
        
        if(success) { System.out.println(" > Creation of directory '" + path + "' done."); }
        else { System.out.println(" > Directory '" + path + "' localized."); }
        
        return folder;
    }
    
    // FR: Fonction permettant de créer un répertoire pour le nom du dossier enregistré dans Storage.var.get(Storage.FolderName);
    // EN: Function to create a folder for the name registered in Storage.var.get(Storage.FolderName);
    public static void CreateSaveFolder() throws IOException {
        File folder = CreateFolder(mainSavesPath + "/" + Storage.var.get(Storage.configLines[1]));
        File[] files = folder.listFiles();
        actualSaves = 0;
        for (File file : files) {
            if (file.isDirectory()) {
                actualSaves++;
            }
        }
    }
    
    // FR: Créé un dossier de backup dans le repértoire. Par exemple "Sauvegarde 1", "Sauvegarde 2" (et peut-étre indiquer la date)
    // EN: Create a backup folder in the directory. For example "Backup 1", "Backup 2" (and maybe indicate the date)
    public static void CreateBackupFolder() throws IOException {
        String newFolderName = "Backup " + (actualSaves + 1);
        String path = mainSavesPath + "/" + Storage.var.get(Storage.configLines[1]) + "/" + newFolderName;
        CreateFolder(path);
        Storage.backupFolderPath = path;
    }
}