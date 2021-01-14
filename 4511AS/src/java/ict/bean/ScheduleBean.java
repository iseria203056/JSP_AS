package ict.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

public class ScheduleBean implements Serializable {

    private int id;
    private String form;
    private String classname;
    private Date date;
    private boolean isHoliday;

    public ScheduleBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isIsHoliday() {
        return isHoliday;
    }

    public void setIsHoliday(boolean isHoliday) {
        this.isHoliday = isHoliday;
    }

    public int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDate());
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    public int getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDate());
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        return month;
    }

    public int getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDate());
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        return day;
    }

}
