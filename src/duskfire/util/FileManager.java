package duskfire.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Kyle Mandell
 */
public class FileManager {
	
	public static Object load(String fileToLoad) {
		Object obj = new Object();
		
		FileInputStream saveFile;
		try {
			File f = new File(System.getProperty("user.dir") + "/data/" + fileToLoad + ".data");
			if(!f.exists()) {
				f.createNewFile();
			}			
			
			saveFile = new FileInputStream(System.getProperty("user.dir") + "/data/" + fileToLoad + ".data");
			ObjectInputStream restore = new ObjectInputStream(saveFile);
			
			obj = restore.readObject();
			restore.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return obj;
	}
	
	public static void save(String fileToSave, Object objToWrite) {
		FileOutputStream saveFile;
		try {
			saveFile = new FileOutputStream(System.getProperty("user.dir") + "/data/" + fileToSave + ".data");
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			
			save.writeObject(objToWrite);
			save.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
