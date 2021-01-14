
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
                <% if (request.getAttribute("message") != null) {
                        out.print("<font color=red>" + request.getAttribute("message") + "</font>");
                    }
                %>
                <form action="adminAction" method="POST">
                    <input type="hidden" name="action" value="addStudent">
                    <table border="0">
                        <h1>Create Student Record</h1>
                        <tr><td>Student ID</td><td><input type="text" name="sid" maxlength="10" required="required"></td></tr>
                        <tr><td>Student Name </td><td> <input type="text" name="name" maxlength="20" required="required"></td></tr>
                        <tr><td>Form</td><td> <input type="number" name="form" maxlength="1" required="required"></td></tr>
                        <tr><td>Class</td> <td><select name="class"><option value="A">A</option><option value="B">B</option><option value="C">C</option><option value="D">D</option></select></td></tr>
                        <tr><td></td><td><input type="submit" class="btn" style="width: 125px;" value="Create">  <input type="reset" value="Clear"></td></tr>
                    </table>
                </form>
            </center>
        </div>
        <jsp:include page="/footer.jsp" flush="true"/>
    </body>
</html>

    </body>
</html>
