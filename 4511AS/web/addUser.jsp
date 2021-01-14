
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add User</title>
        <link rel="stylesheet" href="./css/myStyle.css" />
        <link rel="stylesheet" href="./css/layout.css" />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
    </head>
    <body>
        <jsp:include page="/header.jsp" flush="true"/>
        <div id="main">
            <center>
                <form action="adminAction" method="POST">
                    <input type="hidden" name="action" value="addUser">
                    <table border="0">
                        <h1>Create User </h1>
                        <tr><td>Username: </td><td><input type="text" name="user" maxlength="10" required="required"></td></tr>
                        <tr><td>Password: </td><td> <input type="text" name="pwd" maxlength="10" required="required"></td></tr>
                        <tr><td>Role:admin <input type="radio" name="role" value="admin" ></td>
                            <td>| teacher <input type="radio" name="role" value="teacher" checked="checked">
                                | student <input type="radio" name="role" value="student"></td></tr>
                        <tr><td></td><td><input type="submit" class="btn" style="width: 125px;" value="Create">  <input type="reset" value="Clear"></td></tr>
                    </table>
                </form>
            </center>
        </div>
        <jsp:include page="/footer.jsp" flush="true"/>
    </body>
</html>
