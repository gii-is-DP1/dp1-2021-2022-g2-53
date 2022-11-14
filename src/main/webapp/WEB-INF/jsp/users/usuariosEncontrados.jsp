<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<petclinic:layout pageName="users">
	<body style="background: url(/resources/images/bck-petris.png)">
		<h2>Usuarios encontrados</h2>

		<table id="usersTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 150px;">Nombre de usuario</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td><c:out value="${user.username}" /></td>
						<c:if test="${usuActual != user}">
							<td><spring:url
									value="/invitacionAmigo/{username1}/{username2}/save"
									var="amigoUrl">
									<spring:param name="username1" value="${usuActual.username}" />
									<spring:param name="username2" value="${user.username}" />
								</spring:url> <a href="${fn:escapeXml(amigoUrl)}">Envíale una solicitud
									de amistad para ser amigos</a></td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
</petclinic:layout>
</body>
