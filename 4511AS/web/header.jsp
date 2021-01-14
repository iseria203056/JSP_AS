<%@page import="ict.bean.StudentBean"%>
<div id="flex-container">
    <div id="header" align="center">
        <div class="menu">
            <ul>
                <%
                    String userSessionRole = "";
                    if (session.getAttribute("role") != null) {
                        userSessionRole = (String) session.getAttribute("role");
                    }
                    if (userSessionRole.equalsIgnoreCase("admin")) {
                        out.print("<li><a href=\"adminIndex.jsp\">Home</a></li>");
                        out.print("<li><a href=\"addUser.jsp\">Create account</a></li>"
                                + "<li><a href=\"adminAction?action=showUser\">Manage user role</a></li>"
                                + "<li><a href=\"adminAction?action=selectClass\">Create different account</a></li>"
                        );
                    }

                    if (userSessionRole.equalsIgnoreCase("teacher")) {
                        out.print("<li><a href=\"teacherIndex.jsp\">Home</a></li>");
                        out.print("<li><a href=\"viewAttendance.jsp\"> View Attendance </a></li>"
                                + "<li><a href=\"selectCalendar.jsp\"> View Schedule </a></li>"
                                + "<li><a href=\"viewReportAttendance.jsp\">  Report Attendance </a></li>");

                    }

                    if (userSessionRole.equalsIgnoreCase("student")) {
                        StudentBean s = (StudentBean) session.getAttribute("student");
                        out.print("<li><a href=\"studentIndex.jsp\">Home</a></li>");
                        out.print("<li><a href=\"scheduleController?action=list\"> View Schedule </a></li>"
                                + "<li><a href=\"handleAttendance?action=list\"> View Attendance </a></li>");

                    }

                    if (session.getAttribute("role") != null) {

                %>



                <li style="float:right"><a href="LoginController?action=logout">Logout</a></li>
                <li style="float:right"><a href="profile.jsp">Profile</a></li>

                <%                    }
                %>

            </ul>

        </div>
    </div>
    <br />
    <br />