/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import ict.bean.userBean;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author iseria
 */
public class UserDB {

    private String url = "";
    private String username = "";
    private String password = "";

    public UserDB(String url, String username, String password) {
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

    public boolean isValidUser(String user, String pwd) {
        boolean isValid = false;
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM user WHERE name=? and pwd=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, user);
            pStmnt.setString(2, pwd);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                isValid = true;
                cnnct.close();
            }

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isValid;
    }

    public String getRole(String username) throws SQLException, IOException {
        String role="";
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        cnnct = getConnection();
        String preQueryStatement = "SELECT role FROM user WHERE name=?";
        pStmnt = cnnct.prepareStatement(preQueryStatement);
        pStmnt.setString(1, username);
         ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                role=rs.getString(1);
                cnnct.close();
            }
        return role;
    }
    
   public boolean addUser(String user, String pwd, String role) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "Insert into user value(?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, user);
            pStmnt.setString(2, pwd);
            pStmnt.setString(3, role);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                System.out.println("username already exist");
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
   public ArrayList<userBean> getAllUser(){
   ArrayList<userBean> users=new ArrayList<userBean> ();
   Connection cnnct = null;
   PreparedStatement pStmnt = null;
    try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM user";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
            users.add(new userBean(rs.getString(1),rs.getString(2),rs.getString(3)));
            }

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
   return users;
   }
   public ArrayList<userBean> getUserList(String constrang,String value){
   ArrayList<userBean> users=new ArrayList<userBean>();
   Connection cnnct = null;
   PreparedStatement pStmnt = null;
    try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM user where "+constrang+"=?";
            
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, value);
             System.out.print(pStmnt.toString());
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
            users.add(new userBean(rs.getString(1),rs.getString(2),rs.getString(3)));
            }

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
   return users;
   }
   public boolean editRole(String user,String role){
   Connection cnnct = null;
   PreparedStatement pStmnt = null;
    try {
            cnnct = getConnection();
             String preQueryStatement = "UPDATE user SET Role=? WHERE name=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, role);
            pStmnt.setString(2, user);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                System.out.println("Updated");
                return true;
            }
            

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
   return false;
   }
   public userBean getUser(String name){
   Connection cnnct = null;
   PreparedStatement pStmnt = null;
    try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM user where name=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, name);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
            return new userBean(rs.getString(1),rs.getString(2),rs.getString(3));
            }

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
    public ArrayList getClassWithNoAC(){
        ArrayList classList=new ArrayList();
   Connection cnnct = null;
   PreparedStatement pStmnt = null;
    try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT DISTINCT class,Form FROM student WHERE sid NOT IN (SELECT name FROM user)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
          
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            int i=0;
            
            while (rs.next()) {
            classList.add(rs.getString(2)+rs.getString(1));
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
    public boolean editProfile(String user,String pwd){
    Connection cnnct = null;
   PreparedStatement pStmnt = null;
    try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE user SET pwd=? WHERE name=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, pwd);
            pStmnt.setString(2, user);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                System.out.println("Updated");
                return true;
            }
            
            
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }return false;
    }
}
