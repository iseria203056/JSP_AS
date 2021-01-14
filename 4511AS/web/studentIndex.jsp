
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Index</title>
        <link rel="stylesheet" href="./css/myStyle.css" />
        <link rel="stylesheet" href="./css/layout.css" />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
    </head>
    <body>
        <jsp:include page="/header.jsp" flush="true"/>
        <%
        if(session.getAttribute("role")==null){
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", "index.jsp"); 
        }
        %>
            <div id="main">
                <h1>Welcome</h1>
            </div>
            <jsp:include page="/footer.jsp" flush="true"/>
    </body>
</html>
