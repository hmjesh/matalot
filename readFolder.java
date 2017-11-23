package Shana_B;
import java.io.File;
//based on https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder

public class readFolder {
	public static File[] read(String path)
	{
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
    	/*for (int i = 0; i < listOfFiles.length; i++) 
    	{
    		if (listOfFiles[i].isFile()) 
    			System.out.println("File " + listOfFiles[i].getName());
    		else
    			if (listOfFiles[i].isDirectory()) 
    				System.out.println("Directory " + listOfFiles[i].getName());
    	}**/
    	return listOfFiles;
    }
}
