package Shana_B;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class readWIGLE {
	public static String[][] read(File file) throws IOException {
		//check that the file is csv
		if (!file.getCanonicalPath().endsWith(".csv")) {
			String[][] a = new String[1][1];
			a[0][0] = "wrong file";
			return a;
		}
		//read the file to String
		String text = "";
		FileInputStream fi = new FileInputStream(file);
		int ch, rows = 0;
		while ((ch = fi.read()) != -1) {
			if (ch == 10) {
				rows++;
				text += "%";
			} else
				text += (char) ch + "";
		}
		fi.close();
		if(rows<2){//error, not enough rows
			String[][] a = new String[1][1];
			a[0][0] = "wrong file";
			return a;
		}
			//converting into String[][]
		boolean firstrow=true;
		String[][] csv = new String[rows][11];
		int lastcell = 0, nextrow = 0;
		for (int i = 0; i < rows; i++) {//every row
			int nextcell = 0;
			nextrow = text.indexOf("%", nextrow + 1);
			int j = 0;
			while (nextcell < nextrow && nextcell != -1) {//every cell
				if(j==11){//error, too much cells
					String[][] a = new String[1][1];
					a[0][0] = "wrong file";
					return a;
				}
				nextcell = text.indexOf(",", lastcell + 1);
				if (nextcell != -1) {
					if (nextcell < nextrow)
						csv[i][j] = text.substring(lastcell + 1, nextcell);
					else//last cell in the row
						csv[i][j] = text.substring(lastcell + 1, nextrow);
				}
				else//last cell of the table
					csv[i][j] = text.substring(lastcell + 1, text.length());
				lastcell = nextcell;
				j++;
			}
			if(j<11){//error, not enough cells, exept then first row
				if(firstrow)
					firstrow=false;
				else{
				String[][] a = new String[1][1];
				a[0][0] = "wrong file";
				return a;
				}
			}
			lastcell = nextrow;
		}
		csv[csv.length-1][10]=csv[csv.length-1][10].substring(0, csv[csv.length-1][10].length()-1);
		//print(csv,rows);
		return csv;
	}
	private static void print(String[][] csv, int rows)
	{
		 for (int k = 0; k < rows; k++) { for (int l = 0; l < 11; l++)
		 System.out.print(csv[k][l] + " " + l + ","); System.out.println(); }
		 System.out.println();
	}
}
