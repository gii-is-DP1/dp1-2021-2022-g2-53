<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="game">
	<body style="background: url(/resources/images/bck-petris.png)">

		<div class="row">
			<div class="col-md-12">
				<petclinic:board board="${board}" />
				<c:forEach items="${board.pieces}" var="piece">
					<petclinic:piece size="10" piece="${piece}" />

				</c:forEach>
				<div class="col-md-12">

					<c:forEach items="${board.sarcines}" var="sarcine">
						<petclinic:sarcine size="20" sarcine="${sarcine}" />

					</c:forEach>
				</div>
			</div>
		</div>

		<h2>Datos de la partida</h2>
		<h2>
			<c:out value="${now}" />
		</h2>

		<table class="table table-striped">
			<tr>
				<th>Puntos del jugador negro</th>
				<td><c:out value="${game.pointsBlack}" /></td>
			</tr>
			<tr>
				<th>Puntos del jugador rojo</th>
				<td><c:out value="${game.pointsRed}" /></td>
			</tr>
		</table>
</petclinic:layout>