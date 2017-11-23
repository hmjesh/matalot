package Shana_B;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class writer {
	public static void write(String file, String path) {
		try {
			FileWriter fw = new FileWriter(path);
			PrintWriter outs = new PrintWriter(fw);
			outs.print(file);
			outs.close();
			fw.close();
		}
		catch(IOException ex) {
			System.out.print("Error writing file\n" + ex);
		}
	}
}
