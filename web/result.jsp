<%--
  Created by IntelliJ IDEA.
  User: Limmy
  Date: 16.01.2017
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success!</title>
</head>
<body>
<!-- Send the information from XLS file.
Use parameters from servlet.-->
<% String userName = (String) request.getAttribute("userName");
   int attempts = (Integer) request.getAttribute("attempts");%>
Dear <%out.print(userName);%>, it's the <%=attempts%>st your attempt to connect here.
<% if (attempts == 1) out.print("<br> WELCOME!");%>
<form method="get" action="/enterName">
    <button><p align="center">Try one more time!</p></button>
</form>
</body>
</html>
