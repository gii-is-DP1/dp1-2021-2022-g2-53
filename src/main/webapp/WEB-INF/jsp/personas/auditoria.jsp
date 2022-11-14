<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<body style="background: url(/resources/images/bck-petris.png)">
	<petclinic:layout pageName="personas">

		<h2>Tabla de modificaciones</h2>
		<table id="UsersTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 160px;">Nombre de la persona</th>
					<th style="width: 160px;">Apellido de la persona</th>
					<th style="width: 160px;">Nombre de Usuario</th>
					<th style="width: 160px;">Contraseña</th>
					<th style="width: 160px;">Modificado por</th>
					<th style="width: 160px;">Fecha de modificación</th>
					<th style="width: 160px;">Creador</th>
					<th style="width: 160px;">Fecha de creación</th>



				</tr>
			</thead>
			<tbody>
				<c:forEach items="${personas}" var="personas">


					<c:if test="${personas.modifier != null }">
						<tr>
							<td><c:out value="${personas.firstName}" /></td>
							<td><c:out value="${personas.lastName}" /></td>
							<td><c:out value="${personas.user.username}" /></td>
							<td><c:out value="${personas.user.password}" /></td>
							<td><c:out value="${personas.modifier}" /></td>
							<td><c:out value="${personas.lastModifiedDate}" /></td>
							<td><c:out value="${personas.creator}" /></td>
							<td><c:out value="${personas.createdDate}" /></td>





						</tr>
					</c:if>
				</c:forEach>

			</tbody>
		</table>





	</petclinic:layout>