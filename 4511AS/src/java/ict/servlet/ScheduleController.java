package ict.servlet;

import ict.bean.ScheduleBean;
import ict.bean.StudentBean;
import ict.db.ScheduleDB;
import ict.db.StudentDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ScheduleController", urlPatterns = {"/scheduleController"})
public class ScheduleController extends HttpServlet {

    private ScheduleDB db;
    StudentDB studentDB;

    @Override
    public void init() {
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");

        db = new ScheduleDB(dbUrl, dbUser, dbPassword);
        studentDB = new StudentDB(dbUrl, dbUser, dbPassword);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            String form = request.getParameter("form");
            String classname = request.getParameter("classname");
            String[] allForm = {"1", "2", "3", "4", "5", "6"};
            String[] allClassName = {"A", "B", "C", "D", "E", "F"};

            if ("list".equals(action)) {

                HttpSession session = request.getSession();

                if (!session.getAttribute("role").equals("student")) {
                    ArrayList<ScheduleBean> schedule;

                    if (form.equalsIgnoreCase("All")) {
                        schedule = db.querySchedule();
                    } else if (classname.equalsIgnoreCase("All")) {
                        schedule = db.queryScheduleByForm(form);
                    } else {
                        schedule = db.queryScheduleByClass(form, classname);
                    }

                    request.setAttribute("schedule", schedule);
                    request.setAttribute("form", form);
                    request.setAttribute("classname", classname);

                    RequestDispatcher rd;
                    rd = getServletContext().getRequestDispatcher("/calendar.jsp");
                    rd.forward(request, response);
                } else {

                    String username = (String) session.getAttribute("username");
                    StudentBean sb = studentDB.queryStudentByStudentID(username);
                    String sForm = sb.getForm();
                    String sClassname = sb.getClassName();
                    ArrayList<ScheduleBean> sSchedule;
                    sSchedule = db.queryScheduleByClass(sForm, sClassname);

                    request.setAttribute("sSchedule", sSchedule);
                    request.setAttribute("sForm", sForm);
                    request.setAttribute("sClassname", sClassname);

                    RequestDispatcher rd;
                    rd = getServletContext().getRequestDispatcher("/studentCalendar.jsp");
                    rd.forward(request, response);
                }

            } else if ("addHoliday".equals(action)) {

                Date start = Date.valueOf((request.getParameter("startDate")));
                Date end = Date.valueOf((request.getParameter("endDate")));

                Calendar cStart = Calendar.getInstance();
                cStart.setTime(start);
                Calendar cEnd = Calendar.getInstance();
                cEnd.setTime(end);

                while (cStart.before(cEnd) || cStart.equals(cEnd)) {
                    java.sql.Date date = new java.sql.Date(cStart.getTimeInMillis());

                    if (form.equalsIgnoreCase("All")) {
                        for (String aForm : allForm) {
                            for (String aClassName : allClassName) {
                                db.addRecord(aForm, aClassName, date, true);
                            }
                        }

                    } else if (classname.equalsIgnoreCase("All")) {
                        for (String aClassName : allClassName) {
                            db.addRecord(form, aClassName, date, true);
                        }
                    } else {
                        db.addRecord(form, classname, date, true);
                    }
                    //add one day to date
                    cStart.add(Calendar.DAY_OF_MONTH, 1);
                }

                response.sendRedirect("scheduleController?action=list&form=" + form + "&classname=" + classname);
            } else if ("removeHoliday".equals(action)) {

                Date start = Date.valueOf((request.getParameter("startDate")));
                Date end = Date.valueOf((request.getParameter("endDate")));

                Calendar cStart = Calendar.getInstance();
                cStart.setTime(start);
                Calendar cEnd = Calendar.getInstance();
                cEnd.setTime(end);

                while (cStart.before(cEnd) || cStart.equals(cEnd)) {
                    java.sql.Date date = new java.sql.Date(cStart.getTimeInMillis());

                    if (form.equalsIgnoreCase("All")) {

                        db.delRecordByDate(date);

                    } else if (classname.equalsIgnoreCase("All")) {

                        db.delRecordByForm(form, date);

                    } else {
                        db.delRecordByClass(form, classname, date);
                    }
                    //add one day to date
                    cStart.add(Calendar.DAY_OF_MONTH, 1);
                }

                response.sendRedirect("scheduleController?action=list&form=" + form + "&classname=" + classname);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
