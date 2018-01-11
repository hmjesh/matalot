package ShanaB;

import java.io.*;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class create wpoint from files
 *
 * @author yitzchak shneller
 * @version 1
 */
public class Reader {
    /**
     * the main method for csv reading
     * @param file the file to import
     * @param db the database the insert the info to
     * @return massage if success or not
     */
    public String read(File file, Database db) {
        try {
            if (!file.exists()) {
                throw new IOException("wrong path/file is not exist");
            }
            if (file.isFile() && file.getAbsolutePath().endsWith(".csv")) {
                try {
                    readFile(file, db);
                } catch (ParseException e) {
                    return "problem in file";
                }
                return "file imported";
            }
            if (file.isDirectory()) {
                File[] listOfFiles = file.listFiles();
                if (listOfFiles == null) {
                    return "the folder is empty.";
                }
                for (File correctFile : listOfFiles) {
                    try {
                        readFile(correctFile, db);
                    } catch (ParseException e) {
                        return "problem in folder";
                    }
                }
                return "folder imported";
            }
            return "wrong path";
        } catch (IOException ex) {
            return "wrong path";
        }

    }

    /**
     * This class take a 'csv' file, and covert it to text, then send it to the appropriate methods
     *
     * @param file contain 'csv' file convert it to text and send it to appropriate methods
     * @param db   Database to insert the file to it
     * @throws ParseException from converting the text to wpoint
     */
    private void readFile(File file, Database db) throws ParseException {
        String line;
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //line.split(cvsSplitBy)
        String[] data = lines.toArray(new String[lines.size()]);
        if (data.length == 0)
            return;
        db.addFiles(file);
        if (data[0].charAt(0) == 'T') {
            readFormatted(data, db);
            return;
        }
        readUnformatted(data, db);
    }

    /**
     * This class take text of result file and add it to the Database
     *
     * @param data contain the info
     * @param db   the Database to insert the info
     * @throws ParseException from building wpoint
     */
    private static void readFormatted(String[] data, Database db) throws ParseException {
        for (int i = 1; i < data.length; i++) {//every row
            String[] a = data[i].split(",");
            db.add(new wpoint(a));
        }
    }

    /**
     * This class take text of Wigle_WiFi  file and add it to the Database
     *
     * @param data contain the info
     * @param db   the Database to insert the info
     * @throws ParseException from building wpoint
     */
    private static void readUnformatted(String[] data, Database db) throws ParseException {
        if (data.length < 2) //error, not enough rows
            return;
        if (data[0].split(",").length != 8)
            return;

        String id = data[0].split(",")[5];

        for (int i = 2; i < data.length; i++) {
            String[] line = data[i].split(",");
            if (line.length != 11) return;
            String[] row = new String[10];
            row[0] = line[3];
            row[1] = id;
            row[2] = line[6];
            row[3] = line[7];
            row[4] = line[8];
            row[5] = "1";
            row[6] = line[1];
            row[7] = line[0];
            row[8] = line[4];
            row[9] = line[5];
            db.add(new wpoint(row));
        }
    }

    /**
     * This class take text of filters file and add it to the Database
     *
     * @param file contain the file to import
     * @param db   the Database to insert the filters
     */
    public void filter(File file, Database db) {
        if (!file.exists()) {
            return;
        }
        if (file.isFile() && file.getAbsolutePath().endsWith(".filt")) {
            String line;
            ArrayList<String> lines = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] data = lines.toArray(new String[lines.size()]);
            if (data.length == 0)
                return;
            for (int i = 1; i < data.length; i++) {//every row
                String[] a = data[i].split(",");
                db.addFilter(a);
            }
        }
    }
}
