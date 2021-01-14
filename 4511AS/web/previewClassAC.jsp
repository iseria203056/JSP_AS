
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>preview class ac</title>

        <link rel="stylesheet" href="./css/myStyle.css" />
        <link rel="stylesheet" href="./css/layout.css" />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
    </head>
    <body>
       <jsp:include page="/header.jsp" flush="true"/>
        <div id="main">
            <center>
                <h1>preview Account create LIST</h1>
                <jsp:useBean id="student" scope="request" class="java.util.ArrayList<ict.bean.StudentBean>" />
                <p>the sid will become the user name and default password "s"+sid </p>
                <table border="1">
                    <tr><th>User Name</th><th>Pwd</th><th>student Name</th></tr>
                            <%
                                for (int i = 0; i < student.size(); i++) {
                                    out.print("<tr><td>" + student.get(i).getsId() + "</td><td>s" + student.get(i).getsId() + "</td><td>" + student.get(i).getName() + "</td></tr>");
                                }
                            %>

                </table>
                            <br />
                <form method="POST" action="adminAction">
                    <input type="hidden" name="action" value="addByClass">
                    <input type="hidden" name="class" value="<%=request.getParameter("class")%>">

                    <input type="submit" class="btn" value="Confirm">
                </form>
            </center>
        </div>
        <jsp:include page="/footer.jsp" flush="true"/>
    </body>
</html>
