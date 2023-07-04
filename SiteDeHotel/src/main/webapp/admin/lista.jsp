<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>PromocoesHotel</title>
</head>
<body>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>
	<div align="center">
		<h1>Gerenciamento de Clientes</h1>
		<h2>
			<a href="/<%=contextPath%>">Menu Principal</a>
			<a href="/<%= contextPath%>/admin/cadastroHotel">Cadastra Hotel</a>
			<a href="/<%= contextPath%>/admin/cadastroSite">Cadastra Site</a>
			<a href="/<%= contextPath%>/admin/cadastro">Cadastra ADMIN</a>
		</h2>
	</div>

	<div align="center">
		<table border="1">
			<caption>Lista de Clientes</caption>
			<tr>
				<th>ID</th>
				<th>email</th>
				<th>nome</th>
				<th>ações</th>
			</tr>
			<c:forEach var="cliente" items="${requestScope.listacliente}">
				<tr>
					<td>${cliente.id}</td>
					<td>${cliente.email}</td>
					<td>${cliente.nome}</td>
					<td><a href="/<%= contextPath%>/admin/edicao?id=${cliente.id}">Edição</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="/<%= contextPath%>/admin/remocao?id=${cliente.id}"
						onclick="return confirm('Tem certeza de que deseja excluir este item?');">
							Remoção </a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>