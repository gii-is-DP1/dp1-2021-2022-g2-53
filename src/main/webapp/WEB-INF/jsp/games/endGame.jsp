<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="game">

	<table class="table table-striped">
		<h2>Fin de partida</h2>
		<tr>
			<th>Puntos del jugador negro</th>
			<td><c:out value="${game.pointsBlack}" /></td>
		</tr>
		<tr>
			<th>Puntos del jugador rojo</th>
			<td><c:out value="${game.pointsRed}" /></td>
		</tr>
		<tr>
			<th>El ganador es:</th>
			<td><c:out value="${winner}" /></td>
		</tr>
	</table>

	<td><spring:url value="/welcome" var="url">

		</spring:url> <a href="${fn:escapeXml(url)}">Volver al menú principal</a></td>

</petclinic:layout>