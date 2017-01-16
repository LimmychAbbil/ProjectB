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
    <%
        out.print(request.getAttribute("userName") + " " + request.getAttribute("attempts"));
    %>
</body>
</html>
