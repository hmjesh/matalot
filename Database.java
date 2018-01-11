package ShanaB;

import java.io.File;

/**
 * This class contain the Database of the project
 *
 * @author yitzchak shneller
 * @version 1
 */
public class Database extends wpoint {
    private wpoint[] db = new wpoint[100];
    private int counter = 0;
    private String[][] filters = new String[0][6];
    private File[] files = new File[0];

    /**
     * Add a file to files history
     *
     * @param file added to files
     */
    public void addFiles(File file) {
        File[] newFiles = new File[files.length + 1];
        System.arraycopy(files, 0, newFiles, 0, files.length);
        newFiles[files.length] = file;
        files = newFiles;
    }

    /**
     * @return files from files
     */
    protected File[] getFiles() {
        File[] getFiles = new File[files.length];
        System.arraycopy(files, 0, getFiles, 0, files.length);
        return getFiles;
    }

    /**
     * @return filters from filters
     */
    protected String[][] getFilters() {
        return filters;
    }

    /**
     * reset filters
     */
    protected void resetSaveFilt() {
        this.filters = new String[0][6];
    }

    /**
     * @return db from db
     */
    protected wpoint[] getDb() {
        wpoint[] getDb = new wpoint[db.length];
        System.arraycopy(db, 0, getDb, 0, counter);
        return getDb;
    }

    /**
     * @param wp enter to db, and to dbfilted
     */
    protected void add(wpoint wp) {
        for (int i = 0; i < counter; i++)
            if (db[i].equals(wp)) {
                db[i].addWifi(wp.getSsid(), wp.getMac(), wp.getFrequncy(), wp.getSignal());
                return;
            }
        if (counter == db.length) {
            wpoint[] newdb = new wpoint[db.length + 100];
            System.arraycopy(db, 0, newdb, 0, db.length);
            db = newdb;
        }
        db[counter] = wp;
        counter++;

    }

    /**
     * reset the Database
     */
    protected void reset() {
        db = new wpoint[100];
        counter = 0;
        filters = new String[0][6];
        files = new File[0];
    }

    /**
     * Add a search to search history
     *
     * @param newSf added to filters
     */
    public void addFilter(String[] newSf) {
        String[][] newSaveFilt = new String[filters.length + 1][6];
        System.arraycopy(filters, 0, newSaveFilt, 0, filters.length);
        newSaveFilt[filters.length] = newSf;
        filters = newSaveFilt;
    }

    /**
     * @return counter from counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     * if file chenged - rereading all files
     */
    public void refreshFiles() {
        db = new wpoint[100];
        counter = 0;
        File[] refresh = new File[files.length];
        int filecounter=0;
        for (File file : files) {
            if(file.exists()){
                new Reader().read(file,this);
                refresh[filecounter]=file;
                filecounter++;
            }
        }
        files = new File[filecounter];
        System.arraycopy(refresh,0,files,0,filecounter);
    }
}
