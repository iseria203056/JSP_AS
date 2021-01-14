/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import java.sql.Date;

/**
 *
 * @author iseria
 */
public class TDB {
    public static void main(String[] args) {
    AttendanceDB d=new AttendanceDB("jdbc:mysql://localhost:3306/itp4511_db","root","");
        System.out.println(d.editRecord("80",Date.valueOf("2019-12-15"), true));
    }
}
