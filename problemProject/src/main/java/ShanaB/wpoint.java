package ShanaB;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This class is the basic, the wpoint is data container for wifi-points
 *
 * @author yitzchak shneller
 * @version 1
 */
public class wpoint {

    private Date tim;
    private String id;
    private double lat;
    private double lon;
    private double alt;
    private int wifiNetworks = 0;
    private String ssid[] = new String[10];
    private String mac[] = new String[10];
    private int frequncy[] = new int[10];
    private int signal[] = new int[10];
    private boolean notEmpty = false;

    public wpoint() {
    }

    /**
     * builder function
     *
     * @param info contain all information for wpoint
     * @throws java.text.ParseException if the information is wrong
     */
    public wpoint(String[] info) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tim = dateFormat.parse(info[0]);
        id = info[1];
        lat = Double.parseDouble(info[2]);
        lon = Double.parseDouble(info[3]);
        alt = Double.parseDouble(info[4]);
        wifiNetworks = (info.length - 6) / 4;
        for (int i = 0; i < wifiNetworks; i++) {
            ssid[i] = info[i * 4 + 6];
            mac[i] = info[i * 4 + 7];
            frequncy[i] = Integer.parseInt(info[i * 4 + 8]);
            signal[i] = Integer.parseInt(info[i * 4 + 9]);
        }
        notEmpty = true;
    }

    /**
     * @return time from tim
     */
    protected Date getTim() {
        return tim;
    }

    /**
     * @return id from id
     */
    protected String getId() {
        return id;
    }

    /**
     * @param id to set in id
     */
    protected void setId(String id) {
        this.id = id;
    }

    /**
     * @return lat from lat
     */
    protected double getLat() {
        return lat;
    }

    /**
     * @param lat to set in lat
     */
    protected void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * @return lon from lon
     */
    protected double getLon() {
        return lon;
    }

    /**
     * @param lon to set in lon
     */
    protected void setLon(double lon) {
        this.lon = lon;
    }

    /**
     * @return alt from alt
     */
    protected double getAlt() {
        return alt;
    }

    /**
     * @param alt to set in alt
     */
    protected void setAlt(double alt) {
        this.alt = alt;
    }

    /**
     * @return wiFinetworks from wiFinetworks
     */
    protected int getWifiNetworks() {
        return wifiNetworks;
    }

    /**
     * @return ssid from ssid
     */
    protected String[] getSsid() {
        return ssid;
    }

    /**
     * @return mac from mac
     */
    protected String[] getMac() {
        return mac;
    }

    /**
     * @return frequncy from frequncy
     */
    protected int[] getFrequncy() {
        return frequncy;
    }

    /**
     * @return signal from signal
     */
    protected int[] getSignal() {
        return signal;
    }


    /**
     * @param ssid     to set in ssid
     * @param mac      to set in mac
     * @param frequncy to set in frequncy
     * @param signal   to set in signal.
     *                 if there is already 10, it left the strongest signal
     */
    protected void addWifi(String[] ssid, String mac[], int frequncy[], int signal[]) {
        int amount = 0;
        while (amount < 10&&amount<ssid.length) {
            if (ssid[amount] != null)
                amount++;
            else break;
        }
        boolean flag = (10 - wifiNetworks - amount >= 0);
        int left = flag ? (amount) : (10 - wifiNetworks);//how many rows still can enter
        if (left > 0) {
            //copy the new
            System.arraycopy(ssid, 0, this.ssid, wifiNetworks, left);
            System.arraycopy(mac, 0, this.mac, wifiNetworks, left);
            System.arraycopy(frequncy, 0, this.frequncy, wifiNetworks, left);
            System.arraycopy(signal, 0, this.signal, wifiNetworks, left);
        }
        if (!flag) {//need to replace. ceck the weakest signal.
            int weakest[] = new int[10], place[] = new int[10];
            for (int i = 0; i < weakest.length; i++) {
                weakest[i] = -120;
            }
            for (int i = 0; i < 10; i++) {
                int j = 0;
                while (this.signal[i] <= weakest[j]) {
                    j++;
                }
                for (int k = 9; k > j; k--) {
                    weakest[k] = weakest[k - 1];
                    place[k] = place[k - 1];
                }
                weakest[j] = this.signal[i];
                place[j] = i;
            }
            for (int i = left; i < amount; i++) {
                for (int j = 9; j >= 0; j--) {
                    if (j == 0 && signal[i] > weakest[j]) {
                        weakest[0] = signal[i];
                        this.ssid[0] = ssid[i];
                        this.mac[0] = mac[i];
                        this.frequncy[0] = frequncy[i];
                        this.signal[0] = signal[i];
                        break;
                    }
                    if (signal[i] > weakest[j]) {
                        weakest[j] = weakest[j - 1];
                    } else {
                        if (j != 9) {
                            weakest[j + 1] = signal[i];
                            this.ssid[j + 1] = ssid[i];
                            this.mac[j + 1] = mac[i];
                            this.frequncy[j + 1] = frequncy[i];
                            this.signal[j + 1] = signal[i];
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * return if the scan is the same scan
     *
     * @param wp the wp to comper with
     * @return is the scan is the same scan
     */
    protected boolean equals(wpoint wp) {
        return (tim.equals(wp.tim) && id.equals(wp.id) && lat == wp.lat && lon == wp.lon && alt == wp.alt);
    }

    /**
     * @return all the information of the point
     */
    public String toString() {
        StringBuilder msg = new StringBuilder(tim + "," + id + "," + lat + "," + lon + "," + alt + "," + wifiNetworks);
        for (int i = 0; i < wifiNetworks; i++) {
            msg.append(",").append(ssid[i]).append(",").append(mac[i]).append(",").append(frequncy[i]).append(",").append(signal[i]);
        }
        return msg.toString();
    }
}
