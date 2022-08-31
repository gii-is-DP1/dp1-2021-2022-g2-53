<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<html>
<body style="background: url(/resources/images/backgroundPetris.jpg)">
	<petclinic:layout pageName="home">

		<div align="center" style="margin-left: 50px; margin-top: -20px;">

			<spring:url value="/resources/images/videoPetris3.gif"
				htmlEscape="true" var="videoPetris" />
			<img id="f1" class="img-responsive"
				style="border: 2px solid; border-radius: 15px; color: #E8CEF5;"
				src="${videoPetris}" width="720" height="450" />
		</div>


		<br></br>



		<h2 align="center">NO PUEDES JUGAR UNA PARTIDA CONTIGO MISMO</h2>
		<br>
		<h4 align="center">En Petris no toleramos esa actitud</h4>






		<div class="col-md-8">
			<h2>${group}</h2>
	</petclinic:layout>
</body>
</html>