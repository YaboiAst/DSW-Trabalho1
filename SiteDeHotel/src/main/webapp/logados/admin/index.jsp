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
        <h1><fmt:message key="admin.title"/></h1>
        <p><fmt:message key="main.welcome"/>${sessionScope.usuarioLogado.email}</p>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/logout.jsp"><fmt:message key="main.leave"/></a>
                <a href="${pageContext.request.contextPath}/admin/lista"><fmt:message key="user.manage"/></a>
            </li>
        </ul>
    </body>
</html>