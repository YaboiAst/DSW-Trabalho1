<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PromocoesHotel</title>
    </head>
    <body>
    	<%
		String contextPath = request.getContextPath().replace("/", "");
        %>
        <a href="login.jsp">Fazer Login</a>
        <a href="/<%=contextPath%>/Hotel/lista">Listar Hoteis</a>
    </body>
</html>