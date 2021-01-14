
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teacher Index</title>

        <link rel="stylesheet" href="./css/myStyle.css" />
        <link rel="stylesheet" href="./css/layout.css" />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
    </head>
    <body>
        <%
        if (session.getAttribute("role") == null) {
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "index.jsp");
            } else {
                if (session.getAttribute("role").equals("student")) {
                    response.setStatus(response.SC_MOVED_TEMPORARILY);
                    response.setHeader("Location", session.getAttribute("role") + "Index.jsp");
                }
            }
        
        %>
        <jsp:include page="/header.jsp" flush="true"/>
            <div id="main">
                <h1>welcome back!!</h1>
                <li><a href="viewAttendance.jsp"> student's Attendance </a></li>
                <li><a href="selectCalendar.jsp">  Schedule </a></li>
                <li><a href="selectCalendar.jsp">  Report </a></li>
            </div>

            <jsp:include page="/footer.jsp" flush="true"/>
    </body>
</html>
