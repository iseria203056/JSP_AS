package ict.bean;

import java.io.Serializable;

public class StudentBean implements Serializable {

    private String sId;
    private String name;
    private String form;
    private String className;
    private double attendanceRate;

    public StudentBean() {
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String[] getAllForm() {
        String[] allForm = {"1", "2", "3", "4", "5", "6"};
        return allForm;
    }

    public String[] getAllClassName() {
        String[] allClassName = {"A", "B", "C", "D", "E", "F"};
        return allClassName;
    }

    public void setAttendanceRate(double count, double present) {
        if (count != 0) {
            attendanceRate = present / count;
        } else {
            attendanceRate = 0.0;
        }
    }

    public double getAttendanceRate() {
        return attendanceRate * 100;
    }

}
