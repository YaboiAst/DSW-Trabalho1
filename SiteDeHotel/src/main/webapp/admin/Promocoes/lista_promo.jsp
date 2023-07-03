<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title><fmt:message key="page.title"/></title>
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
		<h1><fmt:message key="promo.manage"/></h1>
		<h2>
			<a href="/<%=contextPath%>"><fmt:message key="main.menu"/></a>
			<c:if test="${Cliente != null }">
				<c:if test="${Cliente.nome == 'CLIENTE'}">
					<a href="/<%=contextPath%>/cliente/"><fmt:message key="client.menu"/></a>
				</c:if>
				<c:if test="${Cliente.nome == 'hotel'}">
					<a href="${pageContext.request.contextPath}/hotel/"><fmt:message key="hotel.page"/></a>
					<a href="${pageContext.request.contextPath}/promocao/cadastro"><fmt:message key="promo.add"/></a>
				</c:if>
			</c:if>
		</h2>
	</div>

	<div align="center">
	<input type="text" id="modelFilter" onkeyup="filterFunction()" placeholder ="Procure pelo modelo">
		<table id="tabelapromocao" border="1">
			<caption><fmt:message key="promo.list"/></caption>
			<tr>
				<th>ID</th>
				<th>urlSite</th>
				<th>cnpjHotel</th>
				<th><fmt:message key="promo.start"/></th>
				<th><fmt:message key="promo.end"/></th>
				<th><fmt:message key="promo.price"/></th>
				<c:choose>
					<c:when test="${Cliente != null}">
						<th><fmt:message key="main.action"/></th>
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
							<td><a href="/<%=contextPath%>/propostas/cadastro?id=${promocao.id}"><fmt:message key="promo.add"/></a></td>
						</c:if>
						<c:if test="${Cliente.nome == 'hotel'}">
							<td><a href="/<%= contextPath%>/hotel/listaProposta?id=${promocao.id}"><fmt:message key="promo.title"/></a></td>
						</c:if>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>