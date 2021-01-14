
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User By Class(select class)</title>

        <link rel="stylesheet" href="./css/myStyle.css" />
        <link rel="stylesheet" href="./css/layout.css" />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
    </head>
    <body>
        <jsp:include page="/header.jsp" flush="true"/>
        <div id="main">
            <center>
                <h1>Select Class For Create Account</h1>
                <form method="POST" action="adminAction">
                    <input type="hidden" name="action" value="selectClass">
                    <%
                        ArrayList classList = (ArrayList) request.getAttribute("classList");
                        if (classList.size() <= 0) {
                            out.print("<font color=red> all class student have account already</font><br />");
                        }
                    %>
                    <select name="class">

                        <%
                            if (classList != null && classList.size() > 0) {
                                for (int i = 0; i < classList.size(); i++) {
                                    out.print("<option value='" + classList.get(i) + "'>" + classList.get(i) + "</option>");
                                }
                            }

                        %>

                    </select>
                    <input type="submit" class="btn" value="Select">
                </form>
            </center>
        </div>
        <jsp:include page="/footer.jsp" flush="true"/>
    </body>
</html>
