
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="games">
	<body style="background: url(/resources/images/bck-petris.png)">

		<h2 style="text-align: center">
			Hola
			<c:out value="${persona}" />
			, aquí puedes espectar las partidas abiertas del resto de la gente.
		</h2>
		<br>


		<table id="gamesTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 80px;">Nº</th>
					<th style="width: 160px;">Jugador negro</th>
					<th style="width: 160px;">Jugador rojo</th>
					<th style="width: 80px;"></th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${games}" var="game">
					<c:if test="${game.pointsBlack < 9 || game.pointsRed < 9 }">
						<tr>
							<td><c:out value="${game.id}" /></td>
							<td><c:out
									value="${game.jugadores[0].persona.user.username}" /></td>
							<td><c:out
									value="${game.jugadores[1].persona.user.username}" /></td>
							<td><spring:url value="/games/{gameId}" var="gameUrl">
									<spring:param name="gameId" value="${game.id}" />
								</spring:url> <a href="${fn:escapeXml(gameUrl)}">Entrar a la partida en
									vivo</a></td>




						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
</petclinic:layout>