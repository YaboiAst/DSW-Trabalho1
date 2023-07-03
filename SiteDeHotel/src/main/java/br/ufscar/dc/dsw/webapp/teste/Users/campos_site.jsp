<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table border="2">
	<caption>
		<c:choose>
			<c:when test="${Cliente != null}">
                            Edição
                        </c:when>
			<c:otherwise>
                            Cadastro
                        </c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${Cliente != null}">
		<input type="hidden" name="id" value="${Cliente.id}" />
	</c:if>
	<tr>
		<td><label for="nome">Nome</label></td>
		<td><input type="text" id="nome" name="nome" size="50"
			required value="${Site.nome}" /></td>
	</tr>
	<tr>
		<td><label for="email">Email</label></td>
		<td><input type="text" id="email" name="email" size="50" required
			value="${Cliente.email}" /></td>
	</tr>
	<tr>
		<td><label for="senha">Senha</label></td>
		<td><input type="text" id="senha" name="senha" size="50" required
			value="${Cliente.senha}" /></td>
	</tr>
	<tr>
		<td><label for="Url">Url</label></td>
		<td><input type="text" id="Url" name="Url" size="50" required
			value="${Site.Url}" /></td>
	</tr>
	<tr>
		<td><label for="Telefone">Telefone</label></td>
		<td><input type="text" id="Telefone" name="Telefone" size="12" required
			value="${Site.Telefone}" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
	</tr>
</table>