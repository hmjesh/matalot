package Shana_B;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class readResult {
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
			}else
				if(ch != 13)			
					text += (char) ch + "";

		}
		fi.close();
		if(rows==0){//error, not enough rows
			String[][] a = new String[1][1];
			a[0][0] = "wrong file";
			return a;
		}
		//converting into String[][]
		String[][] csv = new String[rows][46];
		int lastcell = 0, nextrow = 0;
		for (int i = 0; i < rows; i++) {//every row
			int nextcell = 0;
			nextrow = text.indexOf("%", nextrow + 1);
			int j = 0;
			while (nextcell < nextrow && nextcell != -1) {//every cell
				if(j==46){//error, too much cells
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
			if(j<10){//error, not enough cells, exept then first row
				String[][] a = new String[1][1];
				a[0][0] = "wrong file";
				return a;
			}
			lastcell = nextrow;
		}
		for(int i=9;i<45;i+=4)
		{
			if(csv[csv.length-1][i+1]==null)
			{
				csv[csv.length-1][i]=csv[csv.length-1][i].substring(0, csv[csv.length-1][i].length()-1);
				break;
			}
		}
		//print(csv,rows);
		return csv;
	}
	private static void print(String[][] csv, int rows)
	{
		for (int k = 0; k < rows; k++) { for (int l = 0; l < 46; l++)
			System.out.print(csv[k][l] + " " + l + ","); System.out.println(); }
		System.out.println();
	}
}
