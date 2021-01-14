package ict.db;

import ict.bean.ScheduleBean;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class ScheduleDB {

    private String url = "";
    private String username = "";
    private String password = "";

    public ScheduleDB(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return DriverManager.getConnection(url, username, password);
    }

    public ArrayList<ScheduleBean> queryScheduleByClass(String form, String classname) {

        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList<ScheduleBean> schedule = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM schedule where form=? and class=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, form);
            pStmnt.setString(2, classname);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            schedule = new ArrayList();

            while (rs.next()) {
                ScheduleBean sb = new ScheduleBean();
                sb.setId(rs.getInt("id"));
                sb.setForm(rs.getString("form"));
                sb.setClassname(rs.getString("class"));
                sb.setDate(rs.getDate("date"));
                sb.setIsHoliday(rs.getBoolean("isHoliday"));
                schedule.add(sb);
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return schedule;

    }

    public ArrayList<ScheduleBean> queryScheduleByForm(String form) {

        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList<ScheduleBean> schedule = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM schedule where form=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, form);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            schedule = new ArrayList();

            while (rs.next()) {
                ScheduleBean sb = new ScheduleBean();
                sb.setId(rs.getInt("id"));
                sb.setForm(rs.getString("form"));
                sb.setClassname(rs.getString("class"));
                sb.setDate(rs.getDate("date"));
                sb.setIsHoliday(rs.getBoolean("isHoliday"));
                schedule.add(sb);
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return schedule;

    }
    
    public ArrayList<ScheduleBean> querySchedule() {

        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList<ScheduleBean> schedule = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM schedule";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            schedule = new ArrayList();

            while (rs.next()) {
                ScheduleBean sb = new ScheduleBean();
                sb.setId(rs.getInt("id"));
                sb.setForm(rs.getString("form"));
                sb.setClassname(rs.getString("class"));
                sb.setDate(rs.getDate("date"));
                sb.setIsHoliday(rs.getBoolean("isHoliday"));
                schedule.add(sb);
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return schedule;

    }

    public boolean addRecord(String form, String classname, Date date, boolean isHoliday) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {

            cnnct = getConnection();
            String preQueryStatement = "INSERT  INTO  schedule  VALUES  (NULL,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, form);
            pStmnt.setString(2, classname);
            pStmnt.setDate(3, date);           
            pStmnt.setBoolean(4, isHoliday);

            int rowCount = pStmnt.executeUpdate();

            if (rowCount >= 1) {
                isSuccess = true;
                System.out.println(date + " is added");
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean delRecordByClass(String form, String classname, Date date) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {

            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM schedule WHERE form=? and class=? and date=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, form);
            pStmnt.setString(2, classname);
            pStmnt.setDate(3, date);

            int rowCount = pStmnt.executeUpdate();

            if (rowCount >= 1) {
                isSuccess = true;
                System.out.println("'date: " + date + "' is deleted");
            } else {
                System.out.println("'date: " + date + "' does not exist.");
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public boolean delRecordByForm(String form, Date date) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {

            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM schedule WHERE form=? and date=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, form);
            pStmnt.setDate(2, date);

            int rowCount = pStmnt.executeUpdate();

            if (rowCount >= 1) {
                isSuccess = true;
                System.out.println("'date: " + date + "' is deleted");
            } else {
                System.out.println("'date: " + date + "' does not exist.");
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public boolean delRecordByDate(Date date) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {

            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM schedule WHERE date=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setDate(1, date);

            int rowCount = pStmnt.executeUpdate();

            if (rowCount >= 1) {
                isSuccess = true;
                System.out.println("'date: " + date + "' is deleted");
            } else {
                System.out.println("'date: " + date + "' does not exist.");
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

}
