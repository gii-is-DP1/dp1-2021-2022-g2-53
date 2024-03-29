
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="games">
	<body style="background: url(/resources/images/bck-petris.png)">

		<h2 style="text-align: center">
			Historial de partidas de
			<c:out value="${persona}" />
		</h2>


		<table id="gamesTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 160px;">Nombre jugador negro</th>
					<th style="width: 160px;">Nombre del jugador rojo</th>
					<th style="width: 160px;">Puntos del jugador negro</th>
					<th style="width: 160px;">Puntos del jugador rojo</th>
					<th style="width: 80px;"></th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${games}" var="game">
					<tr>
						<td><c:out value="${game.jugadores[0].persona.user.username}" /></td>
						<td><c:out value="${game.jugadores[1].persona.user.username}" /></td>
						<td><c:out value="${game.pointsBlack}" /></td>
						<td><c:out value="${game.pointsRed}" /></td>
						<td><spring:url value="/games/{gameId}" var="gameUrl">
								<spring:param name="gameId" value="${game.id}" />
							</spring:url> <a href="${fn:escapeXml(gameUrl)}">Ver partida</a></td>




					</tr>
				</c:forEach>
			</tbody>
		</table>
</petclinic:layout>