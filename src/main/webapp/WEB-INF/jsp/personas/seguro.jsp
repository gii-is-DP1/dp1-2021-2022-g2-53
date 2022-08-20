<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>


<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->
<body style="background: url(/resources/images/backgroundPetris.jpg)">
	<petclinic:layout pageName="home">

		<div class="row">
			<div class="col-md-3"></div>



			<div class="col-md-9">
				<br> <br>
				<h1>¿Estás seguro de que deseas modificar tu perfil?</h1>
				<h2>
					Vas a entrar en la vista para modificar tu perfil
					<c:forEach items="${persona}" var="persona">
						<spring:url value="/personas/editperfil/{personaId}"
							var="personaUrl">
							<spring:param name="personaId" value="${persona.id}" />
						</spring:url>
						<a href="${fn:escapeXml(personaUrl)}">Modificar mi perfil</a>
					</c:forEach>
				</h2>
				<br> <br>
				<h3>Ten encuenta que debes salir y volver a iniciar sesión con
					los nuevos datos</h3>
				<br></br>
				<br>
			</div>

		</div>








	</petclinic:layout>
</body>
