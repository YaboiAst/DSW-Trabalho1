<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1">
	<caption>
		<c:choose>
			<c:when test="${Cliente != null}">
                            <fmt:message key="main.edition"/>
                        </c:when>
			<c:otherwise>
                            <fmt:message key="main.cadastro"/>
                        </c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${Cliente != null}">
		<input type="hidden" name="id" value="${Cliente.id}" />
	</c:if>
	<tr>
		<td><label for="email">Email</label></td>
		<td><input type="text" id="email" name="email" size="45" required
			value="${Cliente.email}" /></td>
	</tr>
	<tr>
		<td><label for="senha"><fmt:message key="main.pswd"/></label></td>
		<td><input type="text" id="senha" name="senha" size="45" required
			value="${Cliente.senha}" /></td>
	</tr>
	<tr>
		<td><label for="nome"><fmt:message key="main.name"/></label></td>
		<td><input type="text" id="nome" name="nome" size="20" required
			value="ADMIN" readonly></td>	
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
	</tr>
</table>