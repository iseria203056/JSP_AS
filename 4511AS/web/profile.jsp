
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
        <link rel="stylesheet" href="./css/myStyle.css" />
        <link rel="stylesheet" href="./css/layout.css" />
    </head>
    <body>
        <jsp:include page="/header.jsp" flush="true"/>
        <div id="main">
            <center>
                <% if (request.getAttribute("message") != null) {
                        out.print("<font color=red>" + request.getAttribute("message") + "</font>");
                    }
                %>
                <jsp:useBean id="user" scope="session" class="ict.bean.userBean"/>
                <h1>Profile</h1>
                <form method="POST" action="LoginController">
                    <table border="0">
                        <tr><td>User Name</td><td><input type="text" name="userName" value="<%=user.getName()%>" readonly="readonly"></td></tr>
                        <tr><td>Role</td><td><input type="text" value="<%=user.getRole()%>"readonly="readonly"></td></tr>
                        <tr><td>old Password</td><td><input type="password" name="opwd" required></td></tr>
                        <tr><td>new Password</td><td><input type="password" name="npwd" required></td></tr>
                        <tr><td>Re-enter new Password again</td><td><input type="password" name="rpwd" required></td></tr>

                    </table>
                    <input type="hidden" name="action" value="editing">
                    <input type="submit" value="submit">
                </form>
            </center>
        </div>
        <jsp:include page="/footer.jsp" flush="true"/>
    </body>
</html>
