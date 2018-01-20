package ShanaB;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class contain the Database of the project
 *
 * @author yitzchak shneller
 * @version 1
 */
public class Filter {
    private Database db;
    private wpoint[] filteredDB;
    private boolean not;
    private boolean andOr;
    private wpoint[] filting, wp1;
    private int counter;

    /**
     * starting the filter.
     * take the filtering info from the database, and bring back only the filtered rowd
     *
     * @param db the database with all the info
     * @return the filtered rows
     */
    public wpoint[] get(Database db) {
        this.db = db;
        filteredDB = new wpoint[db.getDb().length];
        System.arraycopy(db.getDb(), 0, filteredDB, 0, filteredDB.length);
        for (String[] filter : db.getFilters()) {

            this.not = filter[2].equals("0");
            this.andOr = filter[1].equals("0");
            if (andOr) {
                filting = new wpoint[db.getCounter()];
                System.arraycopy(filteredDB, 0, filting, 0, db.getCounter());
            } else {
                filting = new wpoint[filteredDB.length];
                System.arraycopy(db.getDb(), 0, filting, 0, db.getDb().length);
            }
            wp1 = new wpoint[db.getDb().length];
            counter = 0;
            switch (Integer.parseInt(filter[0])) {
                case 0: {
                    byTime(filter[3], filter[4]);
                }
                break;
                case 1: {
                    byName(filter[3]);
                }
                break;
                case 2: {
                    byPlace(filter[3], filter[4], filter[5]);
                }
            }
        }
        return filteredDB;
    }

    /**
     * filting the Database according to time
     *
     * @param minTime minimum time to stay
     * @param maxTime maximum time to stay
     */
    private void byTime(String minTime, String maxTime) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateMin = dateFormat.parse(minTime);
            Date dateMax = dateFormat.parse(maxTime);
            for (wpoint filting1 : filting) {
                if (filting1 != null) {
                    Date date = filting1.getTim();
                    if (!not) {
                        if (dateMin.before(date) && date.before(dateMax)) {
                            wp1[counter] = filting1;
                            counter++;
                        }
                    } else {
                        if (date.before(dateMin) || dateMax.before(date)) {
                            wp1[counter] = filting1;
                            counter++;
                        }
                    }
                }
            }
            endFilt();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * filting the Database according to name
     *
     * @param name the name from ssid to stay
     */
    private void byName(String name) {
        for (wpoint filting1 : filting) {
            if (filting1 != null) {
                if (!not) {
                    for (int j = 0; j < filting1.getWifiNetworks(); j++) {
                        if (filting1.getSsid()[j].equals(name)) {
                            wp1[counter] = filting1;
                            counter++;
                            break;
                        }
                    }
                } else {
                    for (int j = 0; j < filting1.getWifiNetworks(); j++) {
                        if (filting1.getSsid()[j].equals(name)) {
                            break;
                        }
                        if (j == filting1.getWifiNetworks() - 1) {
                            wp1[counter] = filting1;
                            counter++;
                        }
                    }
                }
            }
        }
        endFilt();
    }

    /**
     * filting the Database according to place
     *
     * @param sLat    - the center lat for Filter
     * @param sLon    - the center lon for Filter
     * @param sRadios - who distace around the center to Filter
     */
    private void byPlace(String sLat, String sLon, String sRadios) {
        double lat = Double.parseDouble(sLat),
                lon = Double.parseDouble(sLon),
                radios = Double.parseDouble(sRadios);
        for (wpoint filting1 : filting) {
            if (filting1 != null) {
                if (!not) {
                    if (filting1.getLat() >= lat - radios && filting1.getLat() <= lat + radios && filting1.getLon() >= lon - radios && filting1.getLon() <= lon + radios) {
                        wp1[counter] = filting1;
                        counter++;
                    }
                } else {
                    if (filting1.getLat() < lat - radios || filting1.getLat() > lat + radios || filting1.getLon() < lon - radios || filting1.getLon() > lon + radios) {
                        wp1[counter] = filting1;
                        counter++;
                    }
                }
            }
        }
        endFilt();
    }

    /**
     * doing and / or with the filtered info
     */
    private void endFilt() {
        if (!andOr) {    //'or, need to add, not to be instead
            if (counter == 0) {
                return;
            }
            wpoint dbfilted[] = filteredDB;
            for (int i = 0; i < dbfilted.length&&dbfilted[i]!=null; i++) {
                for (int j = 0; i < counter; j++) {
                    if (dbfilted[i].equals(wp1[j])) {
                        break;
                    }
                    if (j == counter - 1) {
                        wp1[counter] = dbfilted[i];
                        counter++;
                        break;
                    }
                }
            }
        }
        filteredDB = wp1;
    }
}
