<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>

<head>
<title><fmt:message key="page.title"/></title>
</head>
<body>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>
	<div align="center">
		<h1><fmt:message key="client.manage"/></h1>
		<h2>
			<a href="/<%=contextPath%>"><fmt:message key="main.menu"/></a>
			<a href="/<%= contextPath%>/admin/cadastroHotel"><fmt:message key="hotel.add"/></a>
			<a href="/<%= contextPath%>/admin/cadastroSite"><fmt:message key="site.add"/></a>
			<a href="/<%= contextPath%>/admin/cadastro"><fmt:message key="admin.add"/></a>
		</h2>
	</div>

	<div align="center">
		<table border="1">
			<caption><fmt:message key="client.list"/></caption>
			<tr>
				<th>ID</th>
				<th>Email</th>
				<th><fmt:message key="main.name"/></th>
				<th><fmt:message key="main.action"/></th>
			</tr>
			<c:forEach var="cliente" items="${requestScope.listacliente}">
				<tr>
					<td>${cliente.id}</td>
					<td>${cliente.email}</td>
					<td>${cliente.nome}</td>
					<td><a href="/<%= contextPath%>/admin/edicao?id=${cliente.id}"><fmt:message key="main.edition"/></a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="/<%= contextPath%>/admin/remocao?id=${cliente.id}"
						onclick="return confirm('Tem certeza de que deseja excluir este item?');">
							<fmt:message key="main.remove"/> </a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>