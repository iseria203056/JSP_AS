<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Attendance Report</title>
        <link rel="stylesheet" href="./css/myStyle.css" />
        <link rel="stylesheet" href="./css/layout.css" />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />

    </head>
    <body>
        <jsp:include page="/header.jsp" flush="true"/>
        <div id="main">
            <center>
                <h1>View Attendance Report</h1>

                <form method="POST" action="handleAttendance">
                    <input type="hidden" name="action"  value="report" />

                    <h2> Get Attendance Report </h2> 
                    <table align="center" border="0">
                        <tr>
                            <td>
                                <p align="right"><b>Form: </b></p>
                            </td>
                            <td colspan="2">
                                <p>
                                    <select name="form" required="required">

                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                    </select>
                                </p>
                            </td>
                        </tr><tr>
                            <td id="abc">
                                <p align="right"><b>Class: </b></p>
                            </td>
                            <td>
                                <p>
                                    <select name="classname" required="required">

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

                            <td colspan="2">
                                <p align="center"><input type="submit" class="btn" value="Generate report" /></p>
                            </td>
                        </tr>
                    </table>
                </form>


            </center>
        </div>

        <jsp:include page="/footer.jsp" flush="true"/>
    </body>
</html>