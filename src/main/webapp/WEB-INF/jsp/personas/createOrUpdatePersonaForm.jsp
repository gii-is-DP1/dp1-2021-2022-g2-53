<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="personas">
	<body style="background: url(/resources/images/bck-petris.png)">

		<h2>
			<c:if test="${persona['new']}">Nueva </c:if>
			Persona
		</h2>
		<form:form modelAttribute="persona" class="form-horizontal"
			id="add-persona-form">
			<div class="form-group has-feedback">
				<petclinic:inputField label="Nombre" name="firstName" />
				<petclinic:inputField label="Apellido" name="lastName" />

				<petclinic:inputField label="Nombre de usuario" name="user.username" />
				<petclinic:inputField label="Contraseña" name="user.password" />
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<c:choose>
						<c:when test="${persona['new']}">
							<button class="btn btn-default" type="submit">
								<span>Registrarse</span>
							</button>
						</c:when>
						<c:otherwise>
							<button class="btn btn-default" type="submit">
								<span>Editar</span>
							</button>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
</petclinic:layout>
