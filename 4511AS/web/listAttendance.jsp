

<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="attendance" scope="request" class="java.util.ArrayList<ict.bean.AttendanceBean>" />
<jsp:useBean id="students" scope="request" class="java.util.ArrayList<ict.bean.StudentBean>" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Attendanece</title>
        <link rel="stylesheet" href="./css/myStyle.css" />
        <link rel="stylesheet" href="./css/layout.css" />
        <%
            String form = request.getAttribute("form").toString();
            String classname = request.getAttribute("classname").toString();
            Date date = (Date) request.getAttribute("date");
            String readonly = "";
            if (session.getAttribute("role").equals("student")) {
                readonly = "readonly=\"readonly\"";
            }

        %>


    </head>
    <body>
        <jsp:include page="/header.jsp" flush="true"/>
        <div id="main">
            <center>
                <h1>List Class <%=form + classname%>, Attendance</h1>
                <h2>Date: <%=date%></h2>

                
                <form method="POST" action="handleAttendance">
                    <table border="1">

                        <%
                            String checked = "";
                            out.println("<tr><th>Student ID</th><th>Student Name</th><th>Attendance</th></tr>");
                            for (int i = 0; i < students.size(); i++) {
                                checked = "";
                                for (int j = 1; j < attendance.size(); j++) {
                                    if (students.get(i).getsId().equals(attendance.get(j).getSid()) && attendance.get(j).isAttendance_present()) {
                                        checked = "checked=\"checked\"";
                                    }
                                }
                                out.println("<tr><td>" + students.get(i).getsId() + "</td><td>" + students.get(i).getName() + "</td><td> <input name=\"editAttendance\" type=\"checkbox\" " + checked + " " + readonly + " value=\"" + students.get(i).getsId() + "\"/></td></tr>");
                            }

                            if (!session.getAttribute("role").equals("student")) {
                        %>
                        <tr align="center">
                            <td></td>
                            <td>
                                <input type="hidden" name="action"  value="edit" />
                                <input type="hidden" name="form"  value="<%=form%>" />
                                <input type="hidden" name="classname"  value="<%=classname%>" />
                                <%if (!session.getAttribute("role").equals("student"))%>
                                <input type="hidden" name="date"  value="<%=date%>" />
                                <input type="submit" class="btn" value="Submit" style="font-size: 50px;" />
                            </td>
                            <td></td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                </form>


            </center>
        </div>
        <jsp:include page="/footer.jsp" flush="true"/>
    </body>
</html>
