<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="personas">
	<body style="background: url(/resources/images/bck-petris.png)">

		<h2>Personas</h2>


		<table id="gamesTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 160px;">Nombre de usuario</th>
					<th style="width: 160px;">Nombre</th>
					<th style="width: 80px;">Apellidos</th>
					<th style="width: 80px;"></th>
					<th style="width: 80px;"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${personas}" var="personas">
					<tr>
						<td><c:out value="${personas.user.username}" /></td>
						<td><c:out value="${personas.firstName}" /></td>
						<td><c:out value="${personas.lastName}" /></td>



						<td><spring:url value="/personas/edit/{personaId}"
								var="personaUrl2">
								<spring:param name="personaId" value="${personas.id}" />
							</spring:url> <a href="${fn:escapeXml(personaUrl2)}">Editar</a></td>


						<td><spring:url value="/personas/delete/{personaId}"
								var="personaUrl3">
								<spring:param name="personaId" value="${personas.id}" />
							</spring:url> <a href="${fn:escapeXml(personaUrl3)}">Borrar</a></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
		<td>Page <c:out value="${page_id + 1}" /> of <c:out
				value="${total_pages}" /></td>
		<br>

		<c:forEach begin="0" step="1" end="${total_pages - 1}" var="variable">
			<td><spring:url value="/personas/registro/{PageId}"
					var="urlBoton">
					<spring:param name="PageId" value="${variable}" />
				</spring:url> <a href="${fn:escapeXml(urlBoton)}">${variable + 1}</a></td>
		</c:forEach>
</petclinic:layout>
