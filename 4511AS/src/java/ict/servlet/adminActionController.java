/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.StudentBean;
import ict.db.UserDB;
import ict.db.StudentDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iseria
 */
@WebServlet(name = "adminActionController", urlPatterns = {"/adminAction"})
public class adminActionController extends HttpServlet {

    UserDB db;
    StudentDB db2;

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet adminActionController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet adminActionController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String action = request.getParameter("action");
        if (action.equals("showUser")) {
            if(request.getParameter("filter")==null){
            request.setAttribute("users", db.getAllUser());
            }else{
            request.setAttribute("users", db.getUserList(request.getParameter("filter"), request.getParameter("content")));
            }
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/userList.jsp");
            rd.forward(request, response);

        } else if (action.equals("delete")) {

        } else if (action.equals("editRole")) {
            request.setAttribute("user", db.getUser(request.getParameter("value")));
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/editRole.jsp");
            rd.forward(request, response);
        } else if (action.equals("selectClass")) {
            request.setAttribute("classList", db.getClassWithNoAC());
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/createUserByClass.jsp");
            rd.forward(request, response);

        }
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
        Writer w = response.getWriter();
        String action = request.getParameter("action");
        w.write(action);
        if (action.equals("addUser")) {
            if (addUser(request, response)) {
                request.setAttribute("users", db.getAllUser());
                request.setAttribute("message", "create success");
                RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/userList.jsp");
                rd.forward(request, response);
            } else {

                request.setAttribute("users", db.getAllUser());
                request.setAttribute("message", "username already exist");
                RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/userList.jsp");
                rd.forward(request, response);
            }
        } else if (action.equals("editUserRole")) {
            String user = request.getParameter("user");
            String Role = request.getParameter("role");
            db.editRole(user, Role);
            request.setAttribute("users", db.getAllUser());
            request.setAttribute("message", "updated");
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/userList.jsp");
            rd.forward(request, response);

        } else if (action.equals("selectClass")) {
            String option = request.getParameter("class");
            request.setAttribute("student", db2.getStudentWithNoAC(option.charAt(0)+"",""+option.charAt(1)));
            request.setAttribute("class", option);
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/previewClassAC.jsp");
            rd.forward(request, response);
        } else if (action.equals("addByClass")) {
            String option = request.getParameter("class");
            ArrayList<StudentBean> students = db2.getStudentWithNoAC(option.charAt(0)+"",""+option.charAt(1));

            for (int i = 0; i < students.size(); i++) {
                db.addUser(students.get(i).getsId(), "s" + students.get(i).getsId(), "student");
            }
            request.setAttribute("message", "create success");
            request.setAttribute("users", db.getAllUser());
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/userList.jsp");
            rd.forward(request, response);
        } else if (action.equals("addStudent")) {
            String message = "";
            if (db2.addRecord(request.getParameter("sid"), request.getParameter("name"), request.getParameter("form"), request.getParameter("class"))) {
                message = "success";
            } else {
                message = "student ID already exist";
            }
            request.setAttribute("message", message);
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/addStudent.jsp");
            rd.forward(request, response);

        } else if (action.equals("editStudent")) {
            String message = "";
            StudentBean s = db2.queryStudentByStudentID(request.getParameter("sid"));
            if (s == null) {
                message = "student do not exist";
            }
            s.setForm(request.getParameter("form"));
            s.setClassName(request.getParameter("class"));
            if (db2.editRecord(s)) {
                message = "success";
            } else {
                message = s.toString();
            }
             request.setAttribute("message", message);
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/changeClass.jsp");
            rd.forward(request, response);
        }
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

    public boolean addUser(HttpServletRequest request, HttpServletResponse response) {

        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        String role = request.getParameter("role");
        if (db.addUser(user, pwd, role)) {
            return true;
        }
        return false;
    }

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new UserDB(dbUrl, dbUser, dbPassword);
        db2 = new StudentDB(dbUrl, dbUser, dbPassword);
    }
}
