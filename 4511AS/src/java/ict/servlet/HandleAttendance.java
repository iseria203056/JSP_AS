package ict.servlet;

import ict.bean.AttendanceBean;
import ict.bean.StudentBean;
import ict.db.AttendanceDB;
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

@WebServlet(name = "HandleAttendance", urlPatterns = {"/handleAttendance"})
public class HandleAttendance extends HttpServlet {

    private AttendanceDB aDB;
    private StudentDB sDB;

    @Override
    public void init() {
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");

        aDB = new AttendanceDB(dbUrl, dbUser, dbPassword);
        sDB = new StudentDB(dbUrl, dbUser, dbPassword);
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
        String action = request.getParameter("action");

        if ("list".equalsIgnoreCase(action)) {

            HttpSession session = request.getSession();
            if (!session.getAttribute("role").equals("student")) {
                String form = request.getParameter("form");
                String classname = request.getParameter("classname");
                Date date = Date.valueOf(request.getParameter("date"));
                ArrayList<AttendanceBean> attendance = aDB.queryAttendanceByDate(date);
                ArrayList<StudentBean> students = sDB.queryStudentByClass(form, classname);
                attendance.add(0, new AttendanceBean());
                request.setAttribute("attendance", attendance);
                request.setAttribute("students", students);
                request.setAttribute("form", form);
                request.setAttribute("classname", classname);
                request.setAttribute("date", date);
                
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/listAttendance.jsp");
                rd.forward(request, response);
            } else {

                String username = (String) session.getAttribute("username");
                StudentBean sb = sDB.queryStudentByStudentID(username);
                String sid = sb.getsId();
                String sForm = sb.getForm();
                String sClassname = sb.getClassName();
                ArrayList<AttendanceBean> attendance = aDB.queryAttendanceBySid(sid);

                request.setAttribute("yourAttendance", attendance);
                request.setAttribute("student", sb);
                request.setAttribute("form", sForm);
                request.setAttribute("classname", sClassname);

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/showYourAttendance.jsp");
                rd.forward(request, response);
            }

        } else if ("selectday".equalsIgnoreCase(action)) {
            String form = request.getParameter("form");
            String classname = request.getParameter("classname");
            Date date = Date.valueOf(request.getParameter("date"));
            ArrayList<AttendanceBean> attendance = aDB.queryAttendanceByDate(date);
            ArrayList<StudentBean> students = sDB.queryStudentByClass(form, classname);

            request.setAttribute("attendance", attendance);
            request.setAttribute("students", students);
            request.setAttribute("form", form);
            request.setAttribute("classname", classname);
            request.setAttribute("date", date);

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/listAttendanceDate.jsp");
            rd.forward(request, response);
        } else if ("edit".equalsIgnoreCase(action)) {
            String form = request.getParameter("form");
            String classname = request.getParameter("classname");
            Date date = Date.valueOf(request.getParameter("date"));
            String[] sIDs_attendance;
            if (request.getParameterValues("editAttendance") != null) {
                sIDs_attendance = request.getParameterValues("editAttendance");
            } else {
                sIDs_attendance = new String[1];
            }
            ArrayList<StudentBean> students = sDB.queryStudentByClass(form, classname);

            for (StudentBean student : students) {
                aDB.editRecord(student.getsId(), date, false);
            }

            for (String sID_attendance : sIDs_attendance) {
                aDB.editRecord(sID_attendance, date, true);
            }

            ArrayList<AttendanceBean> attendance = aDB.queryAttendanceByDate(date);
            request.setAttribute("action", "list");
            request.setAttribute("attendance", attendance);
            request.setAttribute("students", students);
            request.setAttribute("form", form);
            request.setAttribute("classname", classname);
            request.setAttribute("date", date);
            //request.setAttribute("message", "Edit Success");

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/listAttendance.jsp");
            rd.forward(request, response);

        } else if ("report".equalsIgnoreCase(action)) {

            String form = request.getParameter("form");
            String classname = request.getParameter("classname");
            ArrayList<AttendanceBean> attendance = aDB.queryAttendance();
            ArrayList<StudentBean> students = sDB.queryStudentByClass(form, classname);

            //creating a Calendar object 
            Calendar cStart = Calendar.getInstance();

            // set Month 
            // MONTH starts with 0 i.e. ( 0 - Jan) 
            cStart.set(Calendar.MONTH, 8);

            // set Date 
            cStart.set(Calendar.DATE, 01);

            // set Year 
            cStart.set(Calendar.YEAR, 2019);

            Calendar cEnd = Calendar.getInstance();
            ArrayList<StudentBean> ss = sDB.queryStudent();
            double count = 0;
            int i = 0;
            while (cStart.before(cEnd) || cStart.equals(cEnd)) {
                count++;
                for (StudentBean s : ss) {
                    //aDB.addRecord(s.getsId(), new java.sql.Date(cStart.getTime().getTime()), false);
                }
                //add one day to date
                cStart.add(Calendar.DAY_OF_MONTH, 1);
            }

            double present = 0;
            ArrayList<StudentBean> countStudentsAttendance = new ArrayList();
            for (StudentBean student : students) {
                present = 0;
                for (AttendanceBean attend : attendance) {
                    if (attend.isAttendance_present() && attend.getSid().equals(student.getsId())) {
                        present++;
                    }
                }
                student.setAttendanceRate(count, present);
                countStudentsAttendance.add(student);

            }
            // set Month 
            // MONTH starts with 0 i.e. ( 0 - Jan) 
            cStart.set(Calendar.MONTH, 8);

            // set Date 
            cStart.set(Calendar.DATE, 01);

            // set Year 
            cStart.set(Calendar.YEAR, 2019);

            request.setAttribute("countStudentsAttendance", countStudentsAttendance);
            request.setAttribute("form", form);
            request.setAttribute("classname", classname);
            request.setAttribute("startday", cStart);
            request.setAttribute("endday", cEnd);
            
            
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/reportAttendance.jsp");
            rd.forward(request, response);

        } else if ("fail".equalsIgnoreCase(action)) {

            String form = request.getParameter("form");
            String classname = request.getParameter("classname");
            ArrayList<AttendanceBean> attendance = aDB.queryAttendance();
            ArrayList<StudentBean> students = sDB.queryStudentByClass(form, classname);

            //creating a Calendar object 
            Calendar cStart = Calendar.getInstance();

            // set Month 
            // MONTH starts with 0 i.e. ( 0 - Jan) 
            cStart.set(Calendar.MONTH, 8);

            // set Date 
            cStart.set(Calendar.DATE, 01);

            // set Year 
            cStart.set(Calendar.YEAR, 2019);

            Calendar cEnd = Calendar.getInstance();
            ArrayList<StudentBean> ss = sDB.queryStudent();
            double count = 0;
            int i = 0;
            while (cStart.before(cEnd) || cStart.equals(cEnd)) {
                count++;
                for (StudentBean s : ss) {
                    //aDB.addRecord(s.getsId(), new java.sql.Date(cStart.getTime().getTime()), false);
                }
                //add one day to date
                cStart.add(Calendar.DAY_OF_MONTH, 1);
            }

            double present = 0;
            ArrayList<StudentBean> countStudentsAttendance = new ArrayList();
            for (StudentBean student : students) {
                present = 0;
                for (AttendanceBean attend : attendance) {
                    if (attend.isAttendance_present() && attend.getSid().equals(student.getsId())) {
                        present++;
                    }
                }
                student.setAttendanceRate(count, present);
                countStudentsAttendance.add(student);

            }
            // set Month 
            // MONTH starts with 0 i.e. ( 0 - Jan) 
            cStart.set(Calendar.MONTH, 8);

            // set Date 
            cStart.set(Calendar.DATE, 01);

            // set Year 
            cStart.set(Calendar.YEAR, 2019);

            request.setAttribute("countStudentsAttendance", countStudentsAttendance);
            request.setAttribute("form", form);
            request.setAttribute("classname", classname);
            request.setAttribute("startday", cStart);
            request.setAttribute("endday", cEnd);

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/generatefailStudent.jsp");
            rd.forward(request, response);
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
