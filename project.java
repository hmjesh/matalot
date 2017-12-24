package Shana_B;

import java.io.File;
import java.io.IOException;

public class project {

	public static void main(String[] args) throws IOException {
		/*File[] filesToRead = readFolder.read("c:\\whigle_wifi");
		String files ="Time,ID,Lat,Lon,Alt,#WiFinetworks(up to 10),SSID1,MAC1,Frequncy1,Signal1,SSID2,MAC2,Frequncy2,Signal2,SSID3,MAC3,Frequncy3,Signal3,SSID4,MAC4,Frequncy4,Signal4,SSID5,MAC5,Frequncy5,Signal5,SSID6,MAC6,Frequncy6,Signal6,SSID7,MAC7,Frequncy7,Signal7,SSID8,MAC8,Frequncy8,Signal8,SSID9,MAC9,Frequncy9,Signal9,SSID10,MAC10,Frequncy10,Signal10"+((char)10);
		String csv;
		for (int i = 0; i < filesToRead.length; i++) {
			csv = filter.filt(readWIGLE.read(filesToRead[i]));
			if (!csv.equals("wrong file"))
				files +=csv;
		}
		//System.out.println(files);
		writer.write(files, "c:\\whigle_wifi\\result.csv");

		File res = new File ("c:\\whigle_wifi\\result.csv");
		String[][] result = readResult.read(res);
		writer.write(kmlFilter.filt(result), "c:\\whigle_wifi\\result.kml");*/
		
		String[][] have = readResult.read(new File ("c:\\whigle_wifi\\have.csv"));

		String[][] lost = readResult.read(new File ("c:\\whigle_wifi\\lost.csv"));
		finder.find(lost, have, 3);
		String text="";
		for(int i =0;i<lost.length;i++)
		{
			for(int j=0;j<lost[i].length;j++)
			{
				try
				{
					text+=lost[i][j]+",";
				}
				catch (Exception e) {}//if it null
			}
			text.substring(0, text.length()-1);
			text+=((char)10)+"";
		}
		writer.write(text, "c:\\whigle_wifi\\lost1.csv");

	}
}
