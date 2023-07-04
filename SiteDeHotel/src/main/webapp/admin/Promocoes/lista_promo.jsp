<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>PromocoesHotel</title>
</head>
<script>
	function filterFunction() {

	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("hotel");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("tabelaPromos");
	  tr = table.getElementsByTagName("tr");
	

	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[1];
	    if (td) {
	      txtValue = td.textContent || td.innerText;
	      if (txtValue.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }
	  }
	}
</script>
<body>
	<c:if test="${mensagens.existeErros}">
	    <div id="erro">
	        <ul>
           		<c:forEach var="erro" items="${mensagens.erros}">
               		<li> ${erro} </li>
              	</c:forEach>
	        </ul>	
	    </div>
    </c:if>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>
	<div align="center">
		<h1>Gerenciamento de promocaos</h1>
		<h2>
			<a href="/<%=contextPath%>">Menu Principal</a>
			<c:if test="${Cliente != null }">
				<c:if test="${Cliente.nome == 'CLIENTE'}">
					<a href="/<%=contextPath%>/cliente/">Menu Cliente</a>
				</c:if>
				<c:if test="${Cliente.nome == 'hotel'}">
					<a href="${pageContext.request.contextPath}/hotel/">Menu hotel</a>
					<a href="${pageContext.request.contextPath}/promocao/cadastro">Adicione Novo promocao</a>
				</c:if>
			</c:if>
		</h2>
	</div>

	<div align="center">
	<input type="text" id="modelFilter" onkeyup="filterFunction()" placeholder ="Procure pelo modelo">
		<table id="tabelapromocao" border="1">
			<caption>Lista de promocaos</caption>
			<tr>
				<th>ID</th>
				<th>urlSiteReservas</th>
				<th>cnpjHotel</th>
				<th>dataInicial</th>
				<th>dataFinal</th>
				<th>preco</th>
				<c:choose>
					<c:when test="${Cliente != null}">
						<th>Ações</th>
					</c:when>
				</c:choose>
			</tr>
			<c:forEach var="promocao" items="${requestScope.lista_promo}">
				<tr>
					<td>${promocao.id}</td>
					<td>${promocao.urlSiteReservas}</td>
					<td>${promocao.hotel.cnpj}</td>
					<td>${promocao.dataInicial}</td>
					<td>${promocao.dataFinal}</td>
					<td>${promocao.preco}</td>
					<c:if test="${Cliente != null }">
						<c:if test="${Cliente.nome == 'CLIENTE'}">
							<td><a href="/<%=contextPath%>/propostas/cadastro?id=${promocao.id}">Fazer promocao</a></td>
						</c:if>
						<c:if test="${Cliente.nome == 'hotel'}">
							<td><a href="/<%= contextPath%>/hotel/listaProposta?id=${promocao.id}">promocoes</a></td>
						</c:if>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>