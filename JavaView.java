package ShanaB;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaView {
    private JPanel mainPanel;
    private JTable table;
    private JButton importButton;
    private JComboBox selectIfFilter;
    private JButton reset;
    private JLabel massege;
    private JPanel TablePanel;
    private JLabel filtersHeader;
    private JTextField minimumTimeTextField;
    private JTextField maximumTimeTextField;
    private JTextField nameTextField;
    private JTextField centerLatTextField;
    private JTextField centerLonTextField;
    private JTextField radiusTextField;
    private JCheckBox notValueCheckBox;
    private JCheckBox andCheckBox;
    private JButton activeFilterButton;
    private JButton resetFilterButton;
    private JLabel algoritemHeader;
    private JTextField macToFindPlaceTextField;
    private JTextField mac1TextField;
    private JTextField mac2TextField;
    private JTextField mac3TextField;
    private JTextField signal1TextField;
    private JTextField signal2TextField;
    private JTextField signal3TextField;
    private JButton findScanPlaceButton;
    private JLabel algoritemResult;
    private JButton findRowPlaceButton;
    private JButton findMacPlaceButton;
    private JComboBox filterByComboCox;
    private JTextField rowToFind;
    private JButton showFiltersButton;
    private JButton saveFiltersButton;
    private JCheckBox exportFilteredCheckBox;
    private JButton exportButton;
    private JComboBox fileTypeExport;
    private JButton importFiltersButton;

    private static Database db = new Database();

    private static final int COLUMNS = 46;
    private final JFileChooser openFileChooser = new JFileChooser();

    public JavaView(boolean a){}
    private JavaView() {
        mainPanel.setPreferredSize(new Dimension(1200, 600));
        TablePanel.setPreferredSize(new Dimension(3450, 100000));
        table.setModel(new DefaultTableModel(new Object[][]{},
                new String[]{"time", "id", "lat", "lon", "alt", "#WiFinetworks(up to 10)",
                        "ssid1", "mac1", "Frequncy1", "Signal1",
                        "ssid2", "mac2", "Frequncy2", "Signal2",
                        "ssid3", "mac3", "Frequncy3", "Signal3",
                        "ssid4", "mac4", "Frequncy4", "Signal4",
                        "ssid5", "mac5", "Frequncy5", "Signal5",
                        "ssid6", "mac6", "Frequncy6", "Signal6",
                        "ssid7", "mac7", "Frequncy7", "Signal7",
                        "ssid8", "mac8", "Frequncy8", "Signal8",
                        "ssid9", "mac9", "Frequncy9", "Signal9",
                        "ssid10", "mac10", "Frequncy10", "Signal"}) {
            Class<?>[] columnTypes = new Class<?>[]{
                    Data.class, String.class, int.class, int.class, int.class, int.class,
                    String.class, String.class, int.class, int.class,
                    String.class, String.class, int.class, int.class,
                    String.class, String.class, int.class, int.class,
                    String.class, String.class, int.class, int.class,
                    String.class, String.class, int.class, int.class,
                    String.class, String.class, int.class, int.class,
                    String.class, String.class, int.class, int.class,
                    String.class, String.class, int.class, int.class,
                    String.class, String.class, int.class, int.class,
                    String.class, String.class, int.class, int.class};
            boolean[] columnEditable = new boolean[]{
                    false, false, false, false, false, false,
                    false, false, false, false,
                    false, false, false, false,
                    false, false, false, false,
                    false, false, false, false,
                    false, false, false, false,
                    false, false, false, false,
                    false, false, false, false,
                    false, false, false, false,
                    false, false, false, false,
                    false, false, false, false};

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnEditable[columnIndex];
            }
        });

        importButton.addActionListener(e -> {
            openFileChooser.setFileFilter(new FileNameExtensionFilter("CSV file", "csv"));
            openFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int fileChooserStatus = openFileChooser.showOpenDialog(null);
            if (fileChooserStatus == JFileChooser.APPROVE_OPTION) {
                Reader readerObj = new Reader();
                massege.setText(readerObj.read(openFileChooser.getSelectedFile(), db));
            } else {
                massege.setText("no file selected");
            }
            printTable();
        });
        selectIfFilter.addActionListener(e -> printTable());
        reset.addActionListener(e -> {db.reset(); printTable();});
        resetFilterButton.addActionListener(e -> {db.resetSaveFilt();printTable();});
        activeFilterButton.addActionListener(e -> applyFilter());
        findMacPlaceButton.addActionListener(e -> algoritemResult.setText(new Algoritem().macPlace(macToFindPlaceTextField.getText(), db)));
        findRowPlaceButton.addActionListener(e -> algoritemResult.setText(new Algoritem().wpPlace(rowToFind.getText(), db)));
        findScanPlaceButton.addActionListener(e ->
                algoritemResult.setText(new Algoritem().scanPlace(mac1TextField.getText(), mac2TextField.getText(), mac3TextField.getText(),
                        Integer.parseInt(signal1TextField.getText()), Integer.parseInt(signal2TextField.getText()),
                        Integer.parseInt(signal3TextField.getText()), db)));
        showFiltersButton.addActionListener(e -> printFilters());
        saveFiltersButton.addActionListener(e -> {
            openFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            openFileChooser.showSaveDialog(null);
            new Export().filters(db.getFilters(), openFileChooser.getSelectedFile());
        });
        importFiltersButton.addActionListener(e -> {
            openFileChooser.setFileFilter(new FileNameExtensionFilter("filt file", "filt"));
            openFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int fileChooserStatus = openFileChooser.showOpenDialog(null);
            if (fileChooserStatus == JFileChooser.APPROVE_OPTION) {
                new Reader().filter(openFileChooser.getSelectedFile(), db);
                printFilters();
                printTable();
            } else {
                massege.setText("no file selected");
            }
        });
        exportButton.addActionListener(e -> {
            openFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            openFileChooser.showSaveDialog(null);
            wpoint data[];
            data = db.getDb();
            if (exportFilteredCheckBox.isSelected()) {
                data = new Filter().get(db);
            }
            if (fileTypeExport.getSelectedIndex() == 0)
                new Export().csv(data, openFileChooser.getSelectedFile());
            else
                new Export().kml(data, openFileChooser.getSelectedFile());
        });
        algoritemResult.doLayout();
    }

    /**
     * print the filter to massage
     */
    private void printFilters() {
        StringBuilder beginMsg = new StringBuilder();
        StringBuilder msg = new StringBuilder();
        for (String[] filter : db.getFilters()) {
            beginMsg.append("(");
            if (filter[1].equals("0"))
                msg.append("&&");
            else
                msg.append("||");
            if (filter[2].equals("0"))
                msg.append("(!");
            switch (Integer.parseInt(filter[0])) {
                case 0: {
                    msg.append("time(").append(filter[3]).append("<=data<=").append(filter[4]).append("))");
                }
                break;
                case 1: {
                    msg.append("name(SSID==").append(filter[3]).append("))");
                }
                break;
                case 2: {
                    msg.append("Position(radius<").append(filter[6]).append(", center =(").append(filter[3]).append(",").append(filter[4]).append(")))");
                }
            }
            if (filter[2].equals("0"))
                msg.append(")");
        }
        massege.setText(beginMsg.toString() + msg);
    }

    /**
     * print the database/filtered database to table
     */
    public void printTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        String header[] = {"time", "id", "lat", "lon", "alt", "#WiFinetworks(up to 10)",
                "ssid1", "mac1", "Frequncy1", "Signal1",
                "ssid2", "mac2", "Frequncy2", "Signal2",
                "ssid3", "mac3", "Frequncy3", "Signal3",
                "ssid4", "mac4", "Frequncy4", "Signal4",
                "ssid5", "mac5", "Frequncy5", "Signal5",
                "ssid6", "mac6", "Frequncy6", "Signal6",
                "ssid7", "mac7", "Frequncy7", "Signal7",
                "ssid8", "mac8", "Frequncy8", "Signal8",
                "ssid9", "mac9", "Frequncy9", "Signal9",
                "ssid10", "mac10", "Frequncy10", "Signal"};
        model.addRow(header);
        wpoint data[];
        data = db.getDb();
        if (selectIfFilter.getSelectedIndex() == 1) {
            data = new Filter().get(db);
        }
        for (wpoint item : data) {
            if (item != null) {
                String tableRow[] = new String[COLUMNS];
                tableRow[0] = item.getTim().toString();
                tableRow[1] = item.getId();
                tableRow[2] = item.getLat() + "";
                tableRow[3] = item.getLon() + "";
                tableRow[4] = item.getAlt() + "";
                tableRow[5] = item.getWifiNetworks() + "";
                for (int i = 0; i < 10; i++) {
                    tableRow[i * 4 + 6] = item.getSsid()[i];
                    tableRow[i * 4 + 7] = item.getMac()[i];
                    tableRow[i * 4 + 8] = item.getFrequncy()[i] + "";
                    tableRow[i * 4 + 9] = item.getSignal()[i] + "";
                }
                model.addRow(tableRow);
            }
        }
    }

    /**
     * this method print the database on the table, if it requested it filting it first
     */
    private void applyFilter() {
        String filtered[] = new String[6];
        filtered[0] = filterByComboCox.getSelectedIndex() + "";
        filtered[1] = andCheckBox.isSelected() ? "0" : "1";
        filtered[2] = notValueCheckBox.isSelected() ? "0" : "1";
        switch (filterByComboCox.getSelectedIndex()) {
            case 0: {
                filtered[3] = minimumTimeTextField.getText();
                filtered[4] = maximumTimeTextField.getText();
                break;
            }
            case 1: {
                filtered[3] = nameTextField.getText();
                break;
            }
            case 2: {
                filtered[3] = centerLatTextField.getText() + "";
                filtered[4] = centerLonTextField.getText() + "";
                filtered[5] = radiusTextField.getText() + "";
                break;
            }
        }
        db.addFilter(filtered);
        if (selectIfFilter.getSelectedIndex() == 1)
            printTable();
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("javaview");
        JavaView jView=new JavaView();
        frame.setContentPane(jView.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        ChegeDetecter ch = new ChegeDetecter(db,jView);
        Thread t = new Thread(ch);
        t.run();
    }
}
