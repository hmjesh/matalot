package ShanaB;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class contain the Database of the project
 *
 * @author yitzchak shneller
 * @version 1
 */
public class Export {
    /**
     * Export file of database to csv file
     *
     * @param db   the database to export
     * @param path place to export
     */
    public void csv(wpoint[] db, File path) {
        StringBuilder text = new StringBuilder("Time,ID,Lat,Lon,Alt,#WiFinetworks(up to 10)," +
                "SSID1,MAC1,Frequncy1,Signal1," +
                "SSID2,MAC2,Frequncy2,Signal2," +
                "SSID3,MAC3,Frequncy3,Signal3," +
                "SSID4,MAC4,Frequncy4,Signal4," +
                "SSID5,MAC5,Frequncy5,Signal5," +
                "SSID6,MAC6,Frequncy6,Signal6," +
                "SSID7,MAC7,Frequncy7,Signal7," +
                "SSID8,MAC8,Frequncy8,Signal8," +
                "SSID9,MAC9,Frequncy9,Signal9," +
                "SSID10,MAC10,Frequncy10,Signal10" + ((char) 10));
        for (wpoint db1 : db) {
            if ((db1 != null)) {
                text.append(db1.getTim().toString()).append(",")
                        .append(db1.getId()).append(",")
                        .append(db1.getLat() + "").append(",")
                        .append(db1.getLon() + "").append(",")
                        .append(db1.getAlt() + "").append(",")
                        .append(db1.getWifiNetworks()).append(",");
                String[] ssid = db1.getSsid();
                String[] mac = db1.getMac();
                int[] frequncy = db1.getFrequncy();
                int[] signal = db1.getSignal();
                for (int i = 0; i < db1.getWifiNetworks(); i++) {
                    text.append(ssid[i]).append(",")
                            .append(mac[i]).append(",")
                            .append(frequncy[i]).append(",")
                            .append(signal[i]).append(",");
                }
                text.append((char) 10);
            }
        }
        writer(text.toString(), path.getAbsolutePath() + ".csv");
    }

    /**
     * Export file of database to mkl file
     *
     * @param db   the database to export
     * @param path place to export
     */
    public void kml(wpoint[] db, File path) {
        String kmlstart = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n<Document>\n<name>result</name>\n";
        String kmlend = "</Document>/n</kml>";
        String kml = kmlstart;
        for (wpoint db1 : db) {
            if (db1 != null)//check that the line is not deleted in the Filter
            {
                int counter = 0;
                String time = db1.getTim().toString();
                String specieltime = time.substring(0, time.indexOf(" ")) + "T" + time.substring(time.indexOf(" ") + 1) + "Z";
                kml += "<Folder>" +
                        "<name>" + time + "</name>\n" +
                        "<TimeStamp>\n" +
                        "<when>" + specieltime + "</when>\n" +
                        "</TimeStamp>\n" +
                        "<Placemark>\n" +
                        "<name>" + time + "</name>\n" +
                        "<description>\n";
                String[] ssid = db1.getSsid();
                String[] mac = db1.getMac();
                int[] frequncy = db1.getFrequncy();
                int[] signal = db1.getSignal();
                for (int i = 0; i < db1.getWifiNetworks(); i++) {
                    kml += counter + "\n" +
                            "SSID:" + ssid[i] + "\n" +
                            "MAC:" + mac[i] + "\n" +
                            "Frequncy" + frequncy[i] + "\n" +
                            "Signal" + signal[i] + "\n";
                    counter++;
                }
                kml += "</description>\n" +
                        "<Point>\n" +
                        "<coordinates>" + db1.getLon() + "," + db1.getAlt() + "," + db1.getAlt() + "</coordinates>\n" +
                        "</Point>\n" +
                        "</Placemark>\n" +
                        "</Folder>";
            }
        }
        kml += kmlend;
        writer(kml, path.getAbsolutePath() + ".kml");
    }

    /**
     * Export file of saving filters to filt file
     *
     * @param filter the filters to export
     * @param path   place to export
     */
    public void filters(String[][] filter, File path) {
        StringBuilder text = new StringBuilder();
        for (String[] row : filter) {
            for (String cell : row)
                if (cell != null)
                    text.append(cell).append(",");
            text.substring(0, text.length() - 1);
            text.append((char) 10);
        }
        writer(text.toString(), path.getAbsolutePath() + ".filt");
    }

    /**
     * writing the prepered file to the computer
     *
     * @param file the prepered file
     * @param path the path to save in
     */
    private static void writer(String file, String path) {
        try {
            FileWriter fw = new FileWriter(path);
            PrintWriter outs = new PrintWriter(fw);
            outs.print(file);
            outs.close();
            fw.close();
        } catch (IOException ex) {
            System.out.print("Error writing file\n" + ex);
        }
    }
}
