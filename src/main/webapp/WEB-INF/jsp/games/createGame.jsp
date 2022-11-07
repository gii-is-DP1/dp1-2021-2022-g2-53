<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="games">
	<body style="background: url(/resources/images/backgroundPetris.jpg)">

		<form:form modelAttribute="game" class="form-horizontal"
			action="/games/save">
			<div class="form-group has-feedback"></div>
			<div class="form-group"></div>
		</form:form>

		<div class="col-md-6" id="columA">
			<div id="bb">
				<br> <br>
				<h2 align="center">CREA UNA PARTIDA</h2>

				<p align="center">Crea una nueva partida</p>
				<p align="center">en la que obtendrás un</p>

				<p align="center">código que deberás decir a</p>

				<p align="center">tu amigo para poder jugar juntos</p>
				<p align="center">y demostrar lo que valeis</p>

				<p align="center">¡DESAFÍO ENTRE AMIGOS !</p>
				<br> <br>

				<button class="btn btn-default" style="margin-left: 34%"
					onclick="window.location.href='/games/newGame'">
					<span>Juega con amigos</span>
				</button>
			</div>
			<br> <br>

			<spring:url value="/resources/images/img234.png" htmlEscape="true"
				var="error2" />
			<img id="a32" class="img-responsive" src="${error2}" />

		</div>

		<div class="col-md-6" id="columB">

			<spring:url value="/resources/images/img235.png" htmlEscape="true"
				var="error2" />
			<img id="a33" class="img-responsive" src="${error2}" /> <br> <br>

			<div id="aa">
				<h2 align="center">ÚNETE A TU AMIGO</h2>

				<p align="center">Prueba a unirte a un amigo</p>
				<p align="center">pidiéndole el código de la</p>

				<p align="center">partida que acaba de crear</p>

				<p align="center">para poder jugar juntos y</p>
				<p align="center">¡ MACHÁCALO !</p>
				<br> <br>
				<button class="btn btn-default" style="margin-left: 36%"
					onclick="window.location.href='/games/join'">
					<span>Únete a tu amigo</span>
				</button>
			</div>

		</div>
</petclinic:layout>