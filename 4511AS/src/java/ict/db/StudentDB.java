/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import com.mysql.jdbc.Connection;
import ict.bean.StudentBean;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDB {

    private String url = "";
    private String username = "";
    private String password = "";

    public StudentDB(String url, String username, String password) {
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
        return (Connection) DriverManager.getConnection(url, username, password);
    }
public ArrayList getStudentWithNoAC(String f,String c) {
        ArrayList<StudentBean> classList = new ArrayList();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        StudentBean sb = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM student where sid NOT IN(SELECT name FROM user) AND class=? AND Form=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, c);
            pStmnt.setString(2, f);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                sb = new StudentBean();
                sb.setsId(rs.getString("sid"));
                sb.setName(rs.getString("name"));
                sb.setForm(rs.getString("form"));
                sb.setClassName(rs.getString("class"));
                classList.add(sb);
            }
            return classList;
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    

    public StudentBean queryStudentByStudentID(String id) {

        java.sql.Connection cnnct = null;
        PreparedStatement pStmnt = null;

        StudentBean sb = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM student WHERE sid=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            if (rs.next()) {
                sb = new StudentBean();
                sb.setsId(rs.getString("sid"));
                sb.setName(rs.getString("name"));
                sb.setForm(rs.getString("form"));
                sb.setClassName(rs.getString("class"));
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

        return sb;

    }

    public ArrayList<StudentBean> queryStudentByName(String student_name) {

        java.sql.Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList<StudentBean> students = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM student WHERE UPPER(name) LIKE ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, "%" + student_name + "%");

            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            students = new ArrayList();

            while (rs.next()) {
                StudentBean sb = new StudentBean();
                sb.setsId(rs.getString("sid"));
                sb.setName(rs.getString("name"));
                sb.setForm(rs.getString("form"));
                sb.setClassName(rs.getString("class"));
                students.add(sb);
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

        return students;

    }

    public ArrayList<StudentBean> queryStudentByClass(String form, String classname) {

        java.sql.Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList<StudentBean> students = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM student WHERE form = ? and class = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, form);
            pStmnt.setString(2, classname);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            students = new ArrayList();

            while (rs.next()) {
                StudentBean sb = new StudentBean();
                sb.setsId(rs.getString("sid"));
                sb.setName(rs.getString("name"));
                sb.setForm(rs.getString("form"));
                sb.setClassName(rs.getString("class"));
                students.add(sb);
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

        return students;

    }

    public ArrayList<StudentBean> queryStudent() {

        java.sql.Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList<StudentBean> customers = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM student";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            customers = new ArrayList();

            while (rs.next()) {
                StudentBean sb = new StudentBean();
                sb.setsId(rs.getString("sid"));
                sb.setName(rs.getString("name"));
                sb.setForm("form");
                sb.setClassName(rs.getString("class"));
                customers.add(sb);
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

        return customers;

    }

    public boolean addRecord(String sid, String name, String form, String classname) {
        java.sql.Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {

            cnnct = getConnection();
            String preQueryStatement = "INSERT  INTO  student  VALUES  (?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, sid);
            pStmnt.setString(2, name);
            pStmnt.setString(3, form);
            pStmnt.setString(4, classname);

            int rowCount = pStmnt.executeUpdate();

            if (rowCount >= 1) {
                isSuccess = true;
                System.out.println(name + " is added");
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

    public boolean delRecord(String id) {
        java.sql.Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {

            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM student WHERE sid=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);

            int rowCount = pStmnt.executeUpdate();

            if (rowCount >= 1) {
                isSuccess = true;
                System.out.println("'StudentId: " + id + "' is deleted");
            } else {
                System.out.println("'StudentId: " + id + "' does not exist.");
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

    public boolean editRecord(StudentBean sb) {
        java.sql.Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {

            cnnct = getConnection();
            String preQueryStatement = "UPDATE student SET form=?,class=? WHERE sid=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, sb.getForm());
            pStmnt.setString(2, sb.getClassName());
            pStmnt.setString(3, sb.getsId());

            int rowCount = pStmnt.executeUpdate();

            if (rowCount >= 1) {
                isSuccess = true;
                System.out.println("'Student_name: " + sb.getName() + "' is updated");
            } else {
                System.out.println("'Student_name: " + sb.getName() + "' does not exist.");
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
