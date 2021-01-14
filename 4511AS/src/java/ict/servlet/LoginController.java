/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.userBean;
import ict.db.StudentDB;
import ict.db.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author iseria
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
    StudentDB d;
    UserDB db;

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
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
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
        doPost(request, response);
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
        Writer ot = response.getWriter();
        if (request.getParameter("action").equals("profile")) {
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/" + "profile.jsp");
            rd.forward(request, response);

        } else if (request.getParameter("action").equals("login")) {
            try {
                init();
                String username = request.getParameter("name");
                String pwd = request.getParameter("pwd");
                String targetURL;
                if (db.isValidUser(username, pwd)) {

                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", db.getUser(username));
                    String role = db.getRole(username);
                    
                    session.setAttribute("username", username);
                    session.setAttribute("role", role);
                    if (role.equals("admin")) {
                        targetURL = "adminIndex.jsp";
                    } else if (role.equals("student")) {
                        
                        session.setAttribute("student", d.queryStudentByStudentID(username));
                        
                        targetURL = "studentIndex.jsp";
                    } else if (role.equals("teacher")) {
                        targetURL = "teacherIndex.jsp";
                    } else {
                        targetURL = "index.jsp";

                    }

                } else {
                    targetURL = "index.jsp";
                    request.setAttribute("correct", false);

                }

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/" + targetURL);
                rd.forward(request, response);
            } catch (SQLException ex) {
                ot.write("error");
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("action").equals("editing")) {
            String opwd = request.getParameter("opwd");
            String npwd = request.getParameter("npwd");
            String rpwd = request.getParameter("rpwd");
            if (db.getUser(request.getParameter("userName")).getPwd().equals(opwd)) {
                if (npwd.equals(rpwd)) {
                    db.editProfile(request.getParameter("userName"), npwd);
                    request.setAttribute("message", "update success");
                    RequestDispatcher rd;
                    rd = getServletContext().getRequestDispatcher("/profile.jsp");
                    rd.forward(request, response);
                } else {
                    request.setAttribute("message", "new password and re-enter password are not same");
                    RequestDispatcher rd;
                    rd = getServletContext().getRequestDispatcher("/profile.jsp");
                    rd.forward(request, response);
                }
            } else {
                request.setAttribute("message", "password error");
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/" + "profile.jsp");
                rd.forward(request, response);
            }
        } else if (request.getParameter("action").equals("logout")) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute("user");
                session.removeAttribute("username");
                session.removeAttribute("role");
                session.invalidate();
            }
            String targetURL = "index.jsp";
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/" + targetURL);
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

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new UserDB(dbUrl, dbUser, dbPassword);
        d=new StudentDB(dbUrl, dbUser, dbPassword);
    }
}
