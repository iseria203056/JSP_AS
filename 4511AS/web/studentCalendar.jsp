<%@page import="ict.bean.ScheduleBean"%>
<%@ page import="ict.bean.CalendarBean,java.util.*,java.util.*,java.io.*,java.sql.*"%>

<%@ include file="calendarCommon.jsp" %>
<jsp:useBean id="sSchedule" class="java.util.ArrayList<ScheduleBean>" scope="request"/>

<html>
    <head>
        <%            String form = request.getAttribute("sForm").toString();
            String classname = request.getAttribute("sClassname").toString();
            String title = "Class " + form + classname + " students";

        %>
        <title><%=title%> - School Schedule</title>
        <link rel="stylesheet" href="./css/myStyle.css" />
        <link rel="stylesheet" href="./css/layout.css" />

        <style>
            .holiday{text-align: center;border-color:#EAEAEA;border-width:1px;border-style:Solid;width:14%;color:#FF0000;font-weight:bold;text-decoration:none;font-style:normal;}
            .schoolday{border-color:#EAEAEA;border-width:1px;border-style:Solid;width:14%;}
            .weekend{background-color:#EEFFBF;border-color:#EAEAEA;border-width:1px;border-style:Solid;width:14%;}
        </style>


    </head>

    <body>
        <jsp:include page="/header.jsp" flush="true"/>
        <div id="main">
            <center>
                <h1><%=title%>, School Schedule</h1>

                <div>
                    <table cellspacing="0" cellpadding="25" title="Calendar" style="background-color:White;border-color:#8BA938;border-width:1px;border-style:Solid;font-family: Arial, Helvetica, sans-serif;font-size:Medium;height:180px;border-collapse:collapse;">

                        <tr>
                            <td colspan="7" style="background-color:#8BA938;">
                                <table class="oneCalendar" cellspacing="0" style="color:#000000;font-family: Arial, Helvetica, sans-serif;font-size:Medium;font-weight:bold;width:100%;border-collapse:collapse;">
                                    <tr>
                                        <td align="center" style="width:70%;"><%=monthName%>, <%=intYear%></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>

                        <tr>
                            <th align="center" abbr="Sun" scope="col" style="border-color:Silver;border-width:1px;border-style:solid;font-size:12pt;font-weight:bold;">Sun</th>
                            <th align="center" abbr="Mon" scope="col" style="border-color:Silver;border-width:1px;border-style:solid;font-size:12pt;font-weight:bold;">Mon</th>
                            <th align="center" abbr="Tue" scope="col" style="border-color:Silver;border-width:1px;border-style:solid;font-size:12pt;font-weight:bold;">Tue</th>
                            <th align="center" abbr="Wed" scope="col" style="border-color:Silver;border-width:1px;border-style:solid;font-size:12pt;font-weight:bold;">Wed</th>
                            <th align="center" abbr="Thu" scope="col" style="border-color:Silver;border-width:1px;border-style:solid;font-size:12pt;font-weight:bold;">Thu</th>
                            <th align="center" abbr="Fri" scope="col" style="border-color:Silver;border-width:1px;border-style:solid;font-size:12pt;font-weight:bold;">Fri</th>
                            <th align="center" abbr="Sat" scope="col" style="border-color:Silver;border-width:1px;border-style:solid;font-size:12pt;font-weight:bold;">Sat</th>
                        </tr>

                        <%

                            CalendarBean aMonth = CalendarBean.getMonth(Integer.parseInt(currentMonthString), Integer.parseInt(currentYearString));
                            ArrayList<Integer> currentMonthHolidays = new ArrayList();

                            if (sSchedule == null) {
                                sSchedule = new ArrayList();
                            }
                            for (int i = 0; i < sSchedule.size(); i++) {

                                if (sSchedule.get(i).getCurrentMonth() == Integer.parseInt(currentMonthString) && sSchedule.get(i).getCurrentYear() == Integer.parseInt(currentYearString)) {
                                    currentMonthHolidays.add(sSchedule.get(i).getCurrentDate());
                                }
                            }

                            String holiday = "";
                            int[][] days = aMonth.getDays();
                            for (int i = 0; i < aMonth.getNumberOfWeeks(); i++) {
                                out.println("<tr class=\"week_data_row\">");
                                for (int j = 0; j < 7; j++) {
                                    if (days[i][j] == 0) {
                                        out.println("<td class=\"empty_day_cell\">&nbsp;</td>");
                                    } else {
                                        for (int k = 0; k < currentMonthHolidays.size(); k++) {
                                            if (currentMonthHolidays.get(k) == days[i][j]) {
                                                holiday = "holiday";
                                            }
                                        }
                                        // this is "today"
                                        if (currentDayInt == days[i][j] && currentMonthInt == aMonth.getMonth() && currentYearInt == aMonth.getYear()) {
                                            if (currentWeekDayInt == 1 || currentWeekDayInt == 7) {
                                                out.println("<td align=\"center\" class=\"weekend " + holiday + "\">" + days[i][j] + "</td>");
                                            } else {
                                                out.println("<td align=\"center " + holiday + "\">" + days[i][j] + "</td>");
                                            }
                                        } else {
                                            theCal.set(currentYearInt, currentMonthInt, days[i][j]);
                                            if (j == 0 || j == 6) {
                                                out.println("<td align=\"center\" class=\"weekend " + holiday + "\">" + days[i][j] + "</td>");
                                            } else {
                                                out.println("<td align=\"center\" class=\"schoolday " + holiday + "\">" + days[i][j] + "</td>");
                                            }
                                        }

                                    } // end outer if
                                    holiday = "";
                                } // end for
                                out.println("</tr>");
                            }

                        %>
                    </table>

                </div>

                <table border="0">
                    <tr>
                        <td>
                            <form method="post">
                                <input type="submit"  class="right" name="PREV" value=" << " style="width: 150px;">
                                <input type="hidden" name="month" value="<%=prevMonth%>">
                                <input type="hidden" name="year" value="<%=prevYear%>">
                            </form>

                        </td>
                        <td>

                            <form method="post">
                                <input type="submit" name="NEXT" value=" >> " style="width: 150px;">
                                <input type="hidden" name="month" value="<%=nextMonth%>">
                                <input type="hidden" name="year" value="<%=nextYear%>">
                            </form>
                        </td>
                    </tr>

                </table>
            </center>
            <!-- navigation links end -->
            <p>
                Students are not required to attend classes on Saturdays and Sundays. <br />
                The days shown in <b style="color: red;">Red</b> are school holidays and students do not have to attend classes. <br />
            </p>

        </div>

        <jsp:include page="/footer.jsp" flush="true"/>
    </body>
</html>


