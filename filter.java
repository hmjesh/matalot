package Shana_B;

public class filter {
	public static String filt(String[][] tbl) {
		if (tbl.length == 1)// check table
			return "wrong file";
		String firstpart = "", secontpart = "", finnaly = "";
		int i = 2, j = i, amount;
		boolean ok=false;
		while (i < tbl.length) {
			amount = 0;
			secontpart = "";
			firstpart = tbl[i][3] + "," + 
					tbl[0][5].substring(8) + "," +
					tbl[i][6] + "," + 
					tbl[i][7] + "," + tbl[i][7] + ",";
			while (tbl[i][3].equals(tbl[j][3]))
				if (j + 1 < tbl.length)
					j++;
				else{
					j++;
					ok=!ok;
					break;
				}
			if ((j - i) > 10)// if there is more then 10 packs in the same time
			{
				int[][] strongest = new int[10][2];
				for (int k = 0; k < 10; k++) {// the maximum 10
					strongest[k][0] = -10000;
					strongest[k][1] = 0;
				}
				while (i < j) {
					for (int l = 0; l < 10; l++) {
						if (tbl[i][10].equals("WIFI"))
							if (Integer.parseInt(tbl[i][5]) > strongest[l][0]) {
								for (int q = 8; q >= l; q--) {
									strongest[q + 1][0] = strongest[q][0];
									strongest[q + 1][1] = strongest[q][1];
								}
								strongest[l][0] = Integer.parseInt(tbl[i][5]);
								strongest[l][1] = i;
								break;
							}
					}
					amount++;
					i++;
				}
				for (int l = 0; l < 10; l++) {
					secontpart += "," + tbl[strongest[l][1]][1] + "," + tbl[strongest[l][1]][0] + ","
							+ tbl[strongest[l][1]][4] + "," + tbl[strongest[l][1]][5];
				}
			} else {
				while (i < j) {
					if (tbl[i][10].equals("WIFI")) {
						secontpart += "," + tbl[i][1] + "," + tbl[i][0] + "," + tbl[i][4] + "," + tbl[i][5];
						amount++;
					}
					i++;
				}
			}
			if(amount>10)
				amount=10;
			finnaly += firstpart + amount + secontpart + ((char) 10);
			if(ok)
				break;
		}
		return finnaly;
	}
}
