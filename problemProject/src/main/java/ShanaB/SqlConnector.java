package ShanaB;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is the responeable about reading from far database
 *
 * @author Yitzchak Shneller
 * @version 1
 */
class SqlConnector {

    private String _ip = "5.29.193.52";
    private String _port = "3306";
    private String _user = "oop1";
    private String _password = "Lambda1();";
    private Connection _con = null;
    private String _dbName = "oop_course_ariel";
    private String _tableName="ex4_db";
    private Database db;
    private Date lastUpdate;

    /**
     * builder
     * @param Db to enter everything into.
     */
    public SqlConnector(Database Db) {
        db = Db;
    }

    /**
     * copy builder
     * @param sc to copy everything from
     */
    public SqlConnector(SqlConnector sc) {
        _ip = sc._ip;
        _port = sc._port;
        _user = sc._user;
        _password = sc._password;
        _con = sc._con;
        _dbName = sc._dbName;
        db = sc.db;
        lastUpdate = sc.lastUpdate;
    }

    /**
     * check if the connection is already exist, if not - reading by reupdate
     */
    public void read(){
        for (SqlConnector conn:db.getConnectors()) {
            if (conn.equals(this))
                return ;
        }
        reupdate();
    }

    /**
     * reading from database all the info, save last modify
     */
    public void reupdate() {
        lastUpdate = giveUpdate();
        readDB();
    }

    /**
     * check if this connection update
     * @return true if update
     */
    public boolean isUpdate()
    {
        return (!giveUpdate().equals(lastUpdate));
    }

    /**
     * check the last time that the table changed
     * @return the date of last update
     */
    private Date giveUpdate() {
        Statement st = null;
        ResultSet rs = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date isUpdat = null;
        try {
            _con = DriverManager.getConnection("jdbc:mysql://" + _ip + ":" + _port + "/" + _dbName, _user, _password);
            st = _con.createStatement();
            rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = '"+_dbName+"' AND TABLE_NAME = '"+_tableName+"'");
            if (rs.next()) {
                isUpdat = (Date) dateFormat.parse(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(SqlConnector.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (_con != null) {
                    _con.close();
                }
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(SqlConnector.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return isUpdat;
    }

    /**
     * read the table from database, insert indo the Database
     */
    private void readDB() {
        ResultSet rs = null;
        try {
            _con = DriverManager.getConnection("jdbc:mysql://" + _ip + ":" + _port + "/" + _dbName, _user, _password);
            PreparedStatement pst = _con.prepareStatement("SELECT * FROM "+_tableName);
            rs = pst.executeQuery();
            while (rs.next()) {
                String row[] = new String[46];
                for (int i = 0; i < 6; i++)
                    row[i] = rs.getString(i + 2);
                for (int i = 0; i < 10; i++)
                    if (rs.getString(8 + i * 2) != null) {
                        row[6 + i * 4] = "";
                        row[7 + i * 4] = rs.getString(8 + i * 2);
                        row[8 + i * 4] = "0";
                        row[9 + i * 4] = rs.getString(9 + i * 2);
                    } else
                        break;
                db.add(new wpoint(row));
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(SqlConnector.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (_con != null) {
                    _con.close();
                }
                db.addConnect(this);
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(SqlConnector.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }

    /**
     * set _ip into _ip
     * @param _ip
     */
    public void set_ip(String _ip) {
        this._ip = _ip;
    }

    /**
     * set _port into _port
     * @param _port
     */
    public void set_port(String _port) {
        this._port = _port;
    }

    /**
     * set _user into _user
     * @param _user
     */
    public void set_user(String _user) {
        this._user = _user;
    }

    /**
     * set _password into _password
     * @param _password
     */
    public void set_password(String _password) {
        this._password = _password;
    }

    /**
     * set _dbName into _dbName
     * @param _dbName
     */
    public void set_dbName(String _dbName) {
        this._dbName = _dbName;
    }
}
