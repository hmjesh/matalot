/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShanaB;

import java.text.ParseException;

/**
 * This class contain the Database of the project
 *
 * @author yitzchak shneller
 * @version 1
 */
public class Algoritem {
    /**
     * get mac, and find his place
     *
     * @param mac the mac to find place
     * @param db  the information to calculate place with
     * @return massage of the place
     */
    public String macPlace(String mac, Database db) {
        String msg = "for mac" + mac;
        double la = 0, lo = 0, al = 0, m = 0;
        for (wpoint wp : db.getDb()) {
            if ((wp != null))
                for (int j = 0; j < wp.getWifiNetworks(); j++) {
                    if (wp.getMac()[j].equals(mac)) {
                        double m1 = 1 % (wp.getSignal()[j] * wp.getSignal()[j]);
                        la += wp.getLat() * m1;
                        lo += wp.getLon() * m1;
                        al += wp.getAlt() * m1;
                        m += m1;
                        break;
                    }
                }
        }
        if ((la == 0))
            return "no iformation detected";
        msg += "lat=" + la / m + la % m + ", ";
        msg += "lon=" + lo / m + lo % m + ", ";
        msg += "alt=" + al / m + al % m + ", ";
        return msg;
    }

    /**
     * get a row of scan without place info, and find the place
     *
     * @param wpRow the row tithout the place
     * @param db    the information to calculate place with
     * @return the row with the place
     */
    public String wpPlace(String wpRow, Database db) {
        try {
            String[] data = wpRow.split(",");
            wpoint wp = new wpoint(data);
            find(wp, db);
            return wp.toString();
        } catch (ParseException e) {
            return "wrong row";
        }
    }

    /**
     * get 3 macs and 3 signals. need to find the scan place
     *
     * @param mac1    info to calculate with
     * @param mac2    info to calculate with
     * @param mac3    info to calculate with
     * @param signal1 info to calculate with
     * @param signal2 info to calculate with
     * @param signal3 info to calculate with
     * @param db      the information to calculate place with
     * @return the  place
     */
    public String scanPlace(String mac1, String mac2, String mac3, int signal1, int signal2, int signal3, Database db) {
        wpoint row = new wpoint();
        String mac[] = {mac1, mac2, mac3};
        int signal[] = {signal1, signal2, signal3};
        row.addWifi(mac, mac, signal, signal);

        find(row, db);
        return ("the scan place is: lat:" + row.getLat() + ", lon:" + row.getLon() + ", alt:" + row.getAlt() + ".");
    }

    /**
     * doing the realy calculation of place
     *
     * @param wp the row without the place
     * @param db the information to calculate place withw
     */
    private void find(wpoint wp, Database db) {
        wpoint[] have = db.getDb();
        String checking;
        int numOfHave=db.getCounter();
        double[][] calcul = new double[numOfHave + 1][wp.getWifiNetworks() * 3 + 1];//create calculation table
        for (double[] calcul1 : calcul) {// set the last column to 1, so it can be the result.
            calcul1[calcul1.length - 1] = 1;
        }
        for (int j = 0; j < calcul[0].length - 1; j += 3)//insert the knowlage
        {
            calcul[0][j] = wp.getSignal()[j]; //the lost signal
            checking = wp.getMac()[j];//the lost mac, for comparing
            for (int u = 1; u < calcul.length; u++) //the have signal	 j=the signal to calculate, column. u=row in the calculate table.
            {
                calcul[u][j] = -120;        //if there is no information about that mac
                calcul[u][j + 1] = 100;
                    for (int h = 0; h < have[u-1].getWifiNetworks(); h++) //if there is an information about that  mac
                    {
                        if (checking.equals(have[u - 1].getMac()[h])) {
                            calcul[u][j] = have[u - 1].getSignal()[h];
                            calcul[u][j + 1] = Math.abs(calcul[u][j] - calcul[0][j]);
                            if (calcul[u][j + 1] < 3) {
                                calcul[u][j + 1] = 3;
                            }
                        }
                    }
                calcul[u][j + 2] = 10000 / (Math.pow(calcul[u][j + 1], 0.4) * Math.pow(calcul[0][j], 2));
                calcul[u][calcul[u].length - 1] *= calcul[u][j + 2];
            }
        }
        // here we have almost all the first row, we just have to calculate the last columns.
        double sum[] = {0, 0, 0, 0};
        for (int j = 0; j < numOfHave; j++)//calculate the sum
        {
            sum[0] += calcul[j][calcul[0].length - 1];
            sum[1] += have[j].getLat() * calcul[j][calcul[0].length - 1];
            sum[2] += have[j].getLon() * calcul[j][calcul[0].length - 1];
            sum[3] += have[j].getAlt() * calcul[j][calcul[0].length - 1];
        }
        //enter the missed location
        wp.setLat(sum[1] / sum[0]);
        wp.setLon(sum[2] / sum[0]);
        wp.setAlt(sum[3] / sum[0]);
    }

}
