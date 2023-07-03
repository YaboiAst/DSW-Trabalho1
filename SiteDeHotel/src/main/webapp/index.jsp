<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="page.title"/></title>
    </head>
    <body>
    	<%
		String contextPath = request.getContextPath().replace("/", "");
        %>
        <a href="login.jsp"><fmt:message key="page.login"/></a>
        <a href="/<%=contextPath%>/Hotel/lista"><fmt:message key="page.list"/></a>
        <form id="language-form" action="${pageContext.request.contextPath}/changeLanguage" method="post">
  			<select name="language" onchange="this.form.submit()">
    			<option value="pt">Português</option>
    			<option value="en">English</option>
  			</select>
  		<noscript><input type="submit" value="Submit"></noscript>
		</form>
    </body>
</html>