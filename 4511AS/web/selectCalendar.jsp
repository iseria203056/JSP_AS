
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Select Calendar</title>

        <link rel="stylesheet" href="./css/myStyle.css" />
        <link rel="stylesheet" href="./css/layout.css" />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
    </head>
    <body>
        <jsp:include page="/header.jsp" flush="true"/>
        <div id="main">
            <center>

                <div style="width: 100%;height: 100%;font-size: 30px;">
                    <%
                        if (session.getAttribute("role").equals("teacher")||session.getAttribute("role").equals("admin")) {
                            out.println("<h1>Set and view holidays</h1>");
                        } else {
                            out.println("<h1>View schedule</h1>");
                        }
                    %>   
                    <form method="GET" action="scheduleController">
                        <input type="hidden" name="action"  value="list" /> 

                        <br /><br />
                        <table border="0">

                            <tr>
                                <td>
                                    <p align="right"><b>Form: </b></p>
                                </td>
                                <td>
                                    <p>
                                        <select name="form" required="required">
                                            <option value="All">All</option>
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>

                                        </select>
                                    </p>
                                </td>
                            
                                <td>
                                    <p align="right"><b>Class: </b></p>
                                </td>
                                <td>
                                    <p>
                                        <select name="classname" required="required">
                                            <option value="All">All</option>
                                            <option value="A">A</option>
                                            <option value="B">B</option>
                                            <option value="C">C</option>
                                            <option value="D">D</option>
                                            <option value="E">E</option>
                                            <option value="F">F</option>
                                        </select>
                                    </p>
                                </td>
                            </tr>
                            <tr>

                                <td colspan="5">
                                    <p align="center"><input type="submit" class="btn" value="Submit" /></p>
                                </td>
                            </tr>
                        </table>
                    </form>




                </div>
            </center>
        </div>
        <jsp:include page="/footer.jsp" flush="true"/>
    </body>
</html>
