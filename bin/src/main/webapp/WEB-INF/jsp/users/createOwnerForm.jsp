<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<body style="background: url(/resources/images/backgroundPetris.jpg)">
	<petclinic:layout pageName="owners">
		<br>
		<h2 align="center">
			<c:if test="${owner['new']}">Registro de un nuevo </c:if>
			jugador
		</h2>
		<br>
		<br>
		<form:form modelAttribute="owner" class="form-horizontal"
			id="add-owner-form">
			<div class="form-group has-feedback">
				<petclinic:inputField label="First Name" name="firstName" />
				<petclinic:inputField label="Last Name" name="lastName" />
				<petclinic:inputField label="Address" name="address" />
				<petclinic:inputField label="City" name="city" />
				<petclinic:inputField label="Telephone" name="telephone" />
				<petclinic:inputField label="Username" name="user.username" />
				<petclinic:inputField label="Password" name="user.password" />
			</div>
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-10">
					<c:choose>
						<c:when test="${owner['new']}">
							<button class="btn btn-default" type="submit">Añadir
								nuevo jugador</button>
						</c:when>
						<c:otherwise>
							<button class="btn btn-default" type="submit" align="center">Actualizar
								jugador</button>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</petclinic:layout>
</body>