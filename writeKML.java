package Shana_B;

import java.io.IOException;

public class writeKML {
	public static String write(String[][] csv) throws IOException {
		String kmlstart = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n<Document>\n<name>result</name>\n";
		String kmlend = "</Document>/n</kml>";
		String kml = kmlstart;
		for (int i=1;i<csv.length;i++)
		{
			if(csv[i][0]!=null)//check that the line is not deleted in the filter
			{	
				int counter =0;
				String specieltime=csv[i][0].substring(0, csv[i][0].indexOf(" "))+"T"+csv[i][0].substring(csv[i][0].indexOf(" ")+1)+"Z";
				kml+="<Folder>" +
						"<name>"+csv[i][0]+"</name>\n" +	
						"<TimeStamp>\n"+
						"<when>"+specieltime+"</when>\n"+
						"</TimeStamp>\n"+
						"<Placemark>\n" +
						"<name>"+csv[i][0]+"</name>\n"+
						"<description>\n";
				for (int j=6;j<((int)Double.parseDouble(csv[i][5])*4+6);j+=4)
				{					
					kml+=counter+"\n"+
							"SSID:"+csv[i][j]+"\n"+
							"MAC:"+csv[i][j+1]+"\n"+
							"Frequncy"+csv[i][j+2]+"\n"+
							"Signal"+csv[i][j+3]+"\n";
					counter++;
				}
				kml+="</description>\n" +
						"<Point>\n" +
						"<coordinates>"+csv[i][3]+","+csv[i][2]+","+csv[i][4]+ "</coordinates>\n" +
						"</Point>\n" +
						"</Placemark>\n"+
						"</Folder>";
			}
		}
		kml += kmlend;
		return kml;
	}
}
