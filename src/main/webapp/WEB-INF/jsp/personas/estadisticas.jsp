
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="games">
	<body style="background: url(/resources/images/bck-petris.png)">

		<h2 style="text-align:center">Hola <c:out value="${persona}" />, aquí puedes ver las estadísticas y logros.</h2>
	<br>
	

		<table id="gamesTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 80px;">Nº de partidas globales jugadas</th>
					
					
				</tr>
			</thead>
			<tbody>
					<tr>
						<td><c:out value="${id.size()}" /></td>
					</tr>
			</tbody>
		</table>
		<table id="gamesTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 80px;">Nº de partidas individuales jugadas por ti</th>
				</tr>
			</thead>
			<tbody>
					<tr>
						<td><c:out value="${games.size()}" /></td>
					</tr>
		</table>
		<table id="gamesTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 80px;">Porcentaje de partidas jugadas respecto del total</th>
				</tr>
			</thead>
			<tbody>
					<tr>
						<td><c:out value="${games.size()*100/id.size()}%" /></td>
					</tr>
		</table>
		<table id="gamesTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 80px;">Puntos de jugadores negros totales</th>
				</tr>
			</thead>
			<tbody>
					<tr>
						<td><c:out value="${pb}" /></td>
					</tr>
		</table>
		<table id="gamesTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 80px;">Puntos de jugadores rojos totales</th>
				</tr>
			</thead>
			<tbody>
					<tr>
						<td><c:out value="${pr}" /></td>
					</tr>
		</table>
		<table id="gamesTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 80px;">Puntos de jugadores negros totales respecto al total de puntos global</th>
					<th style="width: 80px;">Puntos de jugadores rojos totales respecto al total de puntos global</th>
					
				</tr>
			</thead>
			<tbody>
					<tr>
						<td><c:out value="${pb/(pb+pr)*100}%" /></td>
						<td><c:out value="${pr/(pb+pr)*100}%" /></td>
						
					</tr>
		</table>
		<table id="gamesTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 80px;">Media de puntos totales</th>
				</tr>
			</thead>
			<tbody>
					<tr>
						<td><c:out value="${(pr+pb)/2}" /></td>
					</tr>
		</table>
		
		<h2 style="text-align:center">Ranking actual jugadores con más partidas jugadas</h2>
		<table id="gamesTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 80px;">Jugador</th>
					<th style="width: 80px;">Número de partidas jugadas</th>
					
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${g}" var="g" >
					<tr>
						<td><c:out value="${g.user.username}" /></td>
						<td><c:out value="${u}" /></td>
						
						
						
						
						
					</tr>
			</c:forEach>
		</table>
		
		
		<h2 style="text-align:center">Logros obtenidos.</h2>
		
			<h2 style="text-align:center">Tu rango actual es 
			<c:choose>
			<c:when test="${games.size()>99}">
						LEYENDA
			</c:when>
			<c:when test="${games.size()>74 && games.size()<99}">
						MAESTRO II
			</c:when>
			<c:when test="${games.size()>49 && games.size()<74}">
						MAESTRO I
			</c:when>
			<c:when test="${games.size()>24 && games.size()<49}">
						APRENDIZ II
			</c:when>
			<c:when test="${games.size()>14 && games.size()<24}">
						APRENDIZ I
			</c:when>
			<c:when test="${games.size()>4 && games.size()<14}">
						PRINCIPIANTE II
			</c:when>
			<c:otherwise>
			PRINCIPIANTE I
			</c:otherwise>
			</c:choose>
			</h2>
		
		<table id="gamesTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 80px; background-color:#cd7f32; color:black;">Juega un total de una partida</th>
					<th style="width: 80px;background-color:#cd7f32; color:black;text-align:center">PRINCIPIANTE I</th>
					
				</tr>
			</thead>
			<tbody>
					<tr>
						<td>
						<c:choose>
						<c:when test="${games.size()>0}">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-square" viewBox="0 0 16 16">
						  <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
						  <path d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.235.235 0 0 1 .02-.022z"/>
						</svg>
						</c:when>
						<c:otherwise>
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-square-fill" viewBox="0 0 16 16">
						  <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z"/>
						</svg>
						</c:otherwise>
						</c:choose>
						</td>
					</tr>
		</table>
		
		<table id="gamesTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 80px; background-color:#cd7f32;color:black;">Juega un total de 5 partidas</th>
					<th style="width: 80px;background-color:#cd7f32; color:black;text-align:center;">PRINCIPANTE II</th>
					
					
				</tr>
			</thead>
			<tbody>
					<tr>
						<td>
						<c:choose>
						<c:when test="${games.size()>4}">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-square" viewBox="0 0 16 16">
						  <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
						  <path d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.235.235 0 0 1 .02-.022z"/>
						</svg>
						</c:when>
						<c:otherwise>
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-square-fill" viewBox="0 0 16 16">
						  <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z"/>
						</svg>
						</c:otherwise>
						</c:choose>
						</td>
					</tr>
		</table>
		
		<table id="gamesTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 80px; background-color:#c9c0bb;color:black;">Juega un total de 15 partidas</th>
					<th style="width: 80px;background-color:#c9c0bb; color:black;text-align:center;">APRENDIZ</th>
					
				</tr>
			</thead>
			<tbody>
					<tr>
						<td>
						<c:choose>
						<c:when test="${games.size()>14}">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-square" viewBox="0 0 16 16">
						  <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
						  <path d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.235.235 0 0 1 .02-.022z"/>
						</svg>
						</c:when>
						<c:otherwise>
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-square-fill" viewBox="0 0 16 16">
						  <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z"/>
						</svg>
						</c:otherwise>
						</c:choose>
						</td>
					</tr>
		</table>
		
		<table id="gamesTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 80px; background-color:#c9c0bb;color:black;">Juega un total de 25 partidas</th>
					<th style="width: 80px;background-color:#c9c0bb; color:black;text-align:center;">APRENDIZ II</th>
					
				</tr>
			</thead>
			<tbody>
					<tr>
						<td>
						<c:choose>
						<c:when test="${games.size()>24}">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-square" viewBox="0 0 16 16">
						  <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
						  <path d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.235.235 0 0 1 .02-.022z"/>
						</svg>
						</c:when>
						<c:otherwise>
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-square-fill" viewBox="0 0 16 16">
						  <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z"/>
						</svg>
						</c:otherwise>
						</c:choose>
						</td>
					</tr>
		</table>
		
		<table id="gamesTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 80px; background-color:#e9d66b;color:black;">Juega un total de 50 partidas</th>
					<th style="width: 80px;background-color:#e9d66b; color:black;text-align:center;">MAESTRO I</th>
					
				</tr>
			</thead>
			<tbody>
					<tr>
						<td>
						<c:choose>
						<c:when test="${games.size()>49}">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-square" viewBox="0 0 16 16">
						  <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
						  <path d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.235.235 0 0 1 .02-.022z"/>
						</svg>
						</c:when>
						<c:otherwise>
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-square-fill" viewBox="0 0 16 16">
						  <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z"/>
						</svg>
						</c:otherwise>
						</c:choose>
						</td>
					</tr>
		</table>
		
		<table id="gamesTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 80px; background-color:#e9d66b;color:black;">Juega un total de 75 partidas</th>
					<th style="width: 80px;background-color:#e9d66b; color:black;text-align:center;">MAESTRO II</th>
					
				</tr>
			</thead>
			<tbody>
					<tr>
						<td>
						<c:choose>
						<c:when test="${games.size()>74}">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-square" viewBox="0 0 16 16">
						  <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
						  <path d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.235.235 0 0 1 .02-.022z"/>
						</svg>
						</c:when>
						<c:otherwise>
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-square-fill" viewBox="0 0 16 16">
						  <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z"/>
						</svg>
						</c:otherwise>
						</c:choose>
						</td>
					</tr>
		</table>
		<table id="gamesTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 80px; background-color:#b05ddc;color:black;">Juega un total de 100 partidas</th>
					<th style="width: 80px;background-color:#b05ddc; color:black;text-align:center;">LEYENDA</th>
					
				</tr>
			</thead>
			<tbody>
					<tr>
						<td>
						<c:choose>
						<c:when test="${games.size()>99}">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-square" viewBox="0 0 16 16">
						  <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
						  <path d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.235.235 0 0 1 .02-.022z"/>
						</svg>
						</c:when>
						<c:otherwise>
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-square-fill" viewBox="0 0 16 16">
						  <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z"/>
						</svg>
						</c:otherwise>
						</c:choose>
						</td>
					</tr>
		</table>
		
		
		
		
	<br>
</petclinic:layout>