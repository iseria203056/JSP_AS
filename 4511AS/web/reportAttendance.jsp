

<%@page import="java.sql.Date"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="countStudentsAttendance" scope="request" class="java.util.ArrayList<ict.bean.StudentBean>" />
<jsp:useBean id="startday" scope="request" class="java.util.Calendar" />
<jsp:useBean id="endday" scope="request" class="java.util.Calendar" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report Attendanece</title>
        <link rel="stylesheet" href="./css/myStyle.css" />
        <link rel="stylesheet" href="./css/layout.css" />
        <%
            String form = request.getAttribute("form").toString();
            String classname = request.getAttribute("classname").toString();
            Calendar cStart = startday;
            Calendar cEnd = endday;

            int currentYearInt = cStart.get(Calendar.YEAR);
            int currentMonthInt = cStart.get(Calendar.MONTH);
            int currentDayInt = cStart.get(Calendar.DATE);

            int endYearInt = cEnd.get(Calendar.YEAR);
            int endMonthInt = cEnd.get(Calendar.MONTH);
            int endDayInt = cEnd.get(Calendar.DATE);

            String monthNames[] = {
                "January",
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
                <h1>Report Class <%=form + classname%>, Attendance</h1>
                <h2>Form: <%=monthNames[currentMonthInt] + ", " + currentYearInt%> To: <%=monthNames[endMonthInt] + ", " + endYearInt%></h2>
                
                <form action="handleAttendance" method="POST">
                    <input type="hidden" name="action"  value="fail" />
                    <input type="hidden" name="form"  value="<%=form%>" />
                    <input type="hidden" name="classname" value="<%=classname%>" />

                    <input type="submit" class="btn" value="Generate less than 60% student report" />
                </form>
                <form method="POST" action="handleAttendance">
                    <table border="1">

                        <%
                            out.println("<tr><th>Student ID</th><th>Student Name</th><th>Attendance Rate</th></tr>");
                            for (int i = 0; i < countStudentsAttendance.size() ; i++) {
                                out.println("<tr><td>" + countStudentsAttendance.get(i).getsId() + "</td><td>" + countStudentsAttendance.get(i).getName() + "</td><td> <input name=\"attendanceRate\" type=\"text\" " + " " + "readonly=\"readonly\"" + " value=\"" + countStudentsAttendance.get(i).getAttendanceRate() + "%\"/></td></tr>");
                            }
                                   %>
                    </table>
                </form>
            </center>
        </div>
        <jsp:include page="/footer.jsp" flush="true"/>
    </body>
</html>
