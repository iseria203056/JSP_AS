
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.userBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User List</title>

        <link rel="stylesheet" href="./css/myStyle.css" />
        <link rel="stylesheet" href="./css/layout.css" />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
    </head>
    <body>
        <jsp:include page="/header.jsp" flush="true"/>
        <div id="main">
            <center>
                <h1>User List</h1>
                <% if (request.getAttribute("message") != null) {
                        out.print("<font color=red>" + request.getAttribute("message") + "</font>");
                    }
                %>
                Filter<form method="GET" action="adminAction">
                    <input type="radio" name="filter" value="name">Name|
                    <input type="radio" name="filter" value="role">Role
                    <input type="text" name="content" placeholder="enter full role/user name">
                    <input type="submit" value="search">
                    <input type="hidden" value="showUser" name="action">
                </form>
                <table border="1">
                    
                    <jsp:useBean id="users" scope="request" class="java.util.ArrayList<ict.bean.userBean>" />
                    <%
                        out.println("<tr><th>Username</th><th>Password</th><th>role</th><th>Edit Role?</th></tr>");
                        for (int i = 0; i < users.size(); i++) {
                            out.println("<tr><td>" + users.get(i).getName() + "</td><td>" + users.get(i).getPwd() + "</td><td>" + users.get(i).getRole() + "</td><td><a href='adminAction?action=editRole&value=" + users.get(i).getName() + "'>edit role</a></td></tr>");
                        }

                    %>
                </table> 
                <br />
            </center>
        </div>
        <jsp:include page="/footer.jsp" flush="true"/>
    </body>
</html>
