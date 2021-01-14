<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>

        <link rel="stylesheet" href="./css/myStyle.css" />
        <link rel="stylesheet" href="./css/layout.css" />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
    </head>

    <body>
        <jsp:include page="/header.jsp" flush="true"/>
        <div id="main">
   
            <form method="POST" action="LoginController">
                <h1>Login Page</h1>  <%
                    if (request.getAttribute("correct") != null) {
                        out.print("<font color=red>username or password has worng!</font>");
                    }

                %>
                <table border="0">
                    <tr><td><p align="right"><b>username</b></td>
                        <td><p><input type="text" name="name" maxlength="10" size="15" required="required" ></td>
                    </tr>
                    <tr><td><p align="right"><b>password</b></td>
                        <td><p><input type="password" name="pwd" maxlength="10" size="15" required="required"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><p align="center"><input type="submit" class="btn" value="login" ></td>
                    </tr>
                </table>
                <input type="hidden" name="action" value="login">
            </form>

        </div>

        <jsp:include page="/footer.jsp" flush="true"/>
    </body>
</html>
