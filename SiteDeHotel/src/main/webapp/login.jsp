<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autenticação de Cliente</title>
        <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    <%String contextPath = request.getContextPath().replace("/", "");%>
        <h1><fmt:message key="login.title"/></h1>
        <c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                        </c:forEach>
                </ul>
            </div>
        </c:if>
        <form method="post" action="index.jsp">
            <table>
                <tr>
                    <th>Login: </th>
                    <td><input type="text" name="email"
                               value="${param.email}"/></td>
                </tr>
                <tr>
                    <th><fmt:message key="login.pswd"/></th>
                    <td><input type="password" name="senha" value="${param.senha}" /></td>
                </tr>
                <tr>
                    <td colspan="2"> 
                        <input type="submit" name="Ok" value="Entrar"/>
                    </td>
                </tr>
            </table>
        </form>
        <a href="/<%=contextPath%>"><fmt:message key="main.menu"/></a>
    </body>
</html>