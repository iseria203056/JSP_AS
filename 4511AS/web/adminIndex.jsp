
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/welcomes"  prefix="ict" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Index</title>
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
                if (!session.getAttribute("role").equals("admin")) {
                    response.setStatus(response.SC_MOVED_TEMPORARILY);
                    response.setHeader("Location", session.getAttribute("role") + "Index.jsp");
                }
            }

        %>
        <jsp:include page="/header.jsp" flush="true"/>
        <div id="main">
            <% if (session.getAttribute("role") == null) {%>

            <jsp:useBean id="user" scope="session" class="ict.bean.userBean"/>
            <ict:WelcomeTag users="<%=user%>" />

            <%}%>

            <ul>
                Fully System:
                <li><a href="teacherIndex.jsp">Teacher Index</a></li>
                <li><a href="studentIndex.jsp">Student Index</a></li>

                <br/>                <br/>

                Manage record:
                <li><a href="addStudent.jsp">addStudent</a></li>
                <li><a href="changeClass.jsp">Change Student Class</a></li>

            </ul> 
        </div>

        <jsp:include page="/footer.jsp" flush="true"/>
    </body>
</html>
