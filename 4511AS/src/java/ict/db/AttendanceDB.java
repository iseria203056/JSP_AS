package ict.db;

import ict.bean.AttendanceBean;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class AttendanceDB {

    private String url = "";
    private String username = "";
    private String password = "";

    public AttendanceDB(String url, String username, String password) {
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

    public void createAttendanceTable() {
        Connection cnnct = null;
        Statement stmnt = null;

        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();

            String sql = "CREATE TABLE student IF NOT EXISTS student  ("
                    + "    student_id VARCHAR(20) NOT NULL,"
                    + "    student_name VARCHAR(20) NOT NULL,"
                    + "    CONSTRAINT student_id_pk PRIMARY KEY (student_id)"
                    + ");"
                    + "CREATE TABLE attendance IF NOT EXISTS attendance ("
                    + "    student_id VARCHAR(20) NOT NULL,"
                    + "    attendance_present INT CHECK (attendance_present in (0,1)),"
                    + "    on_time DATETIME,"
                    + "    CONSTRAINT attendance_pk PRIMARY KEY (student_id),"
                    + ");";
            System.out.println(sql);
            stmnt.execute(sql);
            stmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void dropAttendanceTable() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = getConnection();

            String sql = "DROP TABLE attendance";

            pStmnt = cnnct.prepareStatement(sql);
            pStmnt.execute();
            System.out.println("Table is deleted.");

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            System.out.println("Table doesn't exist.");
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<AttendanceBean> queryAttendanceBySidAndDate(String sid, Date date) {

        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList<AttendanceBean> attendance = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM Attendance WHERE sid = ? and date = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, sid);
            pStmnt.setDate(2, date);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            attendance = new ArrayList();

            while (rs.next()) {
                AttendanceBean ab = new AttendanceBean();
                ab.setSid(rs.getString("sid"));
                ab.setDate(rs.getDate("date"));
                ab.setAttendance_present(rs.getBoolean("attendance_present"));
                attendance.add(ab);
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

        return attendance;

    }

    public ArrayList<AttendanceBean> queryAttendanceByClassAndDate(String form, String classname, Date date) {

        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList<AttendanceBean> attendance = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM Attendance WHERE form = ? and class = ? and date = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, form);
            pStmnt.setString(2, classname);
            pStmnt.setDate(3, date);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            attendance = new ArrayList();

            while (rs.next()) {
                AttendanceBean ab = new AttendanceBean();
                ab.setSid(rs.getString("sid"));
                ab.setDate(rs.getDate("date"));
                ab.setAttendance_present(rs.getBoolean("attendance_present"));
                attendance.add(ab);
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

        return attendance;

    }
    
    public int countByClassAndDate(String form, String classname, Date date) {

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int count = 0;

        ArrayList<AttendanceBean> attendance = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM Attendance WHERE form = ? and class = ? and date = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, form);
            pStmnt.setString(2, classname);
            pStmnt.setDate(3, date);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            attendance = new ArrayList();

            while (rs.next()) {
                count++;
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

        return count;

    }
    
    public ArrayList<AttendanceBean> queryAttendanceBySid(String sid) {

        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList<AttendanceBean> attendance = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM Attendance WHERE sid = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, sid);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            attendance = new ArrayList();

            while (rs.next()) {
                AttendanceBean ab = new AttendanceBean();
                ab.setSid(rs.getString("sid"));
                ab.setDate(rs.getDate("date"));
                ab.setAttendance_present(rs.getBoolean("attendance_present"));
                attendance.add(ab);
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

        return attendance;

    }

    public ArrayList<AttendanceBean> queryAttendanceByDate(Date date) {

        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList<AttendanceBean> attendance = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM Attendance WHERE date = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setDate(1, date);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            attendance = new ArrayList();

            while (rs.next()) {
                AttendanceBean ab = new AttendanceBean();
                ab.setSid(rs.getString("sid"));
                ab.setDate(rs.getDate("date"));
                ab.setAttendance_present(rs.getBoolean("attendance_present"));
                attendance.add(ab);
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

        return attendance;

    }

    public ArrayList<AttendanceBean> queryAttendance() {

        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList<AttendanceBean> attendance = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM Attendance";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            attendance = new ArrayList();

            while (rs.next()) {
                AttendanceBean ab = new AttendanceBean();
                ab.setSid(rs.getString("sid"));
                ab.setDate(rs.getDate("date"));
                ab.setAttendance_present(rs.getBoolean("attendance_present"));
                attendance.add(ab);
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

        return attendance;

    }

    public boolean addRecord(String sid, Date date, boolean attendance_present) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {

            cnnct = getConnection();
            String preQueryStatement = "INSERT  INTO  Attendance  VALUES  (Null,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, sid);
            pStmnt.setDate(2, date);
            pStmnt.setBoolean(3, attendance_present);

            int rowCount = pStmnt.executeUpdate();

            if (rowCount >= 1) {
                isSuccess = true;
                System.out.println(sid + " is added");
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

    public boolean delRecord(String sId, Date date) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {

            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM Attendance WHERE sid=? and date=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, sId);
            pStmnt.setDate(2, date);

            int rowCount = pStmnt.executeUpdate();

            if (rowCount >= 1) {
                isSuccess = true;
                System.out.println("'Attendance: " + sId + "' is deleted");
            } else {
                System.out.println("'Attendance: " + sId + "' does not exist.");
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

    public boolean editRecord(String sid, Date date, boolean attendance_present) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {

            cnnct = getConnection();
            String preQueryStatement = "UPDATE Attendance SET attendance_present=? WHERE sid=? and date=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setBoolean(1, attendance_present);
            pStmnt.setString(2, sid);
            pStmnt.setDate(3, date);
            
            
            int rowCount = pStmnt.executeUpdate();

            if (rowCount >= 1) {
                isSuccess = true;
                System.out.println("'Attendance: " + sid + "' is updated");
            } else {
                System.out.println("'Attendance: " + sid + "' does not exist.");
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
