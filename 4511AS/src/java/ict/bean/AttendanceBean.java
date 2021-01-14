package ict.bean;

import java.io.Serializable;
import java.sql.Date;

public class AttendanceBean implements Serializable {

    private String sid;
    private Date date;
    private boolean attendance_present;

    public AttendanceBean() {
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAttendance_present() {
        return attendance_present;
    }

    public void setAttendance_present(boolean attendance_present) {
        this.attendance_present = attendance_present;
    }

}
