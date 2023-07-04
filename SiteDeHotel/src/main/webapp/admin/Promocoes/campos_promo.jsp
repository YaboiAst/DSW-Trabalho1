<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table border="1">
	<caption>
		<c:choose>
			<c:when test="${promocao != null}">
                            Edição
                        </c:when>
			<c:otherwise>
                            Cadastro
                        </c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${promocao != null}">
		<input type="hidden" name="id" value="${promocao.id}" />
	</c:if>

	<tr>
		<td><label for="urlSiteReservas">urlSiteReservas</label></td>
		<td><input type="text" id="urlSiteReservas" name="urlSiteReservas" size="45"
			required value="${promocao.urlSiteReservas}" /></td>
	</tr>
	<tr>
		<td><label for="cnpjHotel">cnpjHotel</label></td>
		<td><input type="text" id="cnpjHotel" name="cnpjHotel" size="45"
			required value="${promocao.cnpjHotel}" /></td>
	</tr>

	<tr>
		<td><label for="dataInicial">dataInicial</label></td>
		<td><input type="number" id="dataInicial" name="dataInicial" size="4" required
			min="1500" value="${promocao.dataInicial}" /></td>
	</tr>
	<tr>
		<td><label for="dataFinal">dataFinal</label></td>
		<td><input type="number" id="dataFinal" name="dataFinal" size="4" required
			value="${promocao.dataFinal}" /></td>
	</tr>

	<tr>
		<td><label for="preco">preco</label></td>
		<td><input type="number" id="preco" name="preco" required
			min="0.01" step="any"  value="${promocao.preco}" /></td>
	</tr>

		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
	</tr>
</table>