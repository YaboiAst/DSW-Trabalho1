<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="system.title"/></title>
    </head>
    <body>
        <h1><fmt:message key="hotel.page"/></h1>
        <p><fmt:message key="main.welcome"/>${sessionScope.usuarioLogado.email}</p>
        <ul>
            <li>
            	<a href="${pageContext.request.contextPath}/promocao/cadastro"><fmt:message key="promo.add"/></a>
            	<a href="${pageContext.request.contextPath}/promocao/lista"><fmt:message key="promo.list"/></a>
                <a href="${pageContext.request.contextPath}/logout.jsp"><fmt:message key="main.leave"/></a>
            </li>
        </ul>
    </body>
</html>