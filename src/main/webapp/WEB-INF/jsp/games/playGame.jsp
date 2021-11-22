<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="game">

	<div class="row">
		<div class="col-md-12">
			<petclinic:board board="${board}" />
			<c:forEach items="${board.pieces}" var="piece">
				<petclinic:piece size="10" piece="${piece}" />

			</c:forEach>
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
		<tr>
			<th>Fase actual</th>
			<td><c:out value="${turnoActual}" /></td>
		</tr>
	</table>

	<form:form modelAttribute="movement" class="form-horizontal"
		id="play-game-form">
		<div class="form-group has-feedback">

			<petclinic:inputField label="Casilla original" name="initialPosition" />
			<petclinic:inputField label="Número de fichas" name="number" />
			<petclinic:inputField label="Casilla destino" name="destinyPosition" />

		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-default" type="submit">Realizar
					movimiento</button>
			</div>
		</div>
	</form:form>


</petclinic:layout>