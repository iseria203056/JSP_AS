

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="yourAttendance" scope="request" class="java.util.ArrayList<ict.bean.AttendanceBean>" />
<jsp:useBean id="student" scope="request" class="ict.bean.StudentBean" />
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
            String readonly = "";
            String studentName = student.getName();
            if (session.getAttribute("role").equals("student")) {
                readonly = "readonly=\"readonly\"";
            }

            Calendar cal = Calendar.getInstance();
            cal.setTime(yourAttendance.get(0).getDate());
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int year = cal.get(Calendar.YEAR);
            String monthNames[] = {"January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"};
        %>


    </head>
    <body>
        <jsp:include page="/header.jsp" flush="true"/>
        <div id="main">
            <center>
                
                <h1>List <%=form + classname + ", " + studentName%> attendance records for</h1>
                <h2>2019</h2>
                <br />
                <form method="POST" action="handleAttendance">
                    <table border="1">

                        <%
                            String checked = "";
                            String attended = "";
                            out.println("<tr><th>Student ID</th><th>Student Name</th><th>Attendance</th><th>Attended</th></tr>");
                            for (int i = 0; i < yourAttendance.size(); i++) {
                                if (yourAttendance.get(i).isAttendance_present()) {
                                    attended = "Attended";
                                }
                                out.println("<tr><td>" + student.getsId() + "</td><td>" + student.getName() + "</td><td> <input name=\"editAttendance\" type=\"date\" " + checked + " " + readonly + " value=\"" + yourAttendance.get(i).getDate() + "\"/></td> <td>" + attended + "</td></tr>");
                            }

                            if (!session.getAttribute("role").equals("student")) {
                        %>
                        <tr align="center">
                            <td></td>
                            <td>
                                <input type="hidden" name="action"  value="edit" />
                                <input type="hidden" name="form"  value="<%=form%>" />
                                <input type="hidden" name="classname"  value="<%=classname%>" />
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
