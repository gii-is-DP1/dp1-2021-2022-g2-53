<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="games">
	<body style="background: url(/resources/images/bck-petris.png)">

		<h2>Partidas</h2>


		<table id="gamesTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 160px;">Puntos del jugador negro</th>
					<th style="width: 160px;">Puntos del jugador rojo</th>

					<th style="width: 160px;">Creador de la partida</th>
					<th style="width: 160px;">Invitado en la partida</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${games}" var="game">
					<tr>
						<td><c:out value="${game.pointsBlack}" /></td>
						<td><c:out value="${game.pointsRed}" /></td>


						<c:forEach items="${game.jugadores}" var="jug">
							<td><c:out value="${jug.persona.user.username}" /></td>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
</petclinic:layout>
