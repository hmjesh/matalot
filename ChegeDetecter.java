package ShanaB;

import java.io.File;
import java.util.Arrays;

/**
 * This class is thread that check that nothing chenged in the files
 *
 * @author yitzchak shneller
 * @version 1
 */
public class ChegeDetecter implements Runnable {
    private Database db;
    private File files[] = new File[0];
    private long update[] = new long[0];
    private JavaView jView;

    public ChegeDetecter(Database db, JavaView jView) {
        this.db = db;
        this.jView = jView;
    }

    /**
     * every 3 second check that database files didn't change, and the file didnt change.
     * id do - refreshing the system
     */
    public void run() {
        files = db.getFiles();
        while (true) {
            try {
                 if (!Arrays.equals(db.getFiles(), files)) {
                    File news[] = new File[db.getFiles().length];
                    long updatefile[] = new long[db.getFiles().length];
                    System.arraycopy(db.getFiles(), 0, news, 0, news.length);
                    for (int i = 0; i < news.length; i++) {
                        updatefile[i] = news[i].lastModified();
                        for (int j = 0; j < files.length; j++) {
                            if (files[j].getAbsolutePath().equals(news[i].getAbsolutePath()))
                                updatefile[i] = update[j];
                        }
                    }
                    files = news;
                    update = updatefile;
                }

                for (int i = 0; i < files.length; i++) {
                    if (!files[i].exists()) {
                        db.refreshFiles();
                        jView.printTable();
                    } else if (files[i].lastModified() != update[i]) {
                        db.refreshFiles();
                        jView.printTable();
                    }
                }
                Thread.sleep(3000);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
