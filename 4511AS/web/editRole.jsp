
<%@page import="ict.bean.userBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="request" class="ict.bean.userBean"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User Role</title>

        <link rel="stylesheet" href="./css/myStyle.css" />
        <link rel="stylesheet" href="./css/layout.css" />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
    </head>
    <body>
        <jsp:include page="/header.jsp" flush="true"/>
        <div id="main">
            <center>
                <h1> Edit User Role </h1>
                
                <form action="./adminAction" method="POST"> 
                    <input type="hidden" name="action" value="editUserRole"><h1><%=user.getName()%>'s information</h1>
                    <table border="0">

                        <tr><td>Username:</td><td><input type="text" name="user" maxlength="10" readonly="readonly" value="<%=user.getName()%>"></td></tr>
                        <tr><td>Password:</td><td> <input type="text" maxlength="10" readonly="readonly" value="<%=user.getPwd()%>"></td></tr>
                        <tr><td>Role</td><td> <input type="text" maxlength="10" readonly="readonly" value="<%=user.getRole()%>"></td></tr>
                    </table>
                    <br />
                    <h3>New Role:</h3>admin<input type="radio" name="role" value="admin" >
                    |teacher<input type="radio" name="role" value="teacher" checked="checked">
                    |student<input type="radio" name="role" value="student">
                    <br/>
                    <input type="submit" value="update"><input type="reset" value="reset">

                </form>
            </center>
        </div>
        <jsp:include page="/footer.jsp" flush="true"/>
    </body>
</html>
